package com.temenos.interaction.example.hateoas.banking;

import java.security.SecureRandom;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.util.Date;

import javax.ws.rs.HttpMethod;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.temenos.interaction.core.command.InteractionCommand;
import com.temenos.interaction.core.command.InteractionContext;
import com.temenos.interaction.core.resource.EntityResource;

public class POSTFundTransferCommand implements InteractionCommand {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(POSTFundTransferCommand.class);

	/* Implement InteractionCommand interface */

	@Override
	public Result execute(InteractionContext ctx) {
		assert(ctx != null);
		assert(ctx.getId() == null || "".equals(ctx.getId()));

		Long key = Math.abs(new SecureRandom().nextLong() % Long.MAX_VALUE);
		Date now = new Date();
		String json = "";
		json += "{";
		json += "  \"_links\" : {";
		json += "    \"self\" : { \"href\" : \"http://localhost:8080/example/api/fundtransfers/new\" }";
		json += "  },";
		json += "  \"id\" : \"" + key + "\",";
		json += "  \"body\" : \"" + now + "\"";
		json += "}";
		EntityResource<FundTransfer> er = null;
		try {
			JSONObject jsonObject = new JSONObject(json);
			er = new EntityResource<FundTransfer>(new FundTransfer(key, jsonObject.toString()));
		}
		catch(JSONException je) {
			LOGGER.error("Error passing JSON Object to Entity", je);
		}
		ctx.setResource(er);
		return Result.SUCCESS;
	}

	public String getMethod() {
		return HttpMethod.POST;
	}

}
