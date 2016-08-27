package com.waleyko.services.listing;

import java.util.UUID;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/listings")
@Produces("application/json")
@Consumes("application/json")
public class ListingService {

    public static final String VERSION = "1.0";

    private static final Logger theLogger = Logger.getLogger(ListingService.class.getName());

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
    @Path("/{uuid}")
    public static Response getListing(@PathParam("uuid") String aUuid)
    {
        theLogger.fine("Getting listing for UUID:" + aUuid);
        
        return Response.ok().entity(aUuid).build();
    }

    @POST
    @Path("/")
    public static Response createListing(Listing aListing)
    {
        String uuid = UUID.randomUUID().toString();
        aListing.setUuid(uuid);

        // save the listing

        return Response.status(Status.CREATED).entity(aListing).build();
    }
}
