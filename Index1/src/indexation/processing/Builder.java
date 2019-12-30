package indexation.processing;

import indexation.AbstractIndex;
import indexation.ArrayIndex;
import indexation.HashIndex;
import indexation.TreeIndex;
import indexation.AbstractIndex.LexiconType;
import indexation.content.IndexEntry;
import indexation.content.Posting;
import indexation.content.Token;
import tools.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Objet construisant un index prenant 
 * la forme d'un fichier inversé. Il a
 * pour cela besoin de recevoir la liste
 * normalisée des paires (tokens, docId).
 */
public class Builder
{	/**
	 * Construit l'index à partir
	 * des tokens passés en paramètres.
	 * 
	 * @param tokens
	 * 		Liste normalisée de tokens à traiter.
	 * @param lexiconType
	 * 		Type de structure de données utilisée pour stocker le lexique.
	 * @return
	 * 		L'index produit.
	 */
	public AbstractIndex buildIndex(List<Token> tokens, LexiconType lexiconType)
	{	AbstractIndex result = null;
		//TODO méthode à compléter (TP2-ex3)
		Boolean scilence = Configuration.isScilence();
		if(!scilence) {
			System.out.println("Sorting tokens...");
		}
		int nb_tokens = tokens.size();
		long start = System.currentTimeMillis();
		Collections.sort(tokens);
		long end = System.currentTimeMillis();
		if(!scilence) {
			System.out.println(nb_tokens + " tokens sorted, duration="
				+(end-start)+" ms\n");
		}
		
		
		if(!scilence) {
			System.out.println("Filtering tokens...");
		}
		start = System.currentTimeMillis();
		int nb_terms = this.filterTokens(tokens);
		end = System.currentTimeMillis();
		int nb_tokens_after_filter = tokens.size();
		if(!scilence) {
			System.out.println(nb_tokens_after_filter +
				" tokens remaining, corresponding to " + nb_terms +
				" terms, duration="+(end-start)+" ms\n");
		}
		
		switch(lexiconType) {
			case ARRAY:
			result = new ArrayIndex(nb_terms);
			break;
			case HASH:
			result = new HashIndex(nb_terms);
			break;
			case TREE:
			result = new TreeIndex();
			break;
		}
		if(!scilence) {
			System.out.println("Building posting lists...");
		}
		start = System.currentTimeMillis();
		int nb_postings = this.buildPostings(tokens, result);
		end = System.currentTimeMillis();
		if(!scilence) {
			System.out.println(nb_postings +
				" postings listed, lexicon type="+lexiconType + ", duration="+(end-start)+" ms\n");
		}
		//TODO méthode à modifier  (TP2-ex8)
		return result;
	}
	
	/**
	 * Supprime de la liste les occurrences
	 * multiples de tokens, à condition qu'ils
	 * appartiennent au même document.
	 * Bien sûr, on garde quand même une occurrence.
	 * 
	 * @param tokens
	 * 		La liste normalisée et triée de tokens à traiter.
	 * @return
	 * 		Nombre de termes distincts dans la liste.
	 */
	private int filterTokens(List<Token> tokens)
	{	int result = 0;
		//TODO méthode à compléter (TP2-ex1)
		if(tokens == null || tokens.size()==0) {
			return result;
		}
		Iterator<Token> itt = tokens.iterator();
		String currType = "";
		int currDocId = -1;
		while(itt.hasNext()) {
			Token token = itt.next();
			if(token.getType().equals(currType) && token.getDocId() == currDocId) {
				itt.remove();
			} else if(token.getType().equals(currType) && token.getDocId() != currDocId) {
				currDocId = token.getDocId();
			} else if(!token.getType().equals(currType)) {
				result +=1;
				currType = token.getType();
				currDocId = token.getDocId();
			}
		}
		return result;
	}
	
	/**
	 * Supprime de la liste les occurrences
	 * multiples de tokens, à condition qu'elles
	 * appartiennent au même document.
	 * Bien sûr, on garde quand même une occurrence.
	 * <br/>
	 * Par rapport à {@link #filterTokens(List)},
	 * cette méthode calcule en plus les fréquences
	 * des tokens dans chaque document où ils apparaissent.
	 * 
	 * @param tokens
	 * 		La liste normalisée et triée de tokens à traiter.
	 * @param frequencies
	 * 		La liste des fréquences associées à ces tokens.
	 * @return
	 * 		Nombre de termes distincts dans la liste.
	 */
	private int filterTokens(List<Token> tokens, List<Integer> frequencies)
	{	int result = 0;
		//TODO méthode à compléter (TP6-ex4)
		return result;
	}
	
