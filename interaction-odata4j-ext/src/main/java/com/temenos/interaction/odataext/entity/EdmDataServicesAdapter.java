package com.temenos.interaction.odataext.entity;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import org.odata4j.core.ImmutableList;
import org.odata4j.core.PrefixedNamespace;
import org.odata4j.edm.EdmAssociation;
import org.odata4j.edm.EdmComplexType;
import org.odata4j.edm.EdmDataServices;
import org.odata4j.edm.EdmEntitySet;
import org.odata4j.edm.EdmEntityType;
import org.odata4j.edm.EdmFunctionImport;
import org.odata4j.edm.EdmPropertyBase;
import org.odata4j.edm.EdmSchema;
import org.odata4j.edm.EdmStructuralType;
import org.odata4j.edm.EdmType;


/**
 * Apdator class allowing EDM meta data, which would otherwise be immutable, to be refreshed 
 *
 * @author mlambert
 *
 */
public final class EdmDataServicesAdapter extends EdmDataServices {
	private MetadataOData4j metadataOData4j;
	
	/**
	 * @param version
	 * @param schemas
	 * @param namespaces
	 */
	EdmDataServicesAdapter(MetadataOData4j metadataOData4j) {
		super(null, null, null);
		
		this.metadataOData4j = metadataOData4j;
	}
			
	@Override
	public EdmComplexType findEdmComplexType(String typeName) {
		return metadataOData4j.findEdmComplexType(typeName);
	}

	@Override
	public EdmEntitySet findEdmEntitySet(String arg0) {					
		return metadataOData4j.getEdmMetadata().findEdmEntitySet(arg0);
	}

	@Override
	public EdmType findEdmEntityType(String typeName) {					
		return metadataOData4j.getEdmEntityTypeByTypeName(typeName);
	}

	@Override
	public EdmFunctionImport findEdmFunctionImport(String arg0) {					
		return metadataOData4j.getEdmMetadata().findEdmFunctionImport(arg0);
	}

	@Override
	public EdmPropertyBase findEdmProperty(String arg0) {					
		return metadataOData4j.getEdmMetadata().findEdmProperty(arg0);
	}

	@Override
	public EdmSchema findSchema(String arg0) {					
		return metadataOData4j.getEdmMetadata().findSchema(arg0);
	}

	@Override
	public Iterable<EdmAssociation> getAssociations() {					
		return metadataOData4j.getEdmMetadata().getAssociations();
	}

	@Override
	public Iterable<EdmComplexType> getComplexTypes() {					
		return metadataOData4j.getEdmMetadata().getComplexTypes();
	}

	@Override
	public EdmEntitySet getEdmEntitySet(EdmEntityType type) {					
		return metadataOData4j.getEdmEntitySetByType(type);
	}

	@Override
	public EdmEntitySet getEdmEntitySet(String entitySetName) {					
		return metadataOData4j.getEdmEntitySetByEntitySetName(entitySetName);
	}

	@Override
	public Iterable<EdmEntitySet> getEntitySets() {					
		return metadataOData4j.getEdmMetadata().getEntitySets();
	}

	@Override
	public Iterable<EdmEntityType> getEntityTypes() {					
		return metadataOData4j.getEdmMetadata().getEntityTypes();
	}

	@Override
	public ImmutableList<PrefixedNamespace> getNamespaces() {					
		return metadataOData4j.getEdmMetadata().getNamespaces();
	}

	@Override
	public ImmutableList<EdmSchema> getSchemas() {					
		return metadataOData4j.getEdmMetadata().getSchemas();
	}

	@Override
	public Iterable<EdmStructuralType> getStructuralTypes() {					
		return metadataOData4j.getEdmMetadata().getStructuralTypes();
	}

	@Override
	public Iterable<EdmStructuralType> getSubTypes(EdmStructuralType t) {					
		return metadataOData4j.getEdmMetadata().getSubTypes(t);
	}

	@Override
	public String getVersion() {					
		return metadataOData4j.getEdmMetadata().getVersion();
	}

	@Override
	public EdmType resolveType(String fqTypeName) {					
		return metadataOData4j.getEdmMetadata().resolveType(fqTypeName);
	}

}
