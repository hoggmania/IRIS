package com.temenos.interaction.core.cache;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Dumbest possible cache implementation.
 * Doesn't even use a ConcurrentHashMap because get makes two accesses so needs to be locked anyway
 *
 * @author amcguinness
 *
 */
public class HashMapCache implements Cache {
	public HashMapCache() {
		logger.debug( "HashMap Cache initialized" );
	}

	private static final Logger logger = LoggerFactory.getLogger(HashMapCache.class);

	private boolean checkKey(Object key) {
		if ( key == null ) {
			logger.warn( "Attempt to cache null key" );
			return false;
		}
		logger.debug( "cache key: " + key );
		return true;
	}

	/** Insert a value into the cache.
	 *  @param maxAge the entry will be invalid after this many seconds
	 */
	@Override
	public synchronized void put(Object key, Response.ResponseBuilder value, int maxAge) {
		put(key, value, maxAge, System.currentTimeMillis() );
	}

	// logic without live time exposed for testing
	synchronized void put(Object key, Response.ResponseBuilder value, int maxAge, long now) {
		if ( !checkKey( key ) )
			return;

		long expires = now + 1000L * maxAge;
		Entry old = data.get( key );
		if ( old != null ) {
			old.expires = expires;
			logger.debug( "Extending expires time for [" + key + "] to " + expires );
		} else {
			Entry entry = new Entry( value, expires );
			data.put( key.toString(), entry );
			logger.debug( "Caching [" + key + "] -> [" + value.toString() + "] until " + expires );
		}
	}

	/** Retrieve a value from the cache
	 *  @return the value if present and valid
	 */
	@Override
	public synchronized Response.ResponseBuilder get(Object key) {
		return get(key, System.currentTimeMillis());
	}

	public synchronized Response.ResponseBuilder get(Object key, long now)
	{
		if ( !checkKey( key ) )
			return null;

		Entry entry = data.get( key.toString() );
		if ( entry != null ) {
			if ( entry.expires > now ) {
				logger.debug("using cached response for " + key);
				return entry.value;
			} else {
				logger.debug("cached value for [" + key + "] expired " + entry.expires);
				data.remove( key );
			}
		} else {
			logger.debug("No cached value for [" + key + "]");
		}
		return null;
	}

	/** inspect the cache state
	 * @param now a timestamp
	 * @return list of all keys which are stored and not expired as of the provided timestamp
	 */
	public List<String> validStoredKeys(long now) {
		ArrayList<String> keys = new ArrayList<String>(data.size()/4);
		for (Map.Entry<String, Entry> e : data.entrySet()) {
			if (e.getValue().expires > now)
				keys.add(e.getKey());
		}
		return keys;
	};

	private final HashMap<String,Entry> data = new HashMap<String,Entry>();

	private static class Entry {
		Entry( Response.ResponseBuilder v, long e ) {
			expires = e; value = v;
		}
		long expires;
		Response.ResponseBuilder value;
	}
}
