package com.temenos.interaction.example.hateoas.dynamic;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import com.temenos.interaction.core.hypermedia.ParameterAndValue;
import com.temenos.interaction.core.hypermedia.ResourceParameterResolver;
import com.temenos.interaction.core.hypermedia.ResourceParameterResolverContext;

public class AuthorResourceParameterResolver implements ResourceParameterResolver {

	@Override
	public ParameterAndValue[] resolve(Object[] aliases, ResourceParameterResolverContext context) {
		return new ParameterAndValue[]{new ParameterAndValue("id", "AU002") };
	}
}
