package com.waleyko.services.listing;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/listings")
@Produces("application/json")
@Consumes("application/json")
public class ListingService {

    public static final String VERSION = "1.0";

    private static final Logger theLogger = Logger.getLogger(ListingService.class.getName());
    private final ListingServiceComponent theComponent;

    public ListingService(ListingServiceComponent aComponent)
    {
        theComponent = aComponent;
    }

    @GET
    @Path("/version")
    public static Response getVersion()
    {
        return Response.ok().entity(VERSION).build();
    }

    @GET
    @Path("/status")
    public static Response getStatus()
    {
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    public Response getListing(@PathParam("id") String anId)
    {
        try {
            theLogger.fine("Getting listing for UUID:" + anId);
            String ret = "";
            Listing listing = theComponent.getListingById(anId);

            if (listing != null) {
                ObjectMapper jsonMapper = new ObjectMapper();
                ret = jsonMapper.writeValueAsString(listing);
            }
            return Response.ok(ret, MediaType.APPLICATION_JSON).build();
        }
        catch (JsonProcessingException e) {
            theLogger.warning("Error mapping listing " + anId + " to JSON");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("/")
    public Response createListing(Listing aListing)
    {
        String uuid = UUID.randomUUID().toString();
        aListing.setId(uuid);

        boolean success = theComponent.createListing(aListing);

        if (success) {
            return Response.status(Status.CREATED).entity(uuid).build();
        }
        else {
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteListing(@PathParam("id") String anId)
    {
        boolean success = theComponent.deleteListing(anId);

        if (success) {
            return Response.status(Status.OK).build(); 
        }
        else {
            theLogger.log(Level.WARNING, "Error deleting listing " + anId);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
