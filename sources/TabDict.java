import java.util.Iterator;
import java.lang.Iterable;

/** ICI les commentaires sur le fonctionnement de la classe */
public class TabDict<K,V> implements Dictionnaire<K,V>,Iterable<K> {
    
    private static final int INIT_SIZE = 100 ;       // taille initiale du tableau
    private Couple<K,V> [] associations ;            // tableau contenant les associations
    private int nbAssoc ;	// nombre d'elements effectivement presents dans le dictionnaire

    /** Crée une instance de dictionnaire vide */
    public TabDict() {
        nbAssoc = 0 ;
        associations = (Couple<K,V>[]) new Couple[INIT_SIZE] ;
    }
    
    // redimensionnement automatique du tableau en une taille double
    private void resize() {
        Couple<K,V> [] tmp = (Couple<K,V>[]) new Couple[associations.length*2];
	for (int i=0;i<nbAssoc;i++)
	    tmp[i]=associations[i];
	associations = tmp;
    }

    // ajoute une association à la première position libre (après avoir 
    // redimensionné le tableau si nécessaire)
    private void add(Couple<K,V> assoc) {
	if (nbAssoc>=associations.length)
	    resize();
	associations[nbAssoc]=assoc;
	nbAssoc++;
    }

    // enlève l'association à l'indice spécifié
    private void remove(int index) {
	for (int i = index;i<nbAssoc;i++) {
	    associations[i]=associations[i+1];
	}
	nbAssoc--;
    }

    // indice de l'association assoc ; -1 si elle est absente 
    private int indexOf(Couple<K,V> assoc) {
	int retour = -1;
	for (int i=0;i<nbAssoc;i++) {
	    if (associations[i].equals(assoc)) {
		retour = i;
		break;
	    }
	}
	return retour;
    }
    
    // indice de l'association de clef c ; -1 si elle est absente
    private int indexOfClef(K c) {
        int retour = -1;
	for (int i=0;i<nbAssoc;i++) {
	    if (associations[i].premier().equals(c)) {
		retour = i;
		break;
	    }
	}
	return retour;
    }

    // recherche si v existe;
    private boolean chercheValeur(V v) {
        boolean retour = false;
	for (int i=0;i<nbAssoc;i++) {
	    if (associations[i].second().equals(v)) {
		retour = true;
		break;
	    }
	}
	return retour;
    }

    // méthode nécessaire pour l'itérateur :
    // retourne la clef située à l'indice i, null si i incorrect
    K clefPourIndex(int i) {
        if (associations[i].premier()!=null)
	    return associations[i].premier();
	else
	    return null;
    }

    /** Créer une chaine de caractères pour l'affichage du dictionnaire **/
    public String toString() {
	String tmp ="";
	for (int i=0;i<nbAssoc;i++)
	    tmp += "clé : "+associations[i].premier()+" valeur : "+associations[i].second()+"\n";
	return tmp;
    }

    //-------------------------------------------------------------------------------------------------
    // IMPLÉMENTATION DE L'INTERFACE Dictionnaire
    //-------------------------------------------------------------------------------------------------

    /** Teste si le dictionnaire ne contient aucune association */
    public boolean estVide() {
	return (nbAssoc==0);
    }
    
    /** Teste si le dictionnaire contient l'association assoc */
    public boolean contient(Couple <K,V> assoc) {
	if (indexOf(assoc)!=-1)
	    return true;
	else
	    return false;
    }
    
    /** Teste si le dictionnaire possède une association de clef c */
    public boolean contientClef(K c) {
	if (indexOfClef(c)!=-1)
	    return true;
	else
	    return false;
    }
    
    /** Teste si le dictionnaire possède une association de valeur v */
    public boolean contientValeur(V v) {
	return chercheValeur(v);
    }

    /** Retourne le nombre d'associations du dictionnaire */
    public int nbElements() {
	return nbAssoc;
    }
    
    /** Retourne l'association correspondant à la clef c spécifiée, null si absente */
    public Couple<K,V> assocPour(K c) {
	if (contientClef(c))
	    return associations[indexOfClef(c)];
	else
	    return null;
    }
    
    /** Retourne la valeur associée à la clef c, null si absente */
    public V valeurPour(K c) {
	if (contientClef(c))
	    return associations[indexOfClef(c)].second();
	else
	    return null;
    }
    
    /** Ajoute l'association assoc au dictionnaire (remplacement si clef présente) */
    public void ajouter(Couple<K,V> assoc) {
	if (contientClef(assoc.premier()))
	    associations[indexOf(assoc)].defSecond(assoc.second());
	else
	    add(assoc);
    }
    
    /** Définit ou modifie la valeur v associée à la clef c dans le dictionnaire */
    public void ajouter(K c, V v) {
	if (contientClef(c))
	    associations[indexOfClef(c)].defSecond(v);
	else {
	    add(new CoupleObj(c,v));
	}
    }
    
    /** Enlève l'association assoc du dictionnaire (si présente) */
    public void enlever(Couple<K,V> assoc) {
	if (contient(assoc))
	    remove(indexOf(assoc));
    }
    
    /** Enlève l'entrée de clef c (si présente) */
    public void enleverPour(K c) {
	if (contientClef(c))
	    remove(indexOfClef(c));
    }

    //-------------------------------------------------------------------------------------------------
    // IMPLÉMENTATION DE L'INTERFACE Iterable (2e partie du TP)
    //-------------------------------------------------------------------------------------------------
    
    /** Itérateur permettant de parcourir les clefs (et d'en supprimer) */
    public Iterator<K> iterator() {
        return new DictIterator<K>(this) ;
    }


    //Main de test
    /* public static void main (String[] args) {
	TabDict<Integer,String> tab = new TabDict();
	tab.add(new CoupleObj(1,"test1"));
	tab.add(new CoupleObj(2,"test2"));
	tab.add(new CoupleObj(3,"test3"));
	tab.add(new CoupleObj(4,"test4"));
	System.out.println(tab.toString());
	System.out.println(tab.clefPourIndex(3));
	System.out.println(tab.indexOfClef(3));
	tab.remove(2);
	System.out.println(tab.toString());
	tab.add(new CoupleObj(5,"test5"));
	tab.add(new CoupleObj(6,"test6"));
	tab.add(new CoupleObj(7,"test7"));
	tab.add(new CoupleObj(8,"test8"));
	System.out.println(tab.toString());
	}*/
    
}
