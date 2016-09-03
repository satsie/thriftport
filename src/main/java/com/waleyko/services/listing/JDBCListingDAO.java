package com.waleyko.services.listing;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.joda.time.DateTime;

public class JDBCListingDAO implements ListingDAO{

    private DataSource theDatasource;

    private static final String INSERT_LISTING="INSERT INTO listings "
            + "(id, userId, name, description, purchaseAmount, sellAmount, listDate, sellDate) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_LISTING = "DELETE FROM listings where id = ?";
    private static final String GET_LISTING_BY_ID="SELECT * FROM listings "
            + "WHERE id = ?";

    public JDBCListingDAO(DataSource aDatasource) {
        theDatasource = aDatasource;
    }

    public boolean insert(Listing aListing)
    {
        Connection connection = null;
        boolean ret = false;

        try {
            connection = theDatasource.getConnection();
            PreparedStatement ps = connection.prepareStatement(INSERT_LISTING);
            ps.setString(1, aListing.getId());
            ps.setString(2, aListing.getUserId());
            ps.setString(3, aListing.getName());
            ps.setString(4, aListing.getDescription());
            ps.setBigDecimal(5, aListing.getPurchaseAmount());
            ps.setBigDecimal(6, aListing.getSellAmount());
            ps.setDate(7, new Date(aListing.getListDate().toDate().getTime()));    // cast from Java Date to sql Date
            ps.setDate(8, new Date(aListing.getSellDate().toDate().getTime()));

            int result = ps.executeUpdate();
            if (result == 1) { ret=true; };
            ps.close();

            return ret;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException e) {
                }
            }
        }
    }

    public boolean delete(String anId)
    {
        Connection connection = null;
        boolean ret = false;
        
        try {
            connection = theDatasource.getConnection();
            PreparedStatement ps = connection.prepareStatement(DELETE_LISTING);
            ps.setString(1, anId);

            int result = ps.executeUpdate();
            if (result == 1) { ret=true; };
            ps.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException e) {
                }
            }
        }
        return ret;
    }

    public Listing getListingById(String anId)
    {
        Connection connection = null;
        Listing ret = null;

        try {
            connection = theDatasource.getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_LISTING_BY_ID);
            ps.setString(1, anId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ret = new Listing();
                ret.setId(rs.getString("id"));
                ret.setUserId(rs.getString("userId"));
                ret.setName(rs.getString("name"));
                ret.setDescription(rs.getString("description"));
                ret.setPurchaseAmount(rs.getBigDecimal("purchaseAmount"));
                ret.setSellAmount(rs.getBigDecimal("sellAmount"));
                ret.setListDate(new DateTime(rs.getDate("listDate")));
                ret.setSellDate(new DateTime(rs.getDate("sellDate")));
            }

            rs.close();
            ps.close();

            return ret;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException e) {
                }
            }
        }
    }
}
