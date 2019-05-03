package com.temenos.interaction.core.loader;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import java.util.List;

/**
 * Interface for loading a list of elements from a source. 
 * 
 * @author kwieconkowski
 * @author andres
 * @author dgroves
 */
public interface LoadingStrategy<T, S> {
    public List<T> load(S source);
}