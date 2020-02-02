package ru.teterin;

/**
 * An object that maps and save keys to values.
 * A cache cannot contain duplicate keys.
 * Each key can map to at most one value.
 *
 * @param <K> the type of keys maintained by this cache
 * @param <V> the type of mapped values
 */
public interface Cache<K, V> {

    /**
     * Associates the specified value with the specified key in this cache.
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     */
    void put(K key, V value);

    /**
     * Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     *
     * @param key the key whose associated value
     * @return the value to which the specified key is mapped, or null if this cache contains no mapping for the key
     */
    V get(K key);

    /**
     * Removes the mapping for a key from this map if it is present.
     *
     * @param key the key whose associated value
     * @return the previous value associated with key, or null if there was no mapping for key.
     */
    V remove(K key);

    /**
     * Returns true if this cache contains a mapping for the specified key.
     *
     * @param key key whose presence in this map is to be tested
     * @return true if this cache contains a mapping for the specified key
     */
    boolean contains(K key);

    /**
     * Clear entire cache by deleting saved data
     */
    void clear();

    /**
     * Returns cache size
     *
     * @return cache size
     */
    int size();

    /**
     * Returns true if this cache is full, size = capacity
     *
     * @return true if this cache is full
     */
    boolean isFull();
}
