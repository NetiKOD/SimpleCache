package ru.teterin.strategy;

import ru.teterin.CacheKey;

import java.util.Set;

/**
 * Common Strategy Interface for cache
 *
 * @param <K> type key in cache
 */
public interface CacheStrategy<K> {

    /**
     * Caching operation.
     *
     * @param keySet   set of cache keys
     * @param cacheKey the key on which the operation is performed
     */
    void cache(Set<CacheKey<K>> keySet, CacheKey<K> cacheKey);

    /**
     * Obtaining the next delete key in accordance with the selected strategy.
     *
     * @param keySet set of cache keys
     * @return the key for remove
     */
    K getNextToRemoveElement(Set<CacheKey<K>> keySet);
}
