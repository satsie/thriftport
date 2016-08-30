package com.waleyko.services.listing;

import static org.junit.Assert.assertTrue;

import java.util.UUID;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.waleyko.test.CustomTestListener;
import com.waleyko.test.CustomTestRunner;
import com.waleyko.test.data.TestData;

@RunWith(CustomTestRunner.class)
@ContextConfiguration("classpath:test-configuration.xml")
public class ListingServiceRestTests implements CustomTestListener {

    @Value("${rest-endpoint}")
    private String ENDPOINT;
    private static Server theServer;

    @Autowired
    private ListingService theListingService;

    // The CustomTestRunner will call this before every test
    // Not using @BeforeTest because it gets called before Spring dependency injection
    public void beforeClassSetup() throws Exception 
    {
        startServer();
    }

    private void startServer() throws Exception
    {
        JAXRSServerFactoryBean serverFactory = new JAXRSServerFactoryBean();
        //serverFactory.setResourceClasses(ListingService.class);
        serverFactory.setServiceBean(theListingService);
        serverFactory.setAddress(ENDPOINT);
        serverFactory.getInInterceptors().add(new LoggingInInterceptor());
        serverFactory.getOutInterceptors().add(new LoggingOutInterceptor());
        serverFactory.setProvider(new JacksonJsonProvider());

        theServer = serverFactory.create();
    }

    @AfterClass
    public static void tearDown() throws Exception{
        theServer.stop();
        theServer.destroy();
    }

    @Test
    public void testGetStatus()
    {
        WebClient client = WebClient.create(ENDPOINT);
        Response r = client.path("/listings/status").get();

        assertTrue(r.getStatus() == Status.OK.getStatusCode());
    }

    @Test
    public void testGetVersion()
    {
        WebClient client = WebClient.create(ENDPOINT);
        Response r = client.path("/listings/version").get();

        assertTrue(r.readEntity(String.class).toString().equals("1.0"));
        assertTrue(r.getStatus() == Status.OK.getStatusCode());
    }

    @Test
    public void testGetListing()
    {
        WebClient client = WebClient.create(ENDPOINT);
        String uuid = UUID.randomUUID().toString();

        Response r = client.path("/listings/" + uuid).get();
        assertTrue(r.getStatus() == Status.OK.getStatusCode());
    }

    @Test
    public void testCreateListing() throws JsonProcessingException
    {
        WebClient client = WebClient.create(ENDPOINT);
        client.type(MediaType.APPLICATION_JSON);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JodaModule());

        Response r = client.path("/listings").post(mapper.writeValueAsString(TestData.getListing()));
        assertTrue(r.getStatus() == Status.CREATED.getStatusCode());
    }
}
