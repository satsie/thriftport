package com.waleyko.services.listing;

public interface ListingDAO {

    public boolean insert(Listing aListing);
    public Listing getListingById(String anId);

}
