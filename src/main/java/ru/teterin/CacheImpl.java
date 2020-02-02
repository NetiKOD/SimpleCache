package ru.teterin;

import ru.teterin.strategy.CacheStrategy;
import ru.teterin.strategy.Strategy;
import ru.teterin.strategy.StrategyFabric;

import java.util.HashMap;
import java.util.Map;

public class CacheImpl<K, V> implements Cache<K, V> {

    private final int capacity;
    private final CacheStrategy<K> strategy;
    private final Map<CacheKey<K>, V> storage;

    private int size;

    /**
     * Constructs an empty Cache with the specified initial capacity and eviction strategy.
     *
     * @param initialCapacity initial capacity
     * @param strategy        eviction strategy
     * @throws IllegalArgumentException if the initial capacity is nonpositive
     * @see Strategy
     */
    public CacheImpl(int initialCapacity, Strategy strategy) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("initial capacity is nonpositive");
        }
        this.capacity = initialCapacity;
        this.strategy = StrategyFabric.getStrategy(strategy);
        this.storage = new HashMap<>(initialCapacity, 1);
    }

    /**
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @throws NullPointerException if key or value is represented as null
     */
    public void put(K key, V value) {
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        }
        if (value == null) {
            throw new NullPointerException("Value cannot be null");
        }
        if (isFull()) {
            remove(strategy.getNextToRemoveElement(storage.keySet()));
        }
        CacheKey<K> cacheKey = new CacheKey<>(key);
        storage.put(cacheKey, value);
        strategy.cache(storage.keySet(), cacheKey);
        size++;
    }

    public V get(K key) {
        CacheKey<K> cacheKey = new CacheKey<>(key);
        V value = storage.get(cacheKey);
        if (value != null) {
            strategy.cache(storage.keySet(), cacheKey);
        }
        return value;
    }

    public V remove(K key) {
        V remove = storage.remove(new CacheKey<>(key));
        if (remove != null) {
            size--;
        }
        return remove;
    }

    public boolean contains(K key) {
        return storage.containsKey(new CacheKey<>(key));
    }

    public void clear() {
        size = 0;
        storage.clear();
    }
    public int size() {
        return size;
    }

    public boolean isFull() {
        return size == capacity;
    }
}
