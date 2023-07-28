package interfacesimpl.billboards;

import database.BillboardDb;
import entity.billboards.Billboard;
import entity.billboards.BookingDetails;
import enums.State;
import exceptions.NoConnectionException;
import interfaces.billboards.IBillboardMethods;
import interfacesimpl.admin.AdminBillboardMethods;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class
BillboardMethods implements IBillboardMethods {
    BillboardDb billboardDb = new BillboardDb();
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    @Override
    public List viewAvailableBillboards(boolean connection) {
        ArrayList<String> billboardsHolder = new ArrayList<>();
        String SEARCH = "SELECT * FROM billboardsdb WHERE state = 'Available'";
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
                    billboardsHolder.add(billboard.ToString());
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
    public String bookBillboard(BookingDetails bookingDetails, char confirm, long id, boolean connection) {
        AdminBillboardMethods adminBillboardMethods = new AdminBillboardMethods();
        String message="";
        LocalDateTime dateTimeGetter = LocalDateTime.now();
//        DateTimeFormatter dateFormatter =DateTimeFormatter.ofPattern("EEEE, MMMM dd yyyy");
//        DateTimeFormatter dateFormatter =DateTimeFormatter.ofPattern("EEEE, MMMM dd yyyy");
        DateTimeFormatter dateFormatter =DateTimeFormatter.ofPattern("yyyy:MM:dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:MM");

        bookingDetails.setBookedDate(dateFormatter.format(dateTimeGetter));
        bookingDetails.setTimeBooked(timeFormatter.format(dateTimeGetter));

        String UPDATE = "UPDATE billboardsdb SET  customer =?, bookeddate =?, timebooked =? , state =?, duration =?, uploadedfile = ?, totalprice = ? WHERE serialnumber =?";
        if(connection){
            try{
               preparedStatement = billboardDb.getConnections().prepareStatement((UPDATE));
               preparedStatement.setString(1,bookingDetails.getCustomer());
               preparedStatement.setString(2,bookingDetails.getBookedDate());
               preparedStatement.setString(3,bookingDetails.getTimeBooked());
               preparedStatement.setString(4,State.Booked.toString());
               preparedStatement.setFloat(5,bookingDetails.getDurationOfBooking());
               preparedStatement.setString(6,bookingDetails.getUploadedFile());
               preparedStatement.setFloat(7,bookingDetails.getPrice());
               preparedStatement.setLong(8, id);

               if (confirm == 'n' || confirm == 'N'){
                   message = " >> Transaction aborted";
               } else if (confirm == 'y' || confirm == 'Y'){
                   preparedStatement.executeUpdate();
                   message = " >> Transaction completed";
                   System.out.println(adminBillboardMethods.viewBillboardById(id, connection));
               }
            }
            catch (SQLException ee){
                ee.printStackTrace();
            }
        } else{
            throw new NoConnectionException(" >> No Connection found");
        }
        return message;
    }
}
