package com.waleyko.services.listing;

import org.joda.time.DateTime;

public class Listing {

    private String theUuid;
    private String theName;
    private String theDescription;
    private double thePurchaseAmount;
    private double theSellAmount;
    private DateTime theSellDate;

    public String getUuid()
    {
        return theUuid;
    }

    public void setUuid(String aUuid)
    {
        theUuid = aUuid;
    }

    public String getName()
    {
        return theName;
    }

    public void setName(String aName)
    {
        theName = aName;
    }

    public String getDescription()
    {
        return theDescription;
    }

    public void setDescription(String aDescription)
    {
        theDescription = aDescription;
    }

    public double getPurchaseAmount()
    {
        return thePurchaseAmount;
    }

    public void setPurchaseAmount(double aPurchaseAmount)
    {
        thePurchaseAmount = aPurchaseAmount;
    }

    public double getSellAmount()
    {
        return theSellAmount;
    }

    public void setSellAmount(double aSellAmount)
    {
        theSellAmount = aSellAmount;
    }

    public DateTime getSellDate()
    {
        return (theSellDate == null) ? null : new DateTime(theSellDate);
    }

    public void setSellDate(DateTime aSellDate)
    {
        theSellDate = new DateTime(aSellDate);
    }
}
