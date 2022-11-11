package interfacesimpliment.admin;

import database.BillboardDb;
import entity.billboards.Billboard;
import entity.billboards.BookingDetails;
import enums.State;
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
    public boolean registerNewBillboard(Billboard billboard) {
        int upd;
        boolean outcome = false;
        String INSERT = "INSERT INTO billboardsdb (serialnumber, location, dimension, state, priceperhr) VALUES (?,?,?,?,?)";
        if (billboardDb.connectToBillboardDb()) {
            try {
                preparedStatement = billboardDb.getConnections().prepareStatement(INSERT);
                preparedStatement.setLong(1, billboard.getSerialNumber());
                preparedStatement.setString(2, billboard.getLocation());
                preparedStatement.setString(3, billboard.getDimension());
                preparedStatement.setString(4, billboard.getState() + "");
                preparedStatement.setInt(5, billboard.getPricePerHr());
                upd = preparedStatement.executeUpdate();

                if (upd == 0) {
//                            Add exception for failure to register billboard
                } else {
                    System.out.println(" >> Billboard registered successfully");
                    outcome = true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return outcome;
    }

    @Override
    public List<Billboard> viewAllBillboards() {
        ArrayList<Billboard> billboardsHolder = new ArrayList<>();
        String SEARCH = "SELECT * FROM billboardsdb";
        if (billboardDb.connectToBillboardDb()) {
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
        }
        return billboardsHolder;
    }

    @Override
    public List<Billboard> viewBillboardByLocation(String location) {
        ArrayList<Billboard> billboardsHolder = new ArrayList<>();
        String SEARCH = "SELECT * FROM billboardsdb WHERE location = ?";
        if (billboardDb.connectToBillboardDb()) {
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
        }
        return billboardsHolder;
    }

    @Override
    public Billboard viewBillboardById(long serialNumber) {
        Billboard billboard = new Billboard();
        BookingDetails bookingDetails = new BookingDetails();
        String SEARCH = "SELECT * FROM billboardsdb WHERE serialnumber = ?";
        if (billboardDb.connectToBillboardDb()) {
            try {
                preparedStatement = billboardDb.getConnections().prepareStatement(SEARCH);
                preparedStatement.setLong(1, serialNumber);
                System.out.println(" >> Serial Number entered is: " + serialNumber);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
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
                }
            } catch (SQLException ee) {
                ee.printStackTrace();
            }
        }
        return billboard;
    }

    @Override
    public String takeDownAd(long serialNumber, char confirm) {
        String message = "";

        viewBillboardById(serialNumber);

        String UPDATE = "UPDATE billboardsdb SET  customer =?, bookeddate =?, timebooked =? , state =?, duration =?, uploadedfile = ?, totalprice = ? WHERE serialnumber =?";
        if (billboardDb.connectToBillboardDb()) {
            try {
                preparedStatement = billboardDb.getConnections().prepareStatement(UPDATE);
                preparedStatement.setString(1, "null");
                preparedStatement.setString(2, "null");
                preparedStatement.setString(3, "null");
                preparedStatement.setString(4, "Available");
                preparedStatement.setString(5, "0");
                preparedStatement.setString(6, "null");
                preparedStatement.setFloat(7,0);
                preparedStatement.setLong(8, serialNumber);
                if (confirm == 'Y' || confirm == 'y') {
                    preparedStatement.executeUpdate();
                    message = " >> Ad has been taken down";
                } else if (confirm == 'N' || confirm == 'n') {
                    message = " >> Operation aborted";
                } else {
//                        Add exception for invalid input
                }
            } catch (SQLException ee) {
                ee.printStackTrace();
            }
        }
        return message;
    }
}
