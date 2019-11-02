package indexation;

import java.lang.reflect.Array;
import java.util.Arrays;

import indexation.content.IndexEntry;
import indexation.content.Posting;

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
		this.data = new IndexEntry[size];
	}
	
	////////////////////////////////////////////////////
	//	DONNÉES
	////////////////////////////////////////////////////
	/** Lexique et postings de l'index */
	private IndexEntry[] data;
	
	@Override
	public void addEntry(IndexEntry indexEntry, int rank)
	{	//TODO méthode à compléter (TP1-ex12)
		this.data[rank] = indexEntry;
	}
	
	@Override
	public IndexEntry getEntry(String term)
	{	IndexEntry result = null;
		//TODO méthode à compléter (TP1-ex13)
		result =this.data[Arrays.binarySearch(this.data, term)];
		return result;
	}
	
	@Override
	public int getSize()
	{	int result = 0;
		//TODO méthode à compléter (TP1-ex14)
		result = this.data.length;
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
		for (IndexEntry entry : data) {
			System.out.println(entry);
		}
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
		ArrayIndex ai = new ArrayIndex(5);
		// test de print
		//TODO méthode à compléter (TP1-ex11)
		IndexEntry entry = new IndexEntry("barque");
		IndexEntry entry2 = new IndexEntry("bateau");
		Posting p = new Posting(4);
		Posting p1 = new Posting(6);
		Posting p3 = new Posting(2);
		entry.addPosting(p);
		entry.addPosting(p1);
		entry2.addPosting(p1);
		entry2.addPosting(p3);
		
		// test de addEntry
		//TODO méthode à compléter (TP1-ex12)
		ai.addEntry(entry, 0);
		ai.addEntry(entry2, 1);
		ai.addEntry(entry, 2);
		ai.addEntry(entry, 3);
		ai.addEntry(entry, 4);
		ai.print();
		
		// test de getEntry
		//TODO méthode à compléter (TP1-ex13)
		System.out.println(ai.getEntry("bateau"));
		
		// test de getSize
		//TODO méthode à compléter (TP1-ex14)
		System.out.println(ai.getSize());
	}
}
