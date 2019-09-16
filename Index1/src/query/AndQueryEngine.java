package query;

import indexation.AbstractIndex;
import indexation.content.Posting;

import java.util.Comparator;
import java.util.List;

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
	{	List<Posting> result = null;
		//TODO méthode à compléter (TP3-ex2)
		//TODO méthode à modifier  (TP4-ex12)
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
	{	List<Posting> result = null;
		//TODO méthode à compléter (TP3-ex4)
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
		
		// test de processConjunction
		// TODO méthode à compléter (TP3-ex2)
		
		// test de COMPARATOR
		// TODO méthode à compléter (TP3-ex3)
		
		// test de processConjunctions
		// TODO méthode à compléter (TP3-ex4)
		
		// test de processQuery
		// TODO méthode à compléter (TP3-ex5)
	}
}
