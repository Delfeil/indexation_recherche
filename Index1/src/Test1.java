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
import performance.RankingEvaluator;
import performance.AbstractEvaluator.MeasureName;
import query.AndQueryEngine;
import query.DocScore;
import query.RankingQueryEngine;
import tools.Configuration;
import tools.FileTools;
import tools.TermCounter;

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
		// Configuration.setCorpusName("springer");
		//TODO méthode à compléter (TP4-ex15)
		// Configuration.setStemmingTokens(true);
		//TODO méthode à compléter (TP5-ex10)
		// Configuration.setFilteringStopWords(true);
		
		// test de l'indexation
		//TODO méthode à compléter (TP2-ex5)
		// Test1.testIndexation();
		// TermCounter.processCorpus();
		
		
		// test du chargement d'index
		//TODO méthode à compléter (TP2-ex11)
		// AbstractIndex index_loaded = AbstractIndex.read();
		// System.out.println(index_loaded);
		// System.out.println(index_loaded.getEntry("the"));
		
		// test du traitement de requêtes
		//TODO méthode à compléter (TP3-ex6)
		Test1.testQuery();
		// Test1.testQueryies();

		// test de l'évaluation de performance
		//TODO méthode à compléter (TP4-ex9)
		// Test1.testEvaluation();
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
		// AbstractIndex index = AbstractIndex.indexCorpus(TokenListType.LINKED, LexiconType.HASH);
		AbstractIndex index = AbstractIndex.indexCorpus(TokenListType.ARRAY, LexiconType.HASH);
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
		/*
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
		*/
		//TODO méthode à compléter (TP3-ex12)
		
		//TODO méthode à compléter (TP5-ex11)
		
		Configuration.setCorpusName("springer");
		System.out.println("\n--------------Evaluation springer-------------\n");
		Test1.testEvaluation();
		Configuration.setFilteringStopWords(true);
		System.out.println("\n--------------Evaluation springer_stopwords-------------\n");
		Test1.testEvaluation();
		Configuration.setFilteringStopWords(false);
		Configuration.setStemmingTokens(true);;
		System.out.println("\n--------------Evaluation springer_stem-------------\n");
		Test1.testEvaluation();
		Configuration.setFilteringStopWords(true);
		System.out.println("\n--------------Evaluation springer_stem_stopwords-------------\n");
		Test1.testEvaluation();
		
		/*
		//TODO méthode à compléter (TP6-ex13)
		Configuration.setCorpusName("wp");
		AbstractIndex index = AbstractIndex.read();
		RankingQueryEngine rqe = new RankingQueryEngine(index);
		// AndQueryEngine aqe = new AndQueryEngine(index);
		int k = 5;
		List<String> queryes = new ArrayList<String>();
		queryes.add("roman");
		queryes.add("recherche d’information sur le Web");
		queryes.add("panneaux solaires électricité");
		List<DocScore> result1 = rqe.processQuery(queryes.get(0), k);
		System.out.println(result1+"\nFiles:\n"+FileTools.getFileNamesFromDocScores(result1) + "\n");
		List<DocScore> result2 = rqe.processQuery(queryes.get(1), k);
		System.out.println(result2+"\nFiles:\n"+FileTools.getFileNamesFromDocScores(result2) + "\n");
		List<DocScore> result3 = rqe.processQuery(queryes.get(2), k);
		System.out.println(result3+"\nFiles:\n"+FileTools.getFileNamesFromDocScores(result3) + "\n");
		*/
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
			List<List<Map<MeasureName,Float>>> lEvaluations = new ArrayList<List<Map<MeasureName,Float>>>();
			// List<Map<MeasureName,Float>> aqeEvaluations = evaluator.evaluateEngine(aqe);
			lEvaluations.add(evaluator.evaluateEngine(aqe));
			RankingQueryEngine rqe = new RankingQueryEngine(index);
			RankingEvaluator rEvaluator = new RankingEvaluator();
			// List<Map<MeasureName,Float>> rEvaluations = rEvaluator.evaluateEngine(rqe);
			lEvaluations.add(rEvaluator.evaluateEngine(rqe));
			
			//Gestion des arrondis
			DecimalFormat df = new DecimalFormat("0.00");
			int j=0;
			for (List<Map<MeasureName,Float>> evaluations : lEvaluations) {
				if(j==0) {
					System.out.println("\n----------------Evaluation AndQueryEngine------------");
				} else {
					System.out.println("\n----------------Evaluation RankingQueryEngine------------");
				}
				System.out.println(MeasureName.PRECISION + "\t" + MeasureName.RECALL + "\t" + MeasureName.F_MEASURE);
				j++;
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
			System.out.println("Durée: " + (end-start) + " ms\n");
		}
	}
}
