package com.temenos.interaction.test;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import java.util.Arrays;
import java.util.List;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Run all JUnit test cases twice. Once for Jersey and once for CXF runtime.
 */
@RunWith(Parameterized.class)
public abstract class AbstractRuntimeTest {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  protected Logger getLogger() {
    return this.log;
  }

  protected enum RuntimeFacadeType {
    JERSEY, CXF
  }

  public AbstractRuntimeTest(RuntimeFacadeType type) {
    switch (type) {
    case JERSEY:
      this.rtFacade = new JerseyRuntimeFacade();
      break;
    case CXF:
      this.rtFacade = new CxfRuntimeFacade();
      break;
    default:
      throw new RuntimeException("JAX-RS runtime type not supported: " + type);
    }
    this.getLogger().info("******************************************************************");
    this.getLogger().info("Activated Runtime Facade: " + type);
    this.getLogger().info("******************************************************************");
  }

  @Parameterized.Parameters
  public static List<Object[]> data() {
    // TODO enable CXF as soon as implementation is completed and all test cases are green
    Object[][] a = new Object[][] { { RuntimeFacadeType.JERSEY } /*, { RuntimeFacadeType.CXF } */ };
    return Arrays.asList(a);
  }

  protected RuntimeFacade rtFacade;

}
