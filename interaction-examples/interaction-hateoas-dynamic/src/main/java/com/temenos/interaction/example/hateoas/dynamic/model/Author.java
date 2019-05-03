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

@Entity(name = "author")
@Table(name="AUTHOR")
@XmlRootElement(name = "author")
@XmlAccessorType(XmlAccessType.FIELD)
public class Author {

	@Id
	@Basic(optional = false)
	private String authorID;
    @XmlElement(name = "name")
    private String name;
    
    /* Hibernate & JAXB */
    public Author() {}
    
    public Author(String id, String name) {
    	this.authorID = id;
    	this.name = name;
    }
    
	public String getAuthorID() {
		return authorID;
	}

	public String getName() {
		return name;
	}
}
