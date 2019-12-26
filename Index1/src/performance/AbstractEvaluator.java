package performance;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import indexation.content.Posting;

/**
 * Classe utilisée pour mettre en commun les méthodes
 * et champs nécessaires à l'évaluation de la performance
 * d'un index sur un jeu de requêtes prédéfinies,
 * pour lesquelles on connait la vérité terrain.
 */
public abstract class AbstractEvaluator
{	
	/**
	 * Initialise un évaluateur pour la vérité terrain 
	 * spécifiée dans la configuration. 
	 * 
	 * @throws ParserConfigurationException
	 * 		Problème lors de la lecture de la vérité terrain 
	 * @throws IOException 
	 * 		Problème lors de la lecture de la vérité terrain 
	 * @throws SAXException 
	 * 		Problème lors de la lecture de la vérité terrain 
	 */
	public AbstractEvaluator() throws ParserConfigurationException, SAXException, IOException
	{	//TODO méthode à compléter  (TP4-ex4)
		this.groundTruth = new GroundTruth();
	}
	
	/**
	 * Nom d'une mesure de performance
	 * utilisée pour évaluer le processus
	 * d'indexation et de recherche.
	 */
	public enum MeasureName
	{	/** Précision */
		PRECISION,
		/** Rappel */
		RECALL,
		/** F-mesure ou F1-score */
		F_MEASURE;
	}
	
	////////////////////////////////////////////////////
	//	VÉRITÉ TERRAIN
	////////////////////////////////////////////////////
	/** Vérité terrain */
	protected GroundTruth groundTruth;
	
	/**
	 * Renvoie la vérité terrain chargée à la construction
	 * de cet évaluateur.
	 * 
	 * @return
	 * 		Objet représentant la vérité terrain.
	 */
	public GroundTruth getGroundTruth()
	{	return groundTruth;
	}
	
	////////////////////////////////////////////////////
	//	MESURES
	////////////////////////////////////////////////////
	/**
	 * Calcule les trois mesures du cours permettant
	 * d'évaluer le resultat de la requête considérée.
	 * Le paramètre {@code queryId} est le numéro de cette
	 * requête parmi celles constituant la vérité terrain,
	 * et le paramètre {@code answer} est la liste de posting
	 * renvoyée par le moteur de recherche pour la requête.
	 *   
	 * @param queryId
	 * 		Numéro de la requête parmi celles constituant la vérité terrain.
	 * @param answer
	 * 		Liste de postings renvoyée par le moteur de recherche pour cette requête.
	 * @return
	 * 		Map contenant les performances calculées pour cette requête et ce résultat.
	 */
	protected Map<MeasureName,Float> evaluateQueryAnswer(int queryId, List<Posting> answer)
	{	 Map<MeasureName,Float> result = null;
		//TODO méthode à compléter  (TP4-ex5)
			//  Récupération des donnée terrain
		List<String> requests = this.groundTruth.getQueries();
		String request = requests.get(queryId);
		List<Posting> groud_truth_result = this.groundTruth.getPostingList(queryId);
		Iterator<Posting> ansi = answer.iterator();
		Float tp, fn, fp;
		tp = fn = fp = 0.0f;
		while (ansi.hasNext()) {
			Posting ansp = ansi.next();			
			if(groud_truth_result.contains(ansp)) {
				tp++;
			} else {
				fp++;
			}
		}
		fn = groud_truth_result.size() - tp;
		// System.out.println("tp: " + tp +", fn: " + fn + ", fp: " + fp);
			// Calcul de la précision
		result = new HashMap<MeasureName,Float>();
		float acc = 0;
		if(tp+fp != 0) {
			acc = tp / (tp+fp);
		}
		float rec = 1;
		if(tp+fn != 0) {
			rec = tp / (tp+fn);
		}
		float f_mesure = 0;
		if(acc + rec != 0.0) {
			f_mesure = 2*((acc*rec)/(acc+rec));
		}
		result.put(MeasureName.PRECISION, acc);
		result.put(MeasureName.RECALL, rec);
		result.put(MeasureName.F_MEASURE, f_mesure);
		return result;
	}
	
	/**
	 * Calcule les trois mesures sur toutes les requêtes considérées
	 * Le paramètre {@code answers} est la liste des réponses
	 * renvoyées par le moteur de recherche pour toutes ces requêtes.
	 *   
	 * @param answers
	 * 		Liste de listes de postings renvoyée par le moteur de recherche pour les requêtes traitées.
	 * @return
	 * 		Liste de maps contenant les performances calculées pour les requêtes. Chaque
	 * 		map correspond à une requête de la vérité terrain, sauf la dernière, qui
	 * 		contient les valeurs moyennes.
	 */
	protected List<Map<MeasureName,Float>> evaluateQueryAnswers(List<List<Posting>> answers)
	{	List<Map<MeasureName,Float>> results = new ArrayList<Map<MeasureName,Float>>();
		//TODO méthode à compléter  (TP4-ex6)
		Map<MeasureName,Float> result = null;
		Map<MeasureName, Float> moyennes = new HashMap<MeasureName, Float>();
		moyennes.put(MeasureName.PRECISION, 0.0f);
		moyennes.put(MeasureName.F_MEASURE, 0.0f);
		moyennes.put(MeasureName.RECALL, 0.0f);
		for (int i = 0; i < answers.size(); i++) {
			System.out.println("index: "+i);
			List<Posting> answer = answers.get(i);
			result = this.evaluateQueryAnswer(i, answer);
			results.add(result);
			moyennes.put(MeasureName.PRECISION,
				moyennes.get(MeasureName.PRECISION) + result.get(MeasureName.PRECISION)
			);
			moyennes.put(MeasureName.F_MEASURE,
				moyennes.get(MeasureName.F_MEASURE) + result.get(MeasureName.F_MEASURE)
			);
			moyennes.put(MeasureName.RECALL,
				moyennes.get(MeasureName.RECALL) + result.get(MeasureName.RECALL)
			);
		}
		moyennes.put(MeasureName.PRECISION,
			moyennes.get(MeasureName.PRECISION)/answers.size()
		);
		moyennes.put(MeasureName.F_MEASURE,
			moyennes.get(MeasureName.F_MEASURE)/answers.size()
		);
		moyennes.put(MeasureName.RECALL,
			moyennes.get(MeasureName.RECALL)/answers.size()
		);
		results.add(moyennes);
		return results;
	}
	
	////////////////////////////////////////////////////
	//	EXPORTATION
	////////////////////////////////////////////////////
	/**
	 * Ecrit dans le fichier texte spécifié dans la configuration
	 * les valeurs passées en paramètre. Chaque type de mesure prend
	 * la forme d'une colonne dans le fichier.
	 *  
	 * @param values
	 * 		Listes de maps de valeurs à traiter.
	 * @throws FileNotFoundException 
	 * 		Problème à l'ouverture du fichier de performances.
	 * @throws UnsupportedEncodingException 
	 * 		Problème à l'ouverture du fichier de performances.
	 */
	protected void writePerformances(List<Map<MeasureName,Float>> values) throws FileNotFoundException, UnsupportedEncodingException
	{	//TODO méthode à compléter  (TP4-ex7)
	}
}
