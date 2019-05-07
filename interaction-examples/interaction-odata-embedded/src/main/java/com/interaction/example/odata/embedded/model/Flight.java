package com.interaction.example.odata.embedded.model;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SuppressWarnings("unused")
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	private Long flightID;

	private Long flightScheduleNum;
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date takeoffTime;
	@JoinColumn(name = "flightScheduleNum", referencedColumnName = "flightScheduleID", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private FlightSchedule flightSchedule;


	public Flight() {
	}
}