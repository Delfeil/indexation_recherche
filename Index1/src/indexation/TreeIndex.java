package indexation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;

import indexation.content.IndexEntry;
import indexation.content.Posting;

/**
 * Objet représentant un index sous la forme d'un fichier inverse simple, dont
 * le lexique est stocké dans un arbre de recherche.
 */
public class TreeIndex extends AbstractIndex {
	/** Class id (juste pour éviter le warning) */
	private static final long serialVersionUID = 1L;

	/**
	 * Construit un nouvel index vide, de la taille indiquée en paramètre.
	 */
	public TreeIndex() { // TODO méthode à compléter (TP1-ex10)
		this.data = new TreeMap<String, IndexEntry>();
	}

	////////////////////////////////////////////////////
	// DONNÉES
	////////////////////////////////////////////////////
	/** Lexique et postings de l'index */
	private TreeMap<String, IndexEntry> data;

	@Override
	public void addEntry(IndexEntry indexEntry, int rank) { // TODO méthode à compléter (TP1-ex12)
		this.data.put(indexEntry.getTerm(), indexEntry);
	}

	@Override
	public IndexEntry getEntry(String term) {
		IndexEntry result = null;
		// TODO méthode à compléter (TP1-ex13)
		result = this.data.get(term);
		return result;
	}

	@Override
	public List<IndexEntry> getEntriesStartingWith(String prefix) {
		List<IndexEntry> result = new ArrayList<IndexEntry>();
		// TODO méthode à compléter (TPexam-ex3)
		IndexEntry tmpEntry = new IndexEntry(prefix);
		SortedMap<String, IndexEntry> temp = data.tailMap(prefix);
		
		boolean goOn = true;
		for (Map.Entry<String, IndexEntry> mapEntry : temp.entrySet()) {
			if(!goOn) {
				continue;
			}
			IndexEntry entry = mapEntry.getValue();
			goOn = entry.getTerm().startsWith(prefix);
			if(goOn) {
				result.add(entry);
			}
		}
		
		return result;
	}

	@Override
	public int getSize() {
		int result = 0;
		// TODO méthode à compléter (TP1-ex14)
		result = this.data.size();
		return result;
	}

	////////////////////////////////////////////////////
	// AFFICHAGE
	////////////////////////////////////////////////////
	/**
	 * Affiche le contenu de l'index.
	 */
	@Override
	public void print() { // TODO méthode à compléter (TP1-ex11)
		for (Map.Entry<String, IndexEntry> it : this.data.entrySet()) {
			IndexEntry entry = it.getValue();
			System.out.println(entry);
		}
	}

	////////////////////////////////////////////////////
	// TEST
	////////////////////////////////////////////////////
	/**
	 * Test des méthodes de cette classe.
	 * 
	 * @param args Pas utilisé.
	 * 
	 * @throws Exception Problème quelconque rencontré.
	 */
	public static void main(String[] args) throws Exception { // test du constructeur
																// TODO méthode à compléter (TP1-ex10)
		TreeIndex ti = new TreeIndex();
		// test de print
		// TODO méthode à compléter (TP1-ex11)
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
		// TODO méthode à compléter (TP1-ex12)
		ti.addEntry(entry, 0);
		ti.addEntry(entry2, 1);
		ti.print();

		// test de getEntry
		// TODO méthode à compléter (TP1-ex13)
		System.out.println(ti.getEntry("bateau"));

		// test de getSize
		// TODO méthode à compléter (TP1-ex14)
		System.out.println(ti.getSize());
		
		// TODO méthode à compléter (exam-ex3)
		System.out.println(ti.getEntriesStartingWith("ba"));
	}
}
