package com.temenos.interaction.example.hateoas.simple;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import java.util.ArrayList;
import java.util.List;

import com.temenos.interaction.core.command.InteractionCommand;
import com.temenos.interaction.core.command.InteractionContext;
import com.temenos.interaction.core.command.InteractionException;
import com.temenos.interaction.core.resource.CollectionResource;
import com.temenos.interaction.core.resource.EntityResource;
import com.temenos.interaction.example.hateoas.simple.model.Profile;

/**
 * Command to get Profiles
 *
 * @author sathisharulmani
 *
 */
public class GETProfilesCommand implements InteractionCommand {
    private Persistence persistence;

    public GETProfilesCommand(Persistence p) {
        persistence = p;
    }

    @Override
    public Result execute(InteractionContext ctx) throws InteractionException {
        List<EntityResource<Profile>> profileEntities = new ArrayList<EntityResource<Profile>>();
        List<Profile> profiles = persistence.getProfiles();
        for (Profile p : profiles) {
            profileEntities.add(new EntityResource<Profile>(p));
        }
        CollectionResource<Profile> profilesResource = new CollectionResource<Profile>("profiles", profileEntities);
        ctx.setResource(profilesResource);

        return Result.SUCCESS;
    }
}
