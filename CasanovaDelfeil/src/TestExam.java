import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import tools.Configuration;
import indexation.AbstractIndex;
import indexation.AbstractIndex.LexiconType;
import indexation.AbstractIndex.TokenListType;

/**
 * Classe permettant de tester l'indexation et les requêtes.
 */
public class TestExam
{	/**
	 * Méthode principale.
	 * 
	 * @param args
	 * 		Pas utilisé.
	 * 
	 * @throws Exception 
	 * 		Problème lors du traitement
	 */
	public static void main(String[] args) throws Exception
	{	// configuration de l'outil
		Configuration.setCorpusName("wp_test");
//		Configuration.setCorpusName("wp");
//		Configuration.setCorpusName("springer");
		Configuration.setStemmingTokens(false);
		Configuration.setFilteringStopWords(true);
		
		// processus d'indexation
		testIndexation();
		
		// interrogation de l'index
//		testQuery();
	}
	
	////////////////////////////////////////////////////
	//	INDEXATION
	////////////////////////////////////////////////////
	/**
	 * Teste les classes permettant de créer le fichier inverse.
	 * 
	 * @throws IOException 
	 * 		Problème lors d'un accès fichier.
	 * @throws SAXException
	 * 		Problème lors du chargement du stemmeur.
	 * @throws ParserConfigurationException 
	 * 		Problème lors du chargement du stemmeur.
	 */
	private static void testIndexation() throws IOException, ParserConfigurationException, SAXException
	{	// construction de l'index
		AbstractIndex index = AbstractIndex.indexCorpus(TokenListType.LINKED, LexiconType.TREE);
		
		// enregistrement de l'index
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
	{	// TODO ex6
	}
}
