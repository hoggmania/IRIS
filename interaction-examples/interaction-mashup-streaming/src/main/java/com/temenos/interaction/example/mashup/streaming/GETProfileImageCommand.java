package com.temenos.interaction.example.mashup.streaming;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.temenos.interaction.core.command.InteractionCommand;
import com.temenos.interaction.core.command.InteractionContext;
import com.temenos.interaction.core.command.InteractionException;
import com.temenos.interaction.core.resource.EntityResource;
import com.temenos.interaction.example.mashup.streaming.model.Profile;

/**
 * Gets a profile image from Gravatar
 * @author aphethean
 */
public class GETProfileImageCommand implements InteractionCommand {
	private final static Logger logger = LoggerFactory.getLogger(Persistence.class);

    private final static String GRAVATAR_URL = "http://www.gravatar.com/avatar/";
    
	private GETProfileCommand getProfileCommand;
	
	public GETProfileImageCommand(Persistence p) {
		getProfileCommand = new GETProfileCommand(p);
	}

	/* Implement InteractionCommand interface */
	
	@Override
	public Result execute(InteractionContext ctx) throws InteractionException {
		assert(ctx != null);
		Profile profile = getProfileCommand.getProfile(ctx);
		if (profile == null) {
			return Result.FAILURE;
		}
		
		logger.info("Loading image for ["+profile.getEmail()+"]");
		
		// set and explicit Content-Type ourselves
		ctx.getResponseHeaders().put("Content-Type", "image/jpeg");
		StreamingOutput streamOutput = null;
		try {
			final InputStream stream = profile.getUserID().equals("someone") 
					? getImageFromResources() 
					: getImageFromGravatar(profile.getEmail());
			streamOutput = new StreamingOutput() {
				@Override
				public void write(OutputStream os) throws IOException, WebApplicationException {
					try {
						IOUtils.copy(stream, os);
					} finally {
						IOUtils.closeQuietly(stream);
					}				
				}
			};
		} catch (IOException ioe) {
			logger.error("Unable to get image", ioe);
			return Result.FAILURE;			
		} finally {
		}

		ctx.setResource(new EntityResource<StreamingOutput>(streamOutput));
		return Result.SUCCESS;

	}

	public InputStream getImageFromGravatar(String email) throws IOException {
		String hash = MD5Util.md5Hex(email);
		String urlStr = GRAVATAR_URL + hash;
		URL url = new URL(urlStr);
		return url.openStream();
	}
	
	public InputStream getImageFromResources() {
		return this.getClass().getResourceAsStream("/testimg.jpg");
	}
}
