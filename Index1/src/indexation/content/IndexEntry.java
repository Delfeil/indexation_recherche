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
		return result;
	}
	
	////////////////////////////////////////////////////
	//	OBJECT
	////////////////////////////////////////////////////
	@Override
	public String toString()
	{	String result = null;
		//TODO méthode à compléter (TP1-ex9)
		return result;
	}
	
	@Override
	public boolean equals(Object o)
	{	boolean result = false;
		//TODO méthode à compléter (TP1-ex9)
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
				
		// test de compareTo
		// TODO méthode à compléter (TP1-ex9)
				
		// test de toString
		// TODO méthode à compléter (TP1-ex9)
	}
}
