package indexation.content;

/**
 * Représente un token, i.e. un couple
 * (mot,numéro) de document.
 */
public class Token implements Comparable<Token>
{	/**
	 * Crée un nouveau token à partir
	 * du type et du numéro de document
	 * passés en paramètres.
	 * 
	 * @param type
	 * 		Type dont le token est une occurrence.
	 * @param docId
	 * 		Numéro du document concerné.
	 */
	public Token(String type, int docId)
	{	this.type = type;
		this.docId = docId;
	}
	
	////////////////////////////////////////////////////
	//	TYPE
	////////////////////////////////////////////////////
	/** Type associé au token */
	private String type;
	
	/**
	 * Renvoie le type associé à ce token.
	 * 
	 * @return
	 * 		Chaîne de caractères représentant le type de ce token.
	 */
	public String getType()
	{	return type;
	}
	
	/**
	 * Modifie le type associé à ce token.
	 * 
	 * @param type
	 * 		Chaîne de caractères représentant le nouveau type de ce token.
	 */
	public void setType(String type)
	{	this.type = type;
	}
	
	////////////////////////////////////////////////////
	//	DOC ID
	////////////////////////////////////////////////////
	/** Numéro du document contenant le token */
	private int docId;
	
	/**
	 * Renvoie le docId associé à ce token.
	 * 
	 * @return
	 * 		Entier représentant le docId de ce token.
	 */
	public int getDocId()
	{	return docId;
	}
	
	////////////////////////////////////////////////////
	//	COMPARABLE
	////////////////////////////////////////////////////
	@Override
	public int compareTo(Token token)
	{	int result = 0;
		//TODO méthode à compléter (TP1-ex1)
		return result;
	}
	
	////////////////////////////////////////////////////
	//	OBJECT
	////////////////////////////////////////////////////
	@Override
	public String toString()
	{	String result = null;
		//TODO méthode à compléter (TP1-ex2)
		return result;
	}
	
	@Override
	public boolean equals(Object o)
	{	boolean result = false;
		//TODO méthode à compléter (TP1-ex2)
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
		// TODO méthode à compléter (TP1-ex1)
		
		// test de equals et toString
		// TODO méthode à compléter (TP1-ex2)
				
	}
}
