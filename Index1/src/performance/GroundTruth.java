package performance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import indexation.content.Posting;
import tools.Configuration;
import tools.FileTools;

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

		this.queries = new ArrayList<String>();
		this.postingLists = new ArrayList<List<Posting>>();

		List<String> files = null;
		Node queryNode = null;
		Element query = null;
		NodeList documeNodeList = null;
		Node documentNode = null;

		String groundTruthFile = FileTools.getGroundTruthFile();
		
		System.out.println("Reading ground truth file " + groundTruthFile);

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(groundTruthFile);
		NodeList queryList = document.getElementsByTagName("query");
		System.out.print("Found " + queryList.getLength() + " queries (");
		for(int i=0; i<queryList.getLength(); i++) {
			queryNode = queryList.item(i);
			if (queryNode.getNodeType() == Node.ELEMENT_NODE) {
				query = (Element) queryNode;
				this.queries.add(query.getAttribute("expr"));
				files = new ArrayList<String>();
				documeNodeList = query.getElementsByTagName("doc");
				System.out.print(documeNodeList.getLength());
				if(i!=queryList.getLength()-1) {
					System.out.print(" ");
				}
				for(int y=0; y<documeNodeList.getLength(); y++) {
					documentNode = documeNodeList.item(y);
					// System.out.println("ici, " + i + ",  " + y);
					// System.out.println(documentNode);
					// System.out.println(documentNode.getTextContent());
					// System.out.println("là");
					files.add(documentNode.getTextContent());
				}
				this.postingLists.add(FileTools.getPostingsFromFileNames(files));
			}
		}
		System.out.println(")");
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
		Configuration.setCorpusName("springer");
		GroundTruth groundTruth = new GroundTruth();
		// System.out.println(groundTruth.getQueries());
		// System.out.println(groundTruth.getQueries());
	}
}
