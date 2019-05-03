package com.temenos.interaction.example.hateoas.banking;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DaoHibernate {
    private final static Logger logger = LoggerFactory.getLogger(DaoHibernate.class);

    @PersistenceContext(unitName = "ResponderServiceHibernate", type = PersistenceContextType.EXTENDED)
    @Access(AccessType.FIELD) 
    private EntityManager entityManager;

    public DaoHibernate(EntityManagerFactory entityManagerFactory) {
    	entityManager = entityManagerFactory.createEntityManager();
    }
    
    public void putFundTransfer(FundTransfer ft) {
    	try {
    		entityManager.getTransaction().begin();
    		entityManager.merge(ft); 
    		entityManager.getTransaction().commit();    		
    	} catch(EntityExistsException eee) {
			logger.error("Failed to commit transaction - entity already exists: ", eee);
    	} catch(IllegalArgumentException iae) {
			logger.error("Failed to commit transaction - object is not an entity: ", iae);
    	} catch(TransactionRequiredException tre) {
			logger.error("Failed to commit transaction - No transaction exists: ", tre);
    	} finally {
    		if (entityManager.getTransaction().isActive())
    			entityManager.getTransaction().rollback();
    	}
    	
    }

	@SuppressWarnings("unchecked")
	public List<FundTransfer> getFundTransfers() {
		List<FundTransfer> entities = null;
		try {
			Query jpaQuery = entityManager.createQuery("SELECT ft FROM FundTransfer ft");
			entities = jpaQuery.getResultList();
		}
		catch(Exception e) {
			logger.error("Error while loading entities: ", e);
		}
		return entities;
    }

	public FundTransfer getFundTransfer(Long id) {
		FundTransfer ft = null;
		try {
			ft = entityManager.find(FundTransfer.class, id);
		}
		catch(Exception e) {
			logger.error("Error while loading entity [" + id + "]: ", e);
		}
		return ft;
    }
	
	@SuppressWarnings("unchecked")
	public List<Customer> getCustomers() {
		List<Customer> entities = null;
		try {
			Query jpaQuery = entityManager.createQuery("SELECT customer FROM Customer customer");
			entities = jpaQuery.getResultList();
		}
		catch(Exception e) {
			logger.error("Error while loading entities: ", e);
		}
		return entities;
    }

	public Customer getCustomer(String name) {
		Customer customer = null;
		try {
			customer = entityManager.find(Customer.class, name);
		}
		catch(Exception e) {
			logger.error("Error while loading entity [" + name + "]: ", e);
		}
		return customer;
    }
}
