package com.temenos.interaction.loader.properties;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class ReloadConfiguration implements Runnable, ApplicationListener<ContextRefreshedEvent> {
  List<ReconfigurableBean> reconfigurableBeans;
  boolean ctxInitialized = false;

  public void setReconfigurableBeans(List<ReconfigurableBean> reconfigurableBeans) {
    // early type check, and avoid aliassing
    this.reconfigurableBeans = new ArrayList<ReconfigurableBean>();
    
    for (ReconfigurableBean bean: reconfigurableBeans) {
      this.reconfigurableBeans.add(bean);
    }
  }

  public void run() {
	if(ctxInitialized) {
	    for (ReconfigurableBean bean: reconfigurableBeans) {
	        try {
	          bean.reloadConfiguration();
	        } catch (Exception e) {
	          throw new RuntimeException("while reloading configuration of " + bean, e);
	        }
	    }		
	}
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    ctxInitialized = true;	
  }
}
