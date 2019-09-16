package performance;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import indexation.content.Posting;

/**
 * Classe utilisée pour représenter une vérité terrain,
 * i.e. une séquence de requêtes d'évaluation, chacune 
 * accompagnée de sa liste de documents pertinents.
 */
public class GroundTruth
{	
	/**
	 * Initialise la vérité terrain à partir du fichier XML
	 * spécifié dans la configuration. 
	 * 
	 * @throws ParserConfigurationException
	 * 		Problème lors de la lecture de la vérité terrain.
	 * @throws IOException 
	 * 		Problème lors de la lecture de la vérité terrain.
	 * @throws SAXException 
	 * 		Problème lors de la lecture de la vérité terrain.
	 */
	public GroundTruth() throws ParserConfigurationException, SAXException, IOException
	{	//TODO méthode à compléter  (TP4-ex3)
	}
	
	////////////////////////////////////////////////////
	//	REQUÊTES
	////////////////////////////////////////////////////
	/** Liste des requêtes utilisées lors de l'évaluation */
	private List<String> queries;
	
	/**
	 * Renvoie les requêtes d'évaluation associées à cette vérité terrain.
	 * 
	 * @return
	 * 		Une liste de chaînes de caractères correspondant chacune à une requête.
	 */
	public List<String> getQueries()
	{	return queries;
	}
	
	////////////////////////////////////////////////////
	//	DOCUMENTS
	////////////////////////////////////////////////////
	/** Liste de documents pertinents pour chaque requête d'évaluation */
	private List<List<Posting>> postingLists;
	
	/**
	 * Renvoie la liste de postings associée
	 * à larequête d'évaluation dont le numéro
	 * est spécifié en paramètre.
	 * 
	 * @param queryId
	 * 		Numéro de la requête concernée.
	 * @return
	 * 		Liste de liste de postings.
	 */
	public List<Posting> getPostingList(int queryId)
	{	List<Posting> result = postingLists.get(queryId);
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
	{	// test du constructeur
		//TODO méthode à compléter  (TP4-ex3)
	}
}
