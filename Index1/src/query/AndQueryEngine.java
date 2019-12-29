package query;

import indexation.AbstractIndex;
import indexation.content.IndexEntry;
import indexation.content.Posting;
import indexation.processing.Normalizer;
import indexation.processing.Tokenizer;
import tools.Configuration;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Objet capable de traiter une
 * requête booléenne sur un index.
 */
public class AndQueryEngine
{	/**
	 * Initialise ce moteur de recherche avec
	 * l'index passé en paramètre, qui sera
	 * considéré comme index de référence
	 * lors de l'évaluation des requêtes
	 * reçues.
	 * 
	 * @param index
	 * 		Index de référence.
	 */
	public AndQueryEngine(AbstractIndex index)
	{	this.index = index;
	}
	
	////////////////////////////////////////////////////
	//	TRAITEMENT GENERAL
	////////////////////////////////////////////////////
	/**
	 * Traite la requête passée en paramètre
	 * et renvoie la liste des documents concernés.
	 * 
	 * @param query
	 * 		Requête à traiter.
	 * @return
	 * 		Liste des documents concernés.
	 */
	public List<Posting> processQuery(String query)
	{	List<Posting> result = null;
		//TODO méthode à compléter (TP3-ex5)
		Boolean scilence = Configuration.isScilence();
		if(!scilence) {
			System.out.println("Processing query \"" + query + "\"");
		}
		long start = System.currentTimeMillis();
		List<List<Posting>> llp = new ArrayList<List<Posting>>();
		this.splitQuery(query, llp);
		result = this.processConjunctions(llp);
		long end = System.currentTimeMillis();
		if(!scilence) {
			System.out.println("Query processed, returned "+
				result.size()+" postings, duration="+(end-start)+" ms");
		}
		return result;
	}
	
	/**
	 * Comparateur traitant deux listes de postings.
	 * On utilise simplement leurs longueurs.
	 */
	private static final Comparator<List<Posting>> COMPARATOR = new Comparator<List<Posting>>()
	{	@Override
		public int compare(List<Posting> l1, List<Posting> l2)
		{	int result = -1;
			//TODO méthode à compléter  (TP3-ex3)
			Integer ll1 = l1.size();
			Integer ll2 = l2.size();
			result = ll1.compareTo(ll2);
			return result;
		}
	};
	
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
	 * 		la fin du traitement les postings de l'index 
	 * 		correspondant aux termes obtenus après nettoyage 
	 * 		de la requête. 
	 */
	private void splitQuery(String query, List<List<Posting>> result)
	{	//TODO méthode à compléter (TP3-ex1)
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
			if(entry == null) {
					// si pas dans l'index, on utilise une liste vide
				result.add(new ArrayList<Posting>());
			} else {
					// sinon, on prend sa liste de postings
				List<Posting> postings = this.index.getEntry(term).getPostings();
				result.add(postings);
				nb_postings = postings.size();
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
	//	CONJONCTIONS
	////////////////////////////////////////////////////
	/**
	 * Combine les deux listes de postings passées
	 * en paramètre en utilisant l'opérateur ET.
	 * 
	 * @param list1
	 * 		Première liste de postings.
	 * @param list2
	 * 		Seconde liste de postings.
	 * @return
	 * 		Le résultat de ET sur ces deux listes.
	 */
	private List<Posting> processConjunction(List<Posting> list1, List<Posting> list2)
	{	List<Posting> result = new ArrayList<Posting>();
		//TODO méthode à compléter (TP3-ex2)
		
		ListIterator<Posting> it1 = list1.listIterator();
		ListIterator<Posting> it2 = list2.listIterator();
		Posting p1, p2 = null;
		while(it1.hasNext() && it2.hasNext()) {
			p1 = it1.next();
			p2 = it2.next();
			if(p1.equals(p2)) {
				result.add(p1);
			} else {
				// On recule l'itérator avec le plus gros docId
				int id1 = p1.getDocId();
				int id2 = p2.getDocId();
				if(id1 < id2) {
					it2.previous();
				} else {
					it1.previous();
				}
			}
		}
		//TODO méthode à modifier  (TP4-ex12)
		System.out.println("Processing conjunction: (" + list1.size() + ") AND (" + list2.size() + ") >> (" + result.size() + ")");
		return result;
	}

	/**
	 * Traite une conjonction de plus de
	 * deux termes.
	 * 
	 * @param lists
	 * 		Liste de listes de postings de l'index, correspondant aux termes à traiter.
	 * @return
	 * 		Intersection de toutes les listes de postings.
	 */
	private List<Posting> processConjunctions(List<List<Posting>> lists)
	// {	List<Posting> result = lists.get(0);
	{	List<Posting> result = new ArrayList<Posting>();
		Boolean scilence = Configuration.isScilence();
		//TODO méthode à compléter (TP3-ex4)
		lists.sort(COMPARATOR);
		System.out.print("Ordering posting list:");
		for (int i = 0; i < lists.size(); i++) {
			System.out.print(" ("+lists.get(i).size() + ")");
		}
		System.out.println("");
		Iterator<List<Posting>> itlp = lists.iterator();
		if(itlp.hasNext()) {
			result = itlp.next();
		}
		while(itlp.hasNext()) {
			if(result.isEmpty())
				return result;
			result = this.processConjunction(result, itlp.next());
		}
		//TODO méthode à modifier  (TP4-ex11)
		return result;
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
	{	// test de splitQuery
		// TODO méthode à compléter (TP3-ex1)

		Configuration.setCorpusName("wp_test");
		AbstractIndex index = AbstractIndex.read();

		AndQueryEngine aqe = new AndQueryEngine(index);

		List<List<Posting>> postings = new ArrayList<List<Posting>>();

		String query = "recherche ET INFORMATION ET Web";

		aqe.splitQuery(query, postings);
		System.out.println(postings);
		
		// test de processConjunction
		// TODO méthode à compléter (TP3-ex2)
		System.out.println(aqe.processConjunction(postings.get(0), postings.get(1)));
		
		
		// test de COMPARATOR
		// TODO méthode à compléter (TP3-ex3)
		System.out.println(
			COMPARATOR.compare(postings.get(0), postings.get(0))
		);
		
		System.out.println(
			COMPARATOR.compare(postings.get(0), postings.get(1))
		);

		System.out.println(
			COMPARATOR.compare(postings.get(1), new ArrayList<Posting>())
		);

		// System.out.println(
		// 	COMPARATOR.compare(null, postings.get(0))
		// );

		// test de processConjunctions
		// TODO méthode à compléter (TP3-ex4)
		System.out.println(aqe.processConjunctions(postings));
		
		// test de processQuery
		// TODO méthode à compléter (TP3-ex5)
		System.out.println(aqe.processQuery(query));
	}
}
