package edu.datastructures.common;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

/**
 * An implementation of a hash-table using open addressing with quadratic probing as a collision
 * resolution method.
 *
 * <p>In this implementation we are using the following probing function: H(k, x) = h(k) + f(x) mod
 * 2^n
 *
 * <p>Where h(k) is the hash for the given key, f(x) = (x + x^2) / 2 and n is a natural number. We
 * are using this probing function because it is guaranteed to find an empty cell (i.e it generates
 * all the numbers in the range [0, 2^n) without repetition for the first 2^n numbers).
 */

@SuppressWarnings("unchecked")
public class HashTableQuadraticProbing<K, V> implements Iterable<K> {

    private double loadFactor;
    private int capacity, threshold, modificationCount = 0;

    // 'usedBuckets' counts the total number of used buckets inside the
    // hash-table (includes cells marked as deleted). While 'keyCount'
    // tracks the number of unique keys currently inside the hash-table.
    private int usedBuckets = 0, keyCount = 0;

    // These arrays store the key-value pairs.
    private K[] keyTable;
    private V[] valueTable;

    // flag used to indicate whether an item was found in the hash table
    private boolean containsFlag = false;

    // Special marker token used to indicate the deletion of a key-value pair
    private final K TOMBSTONE = (K) new Object();

    private static final int DEFAULT_CAPACITY = 8;
    private static final double DEFAULT_LOAD_FACTOR = 0.45;

