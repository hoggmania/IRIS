package com.temenos.useragent.generic.http;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import com.temenos.useragent.generic.Result;

public interface HttpResponse {

	Result result();

	HttpHeader headers();

	String payload();
}
