package com.temenos.interaction.sdk;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.util.List;

public class JoinInfo {

	private String name;
	private String targetType;
	private List<String> annotations;
	
	public JoinInfo(String name, String targetType, List<String> annotations) {
		this.name = name;
		this.targetType = targetType;
		this.annotations = annotations;
	}
	
	public String getName() {
		return name;
	}
	
	public String getTargetType() {
		return targetType;
	}
	
	public List<String> getAnnotations() {
		return annotations;
	}
	
	public boolean equals(Object other) {
		if (other instanceof JoinInfo) {
			JoinInfo theOther = (JoinInfo) other;
			if ((theOther.getName() != null && this.name != null) && theOther.getName().equals(this.name)) {
				if ((theOther.getTargetType() != null && this.targetType != null) && theOther.getTargetType().equals(this.targetType)) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
		return false;
	}
	public int hashCode() {
		int hash = 0;
		if ( name != null ) hash = name.hashCode();
		if ( targetType != null ) hash += 4097 * targetType.hashCode();
		return hash;
	}
}
