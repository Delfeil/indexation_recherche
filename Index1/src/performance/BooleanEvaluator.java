package performance;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import query.AndQueryEngine;

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
		
		// test de evaluateQueryAnswers
		//TODO méthode à compléter  (TP4-ex6)
		
		// test de writePerformances
		//TODO méthode à compléter  (TP4-ex7)
		
		// test de evaluateEngine
		//TODO méthode à compléter  (TP4-ex8)
	}
}
