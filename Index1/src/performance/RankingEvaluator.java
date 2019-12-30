package performance;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import indexation.AbstractIndex;
import indexation.content.Posting;
import query.DocScore;
import query.RankingQueryEngine;
import tools.Configuration;

/**
 * Classe utilisée pour évaluer la performance
 * d'un index vectoriel sur un jeu de requêtes prédéfinies,
 * pour lesquelles on connait la vérité terrain.
 */
public class RankingEvaluator extends AbstractEvaluator
{	
	/**
	 * Initialise un évaluateur vectoriel pour la vérité terrain 
	 * spécifiée dans la configuration. 
	 * 
	 * @throws ParserConfigurationException
	 * 		Problème lors de la lecture de la vérité terrain 
	 * @throws IOException 
	 * 		Problème lors de la lecture de la vérité terrain 
	 * @throws SAXException 
	 * 		Problème lors de la lecture de la vérité terrain 
	 */
	public RankingEvaluator() throws ParserConfigurationException, SAXException, IOException
	{	super();
	}
	
	////////////////////////////////////////////////////
	//	MESURES
	////////////////////////////////////////////////////
	/**
	 * Calcule les trois mesures sur toutes les requêtes considérées
	 * Le paramètre {@code answers} est la liste des réponses <i>ordonnée</i>
	 * renvoyées par le moteur de recherche pour toutes ces requêtes.
	 * Le paramètre {@code k} permet de distinguer les document considérés
	 * comme pertinents par le moteur ({@code k} premiers) des non-pertinents.
	 *   
	 * @param answers
	 * 		Liste de listes d'objets DocScore renvoyée par le moteur de recherche pour les requêtes traitées.
	 * @param k
	 * 		Seuil utilisé pour distinguer les documents considérs comme pertinents des non-pertinents.
	 * @return
	 * 		Liste de maps contenant les performances calculées pour les requêtes. Chaque
	 * 		map correspond à une requête de la vérité terrain, sauf la dernière, qui
	 * 		contient les valeurs moyennes.
	 */
	private List<Map<MeasureName,Float>> evaluateQueryAnswers(List<List<DocScore>> answers, int k)
	{	List<Map<MeasureName,Float>> result = null;
		//TODO méthode à compléter  (TP6-ex14)
		
		
		List<List<Posting>> llpoAnswers = new ArrayList<List<Posting>>();
		for (List<DocScore> docScores : answers) {
			List<Posting> lpoAnswers = new ArrayList<Posting>();
			if(docScores.size()>0) {
				for (int i = 0; i < k; i++) {
					DocScore docScore = docScores.get(i);
					lpoAnswers.add(new Posting(docScore.getDocId()));
				}
			}
			llpoAnswers.add(lpoAnswers);
		}
		result = this.evaluateQueryAnswers(llpoAnswers);
		return result;
	}

	////////////////////////////////////////////////////
	//	ÉVALUATION
	////////////////////////////////////////////////////
	/**
	 * Evalue le moteur de recherche spécifié
	 * avec la vérité terrain précédemment chargée,
	 * et enregistre et renvoie les performances.
	 *  
	 * @param engine
	 * 		Moteur de recherche à évaluer.
	 * @return
	 * 		Liste de maps contenant les performances calculées pour les requêtes. Chaque
	 * 		map correspond à des valeurs moyennes pour une valeur de k donnée. Ces moyennes
	 * 		sont obtenues en considérant toutes les requêtes d'évaluation.
	 * 
	 * @throws UnsupportedEncodingException
	 * 		Problème à l'enregistrement des performances. 
	 * @throws FileNotFoundException 
	 * 		Problème à l'enregistrement des performances. 
	 */
	public List<Map<MeasureName,Float>> evaluateEngine(RankingQueryEngine engine) throws FileNotFoundException, UnsupportedEncodingException
	{	List<Map<MeasureName,Float>> result = null;
		//TODO méthode à compléter  (TP6-ex15)
		System.out.println("Evaluating the search engine " + engine.getClass());
		AbstractIndex index = engine.getIndex();
		int docNbr = index.getDocumentNumber();
		
			// on traite chaque requête d'évaluation
		List<List<DocScore>> answers = new ArrayList<List<DocScore>>();
		List<String> queries = groundTruth.getQueries();
		for(String query: queries) {
			List<DocScore> answer = engine.processQuery(query,0);
			answers.add(answer);
		}
			
			// on calcule les performances correspondant aux réponses
		result = new ArrayList<Map<MeasureName,Float>>();
		for(int k=1;k<=docNbr;k++) {
			// System.out.println("k="+k + "/" + docNbr);
			List<Map<MeasureName,Float>> temp = evaluateQueryAnswers(answers,k);
			Map<MeasureName,Float> meanVals = temp.get(temp.size()-1);
			result.add(meanVals);
		}
		
		writePerformances(result);
		return result;
	}
	
	////////////////////////////////////////////////////
	//	TEST
	////////////////////////////////////////////////////
	/**
	 * Test des méthodes de cette classe.
	 * 
	 * @param args
	 * 		Pas utilisé.
	 * 
	 * @throws Exception 
	 * 		Problème quelconque rencontré.
	 */
	public static void main(String[] args) throws Exception 
	{	// test de evaluateQueryAnswers
		//TODO méthode à compléter  (TP6-ex14)
		Configuration.setCorpusName("springer");
		AbstractIndex index  = AbstractIndex.read();
		System.out.println(index.getDocumentNumber());
		RankingQueryEngine rqe = new RankingQueryEngine(index);
		// List<List<DocScore>> answers = new ArrayList<List<DocScore>>();
		// answers.add(rqe.processQuery("panneaux solaires électricité", 10));
		// answers.add(rqe.processQuery("recherche d’information sur le Web", 10));
		// answers.add(rqe.processQuery("roman", 10));
		RankingEvaluator re = new RankingEvaluator();
		// re.evaluateQueryAnswers(answers, 5);

		// test de evaluateEngine
		//TODO méthode à compléter  (TP6-ex15)
		re.evaluateEngine(rqe);
	}
}
