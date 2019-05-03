package com.interaction.example.odata.error.model;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@SuppressWarnings("unused")
public class Airport {

	@Id
	@Basic(optional = false)
	private String code;

	private String name;
	private String country;

    @OneToMany(mappedBy="departureAirportCode")
    private Collection<FlightSchedule> departures;
    @OneToMany(mappedBy="arrivalAirportCode")
    private Collection<FlightSchedule> arrivals;

	public Airport() {
	}
}