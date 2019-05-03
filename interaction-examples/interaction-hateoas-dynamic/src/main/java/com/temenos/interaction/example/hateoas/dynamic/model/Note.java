package com.temenos.interaction.example.hateoas.dynamic.model;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name = "note")
@Table(name="NOTE")
@XmlRootElement(name = "note")
@XmlAccessorType(XmlAccessType.FIELD)
public class Note {

	@Id
	@Basic(optional = false)
	private Long noteID;
    @XmlElement(name = "body")
    private String body;
    private String reference;
    
    /* Hibernate & JAXB */
    public Note() {}
    
    public Note(Long id, String body, String reference) {
    	this.noteID = id;
    	this.body = body;
    	this.reference = reference;
    }
    
	public Long getNoteID() {
		return noteID;
	}
    
    public String getBody() {
    	return body;
    }
    
    public String getReference() {
    	return reference;
    }
}
