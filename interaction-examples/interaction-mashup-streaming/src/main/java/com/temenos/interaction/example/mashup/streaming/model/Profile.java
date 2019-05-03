package com.temenos.interaction.example.mashup.streaming.model;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Profile {

	@Id
	@Basic(optional = false)
	private String userID;
	
	private String name;
	private String email;
	
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date DOB;
	
	private String location;
	
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date pictureTaken;
		
	public Profile() {}

	public String getUserID() {
		return userID;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public java.util.Date getDOB() {
		return DOB;
	}

	public String getLocation() {
		return location;
	}

	public java.util.Date getPictureTaken() {
		return pictureTaken;
	}
}