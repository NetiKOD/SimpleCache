package ru.teterin;

import org.junit.jupiter.api.Test;
import ru.teterin.strategy.Strategy;

import static org.junit.jupiter.api.Assertions.*;

public class Examples {

    @Test
    void simpleTestForLRU() {
        CacheImpl<Long, String> cache = new CacheImpl<>(3, Strategy.LRU);

        long key1 = 1L;
        long key2 = 2L;
        long key3 = 3L;
        cache.put(key1, TestTool.randomString());
        cache.put(key2, TestTool.randomString());
        cache.put(key3, TestTool.randomString());

        assertTrue(cache.isFull());

        // We expect that after adding the 4th element, the element with key 1 will be excluded from the cache
        long key4 = 4L;
        cache.put(key4, TestTool.randomString());

        assertNull(cache.get(key1));
        assertNotNull(cache.get(key2));
        assertNotNull(cache.get(key3));
        assertNotNull(cache.get(key4));


        // We expect that after adding the 5th element, the element with key 2 will be excluded from the cache
        long key5 = 5L;
        cache.put(key5, TestTool.randomString());

        assertNull(cache.get(key1));
        assertNull(cache.get(key2));
        assertNotNull(cache.get(key3));
        assertNotNull(cache.get(key4));
        assertNotNull(cache.get(key5));
    }

    @Test
    void simpleTestForLRU2() {
        CacheImpl<Long, String> cache = new CacheImpl<>(3, Strategy.LRU);

        long key1 = 1L;
        long key2 = 2L;
        long key3 = 3L;
        cache.put(key1, TestTool.randomString());
        cache.put(key2, TestTool.randomString());
        cache.put(key3, TestTool.randomString());

        assertTrue(cache.isFull());

        // Simulate working with a cache
        assertNotNull(cache.get(key1));

        // We expect that after adding the 4th element, the element with key 2 will be excluded from the cache
        long key4 = 4L;
        cache.put(key4, TestTool.randomString());

        assertNull(cache.get(key2));
        assertNotNull(cache.get(key1));
        assertNotNull(cache.get(key3));
        assertNotNull(cache.get(key4));
    }

    @Test
    void simpleTestForLFU() {
        CacheImpl<Long, String> cache = new CacheImpl<>(3, Strategy.LFU);

        long key1 = 1L;
        long key2 = 2L;
        long key3 = 3L;
        cache.put(key1, TestTool.randomString());
        cache.put(key2, TestTool.randomString());
        cache.put(key3, TestTool.randomString());

        assertTrue(cache.isFull());

        // Simulate working with a cache
        assertNotNull(cache.get(key1));
        assertNotNull(cache.get(key3));
        assertNotNull(cache.get(key2));
        assertNotNull(cache.get(key1));
        assertNotNull(cache.get(key2));

        // We expect that after adding the 4th element, the element with key 3 will be excluded from the cache
        long key4 = 4L;
        cache.put(key4, TestTool.randomString());

        assertNull(cache.get(key3));
        assertNotNull(cache.get(key1));
        assertNotNull(cache.get(key1));
        assertNotNull(cache.get(key4));
    }

    @Test
    void clearCache() {
        int capacity = 3;
        CacheImpl<Long, String> cache = new CacheImpl<>(capacity, Strategy.LFU);

        long key1 = 1L;
        long key2 = 2L;
        long key3 = 3L;
        cache.put(key1, TestTool.randomString());
        cache.put(key2, TestTool.randomString());
        cache.put(key3, TestTool.randomString());

        assertTrue(cache.isFull());
        assertEquals(capacity, cache.size());
        assertNotNull(cache.get(key1));
        assertNotNull(cache.get(key2));
        assertNotNull(cache.get(key3));

        cache.clear();

        assertFalse(cache.isFull());
        assertEquals(0, cache.size());
        assertNull(cache.get(key1));
        assertNull(cache.get(key2));
        assertNull(cache.get(key3));
    }


    @Test
    void remove() {
        int capacity = 3;
        CacheImpl<Long, String> cache = new CacheImpl<>(capacity, Strategy.LFU);

        long key1 = 1L;
        long key2 = 2L;
        long key3 = 3L;
        cache.put(key1, TestTool.randomString());
        cache.put(key2, TestTool.randomString());
        cache.put(key3, TestTool.randomString());

        assertTrue(cache.isFull());
        assertEquals(capacity, cache.size());
        assertNotNull(cache.get(key1));
        assertNotNull(cache.get(key2));
        assertNotNull(cache.get(key3));

        cache.remove(key1);

        assertFalse(cache.isFull());
        assertNull(cache.get(key1));
        assertNotNull(cache.get(key2));
        assertNotNull(cache.get(key3));
    }

    @Test
    void removeNotExistingKey() {
        int capacity = 3;
        CacheImpl<Long, String> cache = new CacheImpl<>(capacity, Strategy.LFU);

        long key1 = 1L;
        long key2 = 2L;
        long key3 = 3L;
        cache.put(key1, TestTool.randomString());
        cache.put(key2, TestTool.randomString());
        cache.put(key3, TestTool.randomString());

        assertTrue(cache.isFull());
        assertEquals(capacity, cache.size());
        assertNotNull(cache.get(key1));
        assertNotNull(cache.get(key2));
        assertNotNull(cache.get(key3));

        long notExistenceKey = 10L;
        cache.remove(notExistenceKey);

        assertTrue(cache.isFull());
        assertEquals(capacity, cache.size());
        assertNotNull(cache.get(key1));
        assertNotNull(cache.get(key2));
        assertNotNull(cache.get(key3));
    }

    @Test
    void removeNullKey() {
        int capacity = 3;
        CacheImpl<Long, String> cache = new CacheImpl<>(capacity, Strategy.LFU);

        long key1 = 1L;
        long key2 = 2L;
        long key3 = 3L;
        cache.put(key1, TestTool.randomString());
        cache.put(key2, TestTool.randomString());
        cache.put(key3, TestTool.randomString());

        assertTrue(cache.isFull());
        assertEquals(capacity, cache.size());
        assertNotNull(cache.get(key1));
        assertNotNull(cache.get(key2));
        assertNotNull(cache.get(key3));

        cache.remove(null);

        assertTrue(cache.isFull());
        assertEquals(capacity, cache.size());
        assertNotNull(cache.get(key1));
        assertNotNull(cache.get(key2));
        assertNotNull(cache.get(key3));
    }

    @Test
    void checkContainsKey() {
        int capacity = 3;
        CacheImpl<Long, String> cache = new CacheImpl<>(capacity, Strategy.LFU);

        long key1 = 1L;
        cache.put(key1, TestTool.randomString());
        assertTrue(cache.contains(key1));

        long notExistenceKey = 10L;
        assertFalse(cache.contains(notExistenceKey));
    }
}
