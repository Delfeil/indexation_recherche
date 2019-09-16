package indexation.processing;

import indexation.content.Token;

import java.io.File;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Objet segmentant des textes
 * en utilisant tous les caractères
 * non alphanumériques comme séparateurs.
 */
public class Tokenizer implements Serializable
{	/** Class id (juste pour éviter le warning) */
	private static final long serialVersionUID = 1L;
	
	////////////////////////////////////////////////////
	//	TRAITEMENT
	////////////////////////////////////////////////////
	/**
	 * Tokenize tout le corpus et renvoie les
	 * tokens obtenus via la liste passée en
	 * paramètre. La méthode renvoie aussi le
	 * nombre de documents traités.
	 * 
	 * @param tokens
	 * 		Liste de tokens résultant du traitement.
	 * @return
	 * 		Nombre de documents traités.
	 * 
	 * @throws UnsupportedEncodingException 
	 * 		Problème de décodage lors de la lecture d'un document.
	 */
	public int tokenizeCorpus(List<Token> tokens) throws UnsupportedEncodingException
	{	int result = 0;
		//TODO méthode à compléter (TP1-ex5)
		return result;
	}
	
	/**
	 * Méthode qui segmente le document
	 * spécifié, et renvoie le résultat
	 * en complétant la liste passée en 
	 * paramètre.
	 * 
	 * @param document
	 * 		Fichier contenant le document à traiter.
	 * @param docId
	 * 		Numéro du document à traiter.
	 * @param tokens
	 * 		La liste de tokens à compléter.
	 * 
	 * @throws UnsupportedEncodingException
	 * 		Problème de décodage lors de la lecture d'un document.
	 */
	public void tokenizeDocument(File document, int docId, List<Token> tokens) throws UnsupportedEncodingException
	{	//TODO méthode à compléter (TP1-ex4)
	}
	
	/**
	 * Renvoie la liste des tokens pour
	 * la chaîne de caractères spécifiée.
	 * 
	 * @param string
	 * 		Chaîne de caractères à traiter.
	 * @return
	 * 		La liste de types correspondant.
	 */
	public List<String> tokenizeString(String string)
	{	List<String> result = null;
		//TODO méthode à compléter (TP1-ex3)
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
	{	// test de tokenizeString
		// TODO méthode à compléter (TP1-ex3)
		
		// test de tokenizeDocument
		// TODO méthode à compléter (TP1-ex4)
				
		// test de tokenizeCorpus
		// TODO méthode à compléter (TP1-ex5)
	}
}
