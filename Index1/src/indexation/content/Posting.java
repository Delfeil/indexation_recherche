package indexation.content;

import java.io.Serializable;

/**
 * Représente un posting, c'est à dire
 * ici : simplement le numéro du document
 * contenant un token donné.
 */
public class Posting implements Serializable, Comparable<Posting>
{	/** Class id (juste pour éviter le warning) */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Construit un nouveau posting
	 * à partir du numéro de document
	 * passé en paramètre.
	 * <br/>
	 * <b>Note :</b> À utiliser à partir du TP 1.
	 * 
	 * @param docId
	 * 		Numéro du document concerné.
	 */
	public Posting(int docId)
	{	this.docId = docId;
		this.frequency = 0;
	}
	
	/**
	 * Construit un nouveau posting
	 * à partir du numéro de document
	 * et de la fréquence passés en paramètre.
	 * <br/>
	 * <b>Note :</b> À utiliser dans le TP 6
	 * 
	 * @param docId
	 * 		Numéro du document concerné.
	 * @param frequency
	 * 		Fréquence du terme dans ce document.
	 */
	public Posting(int docId, int frequency)
	{	this.docId = docId;
		this.frequency = frequency;
	}
	
	////////////////////////////////////////////////////
	//	DOC ID
	////////////////////////////////////////////////////
	/** Numéro du document représenté par ce posting */
	private int docId;
	
	/**
	 * Renvoie le docId associé à ce posting.
	 *  
	 * @return
	 * 		Entier représentant le docId de ce posting.
	 */
	public int getDocId()
	{	return docId;
	}
	
	////////////////////////////////////////////////////
	//	FREQUENCE
	////////////////////////////////////////////////////
	/** Fréquence du terme dans le document */
	private int frequency;
	
	/**
	 * Renvoie la fréquence du terme dans le 
	 * document correspondant à ce posting.
	 * 
	 * @return
	 * 		Nombre d'occurrences du terme dans le document.
	 */
	public int getFrequency()
	{	return frequency;
	}
	
	////////////////////////////////////////////////////
	//	COMPARABLE
	////////////////////////////////////////////////////
	@Override
	public int compareTo(Posting posting)
	{	int result = 0;
		//TODO méthode à compléter (TP1-ex8)
		return result;
	}
	
	////////////////////////////////////////////////////
	//	OBJECT
	////////////////////////////////////////////////////
	@Override
	public String toString()
	{	String result = null;
		//TODO méthode à compléter (TP1-ex8)
		//TODO méthode à modifier  (TP6-ex3)
		return result;
	}
	
	@Override
	public boolean equals(Object o)
	{	boolean result = false;
		//TODO méthode à compléter (TP1-ex8)
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
	{	// test de compareTo
		// TODO méthode à compléter (TP1-ex8)
		
		// test de equals
		// TODO méthode à compléter (TP1-ex8)

		// test de toString
		// TODO méthode à compléter (TP1-ex8)
	}
}