    public HashTableQuadraticProbing() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public HashTableQuadraticProbing(int capacity) {
        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    // Designated constructor
    public HashTableQuadraticProbing(int capacity, double loadFactor) {
        if (capacity <= 0) throw new IllegalArgumentException("Capacity is less or equal 0 " + capacity);
        if (loadFactor <= 0 || Double.isNaN(loadFactor) || Double.isInfinite(loadFactor))
            throw new IllegalArgumentException("Illegal loadFactor: " + loadFactor);
        this.capacity = Math.max(next2Power(capacity), DEFAULT_CAPACITY);
        this.threshold = (int) (this.capacity * loadFactor);
        this.loadFactor = loadFactor;
        keyTable = (K[]) new Object[this.capacity];
        valueTable = (V[]) new Object[this.capacity];
    }

    // Given a number this method finds the next
    // power of two above this value.
    private int next2Power(int n) {
        return Integer.highestOneBit(n) << 1;
    }

    // Quadratic probing function (x^2+x)/2
    private static int P(int x) {
        return (x * x + x) / 2;
    }

    // Converts a hash value to an index. Essentially, this strips the
    // negative sign and places the hash value in the domain [0, capacity)
    private int normalizeIndex(int keyHash) {
        return (keyHash & 0x7FFFFFFF) % capacity;
    }

    // Clears all the contents of the hash-table
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            keyTable[i] = null;
            valueTable[i] = null;
        }
        keyCount = usedBuckets = 0;
        modificationCount++;
    }

    // Returns the number of keys currently inside the hash-table
    public int size() {
        return keyCount;
    }

    // Returns true/false depending on whether the hash-table is empty
    public boolean isEmpty() {
        return size() == 0;
    }

    // Insert, put and add all place a value in the hash-table
    public V put(K key, V value) {
        return insert(key, value);
    }

    public V add(K key, V value) {
        return insert(key, value);
    }

    private V insert(K key, V value) {
        if (key == null) throw new IllegalArgumentException("Null key");
        if (usedBuckets >= threshold) resizeTable();
        final int hash = normalizeIndex(key.hashCode());
        int i = hash, j = -1, x = 1;
        do {
            // The current slot was previously deleted
            if (keyTable[i] == TOMBSTONE) {
                if (j == -1) j = i;
                // The current cell already contains a value
            } else if (keyTable[i] != null) {
                // The key we're trying to insert already exists in the hash-table,
                // so update its value with the most recent value
                if (keyTable[i].equals(key)) {
                    V oldValue = valueTable[i];
                    if (j == -1)
                        valueTable[i] = value;
                    else {
                        keyTable[i] = TOMBSTONE;
                        valueTable[i] = null;
                        keyTable[j] = key;
                        valueTable[j] = value;
                    }
                    modificationCount++;
                    return oldValue;
                }
                // Current cell is null so an insertion/update can occur
            } else {
                // No previously encountered deleted buckets
                if (j == -1) {
                    usedBuckets++;
                    keyCount++;
                    keyTable[i] = key;
                    valueTable[i] = value;
                    // Previously seen deleted bucket. Instead of inserting
                    // the new element at i where the null element is insert
                    // it where the deleted token was found;
                } else {
                    keyCount++;
                    keyTable[j] = key;
                    valueTable[j] = value;
                }
                modificationCount++;
                return null;
            }
            i = normalizeIndex(hash + P(x++));
        } while (true);
    }

    public boolean containsKey(K key) {
        return hasKey(key);
    }

    private boolean hasKey(K key) {
        // sets the 'containsFlag'
        get(key);
        return containsFlag;
    }

    // Get the value associated with the input key.
    // NOTE: returns nll if the value is null AND also returns
    // null if the key does not exist.
    private V get(K key) {
        if (key == null) throw new IllegalArgumentException("Key is null");
        final int hash = normalizeIndex(key.hashCode());
        int i = hash, j = -1, x = 1;

        // Starting at the original hash index quadratically probe until we find a
        // spot where
        // our key is, or we hit a null element in which case our element does not exist.
        do {
            // Ignore deleted cells, but record where the first index
            // of a deleted cell id found to perform lazy relocation later.
            if (keyTable[i] == TOMBSTONE) {
                if (j == -1) j = i;
            } else if (keyTable[i] != null) {
                if (keyTable[i].equals(key)) {
                    containsFlag = true;
                    // if j != -1 this means we previously encountered a deleted cell.
                    // We can perform an optimization by swapping the entries in cells
                    // i and j so that tje next time we search for this key it will be
                    // found faster. This is called lazy deletion/relocation.
                    if (j != -1) {
                        // Copy values to where deleted bucket is
                        keyTable[j] = keyTable[i];
                        valueTable[j] = valueTable[i];

                        // Clear the contents in bucket i and mark it as deleted
                        keyTable[i] = TOMBSTONE;
                        valueTable[i] = null;

                        return valueTable[j];
                    } else {
                        return valueTable[i];
                    }
                    // Element was not found in the hash-table
                } else {
                    containsFlag = false;
                    return null;
                }
            }
            i = normalizeIndex(hash + P(x++));
        } while (true);
    }

    // Removes a key from the map and returns the value.
    // NOTE: returns null if the value is null AND also returns
    // null if the key does not exist.
    public V remove(K key) {
        if (key == null) throw new IllegalArgumentException("Key is null");
        final int hash = normalizeIndex(key.hashCode());
        int i = hash, x = 1;
        // Starting at the hash index quadratically probe until we find a spot where
        // our key is, or we hit a null element in which case our element does not exist
        for (; ; i = normalizeIndex(hash + P(x++))) {
            // Ignore deleted cells
            if (keyTable[i] == TOMBSTONE) continue;
            // Key was not found in hashtable.
            if (keyTable[i] == null) return null;
            if (keyTable[i].equals(key)) {
                keyCount--;
                modificationCount++;
                V oldValue = valueTable[i];
                keyTable[i] = TOMBSTONE;
                valueTable[i] = null;
                return oldValue;
            }
        }


    }

    // Returns a list of keys found in the hash table
    public List<K> keys() {
        List<K> keys = new ArrayList<>(size());
        for (int i = 0; i < capacity; i++) {
            if (keyTable[i] != null && keyTable[i] != TOMBSTONE)
                keys.add(keyTable[i]);
        }
        return keys;
    }

    // Returns a list of values found in the hash table
    public List<V> values() {
        List<V> values = new ArrayList<>(size());
        for (int i = 0; i < capacity; i++) {
            if (valueTable[i] != null && valueTable[i] != TOMBSTONE)
                values.add(valueTable[i]);
        }
        return values;
    }

    // Double the size of the hashtable
    private void resizeTable() {
        capacity *= 2;
        threshold = (int) (capacity * loadFactor);
        K[] newKeyTable = (K[]) new Object[capacity];
        V[] newValueTable = (V[]) new Object[capacity];

        // Perform key table pointer swap
        K[] keyTableTmp = keyTable;
        keyTable = newKeyTable;
        newKeyTable = keyTableTmp;

        V[] valueTableTmp = valueTable;
        valueTable = newValueTable;
        newValueTable = valueTableTmp;

        // Reset the key count and buckets used since we are about to
        // re-insert all the keys into the hash-table.
        keyCount = usedBuckets = 0;

        for (int i = 0; i < newKeyTable.length; i++) {
            if (newKeyTable[i] != null && newKeyTable[i] != TOMBSTONE)
                insert(newKeyTable[i], newValueTable[i]);
            newValueTable[i] = null;
            newKeyTable[i] = null;
        }
    }


    @Override
    public Iterator<K> iterator() {
        // Before the iteration begins record the number of modifications
        // done to the hash-table. This value should not change as we iterate
        // otherwise a concurrent modification has occurred.
        final int MODIFICATION_COUNT = modificationCount;
        return new Iterator<K>() {
            int keysLeft = keyCount, index = 0;

            @Override
            public boolean hasNext() {
                if (MODIFICATION_COUNT != modificationCount) throw new ConcurrentModificationException();
                return keysLeft != 0;
            }

            @Override
            public K next() {
                while (keyTable[index] != null || keyTable[index] != TOMBSTONE)
                    index++;
                keysLeft--;
                return keyTable[index++];
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < capacity; i++)
            if (keyTable[i] != null || keyTable[i] != TOMBSTONE)
                sb.append(keyTable[i] + " ==> " + valueTable[i] + ", ");
        return sb.append("]").toString();
    }
}
