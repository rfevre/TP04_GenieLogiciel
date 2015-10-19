/** ICI les commentaires sur le fonctionnement de la classe */
public class TabDict<K,V> // implements  Dictionnaire<K,V> //, Iterable<K>
{
    private static final int INIT_SIZE = 5 ;       // taille initiale du tableau
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
    void remove(int index) {
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
	return retour;// ... à compléter
    }

    // méthode nécessaire pour l'itérateur :
    // retourne la clef située à l'indice i, null si i incorrect
    K clefPourIndex(int i) {
        if (associations[i].premier()!=null)
	    return associations[i].premier();
	else
	    return null;
    }

    public String toString() {
	String tmp ="";
	for (int i=0;i<nbAssoc;i++)
	    tmp += "clé : "+associations[i].premier()+" valeur : "+associations[i].second()+"\n";
	return tmp;
    }

    // IMPLÉMENTATION DE L'INTERFACE Dictionnaire
    

    // IMPLÉMENTATION DE L'INTERFACE Iterable (2e partie du TP)
    // /** Itérateur permettant de parcourir les clefs (et d'en supprimer) */
    // public Iterator<K> iterator() {
    //     return new DictIterator<K>(this) ;
    // }



    public static void main (String[] args) {
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
    }
    
}
