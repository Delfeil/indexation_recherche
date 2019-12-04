import java.io.IOException;

import indexation.AbstractIndex;
import indexation.AbstractIndex.LexiconType;
import indexation.AbstractIndex.TokenListType;
import tools.Configuration;

/**
 * Classe permettant de tester
 * notre indexation.
 */
public class Test1
{	/**
	 * Méthode principale.
	 * 
	 * @param args
	 * 		Pas utilisé.
	 * 
	 * @throws IOException 
	 * 		Problème lors d'un accès fichier.
	 * @throws ClassNotFoundException 
	 * 		Problème lors de la lecture de l'index
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException
	{	// configuration de l'index
		//TODO méthode à compléter (TP2-ex5)
		Configuration.setCorpusName("wp");
		//TODO méthode à compléter (TP4-ex14)
		//TODO méthode à compléter (TP5-ex10)
		
		// test de l'indexation
		//TODO méthode à compléter (TP2-ex5)
		Test1.testIndexation();
		
		// test du chargement d'index
		//TODO méthode à compléter (TP2-ex11)
		AbstractIndex index_loaded = AbstractIndex.read();
		
		// test du traitement de requêtes
		//TODO méthode à compléter (TP3-ex6)
		
		// test de l'évaluation de performance
		//TODO méthode à compléter (TP4-ex9)
	}
	
	////////////////////////////////////////////////////
	//	INDEXATION
	////////////////////////////////////////////////////
	/**
	 * Teste les classes permettant de créer le fichier inverse.
	 * 
	 * @throws IOException 
	 * 		Problème lors d'un accès fichier.
	 */
	private static void testIndexation() throws IOException
	{	//TODO méthode à compléter (TP2-ex5)
		// AbstractIndex index = AbstractIndex.indexCorpus(TokenListType.LINKED, LexiconType.ARRAY);
		// AbstractIndex index = AbstractIndex.indexCorpus(TokenListType.ARRAY, LexiconType.ARRAY);
		AbstractIndex index = AbstractIndex.indexCorpus(TokenListType.LINKED, LexiconType.HASH);
		// AbstractIndex index = AbstractIndex.indexCorpus(TokenListType.ARRAY, LexiconType.HASH);
		// AbstractIndex index = AbstractIndex.indexCorpus(TokenListType.LINKED, LexiconType.TREE);
		// AbstractIndex index = AbstractIndex.indexCorpus(TokenListType.ARRAY, LexiconType.TREE);
		
		// index.print();

		//TODO méthode à compléter (TP2-ex10)
		index.write();
	}
	
	////////////////////////////////////////////////////
	//	REQUÊTES
	////////////////////////////////////////////////////
	/**
	 * Teste les classes permettant de traiter les requêtes.
	 * 
	 * @throws IOException 
	 * 		Problème lors d'un accès fichier.
	 * @throws ClassNotFoundException 
	 * 		Problème lors de la lecture de l'index
	 */
	private static void testQuery() throws IOException, ClassNotFoundException
	{	//TODO méthode à compléter (TP3-ex6)
		//TODO méthode à compléter (TP3-ex12)
		
		//TODO méthode à compléter (TP5-ex11)
		
		//TODO méthode à compléter (TP6-ex13)
	}
	
	////////////////////////////////////////////////////
	//	ÉVALUATION
	////////////////////////////////////////////////////
	/**
	 * Calcule les performances du moteur de recherche,
	 * et les affiche dans la console.
	 */
	private static void testEvaluation()
	{	//TODO méthode à compléter (TP4-ex9)
		//TODO méthode à compléter (TP6-ex16)
	}
}
