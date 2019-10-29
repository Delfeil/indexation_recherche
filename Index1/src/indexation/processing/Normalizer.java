package indexation.processing;

import indexation.content.Token;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import tools.Configuration;

/**
 * Objet normalisant des tokens
 * en supprimant les signes diacritiques
 * et en les passant en minuscules.
 */
public class Normalizer implements Serializable
{	/** Class id (juste pour éviter le warning) */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Initialise le normalisateur,
	 * en fonction de la configuration 
	 * de {@link Configuration}.
	 * 
	 * @throws FileNotFoundException
	 * 		Problème lors de l'accès au fichier de mots vides. 
	 * @throws UnsupportedEncodingException 
	 * 		Problème lors de l'accès au fichier de mots vides. 
	 */
	public Normalizer() throws FileNotFoundException, UnsupportedEncodingException
	{	initStemmer();
		initStopWords();
	}
	
	////////////////////////////////////////////////////
	//	TRAITEMENT
	////////////////////////////////////////////////////
	/**
	 * Nettoie les tokens reçus en paramètres.
	 * 
	 * @param tokens
	 * 		Liste de tokens à traiter.
	 */
	public void normalizeTokens(List<Token> tokens)
	{	//TODO méthode à compléter (TP1-ex7)
		Iterator<Token> it = tokens.iterator(); 
		while(it.hasNext()) {
			Token token = it.next();
			String normalised = this.normalizeType(token.getType());
			if(normalised == null) {
				it.remove();
			} else {
				token.setType(normalised);
			}
		}
	}
	
	/**
	 * Nettoie le type de token reçu en paramètre.
	 * S'il ne correspond pas à un terme, c'est
	 * la valeur {@code null} qui est renvoyée.
	 * 
	 * @param string
	 * 		La chaîne à traiter : un type associé à un token.
	 * @return
	 * 		La chaîne après traitement : un terme (ou {@code null}).
	 */
	public String normalizeType(String string)
	{	String result = null;
		//TODO méthode à compléter (TP1-ex6)
		if(string != null && !string.equals("")) {
			result = string.toLowerCase();
			result = java.text.Normalizer.normalize(result, Form.NFD)
				.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
		}
		//TODO méthode à modifier  (TP4-ex14)
		//TODO méthode à modifier  (TP5-ex8)
		return result;
	}

	////////////////////////////////////////////////////
	//	RACINISATEUR
	////////////////////////////////////////////////////
	/** Stemmeur utilisé lors de la normalistion (optionnel) */
	private AbstractStemmer stemmer;
	
	/**
	 * Met en place le racinisateur utilisé par ce normalisateur.
	 * En l'absence de racinisateur, le normalisateur n'effectue
	 * pas de racinisation.
	 */
	private void initStemmer()
	{	if(Configuration.isStemmingTokens())
			stemmer = new PorterStemmer();
		else
			stemmer = null;
	}
	
	////////////////////////////////////////////////////
	//	MOTS-VIDES
	////////////////////////////////////////////////////
	/** Liste des mots vides */
	private TreeSet<String> stopWords;
	
	/**
	 * Met en place la liste de mots-vides utilisés
	 * lors du filtrage. En l'absence de liste,
	 * aucun filtrage n'est réalisé.
	 * 
	 * @throws FileNotFoundException
	 * 		Problème lors de l'accès au fichier de mots vides. 
	 * @throws UnsupportedEncodingException 
	 * 		Problème lors de l'accès au fichier de mots vides. 
	 */
	private void initStopWords() throws FileNotFoundException, UnsupportedEncodingException
	{	stopWords = new TreeSet<String>();
		if(Configuration.isFilteringStopWords())
			loadStopWords();
	}	
	
	/**
	 * Charge la liste de mots vides.
	 * 
	 * @throws FileNotFoundException
	 * 		Problème lors de l'accès au fichier de mots vides. 
	 * @throws UnsupportedEncodingException 
	 * 		Problème lors de l'accès au fichier de mots vides. 
	 */
	private void loadStopWords() throws FileNotFoundException, UnsupportedEncodingException
	{	//TODO méthode à compléter (TP5-ex7)
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
	{	// test de normalizeType
		//TODO méthode à compléter (TP1-ex6)
		Normalizer norm = new Normalizer();
		System.out.println(norm.normalizeType("StriNg") + ", " + norm.normalizeType("") + ", " + norm.normalizeType("sTrïng"));
		// test de normalizeTokens
		//TODO méthode à compléter (TP1-ex7)
		List<Token> tokens = new ArrayList<Token>();
		Tokenizer toknize = new Tokenizer();
		File document = new File("./Common/wp/001f1107-8e72-4250-8b83-ef02eeb4d4a4.txt");
		toknize.tokenizeDocument(document, 0, tokens);
		System.out.println(tokens);
		System.out.println("----------------------------------");
		norm.normalizeTokens(tokens);
		System.out.println(tokens);
		// test de loadStopWords
		//TODO méthode à compléter (TP5-ex7)
	}
}
