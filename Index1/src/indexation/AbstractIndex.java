package indexation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import indexation.content.IndexEntry;
import indexation.content.Token;
import indexation.processing.Builder;
import indexation.processing.Normalizer;
import indexation.processing.Tokenizer;
import tools.Configuration;
import tools.FileTools;

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
		Boolean scilence = Configuration.isScilence();
		//TODO méthode à compléter (TP2-ex4)
		String folder = FileTools.getCorpusFolder();
		Tokenizer tokenizer = new Tokenizer();
		Normalizer normalizer = new Normalizer();
		Builder builder = new Builder();
			// Normalizer et Builder doivent être stockés dans les champs appropriés d’Index,
			// pour être utilisés ultérieurement. Une fois l’index créé, il doit être affiché.
		List<Token> tokens = null;
		switch(tokenListType) {
			case ARRAY:
				tokens = new ArrayList<Token>();
			break;
			case LINKED:
				tokens = new LinkedList<Token>();
			break;
		}
		long tot_start = System.currentTimeMillis(); 

		if(!scilence) {
			System.out.println("Tokenizing corpus...");
		}
		long start = System.currentTimeMillis();
		int nb_tokens = tokenizer.tokenizeCorpus(tokens);
		long end = System.currentTimeMillis();
		if(!scilence) {
			System.out.println(nb_tokens + " tokens were found, Durée mesurée : "+
				(end - start) + "  ms\n");
		}

		if(!scilence) {
			System.out.println("Normalizing tokens...");
		}
		start = System.currentTimeMillis();
		normalizer.normalizeTokens(tokens);
		end = System.currentTimeMillis();
		if(!scilence) {
			System.out.println(tokens.size() +
				" tokens remaining after normalization Building index, duration="+
				(end-start)+ "ms");
		}
		
		if(!scilence) {
			System.out.println("Building index...");
		}
		start = System.currentTimeMillis();
		result = builder.buildIndex(tokens, lexiconType);
		end = System.currentTimeMillis();
		if(!scilence) {
			System.out.println("There are "+ result.getSize() +
				" entries in the index, token list="+ tokenListType+
				", duration="+ (end-start)+ "ms");
		}

		long tot_end = System.currentTimeMillis();
		if(!scilence) {
			System.out.println("Total duration="+(tot_end-tot_start)+"  ms\n");
		}
		//TODO méthode à modifier  (TP2-ex8)

		result.tokenizer = tokenizer;
		result.normalizer = normalizer;
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
		long start = System.currentTimeMillis();
		String index_file = FileTools.getIndexFile();
		System.out.println("Loading the index... " + index_file);
		File file = new File(index_file);
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		result = (AbstractIndex) ois.readObject();
		ois.close();
		long end = System.currentTimeMillis();
		System.out.println("Index loaded, duration="+ (end-start) +"  ms\n");
		
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
		String index_file = FileTools.getIndexFile();
		System.out.println("Writing the index... " + index_file);
		long start = System.currentTimeMillis();

		File file = new File(index_file);
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(this);
		oos.close();

		long end = System.currentTimeMillis();
		System.out.println("Index written, duration="+ (end-start)+"  ms\n");
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
		Configuration.setCorpusName("wp_test");
		AbstractIndex index = AbstractIndex.indexCorpus(TokenListType.LINKED, LexiconType.ARRAY);
		// AbstractIndex index = AbstractIndex.indexCorpus(TokenListType.ARRAY, LexiconType.ARRAY);
		// AbstractIndex index = AbstractIndex.indexCorpus(TokenListType.LINKED, LexiconType.HASH);
		// AbstractIndex index = AbstractIndex.indexCorpus(TokenListType.ARRAY, LexiconType.HASH);
		// AbstractIndex index = AbstractIndex.indexCorpus(TokenListType.LINKED, LexiconType.TREE);
		// AbstractIndex index = AbstractIndex.indexCorpus(TokenListType.ARRAY, LexiconType.TREE);
		// index.print();

		// test de write
		//TODO méthode à compléter (TP2-ex10)
		index.write();
		
		// test de read
		//TODO méthode à compléter (TP2-ex11)
		AbstractIndex indexRead = AbstractIndex.read();
		indexRead.print();
	}
}
