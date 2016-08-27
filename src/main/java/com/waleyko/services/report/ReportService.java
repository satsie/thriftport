package com.waleyko.services.report;

import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.waleyko.services.listing.Listing;
import com.waleyko.services.listing.ListingsList;

@Path("/reports")
@Produces("application/json")
@Consumes("application/json")
public class ReportService {

    public static final String VERSION = "1.0";

    private static final Logger theLogger = Logger.getLogger(ReportService.class.getName());

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

    // Change to GET once the db is hooked up. No need for a payload.
    @POST
    @Path("/profit")
    public static Response getProfitReport(ListingsList aListOfListings)
    {
        theLogger.fine("Getting profit report");
        double totalProfit = 0;
        List<Listing> listings = aListOfListings.getListings();
        
        for (Listing listing : listings) {
            double profit = listing.getSellAmount() - listing.getPurchaseAmount();
            totalProfit += profit;
        }

        return Response.ok().entity(totalProfit).build();
    }

}
