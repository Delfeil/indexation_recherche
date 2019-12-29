package tools;

import indexation.content.Token;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Objet comptant les occurrences de termes
 * dans un corpus et exportant le résultat
 * sous forme de fichier texte.
 */
public class TermCounter
{	
	/**
	 * Méthode principale.
	 * 
	 * @param args
	 * 		Pas utilisé.
	 * 
	 * @throws FileNotFoundException 
	 * 		Problème lors de la création du fichier.
	 * @throws UnsupportedEncodingException 
	 * 		Problème de décodage lors de la lecture d'un document.
	 */
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException
	{	
		//TODO méthode à tester (TP5-ex1)
		TermCounter termCounter = new TermCounter();
		List<Token> lTokens = new ArrayList<Token>();
		lTokens.add(new Token("chat", 1));
		lTokens.add(new Token("bateau", 6));
		lTokens.add(new Token("chapeau", 10));
		lTokens.add(new Token("chat", 12));
		lTokens.add(new Token("chat", 4));
		System.out.println(termCounter.countTerms(lTokens));
		
		//TODO méthode à compléter (TP5-ex4)
	}
	
	/**
	 * Compte le nombre d'occurrences de chaque
	 * terme présent dans le corpus courant, puis 
	 * enregistre ces décomptes dans un fichier CSV.
	 * 
	 * @throws FileNotFoundException 
	 * 		Problème lors de la création du fichier.
	 * @throws UnsupportedEncodingException 
	 * 		Problème de décodage lors de la lecture d'un document.
	 */
	public static void processCorpus() throws FileNotFoundException, UnsupportedEncodingException
	{	//TODO méthode à compléter (TP5-ex3)
	}
	
	/**
	 * Compte le nombre d'occurrences de chaque
	 * terme dans la liste passée en paramètre.
	 * 
	 * @param tokens
	 * 		Liste de tokens normalisés à traiter.
	 * @return
	 * 		Map associant son nombre d'occurrences à chaque terme.
	 */
	private static Map<String,Integer> countTerms(List<Token> tokens)
	{	Map<String,Integer> result = new HashMap<String, Integer>();
		//TODO méthode à compléter (TP5-ex1)
		for (Token token : tokens) {
			Integer count = result.get(token.getType());
			if(count == null) {
				count = 0;
			} else {
				count++;
			}
			result.put(token.getType(), count);
		}
		return result;
	}
	
	/**
	 * Enregistre les décomptes des termes.
	 * 
	 * @param counts
	 * 		Map contenant les décomptes des termes.
	 * @param fileName
	 * 		Nom du fichier texte à créer.
	 * 
	 * @throws FileNotFoundException 
	 * 		Problème lors de la création du fichier.
	 * @throws UnsupportedEncodingException 
	 * 		Problème lors de l'écriture des résultats.
	 */
	private static void writeCounts(Map<String,Integer> counts, String fileName) throws FileNotFoundException, UnsupportedEncodingException
	{	//TODO méthode à compléter (TP5-ex2)
	}
}
