package com.temenos.interaction.core.loader;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import com.temenos.interaction.core.hypermedia.ResourceState;

import static com.temenos.interaction.core.loader.ResourceStateLoadingStrategy.ResourceStateResult;

/**
 * Interface for loading a list of ResourceStateResult from a source. Ideally,
 * we would return a list of ResourceState, but we also need to store the ids
 * of each ResourceState for legacy code compatibility (such as storing the
 * bean ids from a PRD file). For this reason we use ResourceStateResult.
 * 
 * @author kwieconkowski
 * @author andres
 * @author dgroves
 */
public interface ResourceStateLoadingStrategy<S> extends LoadingStrategy<ResourceStateResult, S> {
    class ResourceStateResult {
        public final String resourceStateId;
        public final ResourceState resourceState;

        public ResourceStateResult(String resourceStateId, ResourceState resourceState) {
            this.resourceStateId = resourceStateId;
            this.resourceState = resourceState;
        }
    }
}
