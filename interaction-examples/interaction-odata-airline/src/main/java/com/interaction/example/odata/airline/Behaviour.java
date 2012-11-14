package com.interaction.example.odata.airline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.temenos.interaction.core.hypermedia.Action;
import com.temenos.interaction.core.hypermedia.CollectionResourceState;
import com.temenos.interaction.core.hypermedia.ResourceState;
import com.temenos.interaction.core.hypermedia.ResourceStateMachine;
import com.temenos.interaction.core.hypermedia.UriSpecification;
import com.temenos.interaction.core.hypermedia.validation.HypermediaValidator;

public class Behaviour {

    public static void main(String[] args) {
        ResourceStateMachine hypermediaEngine = new ResourceStateMachine(new Behaviour().getSimpleODataInteractionModel());
        HypermediaValidator validator = HypermediaValidator.createValidator(hypermediaEngine);
        System.out.println(validator.graph());
    }

	public ResourceState getSimpleODataInteractionModel() {
		Map<String, String> uriLinkageEntityProperties = new HashMap<String, String>();
		Map<String, String> uriLinkageProperties = new HashMap<String, String>();
		Properties actionViewProperties = new Properties();
		ResourceState initial = null;
		// create states
		CollectionResourceState sServiceDocument = new CollectionResourceState("ServiceDocument", "ServiceDocument", createActionList(new Action("GETServiceDocument", Action.TYPE.VIEW, actionViewProperties), null), "");
		// identify the initial state
		initial = sServiceDocument;
		ResourceState smetadata = new ResourceState("Metadata", "metadata", createActionList(new Action("GETMetadata", Action.TYPE.VIEW, actionViewProperties), null), "/$metadata", new UriSpecification("metadata", "/$metadata"));
		CollectionResourceState sflights = new CollectionResourceState("Flight", "flights", createActionList(new Action("GETEntities", Action.TYPE.VIEW, actionViewProperties), null), "/Flight");
		ResourceState sflight = new ResourceState("Flight", "flight", createActionList(new Action("GETEntity", Action.TYPE.VIEW, actionViewProperties), null), "/Flight({id})", new UriSpecification("flight", "/Flight({id})"));
		CollectionResourceState sairports = new CollectionResourceState("Airport", "airports", createActionList(new Action("GETEntities", Action.TYPE.VIEW, actionViewProperties), null), "/Airport");
		ResourceState sairport = new ResourceState("Airport", "airport", createActionList(new Action("GETEntity", Action.TYPE.VIEW, actionViewProperties), null), "/Airport({id})", new UriSpecification("airport", "/Airport({id})"));
		CollectionResourceState sflightschedules = new CollectionResourceState("FlightSchedule", "flightschedules", createActionList(new Action("GETEntities", Action.TYPE.VIEW, actionViewProperties), null), "/FlightSchedule");
		ResourceState sflightschedule = new ResourceState("FlightSchedule", "flightschedule", createActionList(new Action("GETEntity", Action.TYPE.VIEW, actionViewProperties), null), "/FlightSchedule({id})", new UriSpecification("flightschedule", "/FlightSchedule({id})"));
		actionViewProperties.put("entity", "FlightSchedule");
		ResourceState sdepartureAirport = new ResourceState("Airport", "departureAirport", createActionList(new Action("GETNavProperty", Action.TYPE.VIEW, actionViewProperties), null), "/FlightSchedule({id})/{navproperty}", new UriSpecification("departureAirport", "/FlightSchedule({id})/{navproperty}"));
		actionViewProperties.put("entity", "FlightSchedule");
		ResourceState sarrivalAirport = new ResourceState("Airport", "arrivalAirport", createActionList(new Action("GETNavProperty", Action.TYPE.VIEW, actionViewProperties), null), "/FlightSchedule({id})/{navproperty}", new UriSpecification("arrivalAirport", "/FlightSchedule({id})/{navproperty}"));

		// create regular transitions
		sServiceDocument.addTransition("GET", smetadata, uriLinkageEntityProperties, uriLinkageProperties);
		sServiceDocument.addTransition("GET", sflights, uriLinkageEntityProperties, uriLinkageProperties);
		sServiceDocument.addTransition("GET", sairports, uriLinkageEntityProperties, uriLinkageProperties);
		sServiceDocument.addTransition("GET", sflightschedules, uriLinkageEntityProperties, uriLinkageProperties);
		uriLinkageEntityProperties.put("id", "flightScheduleID");
		uriLinkageProperties.put("navproperty", "departureAirport");
		sflightschedule.addTransition("GET", sdepartureAirport, uriLinkageEntityProperties, uriLinkageProperties);
		uriLinkageEntityProperties.put("id", "flightScheduleID");
		uriLinkageProperties.put("navproperty", "arrivalAirport");
		sflightschedule.addTransition("GET", sarrivalAirport, uriLinkageEntityProperties, uriLinkageProperties);
		uriLinkageEntityProperties.put("id", "code");
		sdepartureAirport.addTransition("GET", sairport, uriLinkageEntityProperties, uriLinkageProperties);
		uriLinkageEntityProperties.put("id", "code");
		sarrivalAirport.addTransition("GET", sairport, uriLinkageEntityProperties, uriLinkageProperties);

        // create foreach transitions
                uriLinkageEntityProperties.put("id", "flightID");
                sflights.addTransitionForEachItem("GET", sflight, uriLinkageEntityProperties, uriLinkageProperties);
                uriLinkageEntityProperties.put("id", "code");
                sairports.addTransitionForEachItem("GET", sairport, uriLinkageEntityProperties, uriLinkageProperties);
                uriLinkageEntityProperties.put("id", "flightScheduleID");
                sflightschedules.addTransitionForEachItem("GET", sflightschedule, uriLinkageEntityProperties, uriLinkageProperties);
                uriLinkageEntityProperties.put("id", "flightScheduleID");
                sflightschedules.addTransitionForEachItem("GET", sdepartureAirport, uriLinkageEntityProperties, uriLinkageProperties);
                uriLinkageEntityProperties.put("id", "flightScheduleID");
                sflightschedules.addTransitionForEachItem("GET", sarrivalAirport, uriLinkageEntityProperties, uriLinkageProperties);

        // create AUTO transitions

	    return initial;
	}
	
	private List<Action> createActionList(Action view, Action entry) {
		List<Action> actions = new ArrayList<Action>();
		if (view != null)
			actions.add(view);
		if (entry != null)
			actions.add(entry);
		return actions;
	}

}