	/**
	 * Construit un index à partir de
	 * la liste de tokens normalisée, triée et filtrée
	 * passée en paramètre.
	 * 
	 * @param tokens
	 * 		Liste normalisée, triée et filtrée de tokens.
	 * @param index
	 * 		L'index obtenu, sous forme de fichier inverse.
	 * @return
	 * 		Nombre de postings listés.
	 */
	private int buildPostings(List<Token> tokens, AbstractIndex index)
	{	int result = 0;
		//TODO méthode à compléter (TP2-ex2)
		
		//On parcours tous les postings
			//Pour chaque posting, on regarde si il y a une entry déjà présente dans l'index
				//SI il y a une entry -> on ajoute le posting
				//Sinon -> On crée l'entry et on lui ajoute le posting et on le place dans l'index
		IndexEntry iEntry = null;
		String prev_token_type = "";
		int nb_token = -1;
		for (Token token : tokens) {
			if(!prev_token_type.equals(token.getType())) {
				prev_token_type = token.getType();
				nb_token++;
			}
			if(index instanceof ArrayIndex) {
				ArrayIndex ai = (ArrayIndex) index;
				iEntry = ai.getEntries()[nb_token];
			} else {
				iEntry = index.getEntry(token.getType());
			}
			if(iEntry == null) {
				iEntry = new IndexEntry(token.getType());
				iEntry.addPosting(new Posting(token.getDocId()));
				index.addEntry(iEntry, nb_token);
				result++;
			} else {
				iEntry.addPosting(new Posting(token.getDocId()));
				result++;
			}
		}
		return result;
	}
	
	/**
	 * Construit un index à partir de
	 * la liste de tokens normalisée, triée et filtrée
	 * passée en paramètre.
	 * <br/>
	 * La différence avec {@link #buildPostings(List, AbstractIndex)}
	 * est que cette méthode prend une liste supplémentaires contenant
	 * les fréquences des termes.
	 * 
	 * @param tokens
	 * 		Liste normalisée, triée et filtrée de tokens.
	 * @param frequencies
	 * 		La liste des fréquences associées à ces tokens.
	 * @param index
	 * 		L'index obtenu, sous forme de fichier inverse.
	 * @return
	 * 		Nombre de postings listés.
	 */
	private int buildPostings(List<Token> tokens, List<Integer> frequencies, AbstractIndex index)
	{	int result = 0;
		//TODO méthode à compléter (TP6-ex5)
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
	{	// test de filterTokens
		//TODO méthode à compléter (TP2-ex1)
		Token t5 = new Token("barque", 0);
		Token t4 = new Token("barque", 4);
		Token t1 = new Token("bateau", 1);
		Token t2 = new Token("bateau", 2);
		Token t3 = new Token("bateau", 2);
		Token t6 = new Token("tram", 3);
		List<Token> tokens = new ArrayList<Token>();
		tokens.add(t5);
		tokens.add(t4);
		tokens.add(t1);
		tokens.add(t2);
		tokens.add(t3);
		tokens.add(t6);
		System.out.println(tokens);
		Builder b = new Builder();
		int nb_tokens = b.filterTokens(tokens);
		System.out.println("nb tokens: " + nb_tokens + " \n " + tokens);

		// test de buildPostings
		//TODO méthode à compléter (TP2-ex2)
		HashIndex hi = new HashIndex(2);
		ArrayIndex ai = new ArrayIndex(3);
		System.out.println( "tokens: " + tokens.size() + ", " + b.buildPostings(tokens, hi));
		System.out.println("index: ");
		hi.print();
		System.out.println( "tokens: " + tokens.size() + ", " + b.buildPostings(tokens, ai));
		System.out.println("index: ");
		ai.print();
		
		// test de buildIndex
		//TODO méthode à compléter (TP2-ex3)
		Tokenizer tokenizer = new Tokenizer();
		List<Token> tokens3 = new ArrayList<Token>();

		Configuration.setCorpusName("wp_test");
		int result = tokenizer.tokenizeCorpus(tokens3);
		// System.out.println("Nombre de tokens trouvés: " + result);
		Normalizer normalizer = new Normalizer();
		normalizer.normalizeTokens(tokens3);

		AbstractIndex indexArray = b.buildIndex(tokens3, LexiconType.ARRAY);
		// indexArray.print();
		// AbstractIndex indexHash = b.buildIndex(tokens3, LexiconType.HASH);
		// indexHash.print();
		// AbstractIndex indexTree = b.buildIndex(tokens3, LexiconType.TREE);
		// indexTree.print();
		
		// test de filterTokens
		//TODO méthode à compléter (TP6-ex4)
		
		// test de buildPostings
		//TODO méthode à compléter (TP6-ex5)
	}
}
