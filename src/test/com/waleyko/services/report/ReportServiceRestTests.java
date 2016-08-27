package com.waleyko.services.report;

import static org.junit.Assert.assertTrue;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.waleyko.test.data.TestData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-configuration.xml")
public class ReportServiceRestTests {

    private static String ENDPOINT;
    private static Server theServer;
    private static boolean serverInitialized = false;

    // Setter injection because Spring doesn't like to inject static variables
    @Value("${rest-endpoint}")
    public void setEndpoint(String anEndpoint)
    {
        ENDPOINT = anEndpoint;
    }

    // Would use @BeforeClass but then the dependency injection wouldn't happen in time.
    @Before
    public void init() throws Exception 
    {
        if (!serverInitialized) {
            startServer();
            serverInitialized = true;
        }
    }

    private static void startServer() throws Exception
    {
        JAXRSServerFactoryBean serverFactory = new JAXRSServerFactoryBean();
        serverFactory.setResourceClasses(ReportService.class);
        serverFactory.setAddress(ENDPOINT);
        serverFactory.getInInterceptors().add(new LoggingInInterceptor());
        serverFactory.getOutInterceptors().add(new LoggingOutInterceptor());
        serverFactory.setProvider(new JacksonJsonProvider());

        theServer = serverFactory.create();
    }

    @AfterClass
    public static void destroy() throws Exception {
       theServer.stop();
       theServer.destroy();
    }

    @Test
    public void testGetStatus()
    {
        WebClient client = WebClient.create(ENDPOINT);
        Response r = client.path("/reports/status").get();

        assertTrue(r.getStatus() == Status.OK.getStatusCode());
    }

    @Test
    public void testGetVersion()
    {
        WebClient client = WebClient.create(ENDPOINT);
        Response r = client.path("/reports/version").get();

        assertTrue(r.readEntity(String.class).toString().equals("1.0"));
        assertTrue(r.getStatus() == Status.OK.getStatusCode());
    }

    @Test
    public void testGetProfitReport() throws JsonProcessingException
    {
        WebClient client = WebClient.create(ENDPOINT);
        client.type(MediaType.APPLICATION_JSON);
        ObjectMapper mapper = new ObjectMapper();

        Response r = client.path("/reports/profit").post(mapper.writeValueAsString(TestData.getListings()));
        assertTrue(r.getStatus() == Status.OK.getStatusCode());
    }
}

