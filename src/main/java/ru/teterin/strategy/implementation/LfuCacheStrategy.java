package ru.teterin.strategy.implementation;

import ru.teterin.CacheKey;
import ru.teterin.exception.CacheException;
import ru.teterin.strategy.CacheStrategy;

import java.util.Comparator;
import java.util.Set;

/**
 * Least-frequently used (LFU)
 * <p>
 * Counts how often an item is needed.
 * Those that are used least often are discarded first.
 * We store the value of how many times it was accessed.
 * So of course while running an access sequence we will replace a block which was used least number of times from our cache.
 * <p>
 *
 * @param <K> key type in cache
 * @see <a href="https://en.wikipedia.org/wiki/Cache_replacement_policies">Wikipedia: cache replacement policies</a>
 */
public class LfuCacheStrategy<K> implements CacheStrategy<K> {

    /**
     * Increases the number of calls to the element.
     *
     * @param cacheKeys set of cache keys
     * @param cacheKey  the key on which the operation is performed
     */
    @Override
    public void cache(Set<CacheKey<K>> cacheKeys, CacheKey<K> cacheKey) {
        cacheKeys.stream()
                .filter(kCacheKey -> kCacheKey.equals(cacheKey))
                .forEach(kCacheKey -> kCacheKey.setCriteria(kCacheKey.getCriteria() + 1));
    }

    /**
     * Selects the item with the least hits.
     *
     * @param cacheKeys set of cache keys
     * @return the key for remove
     */
    @Override
    public K getNextToRemoveElement(Set<CacheKey<K>> cacheKeys) {
        return cacheKeys.stream()
                .min(Comparator.comparingInt(CacheKey::getCriteria))
                .orElseThrow(() -> new CacheException(""))
                .getKey();
    }
}
