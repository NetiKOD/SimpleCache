# SimpleCache
Simple in-memory cache

## Task:
Create an in-memory cache (for caching Objects) with configurable max size and eviction strategy.
Two strategies should be implemented: LRU and LFU.
For this task it is assumed that only one thread will access the cache, so there is no need to make it thread-safe.
Please provide an example of usage of the cache as a unit test(s).

## Possible improvements:
1. Change the work with the CacheKey class, to reduce the generation of unnecessary instances
2. The increase in the number of tests