package com.waleyko.test.data;

import java.util.ArrayList;
import java.util.List;

import com.waleyko.services.listing.Listing;
import com.waleyko.services.listing.ListingsList;

/**
 * Mock data for unit tests
 *
 */
public class TestData {

    public static Listing getListing()
    {
        Listing ret = new Listing();
        ret.setName("Pink Zipped Stitch Sweater");
        ret.setDescription("NWT pink chunky knit pull over with side zip. Size XS.");
        ret.setPurchaseAmount(15);
        ret.setSellAmount(20);

        return ret;
    }

    public static ListingsList getListings()
    {
        Listing listing1 = getListing();
        Listing listing2 = new Listing();
        listing2.setName("Polka Dot Dress");
        listing2.setDescription("Vintage inspired strapless polka dot dress. Size XS");
        listing2.setPurchaseAmount(20);
        listing2.setSellAmount(35);

        List<Listing> listings = new ArrayList<Listing>();
        listings.add(listing1);
        listings.add(listing2);
        ListingsList ret = new ListingsList();
        ret.setListings(listings);

        return ret;
    }
}
