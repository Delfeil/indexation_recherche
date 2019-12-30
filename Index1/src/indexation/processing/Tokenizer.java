package indexation.processing;

import indexation.content.Token;
import tools.Configuration;
import tools.FileTools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
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
		String corpus = FileTools.getCorpusFolder();
		// System.out.println(corpus);
		File f = new File(corpus);
		// System.out.println(f.getPath());
		// return result;
		String[] files = f.list();
		Arrays.sort(files);
		result = files.length;
		int docId = 0;
		for(String file:files) {
			// System.out.println(file);
			File document = new File(f.getPath() + File.separator + file);
			this.tokenizeDocument(document, docId, tokens);
			docId++;
		}
		// result = tokens.size();
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
	{	List<String> result = new ArrayList<String>();
		//TODO méthode à compléter (TP1-ex3)
		// On Séparer la chaine de caractère passée en argument Pour chaques groupes de caractères non alpha numérique ou d'espaces

		// String regex = "([^a-zA-Z0-9]|\\p{Blank})";
		// regex = "\\W*\\s*\\W\\s*\\W*|\\W*\\s*\\p{Punct}\\s*\\W*|\\W*\\s*\\s\\s*\\W*";
		// regex = "\\W+|\\s+"
		// regex = "\\W*\\s*\\W\\s*\\W*|\\W*\\s*\\p{Punct}\\s*\\W*|\\W*\\s*\\s\\s*\\W*";
		// String regex = "\\W+|\\s+|\\p{Punct}+\\p{Space}+\\p{Blank}*|\\p{Space}+\\p{Blank}*";
		// Si la chaine à tokeniser n'est pas vide
		if(string.length()>0) {
			// Séparation des mots
			// String regex = "\\p{Space}+([\\p{Punct}&&[^']&&[^-]]|(?<![a-zA-Z])('|-)|('|-)(?![a-zA-Z]))*|([\\p{Punct}&&[^']&&[^-]]|(?<![a-zA-Z])('|-)|('|-)(?![a-zA-Z]))+\\p{Space}*";
			String regex = "\\p{Space}+|\\p{Punct}+";
			// String regex = "\\p{Space}+([\\p{Punct}&&[^']&&[^-]]|(?<![a-zA-Z])('|-)|('|-)(?![a-zA-Z]))*|([\\p{Punct}&&[^']&&[^-]]|(?<![a-zA-Z])('|-)|('|-)(?![a-zA-Z]))+\\p{Space}*";
			result = new ArrayList<String>(Arrays.asList(string.split(regex)));
			// System.out.println(result.get(0));
			// if(result.get(0).equals("")) {
			// 		// Gestion du cas où la chaine de char commence par un caractère non alphanumérique ex: "-Cas 1"
			// 		//Si c'est le cas, le split retourne [, Cas, 1] -> Enlever le premier élément de la liste.
			// 	result.remove(0);
			// }
			// System.out.println(result);
			while(result.contains("")) {
				result.remove("");
			}
		}
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
		// System.out.println(tokenizer.tokenizeString("-_ç(Salut, |b0nj0ur  \\| comment ça.vas?"));
		System.out.println(tokenizer.tokenizeString("< -Bonjour . comment ça vas?'\n"));
		
		// test de tokenizeDocument
		// TODO méthode à compléter (TP1-ex4)
		List<Token> tokens = new ArrayList<Token>();
		// File document = new File("./Common/wp/001f1107-8e72-4250-8b83-ef02eeb4d4a4.txt");
		File document = new File("./Common/wp/c8b1e439-57a3-4dd7-b7a8-98fc11ab3f7d.txt");
		Tokenizer tknz = new Tokenizer();
		tknz.tokenizeDocument(document, 1, tokens);
		System.out.println(tokens);

		// test de tokenizeCorpus
		// TODO méthode à compléter (TP1-ex5)
		List<Token> tokens5 = new ArrayList<Token>();
		Configuration.setCorpusName("wp_test");
		int result = tokenizer.tokenizeCorpus(tokens5);
		System.out.println("Nopmbre de tokens trouvés: " + result);
	}
}
