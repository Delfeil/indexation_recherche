package performance;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import query.DocScore;
import query.RankingQueryEngine;

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
		
		// test de evaluateEngine
		//TODO méthode à compléter  (TP6-ex15)
	}
}
