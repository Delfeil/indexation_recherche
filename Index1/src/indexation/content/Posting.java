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
		// Soit p1 le posting actuel (this) et p2 le posting auquel on compare p1
		// Si p1 a un docId plus petit que p2 -> return <0 
		// Si p1 a un docId identique à p2 -> return =0 
		// Si p1 a un docId plus grand que p2 -> return >0 
		result = this.getDocId() - posting.getDocId();
		return result;
	}
	
	////////////////////////////////////////////////////
	//	OBJECT
	////////////////////////////////////////////////////
	@Override
	public String toString()
	{	String result = null;
		//TODO méthode à compléter (TP1-ex8)
		result = "<" + Integer.toString(this.getDocId());
		//TODO méthode à modifier  (TP6-ex3)
		result += " [" + Integer.toString(this.frequency) + "]>";
		return result;
	}
	
	@Override
	public boolean equals(Object o)
	{	boolean result = false;
		//TODO méthode à compléter (TP1-ex8)
		if(o != null) {
			Posting p = (Posting) o;
			result =this.compareTo(p)==0;
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
	{	// test de compareTo
		// TODO méthode à compléter (TP1-ex8)
		Posting p = new Posting(4);
		Posting p1 = new Posting(6);
		Posting p3 = new Posting(2);
		Posting p4 = new Posting(4);
		System.out.println("p: "+ p + ", p1: " + p1 + ", " + Integer.toString(p.compareTo(p1)) + " " + p.equals(p4));
		// test de equals
		// TODO méthode à compléter (TP1-ex8)

		// test de toString
		// TODO méthode à compléter (TP1-ex8)
	}
}
