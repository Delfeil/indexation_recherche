package tools;

import indexation.AbstractIndex;
import indexation.content.IndexEntry;
import indexation.content.Posting;
import indexation.content.Token;
import indexation.processing.Normalizer;
import indexation.processing.Tokenizer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
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
		//TODO Test (TP5-ex1)
		// TermCounter termCounter = new TermCounter();
		// List<Token> lTokens = new ArrayList<Token>();
		// lTokens.add(new Token("chat", 1));
		// lTokens.add(new Token("bateau", 6));
		// lTokens.add(new Token("chapeau", 10));
		// lTokens.add(new Token("chat", 12));
		// lTokens.add(new Token("chat", 4));
		
		//TODO Test (TP5-ex2)
		// Map<String,Integer> counts = termCounter.countTerms(lTokens);
		// System.out.println(counts);
		
		//TODO Test (TP5-ex3)
		// String termCountFile = FileTools.getTermCountFile();
		// termCounter.writeCounts(counts, termCountFile);

		// Configuration.setCorpusName("wp_test");
		// termCounter.processCorpus();
		
		//TODO méthode à compléter (TP5-ex4)
		Configuration.setCorpusName("springer");
		Configuration.setStemmingTokens(true);
		TermCounter termCounter = new TermCounter();
		termCounter.processCorpus();
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
		long totStart = System.currentTimeMillis();

			// Tokenization
		System.out.println("Tokenizing corpus");
		long start = System.currentTimeMillis();
		Tokenizer tokenizer = new Tokenizer();
		List<Token> tokens = new LinkedList<Token>(); 
		int countTokens = tokenizer.tokenizeCorpus(tokens);
		long end = System.currentTimeMillis();
		System.out.println(countTokens + "tokens were found, duration=" + (end-start) + " ms");

			// Normalization
		System.out.print("Normalizing tokens ");
		start = System.currentTimeMillis();
		Normalizer normalizer = new Normalizer();
		normalizer.normalizeTokens(tokens);
		end = System.currentTimeMillis();
		System.out.println(tokens.size() + " tokens remaining after normalization, duration=" + (end-start) + " ms");
		
			// Counting terms
		System.out.println("Counting terms");
		start = System.currentTimeMillis();
		TermCounter termCounter = new TermCounter();
		Map<String,Integer> termsCount = termCounter.countTerms(tokens);
		end = System.currentTimeMillis();
		System.out.println("There are "+termsCount.size()+" distinct terms in the corpus, duration=" + (end-start) + " ms");
		
		// Recording counts
		start = System.currentTimeMillis();
		String countFile = FileTools.getTermCountFile();
		System.out.println("Recording counts in "+countFile);
		termCounter.writeCounts(termsCount, countFile);
		end = System.currentTimeMillis();
		System.out.println("Counts recorded, duration=" + (end-start) + " ms");

			// Stop words
		String stopWordsFile = FileTools.getStopWordsFile();
		System.out.println("Stop-words file: " + stopWordsFile);
		long totEnd = System.currentTimeMillis();
		System.out.println("Total duration=" + (totEnd-totStart) + " ms");
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
			}
			count++;
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
		PrintWriter writer = new PrintWriter(fileName);
		for (Map.Entry<String,Integer> type : counts.entrySet()) {
			writer.println("\""+type.getKey() + "\"," + type.getValue());
		}
		writer.close();
	}
}
