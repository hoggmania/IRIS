package com.temenos.interaction.core.hypermedia;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Resolves a {@link LazyResourceState} into real {@link ResourceState}
 * using a {@link ResourceStateProvider}.
 *
 * @author ikarady
 */
public class LazyResourceStateResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(LazyResourceStateResolver.class);

    private ResourceStateProvider resourceStateProvider;

    public LazyResourceStateResolver(ResourceStateProvider resourceStateProvider) {
        this.resourceStateProvider = resourceStateProvider;
    }

    /**
     * Attempts to resolve given {@link ResourceState} into a real {@link ResourceState}
     * if it is of type {@link LazyResourceState}.
     * In case of {@link LazyResourceState} it returns the resolved {@link ResourceState}
     * otherwise the original {@link ResourceState} that is passed in.
     *
     * @param   state   the {@link ResourceState} that is resolved if lazy
     *
     * @return {@link ResourceState}
     */
    public ResourceState resolve(ResourceState state) {
        ResourceState result = resolveState(state);
        if (result != null) {
            for (Transition transition : result.getTransitions()) {
                ResourceState targetState = resolveState(transition.getTarget());
                if (targetState == null) {
                    LOGGER.error("Invalid transition [{}]", transition.getId());
                }
                transition.setTarget(targetState);
            }
            if (result.getErrorState() != null && result.getErrorState().getId().startsWith(".")) {
                result.setErrorState(resolveState(result.getErrorState()));
            }
        }
        return result;
    }

    private ResourceState resolveState(ResourceState state) {
        ResourceState resolvedState = state;
        if (state != null && isLazy(state)) {
            resolvedState = getState(state.getName());
        }
        return resolvedState;
    }

    private ResourceState getState(String name) {
        ResourceState state = resourceStateProvider.getResourceState(name);
        if (state == null) {
            LOGGER.error("Invalid Resource state " + name);
        }
        return state;
    }

    private boolean isLazy(ResourceState state) {
        return state instanceof LazyResourceState || state instanceof LazyCollectionResourceState;
    }

}
