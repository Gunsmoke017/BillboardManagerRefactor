package interfacesimpl.admin;

import database.BillboardDb;
import entity.billboards.Billboard;
import entity.billboards.BookingDetails;
import enums.State;
import exceptions.DatabaseInsertionException;
import exceptions.DoesNotExistException;
import exceptions.InvalidKeyEnteredException;
import exceptions.NoConnectionException;
import interfaces.admin.IAdminBillboardMethods;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminBillboardMethods implements IAdminBillboardMethods {
    BillboardDb billboardDb = new BillboardDb();
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    @Override
    public boolean registerNewBillboard(Billboard billboard, boolean connection) {
        int upd;
        boolean outcome = false;
        String INSERT = "INSERT INTO billboardsdb (serialnumber, location, dimension, state, priceperhr) VALUES (?,?,?,?,?)";
        if (connection) {
            try {
                preparedStatement = billboardDb.getConnections().prepareStatement(INSERT);
                preparedStatement.setLong(1, billboard.getSerialNumber());
                preparedStatement.setString(2, billboard.getLocation());
                preparedStatement.setString(3, billboard.getDimension());
                preparedStatement.setString(4, billboard.getState() + "");
                preparedStatement.setInt(5, billboard.getPricePerHr());
                upd = preparedStatement.executeUpdate();

                if (upd == 0) {
                    throw new DatabaseInsertionException(" >> Could not insert billboard into database");
                } else {
                    System.out.println(" >> Billboard registered successfully");
                    outcome = true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else{
            throw new NoConnectionException(" >> No Connection found");
        }
        return outcome;
    }

    @Override
    public List<Billboard> viewAllBillboards(boolean connection) {
        ArrayList<Billboard> billboardsHolder = new ArrayList<>();
        String SEARCH = "SELECT * FROM billboardsdb";
        if (connection) {
            try {
                preparedStatement = billboardDb.getConnections().prepareStatement(SEARCH);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Billboard billboard = new Billboard();
                    BookingDetails bookingDetails = new BookingDetails();
                    billboard.setSerialNumber(resultSet.getInt("serialnumber"));
                    billboard.setLocation(resultSet.getString("location"));
                    billboard.setDimension(resultSet.getString("dimension"));
                    billboard.setState(State.valueOf(resultSet.getString("state")));
                    billboard.setPricePerHr((resultSet.getInt("priceperhr")));
                    bookingDetails.setCustomer(resultSet.getString("customer"));
                    bookingDetails.setBookedDate(resultSet.getString("bookeddate"));
                    bookingDetails.setTimeBooked(resultSet.getString("timebooked"));
                    bookingDetails.setUploadedFile(resultSet.getString("uploadedfile"));
                    bookingDetails.setDurationOfBooking(resultSet.getInt("duration"));
                    billboard.setBookingDetails(bookingDetails);
                    billboardsHolder.add(billboard);
                }
            } catch (SQLException ee) {
                ee.printStackTrace();
            }
        } else{
            throw new NoConnectionException(" >> No Connection found");
        }
        return billboardsHolder;
    }

    @Override
    public List<Billboard> viewBillboardByLocation(String location, boolean connection) {
        ArrayList<Billboard> billboardsHolder = new ArrayList<>();
        String SEARCH = "SELECT * FROM billboardsdb WHERE location = ?";
        if (connection) {
            try {
                preparedStatement = billboardDb.getConnections().prepareStatement(SEARCH);
                preparedStatement.setString(1, location);
                System.out.println(" >> Location entered is: " + location);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Billboard billboard = new Billboard();
                    BookingDetails bookingDetails = new BookingDetails();
                    billboard.setSerialNumber(resultSet.getInt("serialnumber"));
                    billboard.setLocation(resultSet.getString("location"));
                    billboard.setDimension(resultSet.getString("dimension"));
                    billboard.setState(State.valueOf(resultSet.getString("state")));
                    billboard.setPricePerHr((resultSet.getInt("priceperhr")));
                    bookingDetails.setCustomer(resultSet.getString("customer"));
                    bookingDetails.setBookedDate(resultSet.getString("bookeddate"));
                    bookingDetails.setTimeBooked(resultSet.getString("timebooked"));
                    bookingDetails.setUploadedFile(resultSet.getString("uploadedfile"));
                    bookingDetails.setDurationOfBooking(resultSet.getInt("duration"));
                    billboard.setBookingDetails(bookingDetails);
                    billboardsHolder.add(billboard);
                }
            } catch (SQLException ee) {
                ee.printStackTrace();
            }
        } else{
            throw new NoConnectionException(" >> No Connection found");
        }
        return billboardsHolder;
    }

    @Override
    public Billboard viewBillboardById(long serialNumber, boolean connection) {
        Billboard billboard = new Billboard();
        BookingDetails bookingDetails = new BookingDetails();
        String SEARCH = "SELECT * FROM billboardsdb WHERE serialnumber = ?";
        if (connection) {
            try {
                preparedStatement = billboardDb.getConnections().prepareStatement(SEARCH);
                preparedStatement.setLong(1, serialNumber);
                System.out.println(" >> Serial Number entered is: " + serialNumber);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    billboard.setSerialNumber(resultSet.getInt("serialnumber"));
                    billboard.setLocation(resultSet.getString("location"));
                    billboard.setDimension(resultSet.getString("dimension"));
                    billboard.setState(State.valueOf(resultSet.getString("state")));
                    billboard.setPricePerHr((resultSet.getInt("priceperhr")));
                    bookingDetails.setCustomer(resultSet.getString("customer"));
                    bookingDetails.setBookedDate(resultSet.getString("bookeddate"));
                    bookingDetails.setTimeBooked(resultSet.getString("timebooked"));
                    bookingDetails.setUploadedFile(resultSet.getString("uploadedfile"));
                    bookingDetails.setDurationOfBooking(resultSet.getInt("duration"));
                    bookingDetails.setPrice(resultSet.getFloat("totalprice"));
                    billboard.setBookingDetails(bookingDetails);
                } else {
                    throw new DoesNotExistException(" >> Billboard does not exist");
                }
            } catch (SQLException ee) {
                ee.printStackTrace();
            }
        } else{
            throw new NoConnectionException(" >> No Connection found");
        }
        return billboard;
    }

    @Override
    public String takeDownAd(long serialNumber, char confirm, boolean connection) {
        String message = "";

        viewBillboardById(serialNumber,connection );

        String UPDATE = "UPDATE billboardsdb SET  customer =?, bookeddate =?, timebooked =? , state =?, duration =?, uploadedfile = ?, totalprice = ? WHERE serialnumber =?";
        if (connection) {
            try {
                preparedStatement = billboardDb.getConnections().prepareStatement(UPDATE);
                preparedStatement.setString(1, "null");
                preparedStatement.setString(2, "null");
                preparedStatement.setString(3, "null");
                preparedStatement.setString(4, "Available");
                preparedStatement.setInt(5, 0);
                preparedStatement.setString(6, "null");
                preparedStatement.setInt(7,0);
                preparedStatement.setLong(8, serialNumber);
                if (confirm == 'Y' || confirm == 'y') {
                    preparedStatement.executeUpdate();
                    message = " >> Ad has been taken down";
                } else if (confirm == 'N' || confirm == 'n') {
                    message = " >> Operation aborted";
                } else {
                    throw new InvalidKeyEnteredException(" >> Invalid key entered");
                }
            } catch (SQLException ee) {
                ee.printStackTrace();
            }
        } else{
            throw new NoConnectionException(" >> No Connection found");
        }
        return message;
    }
}
