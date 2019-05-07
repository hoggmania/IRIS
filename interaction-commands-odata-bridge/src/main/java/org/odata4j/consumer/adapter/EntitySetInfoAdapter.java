package org.odata4j.consumer.adapter;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import org.odata4j.core.EntitySetInfo;

public class EntitySetInfoAdapter implements EntitySetInfo {

  private String entitySetName;

  public EntitySetInfoAdapter(String entitySetName) {
    super();
    this.entitySetName = entitySetName;
  }

  @Override
  public String getTitle() {
    return entitySetName;
  }

  @Override
  public String getHref() {
    return entitySetName;
  }

}