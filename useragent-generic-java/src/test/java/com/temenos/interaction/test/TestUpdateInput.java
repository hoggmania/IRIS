package com.temenos.interaction.test;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import com.temenos.useragent.generic.DefaultInteractionSession;
import com.temenos.useragent.generic.InteractionSession;
import com.temenos.useragent.generic.mediatype.AtomPayloadHandler;

@Ignore
// TODO: EXISTS ONLY FOR REFERENCE. To be removed and replaced with integration
// tests against services through an embedded server.
public class TestUpdateInput {

	@Test
	public void testGetAllEntitiesUsingAVersion() {
		InteractionSession session = DefaultInteractionSession.newSession();
		session.basicAuth("INPUTT", "123456")
				.url()
				.baseuri(
						"http://localhost:9089/t24interactiontests-iris/t24interactiontests.svc/GB0010001")
				.path("verCustomers()").get();
		assertTrue(session.entities().isCollection());
		assertFalse(session.entities().collection().isEmpty());
		assertEquals("12345",
				session.entities().byId("12345").get("CustomerCode"));
	}

	@Test
	public void testCreateNewEntityAndUpdate() {
		InteractionSession session = DefaultInteractionSession.newSession();
		session.registerHandler("application/atom+xml",
				AtomPayloadHandler.class)
				.basicAuth("INPUTT", "123456")
				.header("Content-Type", "application/atom+xml")
				.url()
				.baseuri(
						"http://localhost:9089/t24interactiontests-iris/t24interactiontests.svc/GB0010001")
				.path("verCustomer_Inputs()/new").post();

		assertEquals(201, session.result().code());

		String id = session.entities().item().get("CustomerCode");
		session.set("Mnemonic", "C" + id)
				.set("verCustomer_Input_Name1MvGroup/Name1",
						"Mr Robin Peterson" + id)
				.set("verCustomer_Input_ShortNameMvGroup/ShortName", "Rob" + id)
				.set("Sector", "1001").set("Gender", "MALE").set("Title", "MR")
				.set("FamilyName", "Peterson" + id).entities().item().links()
				.byRel("http://temenostech.temenos.com/rels/input").url()
				.post();

		assertEquals(201, session.result().code());

		session.reuse().basicAuth("AUTHOR", "123456").links()
				.byRel("http://temenostech.temenos.com/rels/authorise").url()
				.put();

		assertEquals(200, session.result().code());
	}

	@Test
	public void testForConflictWithConcurrentModificationOfAResource() {

		InteractionSession setupSession = DefaultInteractionSession
				.newSession();
		setupSession
				.header("Content-Type", "application/atom+xml")
				.url()
				.baseuri(
						"http://localhost:9089/t24interactiontests-iris/t24interactiontests.svc/GB0010001")
				.path("verCustomer_Inputs()/new").post();
		assertEquals(201, setupSession.result().code());

		String id = setupSession.entities().item().get("CustomerCode");
		setupSession
				.set("Mnemonic", "C" + id)
				.set("verCustomer_Input_Name1MvGroup/Name1",
						"Mr Robin Peterson" + id)
				.set("verCustomer_Input_ShortNameMvGroup/ShortName", "Rob" + id)
				.set("Sector", "1001").set("Gender", "MALE").set("Title", "MR")
				.set("FamilyName", "Peterson" + id).entities().item().links()
				.byRel("http://temenostech.temenos.com/rels/input").url()
				.post();
		assertEquals(201, setupSession.result().code());

		setupSession.reuse().basicAuth("AUTHOR", "123456").links()
				.byRel("http://temenostech.temenos.com/rels/authorise").url()
				.put();
		assertEquals(200, setupSession.result().code());

		InteractionSession session1 = DefaultInteractionSession.newSession();
		session1.url()
				.baseuri(
						"http://localhost:9089/t24interactiontests-iris/t24interactiontests.svc/GB0010001")
				.path("verCustomer_Inputs('" + id + "')").get();
		String session1Etag = session1.header("ETag");

		InteractionSession session2 = DefaultInteractionSession.newSession();
		session2.url()
				.baseuri(
						"http://localhost:9089/t24interactiontests-iris/t24interactiontests.svc/GB0010001")
				.path("verCustomer_Inputs('" + id + "')").get();
		String session2Etag = session2.header("ETag");

		session1.reuse().header("If-Match", session1Etag)
				.header("Content-Type", "application/atom+xml")
				.set("Gender", "FEMALE").set("Title", "MS").links()
				.byRel("http://temenostech.temenos.com/rels/input").url()
				.post();
		assertEquals(200, setupSession.result().code());

		session2.reuse().header("If-Match", session2Etag)
				.header("Content-Type", "application/atom+xml")
				.set("FamilyName", "Peter" + id).links()
				.byRel("http://temenostech.temenos.com/rels/input").url()
				.post();

		assertEquals(
				"EB-RESOURCE.MODIFIED",
				session2.reuse().links()
						.byRel("http://temenostech.temenos.com/rels/errors")
						.embedded().entity().get("Errors_ErrorsMvGroup/Code"));
	}

	@Test
	public void testForConflictOnReInputOfHeldResource() {
		InteractionSession setupSession = DefaultInteractionSession
				.newSession();
		setupSession
				.header("Content-Type", "application/atom+xml")
				.url()
				.baseuri(
						"http://localhost:9089/t24interactiontests-iris/t24interactiontests.svc/GB0010001")
				.path("verCustomer_Inputs()/new").post();
		assertEquals(201, setupSession.result().code());

		String id = setupSession.reuse().entities().item().get("CustomerCode");
		setupSession
				.set("Mnemonic", "C" + id)
				.set("verCustomer_Input_Name1MvGroup/Name1",
						"Mr Robin Peterson" + id)
				.set("verCustomer_Input_ShortNameMvGroup/ShortName", "Rob" + id)
				.set("Sector", "1001").set("Gender", "MALE").set("Title", "MR")
				.set("FamilyName", "Peterson" + id).entities().item().links()
				.byRel("http://temenostech.temenos.com/rels/input").url()
				.post();
		assertEquals(201, setupSession.result().code());

		setupSession.reuse().basicAuth("AUTHOR", "123456").links()
				.byRel("http://temenostech.temenos.com/rels/authorise").url()
				.put();
		assertEquals(200, setupSession.result().code());

		InteractionSession holdSession = DefaultInteractionSession.newSession();
		holdSession
				.url()
				.baseuri(
						"http://localhost:9089/t24interactiontests-iris/t24interactiontests.svc/GB0010001")
				.path("verCustomer_Inputs('" + id + "')").get();
		holdSession.reuse().header("If-Match", holdSession.header("ETag"))
				.header("Content-Type", "application/atom+xml")
				.set("FamilyName", "Peter" + id).entities().item().links()
				.byRel("http://temenostech.temenos.com/rels/hold").url().post();
		assertEquals(201, holdSession.result().code());

		InteractionSession inputSession = DefaultInteractionSession
				.newSession();
		inputSession
				.url()
				.baseuri(
						"http://localhost:9089/t24interactiontests-iris/t24interactiontests.svc/GB0010001")
				.path("verCustomer_Inputs('" + id + "')").get();

		inputSession.reuse().basicAuth("AUTHOR", "123456")
				.header("If-Match", inputSession.header("ETag"))
				.header("Content-Type", "application/atom+xml")
				.set("Gender", "FEMALE").set("Title", "MS").links()
				.byRel("http://temenostech.temenos.com/rels/input").url()
				.post();
		assertEquals(201, holdSession.result().code());
	}
}
