package com.temenos.interaction.core.cache;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import java.util.Map;

/**
 * Interface for caching objects with the option of specifying their life time.
 * 
 * @author kwieconkowski
 * @author andres
 * @author dgroves
 */
public interface CacheExtended<K, V> {
    void put(K key, V value);

    void put(K key, V value, int ageInSeconds);

    void putAll(Map<K, V> keyValueMap);

    V get(K key);

    void remove(K key);

    void removeAll();

    boolean isEmpty();
}
