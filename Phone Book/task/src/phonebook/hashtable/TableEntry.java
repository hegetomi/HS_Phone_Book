package phonebook.hashtable;

public class TableEntry<K,V> {
    private final K key;
    private final V value;

    public TableEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return value;
    }
}
