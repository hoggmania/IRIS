package com.temenos.interaction.example.mashup.twitter;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.temenos.interaction.example.mashup.twitter.model.User;

public class Persistence {
    private final static Logger logger = LoggerFactory.getLogger(Persistence.class);

    @PersistenceContext(unitName = "ResponderServiceHibernate", type = PersistenceContextType.EXTENDED)
    @Access(AccessType.FIELD) 
    private EntityManager entityManager;

    public Persistence(EntityManagerFactory entityManagerFactory) {
    	entityManager = entityManagerFactory.createEntityManager();
    }

	@SuppressWarnings("unchecked")
	public List<User> getUsers() {
		List<User> entities = null;
		try {
			Query jpaQuery = entityManager.createQuery("SELECT n FROM user n");
			entities = jpaQuery.getResultList();
		} catch(Exception e) {
			logger.error("Error while loading entities: ", e);
		}
		return entities;
    }

	public User getUser(Long id) {
		User user = null;
		try {
			user = entityManager.find(User.class, id);
		} catch(Exception e) {
			logger.error("Error while loading entity [" + id + "]: ", e);
		}
		return user;
    }

}
