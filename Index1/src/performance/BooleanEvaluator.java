package performance;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import indexation.AbstractIndex;
import indexation.AbstractIndex.LexiconType;
import indexation.AbstractIndex.TokenListType;
import indexation.content.Posting;
import query.AndQueryEngine;
import tools.Configuration;

/**
 * Classe utilisée pour évaluer la performance
 * d'un index booléen sur un jeu de requêtes prédéfinies,
 * pour lesquelles on connait la vérité terrain.
 */
public class BooleanEvaluator extends AbstractEvaluator
{	
	/**
	 * Initialise un évaluateur booléen pour la vérité terrain 
	 * spécifiée dans la configuration. 
	 * 
	 * @throws ParserConfigurationException
	 * 		Problème lors de la lecture de la vérité terrain 
	 * @throws IOException 
	 * 		Problème lors de la lecture de la vérité terrain 
	 * @throws SAXException 
	 * 		Problème lors de la lecture de la vérité terrain 
	 */
	public BooleanEvaluator() throws ParserConfigurationException, SAXException, IOException
	{	super();
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
	 * 		map correspond à une requête de la vérité terrain, sauf la dernière, qui
	 * 		contient les valeurs moyennes.
	 * 
	 * @throws UnsupportedEncodingException
	 * 		Problème à l'enregistrement des performances. 
	 * @throws FileNotFoundException 
	 * 		Problème à l'enregistrement des performances. 
	 */
	public List<Map<MeasureName,Float>> evaluateEngine(AndQueryEngine engine) throws FileNotFoundException, UnsupportedEncodingException
	{	List<Map<MeasureName,Float>> result = null;
		//TODO méthode à compléter  (TP4-ex7)
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
	{	// test de evaluateQueryAnswer
		//TODO méthode à compléter  (TP4-ex5)
		Configuration.setCorpusName("springer");
		
		// AbstractIndex index = AbstractIndex.indexCorpus(TokenListType.LINKED, LexiconType.HASH);
		AbstractEvaluator evaluator = new BooleanEvaluator();
		String query = evaluator.groundTruth.getQueries().get(1);
		// System.out.println(query);
		List<List<Posting>> answers = new ArrayList<List<Posting>>();
		List<Posting> answer = new ArrayList<Posting>();
		for (int j = 15; j < 21; j++) {
			Posting p =new Posting(j);
			answer.add(p);
		}
		for (int i = 0; i < 25; i++) {
			answers.add(answer);
		}
		// System.out.println(answer);
		Map<MeasureName, Float> evaluation = new HashMap<MeasureName, Float>();
		evaluation = evaluator.evaluateQueryAnswer(1, answer);
		System.out.println(evaluation);
		
		// test de evaluateQueryAnswers
		//TODO méthode à compléter  (TP4-ex6)
		// AbstractIndex index = AbstractIndex.read();
		// AndQueryEngine aqe = new AndQueryEngine(index);
		// List<String> queries =  evaluator.groundTruth.getQueries();
		// List<List<Posting>> answers = new ArrayList<List<Posting>>();
		// for (int i = 0; i < queries.size(); i++) {
		// 	answers.add(aqe.processQuery(queries.get(i)));
		// }

		System.out.println(evaluator.evaluateQueryAnswers(answers));
		
		
		// test de writePerformances
		//TODO méthode à compléter  (TP4-ex7)
		
		// test de evaluateEngine
		//TODO méthode à compléter  (TP4-ex8)
	}
}
