package ru.teterin.strategy;

import lombok.experimental.UtilityClass;
import ru.teterin.strategy.implementation.LfuCacheStrategy;
import ru.teterin.strategy.implementation.LruCacheStrategy;

@UtilityClass
public class StrategyFabric {

    /**
     * Returns the selected strategy based on the passed type
     *
     * @param strategy type strategy
     * @param <K>      type key in cache
     * @return selected eviction strategy
     * @throws IllegalStateException if passed type not implement
     * @see Strategy
     */
    public <K> CacheStrategy<K> getStrategy(Strategy strategy) {
        CacheStrategy<K> result;
        switch (strategy) {
            case LFU:
                result = new LfuCacheStrategy<>();
                break;
            case LRU:
                result = new LruCacheStrategy<>();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + strategy);
        }
        return result;
    }
}
