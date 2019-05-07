package com.temenos.useragent.generic.http;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import java.net.URI;

import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

/**
 * HttpDelete will not support Delete with an Entity, Hence this class will override this functionality.
 *
 * @author mohamednazir
 *
 */

@NotThreadSafe // HttpRequestBase is @NotThreadSafe
public class HttpDeleteWithBody extends HttpEntityEnclosingRequestBase {
    
    public static final String METHOD_NAME = "DELETE";
    public String getMethod() { 
        return METHOD_NAME;
    }

    public HttpDeleteWithBody(final String uri) {
        super();
        setURI(URI.create(uri));
    }
    
    public HttpDeleteWithBody(final URI uri) {
        super();
        setURI(uri);
    }
    
    public HttpDeleteWithBody() { 
        super();
    }

}
