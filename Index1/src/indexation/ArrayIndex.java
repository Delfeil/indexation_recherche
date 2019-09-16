package indexation;

import indexation.content.IndexEntry;

/**
 * Objet représentant un index sous
 * la forme d'un fichier inverse simple,
 * dont le lexique est stocké dans un
 * tableau.
 */
public class ArrayIndex extends AbstractIndex
{	/** Class id (juste pour éviter le warning) */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Construit un nouvel index vide,
	 * de la taille indiquée en paramétre.
	 * 
	 * @param size
	 * 		Taille de l'index (expriée en nombre de termes).
	 */
	public ArrayIndex(int size)
	{	//TODO méthode à compléter (TP1-ex10)
	}
	
	////////////////////////////////////////////////////
	//	DONNÉES
	////////////////////////////////////////////////////
	/** Lexique et postings de l'index */
	private IndexEntry[] data;
	
	@Override
	public void addEntry(IndexEntry indexEntry, int rank)
	{	//TODO méthode à compléter (TP1-ex12)
	}
	
	@Override
	public IndexEntry getEntry(String term)
	{	IndexEntry result = null;
		//TODO méthode à compléter (TP1-ex13)
		return result;
	}
	
	@Override
	public int getSize()
	{	int result = 0;
		//TODO méthode à compléter (TP1-ex14)
		return result;
	}
	
	/**
	 * Renvoie le tableau correspondant
	 * au lexique de cet index.
	 * 
	 * @return
	 * 		Lexique sous forme de tableau d'entrées.
	 */
	public IndexEntry[] getEntries()
	{	return data;
	}
	
	////////////////////////////////////////////////////
	//	AFFICHAGE
	////////////////////////////////////////////////////
	/**
	 * Affiche le contenu de l'index.
	 */
	@Override
	public void print()
	{	//TODO méthode à compléter (TP1-ex11)
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
		//TODO méthode à compléter (TP1-ex10)
		
		// test de print
		//TODO méthode à compléter (TP1-ex11)
		
		// test de addEntry
		//TODO méthode à compléter (TP1-ex12)
		
		// test de getEntry
		//TODO méthode à compléter (TP1-ex13)
		
		// test de getSize
		//TODO méthode à compléter (TP1-ex14)
	}
}
