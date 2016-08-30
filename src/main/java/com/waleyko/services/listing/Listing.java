package com.waleyko.services.listing;

import java.math.BigDecimal;

import org.joda.time.DateTime;

public class Listing {

    private String theId;
    private String theUserId;
    private String theName;
    private String theDescription;
    private BigDecimal thePurchaseAmount;
    private BigDecimal theSellAmount;
    private DateTime theListDate;
    private DateTime theSellDate;

    public String getId()
    {
        return theId;
    }

    public void setId(String anId)
    {
        theId = anId;
    }

    public String getUserId()
    {
        return theUserId;
    }

    public void setUserId(String aUserId)
    {
        theUserId = aUserId;
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

    public BigDecimal getPurchaseAmount()
    {
        return thePurchaseAmount;
    }

    public void setPurchaseAmount(BigDecimal aPurchaseAmount)
    {
        thePurchaseAmount = aPurchaseAmount;
    }

    public BigDecimal getSellAmount()
    {
        return theSellAmount;
    }

    public void setSellAmount(BigDecimal aSellAmount)
    {
        theSellAmount = aSellAmount;
    }

    public DateTime getListDate()
    {
        return new DateTime(theListDate);
    }

    public void setListDate(DateTime aListDate)
    {
        theListDate = new DateTime(aListDate);
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
