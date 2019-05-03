package com.temenos.interaction.example.mashup.streaming;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.temenos.interaction.example.mashup.streaming.model.Profile;

public class Persistence {
    private final static Logger logger = LoggerFactory.getLogger(Persistence.class);

    @PersistenceContext(unitName = "ResponderServiceHibernate", type = PersistenceContextType.EXTENDED)
    @Access(AccessType.FIELD) 
    private EntityManager entityManager;

    public Persistence(EntityManagerFactory entityManagerFactory) {
    	entityManager = entityManagerFactory.createEntityManager();
    }

	public Profile getProfile(String id) {
		Profile profile = null;
		try {
			profile = entityManager.find(Profile.class, id);
		} catch(Exception e) {
			logger.error("Error while loading entity [" + id + "]: ", e);
		}
		return profile;
    }

}
