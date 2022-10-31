package interfacesimpliment.billboards;

import database.BillboardDb;
import entity.billboards.Billboard;
import entity.billboards.BookingDetails;
import enums.State;
import interfaces.billboards.IBillboardMethods;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BillboardMethods implements IBillboardMethods {
    BillboardDb billboardDb = new BillboardDb();
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    @Override
    public List viewAvailableBillboards() {
        ArrayList<Billboard> billboardsHolder = new ArrayList<>();
        String SEARCH = "SELECT * FROM billboardsdb WHERE state = 'Available'";
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
    public String bookBillboard(BookingDetails bookingDetails, char confirm, long id) {
        String message="";
        LocalTime timeGetter = LocalTime.now();
        LocalDateTime dateGetter = LocalDateTime.now();
        DateTimeFormatter dateFormatter =DateTimeFormatter.ofPattern("EEEE, MMMM dd yyyy");
        String time = timeGetter.toString();
        int upd;
        float price =0;

        String UPDATE = "UPDATE billboardsdb SET  customer =?, bookeddate =?, timebooked =? , state =?, duration =?, uploadedfile = ? WHERE serialnumber =?";
        if(billboardDb.connectToBillboardDb()){
            try{
               preparedStatement = billboardDb.getConnections().prepareStatement((UPDATE));
               preparedStatement.setString(1,bookingDetails.getCustomer());
               preparedStatement.setString(2,dateGetter.format(dateFormatter));
               preparedStatement.setString(3,time);
               preparedStatement.setString(4,"Booked");
               preparedStatement.setFloat(5,bookingDetails.getDurationOfBooking());
               preparedStatement.setString(6,bookingDetails.getUploadedFile());
               preparedStatement.setLong(7, id);

               if (confirm == 'n' || confirm == 'N'){
                   message = " >> Transaction aborted";
               } else if (confirm == 'y' || confirm == 'Y'){
                   preparedStatement.executeUpdate();
                   message = " >> Transaction completed";
               }
            }
            catch (SQLException ee){
                ee.printStackTrace();
            }
        }
        return message;
    }
}
