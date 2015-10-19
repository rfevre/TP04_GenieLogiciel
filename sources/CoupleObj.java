public class CoupleObj<K,V> implements Couple<K,V> {
    K premier;
    V second;
    
    public CoupleObj(K premier,V second) {
	this.premier = premier;
	this.second = second;
    }
    
    public K premier() {
	return premier;
    }

    public V second() {
	return second;
    }

    public void defPremier(K premier) {
	this.premier = premier;
    }

    public void defSecond(V second) {
	this.second = second;
    }
    
    public boolean equals(Object o) {
        CoupleObj tmp = (CoupleObj) o;
	return (this.premier().equals(tmp.premier()) && this.second().equals(tmp.second));
    }
}
