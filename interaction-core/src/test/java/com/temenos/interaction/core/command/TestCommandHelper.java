package com.temenos.interaction.core.command;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.*;

import javax.ws.rs.core.GenericEntity;

import com.temenos.interaction.core.hypermedia.Link;
import com.temenos.interaction.core.hypermedia.Transition;
import com.temenos.interaction.core.resource.RESTResource;
import org.junit.Before;
import org.junit.Test;

import com.temenos.interaction.core.entity.Entity;
import com.temenos.interaction.core.entity.EntityProperties;
import com.temenos.interaction.core.entity.EntityProperty;
import com.temenos.interaction.core.entity.GenericError;
import com.temenos.interaction.core.resource.EntityResource;
import com.temenos.interaction.core.resource.ResourceTypeHelper;

public class TestCommandHelper {

	private static final String ENTITY_TAG = "ABCDE";

	private Entity entity;
	private Map<Transition, RESTResource> embedded;
	private Collection<Link> links;

	@Before
	public void setup() {
		entity = createMockEntity("Customer");;
		embedded = new HashMap<>();
		links = new ArrayList<>();
	}

	@Test
	public void testCreateEntityResource() {
		EntityResource<GenericError> er = CommandHelper.createEntityResource(new GenericError("123", "My error message"), GenericError.class);

		GenericEntity<EntityResource<GenericError>> ge = er.getGenericEntity();
		assertTrue(ResourceTypeHelper.isType(ge.getRawType(), ge.getType(), EntityResource.class));
		assertTrue(ResourceTypeHelper.isType(ge.getRawType(), ge.getType(), EntityResource.class, GenericError.class));
	}
	
	@Test
	public void testCreateEntityResourceWithEntityName() {
		EntityResource<Entity> er = CommandHelper.createEntityResource("Customer", createMockEntity("Customer"), Entity.class);

		GenericEntity<EntityResource<Entity>> ge = er.getGenericEntity();
		assertTrue(ResourceTypeHelper.isType(ge.getRawType(), ge.getType(), EntityResource.class));
		assertTrue(ResourceTypeHelper.isType(ge.getRawType(), ge.getType(), EntityResource.class, Entity.class));
	}

	@Test
	public void testCreateEntityResourceWithExplicitType() {
		EntityResource<Entity> er = CommandHelper.createEntityResource("Customer", createMockEntity("Customer"), Entity.class);

		GenericEntity<EntityResource<Entity>> ge = er.getGenericEntity();
		assertTrue(ResourceTypeHelper.isType(ge.getRawType(), ge.getType(), EntityResource.class));
		assertTrue(ResourceTypeHelper.isType(ge.getRawType(), ge.getType(), EntityResource.class, Entity.class));
	}

	@Test(expected=AssertionError.class)
	public void testCreateEntityResourceWithWrongExplicitType() {
		EntityResource<Entity> er = CommandHelper.createEntityResource("Customer", createMockEntity("Customer"), GenericError.class);

		GenericEntity<EntityResource<Entity>> ge = er.getGenericEntity();
		assertTrue(ResourceTypeHelper.isType(ge.getRawType(), ge.getType(), EntityResource.class));
		assertTrue(ResourceTypeHelper.isType(ge.getRawType(), ge.getType(), EntityResource.class, Entity.class));
	}
	
	@Test
	public void testCreateEntityResourceWithoutExplicitEntityName() {
		EntityResource<Entity> er = CommandHelper.createEntityResource(createMockEntity("Customer"));

		GenericEntity<EntityResource<Entity>> ge = er.getGenericEntity();
		assertTrue(ResourceTypeHelper.isType(ge.getRawType(), ge.getType(), EntityResource.class));
		assertTrue(ResourceTypeHelper.isType(ge.getRawType(), ge.getType(), EntityResource.class, Entity.class));
		assertEquals("Customer", ge.getEntity().getEntityName());
		assertEquals("Customer", ge.getEntity().getEntity().getName());
	}

