package com.temenos.interaction.odataext.odataparser.data;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/
import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.odata4j.expression.BoolCommonExpression;
import org.odata4j.producer.resources.OptionsQueryParser;

public class AccessProfileTest {

    @Test
    public void testConstruct() {
        BoolCommonExpression filterStr = OptionsQueryParser.parseFilter("aname eq avalue");
        RowFilters expectedFilters = new RowFilters(filterStr);
        
        Set<FieldName> expectedSelects = new HashSet<FieldName>();
        
        AccessProfile profile = new AccessProfile(expectedFilters, expectedSelects);

        assertEquals(expectedFilters, profile.getNewRowFilters());
        assertEquals(expectedSelects, profile.getFieldNames());
    }
}
