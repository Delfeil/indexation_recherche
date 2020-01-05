# Recherche approchée par Jocker

## Jocker (wild card)

__Caractère spécial__ se substituant à une __sous-séquence__ de caractère dans une __requête__
Souvent *

## Lexiques compatibles

### Arbre de recherche

  - Label = préfixe (1 mot = 1 feuille)
  - Temps de  recherche logarithmique
  - possibilité de trouver des variantes
  - Possibilité de rechercher par préfixe
  - Inserton d'éléments coûteux (B-trees)
  - Occupation mémoire > HasMap

## Recherche sur l'arbre de recherche


### Préfixe

ex: part*
Étapes:

  - Naviguer jusqu'au noeud correspondant au préfixe
  - Parcourir en partant de ce noeud, jusqu'à rencontrer un noeud ne correspondant plus au préfixe

Ex. : pour mon* : aller à mon- et parcourir jusqu’à moo-

### Suffice

Ex: *lier
Étapes:

  - L'arbe de recherche doit être construit à l'envert

### Indixe

Ex: part*lier
Étapes:

  - Calculer l'ensemble des termes pour part*
  - Calculer l'ensemble des termes pour *lier
  - Prendre leur intersection

(Approche couteuse)

## Permuterm

Objectif: Transformer la requête afin de placer le * à la fin

Le lexique doit contenir pour chaque terme ses rotations

    Ex. : racine → racine$, $racine, e$racin, ne$raci, ine$rac, cine$ra, acine$r

Étapes:

  - On place le caractère $ à la fin du terme
  - On fait des rotations pour amener * à la fin

        racine → racine$
        *ine → ine$* ; rac* → $rac*
        r*e → e$r* ; *ci* → ci*

## Recherche approchée à l'aide d'un indexe de __k-grammes__ de caractère

On utilise $ pour noter le début ou la fin d'un terme

Lexique constitué de tous les k-grammes des termes

    Ex: K=3 (3-gramme): chat 6> $ch, cha, hat, at$

### Préfixe

    Ex: ra* → on cherche le trigramme $ra

### Suddixe:

    Ex: *ne → on cherche le trigramme ne$

### Infixe:

    Ex: ra*ne → intersection des résultats de $ra et ne$

### Complication cas (red*)

    $re et red vont produire (entre autres) retired

Besoin d'un filtrage supplémentaire (à l'aide d'une comparaison)

# Recherche approchée par correction

## Distance de Levensthein

__Distance edit__: Comparer 2 séquences en déterminant le __nombre minimal d'opérations__ nécessaire pour passer de l'une à l'autre.

__Distance de Levenshtein__: Les opérations autorisées sont __l'insertion__, __supprimer__ et __remplacer__.

### Méthodes de calcul de la distance de Levenshtein

Notations:
  - |s|: Longueur de la chaine s

        Ex: |"truc"| = 4

  - init(s): Chaine privée de son dernier caractère

        Ex: iniit("truc") = "tru"

  - last(s): dernier caractère

        Ex: last("truc") = "c"

  - |P|: Crochet d'inversion

        Ex: [1>4] = 0, mais [1<4] = 1

#### Naïve

```
Function lev(s1, s2):
    Input: s1, s2: String
    Output: d: Integer
    if s1 !=  ∅ then
        d <- |s2|
    else if s2 = ∅ then
        d <- |s1|
    else
        d <- min(lev(init(s1), s2)+1;
            lev(s1, init(s2))+1;
            lev(init(s1), init(s2)) + [last(s1)!=last(s2)])
    end if
    return d
return
```

#### Wagner-Fisher

- Utilisation d'une matrice entière __D__ de taille |s1|x|s2| pour stocker les résultats des calculs intermédiaires

      Initialisée avec des valeurs négatives

- Chaque élément __Dij__ contiend la distance entre
  
  - La sous-chaîne contenant les i premiers caractères de s1
  - Et celle contenant les j premiers caractères de s2

Si on a besoin d’une distance déjà calculée, on la
récupère dans la matrice au lieu de la recalculer

```
Function lev(s1, s2, D) :
    Input : s1,s2: String, D : Integer Matrix
    Output : d : Integer
    if s1= ∅ then
        d ← |s2|
    else if s2= ∅ then
        d ← |s1|
    else if D |s1|,|s2| ≥ 0 then
        d ← D |s1|,|s2|
    else
        d ← min (lev (init   (s1), s2) + 1 ;
            lev (s1, init (s2)) + 1 ;
            lev (init (s1), init (s2)) + [last (s1) ̸ = last (s2)])
        D|s1|,|s2| ← d
    end if
    return d
return
```

__Correction__: Remplacer par le terme le plus proche dans le lexique

Recherche exhaustive = trop couteux
-> Approche heuristique

- Restriction aux mots de même initiale
- Restriction aux termes commençant par une rotation du
mot
- Restriction aux termes ayant un certain nombre de
k-grammes en commun
  - Déterminer tous les k-grammes du mot requête
  - Obtenir tous les termes ayant des k-grammes communs
  (via l’index k-gramme des jokers)
  - Filtrer ceux qui ont n k-grammes communs (par
  intersection des ensembles obtenus)
  - Autre possibilité : coefficient de Jaccard ∩/∪

## Correction phonétique

Requêtes tapées à l'oreil

Principe:
- Chaques mot est remplacé par un _code_ pour représenter sa prononciation
- 2 _homophiones_ ont le même code
- Comparaison du code des mots de la requête à ceux des termes dans le lexique

### Soundex

```
Garder la première lettre du mot
Rmplacer toutes les voyelles et lettres muettes (H, W) par 0
Rempacer toutes les consonnes par les numérots de classes:
    B, F, P, V = 1
    C, G, J, K, Q, S, X, Z = 2
    D, T = 3
    L=4
    N, M = 5
    R=6
Remplacer les valeurs consécutives égales par une seule occurrence
Supprimer les 0
Remplacer les 4 premier caractères
Compléter par des 0 si nécessaire
```
