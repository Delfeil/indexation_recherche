package query;

import indexation.AbstractIndex;
import indexation.ArrayIndex;
import indexation.content.IndexEntry;
import indexation.content.Posting;
import indexation.processing.Normalizer;
import indexation.processing.Tokenizer;
import tools.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 * Objet capable de traiter une
 * requête de type sac-de-mots.
 */
public class RankingQueryEngine
{	/**
	 * Initialise ce moteur de requête avec
	 * l'index passé en paramètre, qui sera
	 * considéré comme index de référence
	 * lors de l'évaluation des requêtes
	 * reçues.
	 * 
	 * @param index
	 * 		Index de référence.
	 */
	public RankingQueryEngine(AbstractIndex index)
	{	this.index = index;
	}
	
	////////////////////////////////////////////////////
	//	TRAITEMENT GENERAL
	////////////////////////////////////////////////////
	/**
	 * Traite la requête passée en paramètre
	 * et renvoie la liste ordonnée des {@code k} documents 
	 * les plus pertinents, sous dorme d'objets {@link DocScore}.
	 * La valeur zéro pour {@code k} signifie qu'on veut tous les
	 * documents du corpus.
	 * 
	 * @param query
	 * 		Requête à traiter.
	 * @param k
	 * 		Nombre maximum de documents à renvoyer, ou zéro pour tous les documents.
	 * @return
	 * 		Liste ordonnée des documents sélectionnés, avec leurs scores.
	 */
	public List<DocScore> processQuery(String query, int k)
	{	List<DocScore> result = new ArrayList<DocScore>();
		//TODO méthode à compléter (TP6-ex11)
		Boolean scilence = Configuration.isScilence();
		if(!scilence) {
			System.out.println("Processing query \"" + query + "\" with RankingQueryEngine");
		}
		long start = System.currentTimeMillis();
		// List<List<Posting>> llp = new ArrayList<List<Posting>>();
		List<IndexEntry> lie = new ArrayList<IndexEntry>();
		this.splitQuery(query, lie);
		this.sortDocuments(lie, k, result);
		// result = this.processConjunctions(llp);
		long end = System.currentTimeMillis();
		if(!scilence) {
			System.out.println("Query processed, returned "+
				result.size()+" postings, duration="+(end-start)+" ms\n");
		}
		return result;
	}
	
	/**
	 * Tokénise et normalise la requête, de manière
	 * à obtenir une liste de termes. Ces termes
	 * sont ensuite traités pour récupérer les
	 * entrées correspondantes dans l'index, et
	 * surtout leurs listes de postings.
	 * 
	 * @param query
	 * 		Requête à traiter.
	 * @param result
	 * 		Liste résultat à compléter, qui doit contenir à
	 * 		la fin du traitement les entrées de l'index 
	 * 		correspondant aux termes obtenus après nettoyage 
	 * 		de la requête. 
	 */
	private void splitQuery(String query, List<IndexEntry> result)
	{	//TODO méthode à compléter (TP6-ex10)
		Boolean scilence = Configuration.isScilence();
		if(!scilence) {
			System.out.print("Normalizing:");
		}
			// on tokénize la requête
		Tokenizer tokenizer = this.index.getTokenizer();
		Normalizer normalizer = this.index.getNormalizer();
		List<String> tokensS = tokenizer.tokenizeString(query);
		for (String term : tokensS) {
				// la normalisation du type donne le terme (ou null)
			term = normalizer.normalizeType(term);
			if(term == null || term.equals("ET")) {
				continue;
			}
				// on récupère l'entrée associée au terme dans l'index
			IndexEntry entry = this.index.getEntry(term);
			int nb_postings = 0;
			if(entry != null) {
					// si pas dans l'index, on utilise une liste vide
					// sinon, on prend sa liste de postings
				List<Posting> postings = this.index.getEntry(term).getPostings();
				nb_postings = postings.size();
				result.add(entry);
			}
			if(!scilence) {
				System.out.print(" \"" + term + "\"(" + nb_postings + ")");
			}
		}
		if(!scilence) {
			System.out.println("");
		}

		//TODO méthode à modifier  (TP4-ex10)
	}
	
	////////////////////////////////////////////////////
	//	POIDS
	////////////////////////////////////////////////////
	/**
	 * Calcule la pondération log-fréquence
	 * associée à un terme dans un document.
	 * 
	 * @param posting
	 * 		Document à traiter, sous forme de posting.
	 * @return
	 * 		Poids correspondant au document.
	 */
	private float processWf(Posting posting)
	{	float result = 0;
		//TODO méthode à compléter (TP6-ex7)
		int tf = posting.getFrequency();
		if(tf >0) {
			result = 1 + (float) Math.log10(tf);
		}
		return result;
	}
	
	/**
	 * Calcule la fréquence de document inverse
	 * associée à un terme dans une collection.
	 * 
	 * @param entry
	 * 		Terme considéré, sous la forme d'une entrée d'index.
	 * @return
	 * 		Fréquence inverse correspondant.
	 */
	private float processIdf(IndexEntry entry)
	{	float result = 0;
		//TODO méthode à compléter (TP6-ex8)
		int D = this.index.getDocumentNumber();
		float df = entry.getFrequency();
		result = (float) Math.log10(D/df);
		return result;
	}
	
