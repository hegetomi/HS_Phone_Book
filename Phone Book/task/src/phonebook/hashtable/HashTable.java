package phonebook.hashtable;

import java.util.HashSet;
import java.util.Set;

public class HashTable<K, V> {

    private int size;
    private TableEntry<K, V>[] table;

    public HashTable(int size) {
        this.size = size;
        this.table = new TableEntry[size];
    }

    private int findKey(K key) {
        int hashcode = (key.hashCode() & 2147483647);
        int hash = hashcode % size;
        while (!(table[hash] == null || table[hash].getKey().equals(key))) {
            hash = (hash + 1) % size;
            //Math.abs(key.hashCode()) % size;
            //(key.hashCode() & 2147483647) % size
            if (hash == hashcode % size) {
                return -1;
            }
        }
        return hash;
    }


    public V get(K key) {
        int index = findKey(key);

        if (index == -1 || table[index] == null) {
            //System.out.println(index + " " + (table[index] == null));
            return null;
        }
        return (V) table[index].getValue();
    }

    public boolean put(K key, V value) {
        int index = findKey(key);
        if (index == -1) {
            rehash();
            index = findKey(key);
        }
        table[index] = new TableEntry<>(key, value);
        return true;
    }

    public Set<TableEntry<K, V>> entrySet() {
        // put your code here
        Set<TableEntry<K, V>> hashset = new HashSet<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                hashset.add(table[i]);
            }
        }
        return hashset;
    }


    private void rehash() {
        // put your code here
        this.size = size * 2;
        TableEntry<K, V>[] oldEntries = table.clone();
        TableEntry<K, V>[] newEntries = new TableEntry[this.size];
        table = newEntries;

        for (TableEntry<K, V> oldEntry : oldEntries) {
            if (oldEntry != null) {
                put(oldEntry.getKey(), oldEntry.getValue());
            }
        }
    }


}


