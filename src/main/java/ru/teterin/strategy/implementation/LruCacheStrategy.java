package ru.teterin.strategy.implementation;

import ru.teterin.CacheKey;
import ru.teterin.exception.CacheException;
import ru.teterin.strategy.CacheStrategy;

import java.util.Comparator;
import java.util.Set;

/**
 * Least recently used (LRU)
 * <p>
 * Discards the least recently used items first.
 * This algorithm requires keeping track of what was used when,
 * which is expensive if one wants to make sure the algorithm always discards the least recently used item.
 * <p>
 *
 * @param <K> key type in cache
 * @see <a href="https://en.wikipedia.org/wiki/Cache_replacement_policies">Wikipedia: cache replacement policies</a>
 */
public class LruCacheStrategy<K> implements CacheStrategy<K> {

    /**
     * The key on which the operation is performed is reset to the "age bit". For the rest it increases.
     *
     * @param cacheKeys set of cache keys
     * @param cacheKey  the key on which the operation is performed
     */
    @Override
    public void cache(Set<CacheKey<K>> cacheKeys, CacheKey<K> cacheKey) {
        for (CacheKey<K> kCacheKey : cacheKeys) {
            if (kCacheKey.equals(cacheKey)) {
                kCacheKey.setCriteria(0);
            } else {
                kCacheKey.setCriteria(kCacheKey.getCriteria() + 1);
            }
        }
    }

    /**
     * Returns the "oldest" element, based on the "age bit".
     *
     * @param cacheKeys set of cache keys
     * @return the key for remove
     */
    @Override
    public K getNextToRemoveElement(Set<CacheKey<K>> cacheKeys) {
        return cacheKeys.stream()
                .max(Comparator.comparingInt(CacheKey::getCriteria))
                .orElseThrow(() -> new CacheException(""))
                .getKey();
    }
}