	////////////////////////////////////////////////////
	//	SCORES
	////////////////////////////////////////////////////
	/**
	 * Trie les documents en fonction de leur similarité avec
	 * la requête spécifiée, et ne garde que les {@code k} plus
	 * pertinents (ainsi que leurs scores). La valeur zéro pour
	 * {@code k} indique que l'on veut renvoyer la liste complète
	 * de tous les documents (toujours avec leur score).
	 * 
	 * @param queryEntries
	 * 		Entrées correspondant à la requête à traiter.
	 * @param k
	 * 		Nombre de documents désiré (ou zéro pour tous les documents).
	 * @param docScores
	 * 		DocIds et scores des {@code k} documents les plus pertinents.
	 */
	private void sortDocuments(List<IndexEntry> queryEntries, int k, List<DocScore> docScores)
	{	//TODO méthode à compléter (TP6-ex9)
		TreeSet<DocScore> orderedIds = new TreeSet<DocScore>();
		int docNbr = this.index.getDocumentNumber();
			// Score des documents (produits scalaire entre les doc et la requête)
		float scores[] = new float[docNbr];
		Arrays.fill(scores, 0);
			// Normes de document d
		float norms[] = new float[docNbr];
		Arrays.fill(norms, 0);
			// Norme de la requête q
		float queryNorm = 0;
		for (IndexEntry entry : queryEntries) {
				// On traite chaques term t de la requête q
			float idf = this.processIdf(entry);
			float stq = idf;
				// Mise à jours de la norme de la requête
			queryNorm = queryNorm + (float)Math.pow(stq, 2);
				// Liste des postings associés au terme t
			List<Posting> postings = entry.getPostings();
			for (Posting posting : postings) {
					// On traite chaque document d de la liste des postings
					// On calcul le score individuel du terme pour le document
				float std = processWf(posting) * idf;
				int docId = posting.getDocId();
					// Mise à jours du score du document d (produit scalaire entre d et q)
				scores[docId] = scores[docId] + stq*std;
				norms[docId] = norms[docId] + (float)Math.pow(std, 2);
			}
		}

			// On finalise le calcul des normes des documents d
		for (int i = 0; i < norms.length; i++) {
			norms[i] = (float)Math.sqrt(norms[i]);
		}
			// On finalise le calcul de la même manière pour la norme de la requête q
		queryNorm = (float)Math.sqrt(queryNorm);

			// on termine le calcul des scores et on ordonne les documents
		for(int i=0;i<scores.length;i++){
			if(norms[i]==0) {
				scores[i] = 0;
			} else {
				scores[i] = scores[i] / (norms[i]*queryNorm);
			}
			DocScore docScore = new DocScore(i, scores[i]);
			orderedIds.add(docScore);
		}
		
			// on garde tout si k vaut zéro
		if(k==0) {
			k = orderedIds.size();
		}
			// On finalise le calcul des scores des documents
		Iterator<DocScore> it = orderedIds.descendingIterator();
		int i=0;
		while (i<k && it.hasNext()) {
			DocScore dScore = it.next();
			docScores.add(dScore);
			i++;
		}
	
	}
	
	////////////////////////////////////////////////////
	//	INDEX
	////////////////////////////////////////////////////
	/** Index de référence */
	private AbstractIndex index;
	
	/**
	 * Renvoie l'index associé à ce moteur.
	 * 
	 * @return
	 * 		Index associé à ce moteur.
	 */
	public AbstractIndex getIndex()
	{	return index;
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
	{	// test de processWf
		//TODO méthode à compléter (TP6-ex7)
		Configuration.setCorpusName("wp_test");
		AbstractIndex index = AbstractIndex.read();
		RankingQueryEngine rEngine = new RankingQueryEngine(index);

		Posting p1 = new Posting(10, 4);
		Posting p2 = new Posting(10, 0);
		

		System.out.println(rEngine.processWf(p1) + ", " + rEngine.processWf(p2));

		// test de processIdf
		//TODO méthode à compléter (TP6-ex8)
		IndexEntry iEntry = new IndexEntry("term");
		iEntry.addPosting(p1);
		iEntry.addPosting(p2);

		System.out.println(rEngine.processIdf(iEntry));

		// test de sortDocuments
		//TODO méthode à compléter (TP6-ex9)
		AndQueryEngine engine = new AndQueryEngine(index);
		List<DocScore> results = rEngine.processQuery("recherche ET INFORMATION ET Web", 10);
		System.out.println("----------TP6 ex9-----------");
		System.out.println(results);
		// rEngine.sortDocuments(queryEntries, 10, );

		// test de splitQuery
		//TODO méthode à compléter (TP6-ex10)
		
		// test de processQuery
		//TODO méthode à compléter (TP6-ex11)
	}
}
