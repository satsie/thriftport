package com.waleyko.services.listing;

import java.util.ArrayList;
import java.util.List;

public class ListingsList {

    List<Listing> theListings;

    public List<Listing> getListings()
    {
        return (theListings == null) ? new ArrayList<Listing>() : theListings;
    }

    public void setListings(List<Listing> aListOfListings)
    {
        theListings = aListOfListings;
    }
}
