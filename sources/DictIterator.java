import java.util.Iterator;

public class DictIterator<K> implements Iterator<K> {

    TabDict<K,?> dict;
    int cursor=0;
    
    public DictIterator(TabDict<K,?> dict) {
	this.dict=dict;
    }
    
    public boolean hasNext() {
	return cursor < dict.nbElements();
    }
    
    public K next() {
	return dict.clefPourIndex(cursor++);
    }
    
    public void remove() {
	dict.enleverPour(dict.clefPourIndex(cursor));
    }
}
