package edu.java_core.datastructures.common;

import java.util.*;

@SuppressWarnings("unchecked")
public class HashTableSeparateChainingSelfImpl<K, V> implements Iterable<K> {

    private static final int DEFAULT_CAPACITY = 3;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    private double maxLoadFactor;
    private int capacity, threshold, size = 0;
    private LinkedList<Entry<K, V>>[] table;

    public HashTableSeparateChainingSelfImpl() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public HashTableSeparateChainingSelfImpl(int capacity) {
        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    // Designated constructor
    public HashTableSeparateChainingSelfImpl(int capacity, double maxLoadFactor) {
        if (capacity < 0) throw new IllegalArgumentException("Capacity is less then 0!");
        if (maxLoadFactor <= 0 || Double.isNaN(maxLoadFactor) || Double.isInfinite(maxLoadFactor))
            throw new IllegalArgumentException("Something wrong with a maxLoadFactor!");
        this.maxLoadFactor = maxLoadFactor;
        this.capacity = Math.max(DEFAULT_CAPACITY, capacity);
        this.threshold = (int) (capacity * maxLoadFactor);
        table = new LinkedList[this.capacity];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    // Converts a hash value to an index. Essentially, this strips the
    // negative sign and places the hash value in the domain [0, capacity)
    private int normalizeIndex(int keyHash) {
        return (keyHash & 0x7FFFFFFF) % capacity;
    }

    public boolean containsKey(K key) {
        return hasKey(key);
    }

    // Returns true/false depending on whether a key is in the hash table
    public boolean hasKey(K key) {
        int bucketIndex = normalizeIndex(key.hashCode());
        return bucketSeekEntry(bucketIndex, key) != null;
    }

    public void clear() {
        Arrays.fill(table, null);
        size = 0;
    }

    public V add(K key, V value) {
        return insert(key, value);
    }

    public V put(K key, V value) {
        return insert(key, value);
    }

    private V insert(K key, V value) {
        if (key == null) throw new IllegalArgumentException("Key is null");
        int bucketIndex = normalizeIndex(key.hashCode());
        Entry<K, V> entry = new Entry<>(key, value);
        return bucketInsertEntry(bucketIndex, entry);
    }

    // Gets a key's values from the map and returns the value.
    // NOTE: returns null if the value is null AND also returns
    // null if the key does not exist, so watch out...
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("Key is null");
        int bucketIndex = normalizeIndex(key.hashCode());
        Entry<K, V> entry = bucketSeekEntry(bucketIndex, key);
        if (entry != null) return entry.value;
        return null;
    }

    // Removes a key from the map and returns the value.
    // NOTE: returns null if the value is null AND also returns
    // null if the key does not exist.
    public V remove(K key) {
        if (key == null) throw new IllegalArgumentException("Key is null");
        int bucketIndex = normalizeIndex(key.hashCode());
        return bucketRemoveEntry(bucketIndex, key);
    }

    // Remove an entry form a given bucket if it exists
    private V bucketRemoveEntry(int bucketIndex, K key) {
        Entry<K, V> entry = bucketSeekEntry(bucketIndex, key);
        if (entry != null) {
            LinkedList<Entry<K, V>> list = table[bucketIndex];
            list.remove(entry);
            --size;
            return entry.value;
        } else return null;
    }

    // Insets an entry in a given bucket only if the entry doesn't already
    // exist in the given bucket, but if it does then update the entry value
    private V bucketInsertEntry(int bucketIndex, Entry<K, V> entry) {
        LinkedList<Entry<K, V>> list = table[bucketIndex];
        if (list == null) table[bucketIndex] = list = new LinkedList<>();

        Entry<K, V> existentEntry = bucketSeekEntry(bucketIndex, entry.key);
        if (existentEntry == null) {
            list.add(entry);
            if (++size > threshold) resizeTable();
            return null; // Use null to indicate that there was no previous entry
        } else {
            V oldVal = existentEntry.value;
            existentEntry.value = entry.value;
            return oldVal;
        }
    }

    // Finds and returns a particular entry in a given bucket if it exists, returns null otherwise
    private Entry<K, V> bucketSeekEntry(int bucketIndex, K key) {
        if (key == null) return null;
        LinkedList<Entry<K, V>> list = table[bucketIndex];
        if (list == null) return null;
        for (Entry<K, V> entry : list)
            return entry;
        return null;
    }

    // Resizes the internal table holding buckets of entries
    private void resizeTable() {
        capacity *= 2;
        threshold = (int) (capacity * maxLoadFactor);

        LinkedList<Entry<K, V>>[] newTable = new LinkedList[capacity];
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                for (Entry<K, V> entry : table[i]) {
                    int bucketIndex = normalizeIndex(entry.hash);
                    LinkedList<Entry<K, V>> bucket = newTable[bucketIndex];
                    if (bucket == null) newTable[bucketIndex] = bucket = new LinkedList<>();
                    bucket.add(entry);
                }
            }
            // Avoid memory leak. Help the GC
            table[i].clear();
            table[i] = null;
        }
        table = newTable;
    }

    // Returns the list of keys found within the hash table
    public List<K> keys() {
        List<K> keys = new ArrayList<>(size());
        for (LinkedList<Entry<K, V>> bucket : table)
            if (bucket != null)
                for (Entry<K, V> entry : bucket)
                    keys.add(entry.key);
        return keys;
    }

    // Returns the list of values found within the hash table
    public List<V> values() {
        List<V> values = new ArrayList<>(size());
        for (LinkedList<Entry<K, V>> bucket : table)
            if (bucket != null)
                for (Entry<K, V> entry : bucket)
                    values.add(entry.value);
        return values;
    }


    @Override
    public Iterator<K> iterator() {
        final int elementCount = size();
        return new Iterator<K>() {
            int bucketIndex = 0;
            Iterator<Entry<K, V>> bucketIter = (table == null) ? null : table[0].iterator();

            @Override
            public boolean hasNext() {
                // An item was added or removed while iterating
                if (elementCount != size()) throw new ConcurrentModificationException();

                // No iterator or the current iterator is empty
                if (bucketIter == null || !bucketIter.hasNext()) {
                    // Search next buckets until a valid iterator is found
                    while (++bucketIndex < capacity) {
                        if (table[bucketIndex] != null) {
                            // Make sure this iterator actually has elements
                            Iterator<Entry<K, V>> nextIter = table[bucketIndex].iterator();
                            if (nextIter.hasNext()) {
                                bucketIter = nextIter;
                                break;
                            }
                        }
                    }
                }
                return bucketIndex < capacity;
            }

            @Override
            public K next() {
                return bucketIter.next().key;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < capacity ; i++) {
            if (table[i] == null) continue;
            for (Entry<K, V> entry : table[i])
                sb.append(entry + ", ");
        }
        sb.append("}");
        return sb.toString();
    }
}


class Entry<K, V> {

    int hash;
    K key;
    V value;

    // We are not overriding the Object equals method
    // No casting is required with this method.
    public boolean equals(Entry<K, V> other) {
        if (hash != other.hash) return false;
        return key.equals(other.key);
    }

    public Entry(K key, V value) {
        this.hash = key.hashCode();
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return key + "==> " + value;
    }
}