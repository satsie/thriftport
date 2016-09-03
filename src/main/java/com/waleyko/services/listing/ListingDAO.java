package com.waleyko.services.listing;

public interface ListingDAO {

    public boolean insert(Listing aListing);
    public boolean delete(String anId);
    public Listing getListingById(String anId);

}
