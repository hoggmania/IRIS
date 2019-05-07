package com.temenos.interaction.core.hypermedia;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class TestLink {

    @Test
    public void testGetRelativeHref() throws Exception {
        Link link = new Link(null, "NoteToPersonLink", "Person", 
                "http://localhost:8080/example/interaction-odata-notes.svc/Notes(1)/Person",
                null, null, "GET", null);
        String hrefTransition = link.getRelativeHref("example/interaction-odata-notes.svc");
        assertEquals("Notes(1)/Person", hrefTransition);
    }

    /**
     * Where a transition is between two resources with a basePath, we simply
     * strip the leading paths if they match to form a relative path to the target
     * @throws Exception
     */
    @Test
    public void testGetRelativeHrefWithBasePath() throws Exception {
        Link link = new Link(null, "ServiceDocumentToEntitySet", "rel", 
                "http://localhost:8080/example/interaction-odata-multicompany.svc/MockCompany001/Flights",
                null, null, "GET", null);
        String relativeHref = link.getRelativeHref("http://localhost:8080/example/interaction-odata-multicompany.svc/{companyid}");
        assertEquals("Flights", relativeHref);
    }

    
    
    @Test
    public void testGetRelativeHref_FundsTransferNew() throws Exception {
        Link link = new Link(null, "ServiceDocumentToEntitySet", "rel", 
                "http://portal3.xlb2.lo/tapt24-iris/tapt24.svc/LU0010001/FundsTransfer_FtTaps()/new",
                null, null, "GET", null);
        String relativeHref = link.getRelativeHref("http://portal3.xlb2.lo/tapt24-iris/tapt24.svc/{companyid}");
        assertEquals("FundsTransfer_FtTaps()/new", relativeHref);

    }

    @Test
    public void testGetRelativeHref_FundsTransferValidate() throws Exception {
        Link link = new Link(null, "ServiceDocumentToEntitySet", "rel", 
                "http://portal3.xlb2.lo/tapt24-iris/tapt24.svc/LU0010001/FundsTransfer_FtTaps('FT1336500058')/validate",
                null, null, "GET", null);
        String relativeHref = link.getRelativeHref("http://portal3.xlb2.lo/tapt24-iris/tapt24.svc/{companyid}");
        assertEquals("FundsTransfer_FtTaps('FT1336500058')/validate", relativeHref);

    }

    @Test
    public void testGetRelativeHref_FundsTransferFtTaps() throws Exception {
        Link link = new Link(null, "ServiceDocumentToEntitySet", "rel", 
                "http://portal3.xlb2.lo/tapt24-iris/tapt24.svc/LU0010001/FundsTransfer_FtTaps()",
                null, null, "GET", null);
        String relativeHref = link.getRelativeHref("http://portal3.xlb2.lo/tapt24-iris/tapt24.svc/{companyid}");
        assertEquals("FundsTransfer_FtTaps()", relativeHref);

    }

    @Test
    public void testGetRelativeHref_FundsTransferFtNewHold() throws Exception {
        Link link = new Link(null, "ServiceDocumentToEntitySet", "rel", 
                "http://portal3.xlb2.lo/tapt24-iris/tapt24.svc/LU0010001/FundsTransfer_FtTaps()/new/hold?id=FT1336500058",
                null, null, "GET", null);
        String relativeHref = link.getRelativeHref("http://portal3.xlb2.lo/tapt24-iris/tapt24.svc/{companyid}");
        assertEquals("FundsTransfer_FtTaps()/new/hold?id=FT1336500058", relativeHref);

    }
    
    @Test
    public void testGetRelativeHrefHttp() throws Exception {
        Link link = new Link(null, "ServiceDocumentToEntitySet", "rel",
                "http://portal3.xlb2.lo/tapt24-iris/tapt24.svc/LU0010001/FundsTransfer_FtTaps()/new", null, null, "GET",
                null);
        String relativeHref = link.getRelativeHref("http://portal3.xlb2.lo:80/tapt24-iris/tapt24.svc/{companyid}");
        assertEquals("FundsTransfer_FtTaps()/new", relativeHref);

    }

    @Test
    public void testGetRelativeHrefHttps() throws Exception {
        Link link = new Link(null, "ServiceDocumentToEntitySet", "rel",
                "https://portal3.xlb2.lo/tapt24-iris/tapt24.svc/LU0010001/FundsTransfer_FtTaps()/new", null, null,
                "GET", null);
        String relativeHref = link.getRelativeHref("https://portal3.xlb2.lo:443/tapt24-iris/tapt24.svc/{companyid}");
        assertEquals("FundsTransfer_FtTaps()/new", relativeHref);

    }

    @Test
    public void testGetRelativeHref1() throws Exception {
        Link link = new Link(null, "ServiceDocumentToEntitySet", "rel", 
                "http://example.com/boo/moo",
                null, null, "GET", null);
        String relativeHref = link.getRelativeHref("http://example.com/{foo}");
        assertEquals("moo", relativeHref);
    }

    @Test
    public void testTitleWithoutLabel() throws Exception {
        Transition t = mock(Transition.class);
        when(t.getLabel()).thenReturn(null);
        ResourceState state = mock(ResourceState.class);
        when(state.getName()).thenReturn("FlightSchedules");
        when(t.getTarget()).thenReturn(state);
        Link link = new Link(t, "arrivals", "http://localhost:8080/example/Airport/arrivals", "GET");

        assertEquals("FlightSchedules", link.getTitle());
        assertEquals("arrivals", link.getRel());
    }

    @Test
    public void testTitleWithEmptyLabel() throws Exception {
        Transition t = mock(Transition.class);
        when(t.getLabel()).thenReturn("");
        ResourceState state = mock(ResourceState.class);
        when(state.getName()).thenReturn("FlightSchedules");
        when(t.getTarget()).thenReturn(state);
        Link link = new Link(t, "arrivals", "http://localhost:8080/example/Airport/arrivals", "GET");

        assertEquals("FlightSchedules", link.getTitle());
        assertEquals("arrivals", link.getRel());
    }
    
    @Test
    public void testTitleWithLabel() throws Exception {
        Transition t = mock(Transition.class);
        when(t.getLabel()).thenReturn("arrivals");
        ResourceState state = mock(ResourceState.class);
        when(state.getName()).thenReturn("FlightSchedules");
        when(t.getTarget()).thenReturn(state);
        Link link = new Link(t, "arrivals", "http://localhost:8080/example/Airport/arrivals", "GET");

        assertEquals("arrivals", link.getTitle());
        assertEquals("arrivals", link.getRel());
    }
    
    @Test
    public void testEquals() {
        Link aLink = new Link.Builder()
                .id("id")
                .href("href")
                .title("title")
                .build();
        Link aNOtherLink = new Link.Builder()
                .id("id")
                .href("href")
                .title("title")
                .build();
        assertEquals(aLink, aNOtherLink);
        Link aLinkDiffId = new Link.Builder()
                .id("id_diff")
                .href("href")
                .title("title")
                .build();
        assertNotSame(aLink, aLinkDiffId);
        Link aLinkDiffHref = new Link.Builder()
                .id("id")
                .href("href_diff")
                .title("title")
                .build();
        assertNotSame(aLink, aLinkDiffHref);
        Link aLinkDiffTitle = new Link.Builder()
                .id("id")
                .href("href")
                .title("title_diff")
                .build();
        assertNotSame(aLink, aLinkDiffTitle);
    }
    
    @Test
    public void testLinkIdEquals() {
        Link aLink = new Link.Builder()
                .id("id")
                .href("href")
                .title("title")
                .linkId("123456")
                .build();
        Link aNOtherLink = new Link.Builder()
                .id("id")
                .href("href")
                .title("title")
                .linkId("123456")
                .build();
        assertEquals(aLink, aNOtherLink);
        
        aLink = new Link.Builder()
                .linkId("123456")
                .build();
        aNOtherLink = new Link.Builder()
                .linkId("123456")
                .build();
        assertEquals(aLink, aNOtherLink);
        
        aLink = new Link.Builder()
                .linkId("123456")
                .build();
        aNOtherLink = new Link.Builder()
                .linkId("654321")
                .build();
        assertNotSame(aLink, aNOtherLink);
    }
    
    @Test
    public void testLinkIdWithTransition() {
        Transition t = mock(Transition.class);
        when(t.getLabel()).thenReturn("arrivals");
        when(t.getLinkId()).thenReturn("123456");
        ResourceState state = mock(ResourceState.class);
        when(state.getName()).thenReturn("FlightSchedules");
        when(t.getTarget()).thenReturn(state);
        Link link = new Link(t, "arrivals", "http://localhost:8080/example/Airport/arrivals", "GET");

        assertEquals("arrivals", link.getTitle());
        assertEquals("arrivals", link.getRel());
        assertEquals("123456", link.getLinkId());
    }
    
    @Test
    public void testNullLinkId() {
        Link link = new Link(null, "ServiceDocumentToEntitySet", "rel", 
            "http://example.com/boo/moo",
            null, null, "GET", null);
        assertNull(link.getLinkId());
    }
    
    @Test
    public void testNullLinkIdWithTransition() {
        Transition t = mock(Transition.class);
        when(t.getLabel()).thenReturn("arrivals");
        ResourceState state = mock(ResourceState.class);
        when(state.getName()).thenReturn("FlightSchedules");
        when(t.getTarget()).thenReturn(state);
        Link link = new Link(t, "arrivals", "http://localhost:8080/example/Airport/arrivals", "GET");

        assertEquals("arrivals", link.getTitle());
        assertEquals("arrivals", link.getRel());
        assertNull(link.getLinkId());
    }
    
    @Test
    public void testFieldLabelEquals() {
        Link aLink = new Link.Builder()
        .id("id")
        .href("href")
        .title("title")
        .sourceField("ABCD")
        .build();
        
        Link aNOtherLink = new Link.Builder()
        .id("id")
        .href("href")
        .title("title")
        .sourceField("ABCD")
        .build();
        assertEquals(aLink, aNOtherLink);

        aLink = new Link.Builder()
        .linkId("EFGH")
        .build();
        aNOtherLink = new Link.Builder()
        .linkId("EFGH")
        .build();
        assertEquals(aLink, aNOtherLink);

        aLink = new Link.Builder()
        .linkId("IJKL")
        .build();
        aNOtherLink = new Link.Builder()
        .linkId("MNOP")
        .build();
        assertNotSame(aLink, aNOtherLink);
    }

}
