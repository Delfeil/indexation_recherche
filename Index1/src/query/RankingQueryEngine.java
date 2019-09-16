package query;

import indexation.AbstractIndex;
import indexation.content.IndexEntry;
import indexation.content.Posting;

import java.util.List;

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
	{	List<DocScore> result = null;
		//TODO méthode à compléter (TP6-ex11)
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
		
		// test de processIdf
		//TODO méthode à compléter (TP6-ex8)
		
		// test de sortDocuments
		//TODO méthode à compléter (TP6-ex9)
		
		// test de splitQuery
		//TODO méthode à compléter (TP6-ex10)
		
		// test de processQuery
		//TODO méthode à compléter (TP6-ex11)
	}
}
