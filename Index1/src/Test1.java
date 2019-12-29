import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import indexation.AbstractIndex;
import indexation.AbstractIndex.LexiconType;
import indexation.AbstractIndex.TokenListType;
import indexation.content.Posting;
import performance.BooleanEvaluator;
import performance.GroundTruth;
import performance.AbstractEvaluator.MeasureName;
import query.AndQueryEngine;
import tools.Configuration;
import tools.FileTools;

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
		// Configuration.setCorpusName("wp");
		Configuration.setCorpusName("springer");
		Configuration.setStemmingTokens(true);
		//TODO méthode à compléter (TP4-ex15)
		//TODO méthode à compléter (TP5-ex10)
		
		// test de l'indexation
		//TODO méthode à compléter (TP2-ex5)
		Test1.testIndexation();
		
		// test du chargement d'index
		//TODO méthode à compléter (TP2-ex11)
		// AbstractIndex index_loaded = AbstractIndex.read();
		
		// test du traitement de requêtes
		//TODO méthode à compléter (TP3-ex6)
		// Test1.testQuery();
		// Test1.testQueryies();

		// test de l'évaluation de performance
		//TODO méthode à compléter (TP4-ex9)
		Test1.testEvaluation();
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
		Configuration.setCorpusName("wp");
		AbstractIndex index = AbstractIndex.read();
		AndQueryEngine aqe = new AndQueryEngine(index);

		List<String> queryes = new ArrayList<String>();
		queryes.add("recherche");
		queryes.add("recherche ET INFORMATION");
		queryes.add("recherche ET INFORMATION ET Web");
		List<Posting> rq1 = aqe.processQuery(queryes.get(0));
		System.out.println(rq1+"\nFiles:\n"+FileTools.getFileNamesFromPostings(rq1));
		List<Posting> rq2 = aqe.processQuery(queryes.get(1));
		System.out.println(rq2+"\nFiles:\n"+FileTools.getFileNamesFromPostings(rq2));
		List<Posting> rq3 = aqe.processQuery(queryes.get(2));
		System.out.println(rq3+"\nFiles:\n"+FileTools.getFileNamesFromPostings(rq3));
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
		try {
			AbstractIndex index = AbstractIndex.read();
			AndQueryEngine aqe = new AndQueryEngine(index);
			BooleanEvaluator evaluator = new BooleanEvaluator();
			GroundTruth gTruth = evaluator.getGroundTruth();
			List<String> queries =  gTruth.getQueries();
			List<Map<MeasureName,Float>> evaluations = evaluator.evaluateEngine(aqe);

			//Gestion des arrondis
			DecimalFormat df = new DecimalFormat("0.00");

			System.out.println(MeasureName.PRECISION + "\t" + MeasureName.RECALL + "\t" + MeasureName.F_MEASURE);
			for (int i = 0; i < evaluations.size(); i++) {
				Map<MeasureName,Float> evaluation = evaluations.get(i);
				if(i == queries.size()) {
					System.out.println("––––––––––––––––––––––––––––––––––––––-");
					System.out.println(df.format(evaluation.get(MeasureName.PRECISION)) + "\t" +
						df.format(evaluation.get(MeasureName.RECALL)) + "\t" + df.format(evaluation.get(MeasureName.F_MEASURE))
					);
				} else {
					String query = queries.get(i); 
					System.out.println(df.format(evaluation.get(MeasureName.PRECISION)) + "\t" +
						df.format(evaluation.get(MeasureName.RECALL)) + "\t" + df.format(evaluation.get(MeasureName.F_MEASURE)) + "\t" + query
					);
				}
			}
		} catch (Exception e) {
			//TODO: handle exception
			e.printStackTrace();
		}
		//TODO méthode à compléter (TP6-ex16)
	}

	private static void testQueryies() throws IOException {
		Configuration.setCorpusName("wp");
		Configuration.setScilence(true);
		List<AbstractIndex> lIndexs = new ArrayList<AbstractIndex>();
		System.out.println("Build Indexes");
		System.out.println("Construction ArrayIndex");
		long start = System.currentTimeMillis();
		lIndexs.add(AbstractIndex.indexCorpus(TokenListType.LINKED, LexiconType.ARRAY));
		long end = System.currentTimeMillis();
		System.out.println("ArrayIndex construit: "+(end-start)+"ms");
		
		System.out.println("Construction HashIndex");
		start = System.currentTimeMillis();
		lIndexs.add(AbstractIndex.indexCorpus(TokenListType.LINKED, LexiconType.HASH));
		end = System.currentTimeMillis();
		System.out.println("HashIndex construit: "+(end-start)+"ms");
		
		System.out.println("Construction TreeIndex");
		start = System.currentTimeMillis();
		lIndexs.add(AbstractIndex.indexCorpus(TokenListType.LINKED, LexiconType.TREE));
		end = System.currentTimeMillis();
		System.out.println("TreeIndex construit: "+(end-start)+"ms");
		
		String[] queries2 = {"recherche d'information", "modèle de travail", "microsoft windows", "contrôle des connaissances", "berners-lee", "système de notation", "internet mondial", "points forts", "moteur de recherche", "réseaux sociaux"};

		System.out.println("Testing queries");
		Iterator<AbstractIndex> iIndex = lIndexs.iterator();
		AndQueryEngine aqe = null;
		List<Posting> res = null;
		Boolean silent = true;
		while (iIndex.hasNext()) {
			AbstractIndex index = iIndex.next();
			aqe = new AndQueryEngine(index);
			start = System.currentTimeMillis();
			for (int i = 0; i < 1000; i++) {
				for (String query : queries2) {
					res = aqe.processQuery(query);
				}
			}
			end = System.currentTimeMillis();
	
			System.out.println("Test:");
			System.out.println("Type d'index: " + index.getClass().getName());
			System.out.println("Durée: " + (end-start) + " ms");
		}
	}
}
