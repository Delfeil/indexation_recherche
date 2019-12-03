# Indexation et recherche d'information

## Fiches code et fichiers

### Composition d'un index:


- __Token__: chaine de caractère dans un document 

    __mot__ (__type__) dans le texte + le __numérot du document__ (__doc_id__) dans lequel il est situé

    Classe ./src/indexation/content/Token.java
    
- __Tokenizer__: Transforme Une String ou tout un ensemble de documents 

    ./src/indexation/processing/Tokenizer

- __Normalizer__: Normalise une liste de Token (ou une String)
  
  Supprime les signes diacritiques et en les passant en minuscules.

  Index1/src/indexation/processing/Normalizer.java

- __Postings__: Correspond au numérot du document contenant un token 

    ./src/indexation/content/Posting.java

- __IndexEntry__: représent une entrée dans un index, composé d'un token une liste de postings etla fréquence d'apparition de ce toiken dans le corpus

    1 Token -> liste de postings

    /indexation/content/IndexEntry.java

- __AbstractIndex__: Classe abstraite représentatn un Index inverse

    Index Inverse: Liste d'__IndexEntry__ = 1 __Token__ asssocié à une liste de __Posting__

    <img src="./cours/index_inverse.PNG" alt="Index_invese" style="width: 50%;"/>
    <!-- ![Index_invese](./cours/index_inverse.PNG) -->

    /indexation/AbstractIndex.java

  - __ArrayIndex__: Version de l'index inverse avec un Tableau d'__IndexEntry__

    /indexation/ArrayIndex.java

  - __HashIndex__: Version de l'index inverse avec une __HashMap__ d'__IndexEntry__

    /indexation/HashIndex.java

  - __TreeIndex__: Version de l'index inverse avec une __TreeMan__ d'__IndexEntry__

    /indexation/TreeIndex.java
