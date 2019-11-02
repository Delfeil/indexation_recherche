package indexation;

import java.util.HashMap;
import java.util.Map;

import indexation.content.IndexEntry;
import indexation.content.Posting;

/**
 * Objet représentant un index sous
 * la forme d'un fichier inverse simple,
 * dont le lexique est stocké dans une
 * table de hachage.
 */
public class HashIndex extends AbstractIndex
{	/** Class id (juste pour éviter le warning) */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Construit un nouvel index vide,
	 * de la taille indiquée en paramètre.
	 * 
	 * @param size
	 * 		Taille de l'index (exprimée en nombre de termes).
	 */
	public HashIndex(int size)
	{	//TODO méthode à compléter (TP1-ex10)
		this.data = new HashMap<String,IndexEntry>(size);
	}
	
	////////////////////////////////////////////////////
	//	DONNÉES
	////////////////////////////////////////////////////
	/** Lexique et postings de l'index */
	private HashMap<String,IndexEntry> data;
	
	@Override
	public void addEntry(IndexEntry indexEntry, int rank)
	{	//TODO méthode à compléter (TP1-ex12)
		this.data.put(indexEntry.getTerm(), indexEntry);
	}
	
	@Override
	public IndexEntry getEntry(String term)
	{	IndexEntry result = null;
		//TODO méthode à compléter (TP1-ex13)
		result = this.data.get(term);
		return result;
	}
	
	@Override
	public int getSize()
	{	int result = 0;
		//TODO méthode à compléter (TP1-ex14)
		result = this.data.size();
		return result;
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
		for (Map.Entry<String, IndexEntry> it : this.data.entrySet()){
			IndexEntry entry = it.getValue();
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
		HashIndex hi = new HashIndex(5);
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
		hi.addEntry(entry, 0);
		hi.addEntry(entry2, 1);
		hi.print();
		
		// test de getEntry
		//TODO méthode à compléter (TP1-ex13)
		System.out.println(hi.getEntry("bateau"));
		
		// test de getSize
		//TODO méthode à compléter (TP1-ex14)
		System.out.println(hi.getSize());

	}
}
