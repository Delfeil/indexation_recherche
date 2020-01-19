package indexation.processing;

import java.io.Serializable;

/**
 * Objet racinisant les tokens
 * en appliquant une approche à 
 * base de règles, de type Porter.
 */
public abstract class AbstractStemmer implements Serializable
{	/** Class id (juste pour éviter le warning) */
	private static final long serialVersionUID = 1L;
	
	////////////////////////////////////////////////////
	//	TRAITEMENT
	////////////////////////////////////////////////////
	/**
	 * Racinise le type de token reçu en paramètre.
	 * 
	 * @param string
	 * 		La chaîne à traiter : un type associé à un token.
	 * @return
	 * 		La chaîne après traitement : la racine du type.
	 */
	public abstract String stemType(String string);
}
