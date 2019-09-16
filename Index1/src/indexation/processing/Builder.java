package indexation.processing;

import indexation.AbstractIndex;
import indexation.AbstractIndex.LexiconType;
import indexation.content.IndexEntry;
import indexation.content.Posting;
import indexation.content.Token;

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
		
		// test de buildPostings
		//TODO méthode à compléter (TP2-ex2)
		
		// test de buildIndex
		//TODO méthode à compléter (TP2-ex3)
		
		// test de filterTokens
		//TODO méthode à compléter (TP6-ex4)
		
		// test de buildPostings
		//TODO méthode à compléter (TP6-ex5)
	}
}
