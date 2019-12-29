package tools;

import indexation.content.Posting;
import indexation.content.Token;
import indexation.processing.Tokenizer;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import query.DocScore;

/**
 * Permet de convertir les noms de fichiers en
 * postings, et inversement.
 */
public class FileTools
{	
	////////////////////////////////////////////////////
	//	CHEMINS
	////////////////////////////////////////////////////
	/**
	 * Renvoie le chemin vers le dossier racine du corpus.
	 * 
	 * @return
	 * 		Chemin du dossier racine du corpus.
	 */
	public static String getCorpusFolder()
	{	String corpusName = Configuration.getCorpusName();
		// String result = ".." + File.separator + "Common" + File.separator + corpusName;
		String result = "." + File.separator + "Common" + File.separator + corpusName;
		return result;
	}
	
	/**
	 * Renvoie le chemin vers le fichier (XML) de la vérité terrain.
	 * 
	 * @return
	 * 		Chemin du fichier (XML) de la vérité terrain.
	 */
	public static String getGroundTruthFile()
	{	String corpusName = Configuration.getCorpusName();
		String result = "." + File.separator + "Common" + File.separator + corpusName + "_reference.xml";
		return result;
	}
	
	/**
	 * Renvoie le chemin vers le fichier contenant l'index.
	 * 
	 * @return
	 * 		Chemin du fichier (binaire) de l'index.
	 */
	public static String getIndexFile()
	{	String corpusName = Configuration.getCorpusName();
		String options = "";
		if(Configuration.isFilteringStopWords())
			options = options + "_filter";
		if(Configuration.isStemmingTokens())
			options = options + "_stem";
		String result = "data" + File.separator + corpusName+ options + "_index.data";
		return result;
	}
	
	/**
	 * Renvoie le chemin vers le fichier (CSV) de décompte des termes.
	 * 
	 * @return
	 * 		Chemin du fichier (CSV) de décompte.
	 */
	public static String getTermCountFile()
	{	String corpusName = Configuration.getCorpusName();
		String options = "";
		if(Configuration.isStemmingTokens())
			options = options + "_stem";
		String result = "data" + File.separator + corpusName + options + "_termcount.csv";
		return result;
	}
	
	/**
	 * Renvoie le chemin vers le fichier (texte) des mots-vides.
	 * 
	 * @return
	 * 		Chemin du fichier (texte) des mots-vides.
	 */
	public static String getStopWordsFile()
	{	String corpusName = Configuration.getCorpusName();
		String options = "";
		if(Configuration.isStemmingTokens())
			options = options + "_stem";
		String result = "data" + File.separator + corpusName + options + "_stopwords.txt";
		return result;
	}
	
	/**
	 * Renvoie le chemin vers le fichier (texte) dans lequel
	 * on enregistre les mesures de performance.
	 * 
	 * @return
	 * 		Chemin du fichier (texte) des performances.
	 */
	public static String getPerformanceFile()
	{	String corpusName = Configuration.getCorpusName();
		String options = "";
		if(Configuration.isFilteringStopWords())
			options = options + "_filter";
		if(Configuration.isStemmingTokens())
			options = options + "_stem";
		if(Configuration.isComputingScores())
			options = options + "_score";
		String result = "data" + File.separator + corpusName + options + "_performance.txt";
		return result;
	}
	
	////////////////////////////////////////////////////
	//	CONVERSION
	////////////////////////////////////////////////////
	/**
	 * Renvoie la liste des noms de fichier correspondant
	 * aux postings passés en paramètres.
	 * 
	 * @param postings
	 * 		Liste de postings.
	 * @return
	 * 		Liste de noms de fichiers.
	 */
	public static List<String> getFileNamesFromPostings(List<Posting> postings)
	{	List<String> result = null;
		//TODO méthode à compléter  (TP2-ex6)
		String folder_name = FileTools.getCorpusFolder();
		File f = new File(folder_name);
		String[] files = f.list();
		Arrays.sort(files);
		if(files.length > 0 && postings != null && postings.size() >0)
			result = new ArrayList<String>();
		for (Posting posting : postings) {
			result.add(files[posting.getDocId()]);
		}
		return result;
	}
	
	/**
	 * Renvoie la liste des noms de fichier correspondant
	 * aux objets {@code DocScore} passés en paramètres.
	 * 
	 * @param docScores
	 * 		Liste d'objets {@code DocScore}.
	 * @return
	 * 		Liste de noms de fichiers.
	 */
	public static List<String> getFileNamesFromDocScores(List<DocScore> docScores)
	{	List<String> result = null;
		//TODO méthode à compléter  (TP6-ex12)
		return result;
	}
	
	/**
	 * Renvoie la liste des postings correspondant
	 * aux noms de fichier passés en paramètres.
	 * 
	 * @param fileNames
	 * 		Liste de noms de fichiers.
	 * @return
	 * 		Liste de postings.
	 */
	public static List<Posting> getPostingsFromFileNames(List<String> fileNames)
	{	List<Posting> result = new ArrayList<Posting>();
		//TODO méthode à compléter  (TP4-ex1)
		String folder_name = FileTools.getCorpusFolder();
		File f = new File(folder_name);
		String[] files = f.list();
		List<String> filesList = new ArrayList<String>(Arrays.asList(files));
		Collections.sort(filesList);
		
		for (String file_name : fileNames) {
			int docId = filesList.indexOf(file_name);
			result.add(new Posting(docId));
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
	{	// test de getFileNamesFromPostings
		//TODO méthode à compléter  (TP2-ex6)
		Configuration.setCorpusName("wp");
		List<Posting> postings = new ArrayList<Posting>();
		postings.add(new Posting(1));
		postings.add(new Posting(2));
		postings.add(new Posting(3));
		List<String> list_files = FileTools.getFileNamesFromPostings(postings);
		System.out.println(list_files);
		// test de getPostingsFromFileNames
		//TODO méthode à compléter  (TP4-ex1)
		List<String> files = new ArrayList<String>();
		files.add("0a0ff29e-66a2-4cd5-844f-170267e3d493.txt");
		files.add("0a81916b-0849-4084-b6e4-d5e69c73ad1d.txt");
		files.add("0ad87a98-842c-4bbf-9b42-f224ff3d0e55.txt");
		System.out.println(FileTools.getPostingsFromFileNames(files));
		
		// test de getFileNamesFromDocScores
		//TODO méthode à compléter  (TP6-ex12)
	}
}
