package query;

import java.text.NumberFormat;

/**
 * Classe représentant un couple (docId,score).
 */
public class DocScore implements Comparable<DocScore>
{	/**
	 * Crée un nouvel objet à partir
	 * des paramètres spécifiés.
	 * 
	 * @param docId
	 * 		Numéro du document concerné.
	 * @param score
	 * 		Score associé au document.
	 */
	public DocScore(int docId, float score)
	{	this.docId = docId;
		this.score = score;
	}
	
	////////////////////////////////////////////////////
	//	DOC ID
	////////////////////////////////////////////////////
	/** Numéro du document */
	private int docId;
	
	/**
	 * Renvoie le docId dont le score est 
	 * représenté par cet objet.
	 * 
	 * @return
	 * 		DocId traité par cet objet.
	 */
	public int getDocId()
	{	return docId;
	}

	////////////////////////////////////////////////////
	//	POSITION
	////////////////////////////////////////////////////
	/** Score du document */
	private float score;
	
	/**
	 * Renvoie le score du document 
	 * représenté par cet objet.
	 * 
	 * @return
	 * 		Score du document (un réel).
	 */
	public float getScore()
	{	return score;
	}
	
	////////////////////////////////////////////////////
	//	COMPARABLE
	////////////////////////////////////////////////////
	@Override
	public int compareTo(DocScore docScore)
	{	int result = 0;
		//TODO méthode à compléter (TP6-ex2)
		return result;
	}
	
	////////////////////////////////////////////////////
	//	OBJECT
	////////////////////////////////////////////////////
	/** Format utilisé lors de l'affichage du score, pour le limiter à 4 décimales */
	private static NumberFormat NUMBER_FORMAT;
	{	//TODO bloc statique à compléter (TP6-ex1)
	}
	
	@Override
	public String toString()
	{	String result = null;
		//TODO méthode à compléter (TP6-ex1)
		return result;
	}
	
	@Override
	public boolean equals(Object o)
	{	boolean result = false;
		//TODO méthode à compléter (TP6-ex2)
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
	{	// test de toString
		// TODO méthode à compléter (TP6-ex1)
		
		// test de compareTo et equals
		// TODO méthode à compléter (TP6-ex2)
				
	}
}