	@Test
	public void testCreateEntityResourceFromClonableEntityResource() {
		EntityResource<Entity> er = CommandHelper.createEntityResource(entity);
		er.setEmbedded(embedded);
		er.setLinks(links);
		er.setEntityTag(ENTITY_TAG);

		EntityResource<Entity> erCopy = CommandHelper.createEntityResource(er);

		assertNotSame(er, erCopy);
		assertEquals(er.getEntity(), erCopy.getEntity());
		assertEquals(er.getEmbedded(), erCopy.getEmbedded());
		assertEquals(er.getLinks(), erCopy.getLinks());
		assertEquals(er.getEntityTag(), erCopy.getEntityTag());
		assertEquals(entity, erCopy.getEntity());
		assertEquals(embedded, erCopy.getEmbedded());
		assertEquals(links, erCopy.getLinks());
		assertEquals(ENTITY_TAG, erCopy.getEntityTag());
	}

	@Test
	public void testCreateEntityResourceFromNonClonableEntityResource() {
		EntityResource<Entity> er = new EntityResource<Entity>(entity) {
			private Entity entity;
			@Override
			public EntityResource<Entity> clone() throws CloneNotSupportedException {
				throw new CloneNotSupportedException();
			}
		};
		er.setEmbedded(embedded);
		er.setLinks(links);
		er.setEntityTag(ENTITY_TAG);

		EntityResource<Entity> erCopy = CommandHelper.createEntityResource(er);

		assertNotSame(er, erCopy);
		assertEquals(er.getEntity(), erCopy.getEntity());
		assertEquals(er.getEmbedded(), erCopy.getEmbedded());
		assertEquals(er.getLinks(), erCopy.getLinks());
		assertEquals(er.getEntityTag(), erCopy.getEntityTag());
		assertEquals(entity, erCopy.getEntity());
		assertEquals(embedded, erCopy.getEmbedded());
		assertEquals(links, erCopy.getLinks());
		assertEquals(ENTITY_TAG, erCopy.getEntityTag());
	}

	@Test
	public void testCreateEntityResourceFromNonClonableEntityResourceWithoutEntity() {
		EntityResource<Entity> er = new EntityResource<Entity>() {
			private Entity entity;
			@Override
			public EntityResource<Entity> clone() throws CloneNotSupportedException {
				throw new CloneNotSupportedException();
			}
		};
		er.setEmbedded(embedded);
		er.setLinks(links);
		er.setEntityTag(ENTITY_TAG);

		EntityResource<Entity> erCopy = CommandHelper.createEntityResource(er);

		assertNotSame(er, erCopy);
		assertEquals(er.getEntity(), erCopy.getEntity());
		assertEquals(er.getEmbedded(), erCopy.getEmbedded());
		assertEquals(er.getLinks(), erCopy.getLinks());
		assertEquals(er.getEntityTag(), erCopy.getEntityTag());
		assertNull(erCopy.getEntity());
		assertEquals(embedded, erCopy.getEmbedded());
		assertEquals(links, erCopy.getLinks());
		assertEquals(ENTITY_TAG, erCopy.getEntityTag());
	}

	@Test
	public void testCreateEntityResourceFromNullEntityResource() {
		assertNull(CommandHelper.createEntityResource((EntityResource<?>) null));
	}

	@SuppressWarnings("unchecked")
	@Test
	public<E> void testGetEffectiveGenericTypeVariable() {
		Entity entity = createMockEntity("MyEntity");
		GenericEntity<E> ge = new GenericEntity<E>((E)entity) {};
		Type t = CommandHelper.getEffectiveGenericType(ge.getType(), entity);
		
		assertTrue(t instanceof TypeVariable);
		TypeVariable<?> tv = (TypeVariable<?>) t;
		assertEquals("Entity", tv.getName());
	}
	
	private Entity createMockEntity(String entityName) {
		EntityProperties customerFields = new EntityProperties();
		customerFields.setProperty(new EntityProperty("name", "Fred"));
		return new Entity(entityName, customerFields);
	}
}
