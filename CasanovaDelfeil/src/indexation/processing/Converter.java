package indexation.processing;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import indexation.content.Token;

/**
 * Objet convertissant un token en une liste de rotations, afin
 * de mettre en place un index de type <i>permuterm</i>.
 */
public class Converter implements Serializable
{	/** Class id (juste pour éviter le warning) */
	private static final long serialVersionUID = 1L;
	
	////////////////////////////////////////////////////
	//	TRAITEMENT
	////////////////////////////////////////////////////
	/** Marqueur de fin de terme */
	private final static String CHAR_END = "$";
	/** Caractère joker */
	private final static String CHAR_JOKER = "*";
	
	/**
	 * Test si la chaîne de caractères passée en paramètre contient
	 * un caractère joker ou pas.
	 * 
	 * @param token
	 * 		Chaîne de caractères à tester.
	 * @return
	 * 		{@code true} ssi la chaîne contient {@link #CHAR_JOKER}. 
	 */
	public boolean containsJoker(String token)
	{	boolean result = token.contains(CHAR_JOKER);
		return result;
	}
	
	/**
	 * Convertit les tokens reçus en paramètres lors de l'indexation.
	 * 
	 * @param tokens
	 * 		Liste de tokens à traiter.
	 * @return
	 * 		Liste de toutes les rotations de tous les tokens.
	 */
	public List<Token> convertIndexTokens(List<Token> tokens)
	{	List<Token> result = new LinkedList<Token>();
		
		// TODO ex1
		for (Token token : tokens) {
			String type = token.getType();
			int docId = token.getDocId();
			type = CHAR_END + type;
			int y =0;
			// String lastChar = type.substring(type.length(), -1);
			// System.out.println(lastChar);
			while(!"$".equals(type.substring(type.length() - 1))) {
				if(y==0) {
					result.add(new Token(type, docId));
				} else {
					String lastChar = type.substring(type.length() - 1);
					// String fisrtChar = type.substring(0, 1);
					type = lastChar + type.substring(0, type.length() - 1);
					result.add(new Token(type, docId));
				}
				y++;
			}
		}
		
		return result;
	}

	/**
	 * Convertit l'expression reçue en paramètre lors du traitement d'une requête.
	 * 
	 * @param token
	 * 		Expression à traiter.
	 * @return
	 * 		Rotation de l'expression.
	 */
	public String convertQueryToken(String token)
	{	String result = null;
		
		// TODO ex4
		token.indexOf(CHAR_JOKER);
		// token = CHAR_END + token;
		while(!"$".equals(token.substring(token.length() - 1))) {
			String lastChar = token.substring(token.length() - 1);
			String fisrtChar = token.substring(0, 1);
			token = lastChar + token.substring(0, token.length() - 1);
			System.out.println(fisrtChar + " , " + lastChar + " , " + token);
		}

		return result;
	}

	/**
	 * Convertit l'expression reçue en paramètre lors du traitement d'une requête.
	 * La différence avec {@link #convertQueryToken(String)} est que cette méthode
	 * gère un cas de plus : celui de la forme {@code *infixe*}. 
	 * 
	 * @param token
	 * 		Expression à traiter.
	 * @return
	 * 		Rotation de l'expression.
	 */
	public String convertQueryToken2(String token)
	{	String result = null;
		
		// TODO ex4
		
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
	 * @throws UnsupportedEncodingException 
	 * 		Problème lors de l'accès aux textes.
	 */
	public static void main(String[] args) throws UnsupportedEncodingException 
	{	// convertIndexTokens
		// TODO test ex1
		/*String test = "$abc";
		while(!"$".equals(test.substring(test.length() - 1))) {
			// if(y==0) {
			// 	result.add(new Token(test, docId));
			// } else {
				
			// }
			String lastChar = test.substring(test.length() - 1);
			String fisrtChar = test.substring(0, 1);
			test = lastChar + test.substring(0, test.length() - 1);
			System.out.println(fisrtChar + " , " + lastChar + " , " + test);
		}
		// String lastChar = test.substring(test.length() - 1);
		// String firstChar = test.substring(0, 1);
		// String replace = test = lastChar + test.substring(0, test.length() - 1);
		// System.out.println(firstChar + " , " + lastChar + " , " + replace);
		*/

		Converter c = new Converter();
		List<Token> t = new ArrayList<Token>();
		t.add(new Token("abc", 1));
		t.add(new Token("de", 99));
		System.out.println(t);
		t = c.convertIndexTokens(t);
		System.out.println(t);
		
		
		// convertQueryToken
		// TODO test ex4
		
		// convertQueryToken2
		// TODO test ex7
	}
}
