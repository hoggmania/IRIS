package com.temenos.interaction.media.hal;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


public class Children {

	private String name;
	private int age;
	private String shoeSize;
	
	public Children(String name, int age, String shoeSize) {
		this.name = name;
		this.age = age;
		this.shoeSize = shoeSize;
	}
	
	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getShoeSize() {
		return shoeSize;
	}
}
