package com.waleyko.services.listing;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateListingDAO implements ListingDAO {
    private SessionFactory theSessionFactory;

    public HibernateListingDAO(SessionFactory aSessionFactory)
    {
        theSessionFactory = aSessionFactory;
    }

    public boolean insert(Listing aListing)
    {
        Session session = theSessionFactory.openSession();

        Transaction transaction = session.beginTransaction();
        session.persist(aListing);
        transaction.commit();
        session.close();

        return true;
    }

    public boolean delete(String anId)
    {
        // TODO
        return false;
    }

    public Listing getListingById(String anId)
    {
        // TODO
        return null;
    }
}
