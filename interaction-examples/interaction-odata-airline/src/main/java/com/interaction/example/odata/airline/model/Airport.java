package com.interaction.example.odata.airline.model;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@SuppressWarnings("unused")
@Entity
public class Airport {

	@Id
	@Basic(optional = false)
	private String code;
	private String name;
	private String country;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "departureAirport")
	private Collection<FlightSchedule> departures;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "arrivalAirport")
	private Collection<FlightSchedule> arrivals;

	public Airport() {}
}