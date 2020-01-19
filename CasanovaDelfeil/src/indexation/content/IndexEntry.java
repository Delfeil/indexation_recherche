package indexation.content;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Représente une entrée de l'index,
 * comprenant un terme, une liste
 * de postings et la fréquence du terme
 * exprimée en documents.
 */
public class IndexEntry implements Serializable, Comparable<IndexEntry>
{	/** Class id (juste pour éviter le warning) */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Crée une nouvelle entrée d'index,
	 * à partir du terme passé en paramètre.
	 * 
	 * @param term
	 * 		Terme inséré dans l'index.
	 */
	public IndexEntry(String term)
	{	this.term = term;
		this.postings = new ArrayList<Posting>();
		this.frequency = 0;
	}
	
	////////////////////////////////////////////////////
	//	TERME
	////////////////////////////////////////////////////
	/** Terme concerné par ce posting */
	private String term;
	
	/**
	 * Renvoie le terme associé à cette entrée de l'index.
	 * 
	 * @return
	 * 		Le terme de cette entrée.
	 */
	public String getTerm()
	{	return term;
	}
	
	////////////////////////////////////////////////////
	//	POSTINGS
	////////////////////////////////////////////////////
	/** Liste des postings contenant le terme */
	private List<Posting> postings;
	
	/**
	 * Renvoie la liste de postings associée à cette entrée de l'index.
	 * 
	 * @return
	 * 		La liste de postings de cette entrée.
	 */
	public List<Posting> getPostings()
	{	return postings;
	}
	
	/**
	 * Ajoute le posting spécifié à la liste associée
	 * à cette entrée de l'index, et incrémente la
	 * fréquence associée.
	 * 
	 * @param posting
	 * 		Posting à ajouter à la liste de cette entrée.
	 */
	public void addPosting(Posting posting)
	{	postings.add(posting);
		frequency++;
	}
	
	////////////////////////////////////////////////////
	//	FREQUENCE
	////////////////////////////////////////////////////
	/** Fréquence du terme exprimée en nombre de documents */
	private int frequency;
	
	/**
	 * Renvoie la fréquence associée à cette entrée de l'index,
	 * exprimée en nombre de documents.
	 * 
	 * @return
	 * 		La fréquence de cette entrée, en nombre de documents.
	 */
	public int getFrequency()
	{	return frequency;
	}
	
	////////////////////////////////////////////////////
	//	COMPARABLE
	////////////////////////////////////////////////////
	@Override
	public int compareTo(IndexEntry entry)
	{	int result = 0;
		//TODO méthode à compléter (TP1-ex9)
		// String this_term =this.getTerm(); 
		// String entry_term =entry.getTerm();
		// result =  this_term.compareTo(entry_term);
		result = this.getTerm().compareTo(entry.getTerm());
		return result;
	}
	
	////////////////////////////////////////////////////
	//	OBJECT
	////////////////////////////////////////////////////
	@Override
	public String toString()
	{	String result = null;
		//TODO méthode à compléter (TP1-ex9)
		result = "<" + this.getTerm() + " [" + this.getFrequency() + "] ( ";/*  + this.getPostings() + ")>"; */
		for (Posting p : this.getPostings()) {
			result = result + p + " ";
		}
		result = result + ")>";
		return result;
	}
	
	@Override
	public boolean equals(Object o)
	{	boolean result = false;
		//TODO méthode à compléter (TP1-ex9)
		if(o != null) {
			IndexEntry entry = (IndexEntry) o;
			result = this.compareTo(entry)==0;
		}
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
	{	// test de equals
		// TODO méthode à compléter (TP1-ex9)
		Posting p = new Posting(4);
		Posting p1 = new Posting(6);
		Posting p3 = new Posting(2);
		IndexEntry entry = new IndexEntry("bonjour");
		entry.addPosting(p3);
		entry.addPosting(p);
		entry.addPosting(p1);
		IndexEntry entry2 = new IndexEntry("bonjour");
		IndexEntry entry3 = new IndexEntry("salut");
		System.out.println(entry);
		System.out.println(entry.equals(entry2));
		System.out.println(entry.equals(entry3));

		// test de compareTo
		// TODO méthode à compléter (TP1-ex9)
				
		// test de toString
		// TODO méthode à compléter (TP1-ex9)
	}
}
