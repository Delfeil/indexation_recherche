package indexation.processing;

import indexation.content.Token;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

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
		try {
			// Ouverture du document passé en paramètre
			FileInputStream fis = new FileInputStream(document);
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			Scanner scanner = new Scanner(isr);
			List<String> ls = new ArrayList<String>();
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				ls.addAll(this.tokenizeString(line));
			}
			Iterator<String> is = ls.iterator();
			while(is.hasNext()) {
				String token = is.next();
				tokens.add(new Token(token, docId));
			}
			scanner.close();
		} catch(FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
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
		//TODO tester
		String regex = "([^a-zA-Z0-9]|\\p{Blank})";
		regex = "\\W*\\s*\\W\\s*\\W*|\\W*\\s*\\p{Punct}\\s*\\W*|\\W*\\s*\\s\\s*\\W*";
		regex = "\\W+|\\s+"
			// regex = "\\W*\\s*\\W\\s*\\W*|\\W*\\s*\\p{Punct}\\s*\\W*|\\W*\\s*\\s\\s*\\W*";
		String regex = "\\W+|\\s+";
		result = Arrays.asList(string.split(regex));
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
		Tokenizer tokenizer = new Tokenizer();
		System.out.println(tokenizer.tokenizeString("Salut, |b0nj0ur  \\| comment ça.vas?"));
		
		// test de tokenizeDocument
		// TODO méthode à compléter (TP1-ex4)
		List<Token> tokens = new ArrayList<Token>();
		File document = new File("./Common/wp/001f1107-8e72-4250-8b83-ef02eeb4d4a4.txt");
		Tokenizer tknz = new Tokenizer();
		tknz.tokenizeDocument(document, 1, tokens);
		System.out.println(tokens);

		// test de tokenizeCorpus
		// TODO méthode à compléter (TP1-ex5)
	}
}
