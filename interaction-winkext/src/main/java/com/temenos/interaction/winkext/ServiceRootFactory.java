package com.temenos.interaction.winkext;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.util.Set;

import com.temenos.interaction.core.rim.HTTPResourceInteractionModel;

public interface ServiceRootFactory {
	public Set<HTTPResourceInteractionModel> getServiceRoots();	
}
