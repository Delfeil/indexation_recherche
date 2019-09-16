package indexation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import indexation.content.IndexEntry;
import indexation.processing.Normalizer;
import indexation.processing.Tokenizer;

/**
 * Objet représentant un index sous
 * la forme d'un fichier inverse simple.
 * Les classes filles différent dans la 
 * structure de données qu'elles utilisent
 * pour représenter le lexique.
 */
public abstract class AbstractIndex implements Serializable
{	/** Class id (juste pour éviter le warning) */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Méthode de classe permettant la création
	 * d'un index prenant la forme d'un fichier inverse.
	 * 
	 * @param tokenListType
	 * 		Type de liste à utiliser pour stocker les tokens lors de l'indexation.
	 * @param lexiconType
	 * 		Type de structure de données utilisée pour stocker le lexique.
	 * @return
	 * 		Index représentant le corpus.
	 * 
	 * @throws UnsupportedEncodingException
	 * 		Problème de décodage lors de la lecture d'un document.
	 * @throws FileNotFoundException 
	 * 		Problème de lecture de fichier
	 */
	public static AbstractIndex indexCorpus(TokenListType tokenListType, LexiconType lexiconType) throws UnsupportedEncodingException, FileNotFoundException
	{	AbstractIndex result = null;
		//TODO méthode à compléter (TP2-ex4)
		//TODO méthode à modifier  (TP2-ex8)
		return result;
	}
	
	/**
	 * Permet de controler le type de liste utilisé pour 
	 * stocker les tokens lors de l'indexation.
	 */
	public enum TokenListType
	{	/** Utilise une liste tabulée pour stocker les tokens */ 
		ARRAY,
		/** Utilise une liste chaînée pour stocker les tokens */
		LINKED;
	}
	
	/**
	 * Permet de controler le type de lexique utilisé 
	 * dans l'index.
	 */
	public enum LexiconType
	{	/** Utilise un tableau */ 
		ARRAY,
		/** Utilise une table de hashage */
		HASH,
		/** Utilise un arbre */
		TREE;
	}
	
	////////////////////////////////////////////////////
	//	CORPUS
	////////////////////////////////////////////////////
	/** Nombre de documents dans la collection */
	private int docNbr;
	
	/**
	 * Renvoie la taille du corpus indexé, 
	 * exprimée en nombre de documents.
	 * 
	 * @return
	 * 		Nombre de documents dans le corpus indexé.
	 */
	public int getDocumentNumber()
	{	return docNbr;
	}
	
	////////////////////////////////////////////////////
	//	TERMES
	////////////////////////////////////////////////////
	/**
	 * Renvoie l'entrée correspondant au terme
	 * passé en paramètre. Si une telle entrée n'existe 
	 * pas, alors la méthode renvoie {@code null}.
	 * 
	 * @param term
	 * 		Terme à rechercher.
	 * @return
	 * 		Entrée associéée au terme.
	 */
	public abstract IndexEntry getEntry(String term);
	
	/**
	 * Ajoute une entrée dans l'index, à la suite de celles
	 * qui y sont déjà stockées.
	 * 
	 * @param indexEntry
	 * 		L'entrée à ajouter à l'index.
	 * @param rank
	 * 		Le numéro de l'entrée dans le lexique. Cette information
	 * 		n'est utile que dans le cas où le lexique est un
	 * 		tableau. 
	 */
	public abstract void addEntry(IndexEntry indexEntry, int rank);
	
	/**
	 * Renvoie la taille de cet index (exprimée en nombre
	 * de termes).
	 * 
	 * @return
	 * 		Un entier correspondant au nombre de termes dans cet index.
	 */
	public abstract int getSize();
	
	////////////////////////////////////////////////////
	//	TOKÉNISATION
	////////////////////////////////////////////////////
	/** Objet utilisé pour tokéniser le texte lors de l'indexation */
	private Tokenizer tokenizer;
	
	/**
	 * Renvoie le tokéniseur utilisé lors de la construction
	 * de cet index.
	 * 
	 * @return
	 * 		Tokéniseur utilisé lors de l'indexation.
	 */
	public Tokenizer getTokenizer()
	{	return tokenizer;
	}
	
	////////////////////////////////////////////////////
	//	NORMALISATION
	////////////////////////////////////////////////////
	/** Objet utilisé pour normaliser le texte lors de l'indexation */
	private Normalizer normalizer;
	
	/**
	 * Renvoie le normalisateur utilisé lors de la construction
	 * de cet index.
	 * 
	 * @return
	 * 		Normalisateur utilisé lors de l'indexation.
	 */
	public Normalizer getNormalizer()
	{	return normalizer;
	}
	
	////////////////////////////////////////////////////
	//	STOCKAGE
	////////////////////////////////////////////////////
	/**
	 * Lecture d'un index dans le fichier configuré.
	 * On utilise simplement le mécanisme de sérialisation
	 * de Java.
	 * 
	 * @return
	 * 		L'index lu dans le fichier.
	 * 
	 * @throws IOException
	 * 		Problème lors de la lecture de l'index.
	 * @throws ClassNotFoundException
	 * 		Problème lors de la lecture de l'index.
	 */
	public static AbstractIndex read() throws IOException, ClassNotFoundException
	{	AbstractIndex result = null;
		//TODO méthode à compléter (TP2-ex11)
		return result;
	}
	
	/**
	 * Enregistrement de cet index dans le fichier configuré.
	 * On utilise simplement le mécanisme de sérialisation
	 * de Java.
	 * 
	 * @throws IOException
	 * 		Problème lors de l'écriture de l'index.
	 */
	public void write() throws IOException
	{	//TODO méthode à compléter (TP2-ex10)
	}

	////////////////////////////////////////////////////
	//	AFFICHAGE
	////////////////////////////////////////////////////
	/**
	 * Affiche le contenu de l'index.
	 */
	public abstract void print();

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
	{	// test de indexCorpus
		//TODO méthode à compléter (TP2-ex4)
		
		// test de write
		//TODO méthode à compléter (TP2-ex10)
		
		// test de read
		//TODO méthode à compléter (TP2-ex11)
	}
}
