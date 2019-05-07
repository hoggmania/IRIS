package com.temenos.useragent.generic.http;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/



public interface HttpRequest {

	HttpHeader headers();
	
	String payload();
}
