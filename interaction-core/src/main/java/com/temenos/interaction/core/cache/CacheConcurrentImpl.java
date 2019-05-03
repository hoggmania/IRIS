package com.temenos.interaction.core.cache;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Implementation of a cache using a ConcurrentHashMap.
 * 
 * @author kwieconkowski
 * @author andres
 * @author dgroves
 */
public class CacheConcurrentImpl<K, V> implements CacheExtended<K, V> {
    private ConcurrentMap<K, V> cache = new ConcurrentHashMap<K, V>();

    @Override
    public void put(K key, V value) {
        cache.put(key, value);
    }

    @Override
    public void put(K key, V value, int ageInSeconds) {
        throw new UnsupportedOperationException("Not supported operation for this implementation");
    }

    @Override
    public void putAll(Map<K, V> keyValueMap) {
        cache.putAll(keyValueMap);
    }

    @Override
    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public void remove(K key) {
        cache.remove(key);
    }

    @Override
    public void removeAll() {
        cache.clear();
    }

    @Override
    public boolean isEmpty() {
        return cache.isEmpty();
    }
}
