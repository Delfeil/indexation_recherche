package tools;

/**
 * Permet de convertir les noms de fichiers en
 * postings, et inversement.
 */
public class Configuration
{	
	////////////////////////////////////////////////////
	//	CORPUS
	////////////////////////////////////////////////////
	/** Nom du corpus traité (wp, wp_test, springer...) */
	private static String corpusName;
	
	/**
	 * Permet à l'utilisateur de spécifier le nom du
	 * corpus à traiter par l'index.
	 * 
	 * @param corpusName
	 * 		Noms du corpus : {@code wp}, {@code wp_test}
	 * 		 ou {@code springer}.
	 */
	public static void setCorpusName(String corpusName)
	{	Configuration.corpusName = corpusName;
	}
	
	/**
	 * Renvoie le nom du corpus à traiter par l'index.
	 * 
	 * @return
	 * 		Noms du corpus : {@code wp}, {@code wp_test}
	 * 		 ou {@code springer}.
	 */
	public static String  getCorpusName()
	{	return corpusName;
	}
	
	////////////////////////////////////////////////////
	//	MOTS VIDES
	////////////////////////////////////////////////////
	/** Indique s'il faut ou pas filtrer les mots-vides */
	private static boolean filteringStopWords = false;
	
	/**
	 * Permet d'indiquer si les mots-vides doivent être
	 * filtrés ({@code true}) ou pas ({@code false}).
	 * 
	 * @param filterinStopWords
	 * 		Filtrer les mots-vides ({@code true}) ou pas ({@code false}).
	 */
	public static void setFilteringStopWords(boolean filterinStopWords)
	{	Configuration.filteringStopWords = filterinStopWords;
	}
	
	/**
	 * Indique si les mots-vides doivent être
	 * filtrés ({@code true}) ou pas ({@code false}).
	 * 
	 * @return
	 * 		Filtrer les mots-vides ({@code true}) ou pas ({@code false}).
	 */
	public static boolean isFilteringStopWords()
	{	return filteringStopWords;
	}
	
	////////////////////////////////////////////////////
	//	RACINISATEUR
	////////////////////////////////////////////////////
	/** Indique s'il faut ou pas raciniser les tokens */
	private static boolean stemmingTokens = false;
	
	/**
	 * Permet d'indiquer si les tokens doivent être
	 * racinisés ({@code true}) ou pas ({@code false}).
	 * 
	 * @param stemmingTokens
	 * 		Raciniser les tokens ({@code true}) ou pas ({@code false}).
	 */
	public static void setStemmingTokens(boolean stemmingTokens)
	{	Configuration.stemmingTokens = stemmingTokens;
	}
	
	/**
	 * Indique si les tokens doivent être
	 * racinisés ({@code true}) ou pas ({@code false}).
	 * 
	 * @return
	 * 		Raciniser les tokens ({@code true}) ou pas ({@code false}).
	 */
	public static boolean isStemmingTokens()
	{	return stemmingTokens;
	}
	
	////////////////////////////////////////////////////
	//	SCORES
	////////////////////////////////////////////////////
	/** Indique s'il faut ou pas calculer les scores */
	private static boolean computingScores = false;
	
	/**
	 * Permet d'indiquer si les scores doivent être
	 * calculés ({@code true}) ou pas ({@code false}).
	 * 
	 * @param computingScores
	 * 		Calculer les scores ({@code true}) ou pas ({@code false}).
	 */
	public static void setComputingScores(boolean computingScores)
	{	Configuration.computingScores = computingScores;
	}
	
	/**
	 * Indique si les scores doivent être
	 * calculés ({@code true}) ou pas ({@code false}).
	 * 
	 * @return
	 * 		Calculer les scores ({@code true}) ou pas ({@code false}).
	 */
	public static boolean isComputingScores()
	{	return computingScores;
	}
}
