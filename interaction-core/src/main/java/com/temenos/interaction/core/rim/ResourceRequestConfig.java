package com.temenos.interaction.core.rim;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import java.util.ArrayList;
import java.util.List;

import com.temenos.interaction.core.hypermedia.Transition;

public class ResourceRequestConfig {

	/* the resources we want to get */
	private List<Transition> transitions;
	private boolean injectLinks;
	private boolean embedResources;
	private Transition selfTransition;

	public List<Transition> getTransitions() {
		return transitions;
	}
	
	public boolean isInjectLinks() {
		return injectLinks;
	}

	public boolean isEmbedResources() {
		return embedResources;
	}

	public Transition getSelfTransition() {
		return selfTransition;
	}

	/* The Builder can be regenerated with the fast code eclipse plugin */

	public static class Builder {
		private List<Transition> transitions = new ArrayList<Transition>();
		private boolean injectLinks = true;
		private boolean embedResources = true;
		private Transition selfTransition;

		public Builder transition(Transition transition) {
			this.transitions.add(transition);
			return this;
		}

		public Builder injectLinks(boolean injectLinks) {
			this.injectLinks = injectLinks;
			return this;
		}

		public Builder embedResources(boolean embedResources) {
			this.embedResources = embedResources;
			return this;
		}

		public Builder selfTransition(Transition selfTransition) {
			this.selfTransition = selfTransition;
			return this;
		}

		public ResourceRequestConfig build() {
			return new ResourceRequestConfig(this);
		}
	}

	private ResourceRequestConfig(Builder builder) {
		this.transitions = builder.transitions;
		this.injectLinks = builder.injectLinks;
		this.embedResources = builder.embedResources;
		this.selfTransition = builder.selfTransition;
	}
}
