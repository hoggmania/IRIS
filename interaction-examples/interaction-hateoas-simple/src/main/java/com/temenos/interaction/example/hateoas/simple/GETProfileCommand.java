package com.temenos.interaction.example.hateoas.simple;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import javax.ws.rs.core.Response.Status;

import com.temenos.interaction.core.command.InteractionCommand;
import com.temenos.interaction.core.command.InteractionContext;
import com.temenos.interaction.core.command.InteractionException;
import com.temenos.interaction.core.resource.EntityResource;
import com.temenos.interaction.example.hateoas.simple.model.Profile;

/**
 * Command to get Profile data
 *
 * @author sathisharulmani
 *
 */
public class GETProfileCommand implements InteractionCommand {
    private Persistence persistence;

    public GETProfileCommand(Persistence p) {
        persistence = p;
    }

    @Override
    public Result execute(InteractionContext ctx) throws InteractionException {
        String profileId = ctx.getId();
        Profile profile = persistence.getProfile(profileId);

        if (profile != null) {
            ctx.setResource(new EntityResource<Profile>(profile));
            return Result.SUCCESS;
        } else {
            throw new InteractionException(Status.NOT_FOUND);
        }
    }
}
