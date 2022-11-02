package interfacesimpliment.billboards;

import entity.billboards.Billboard;
import entity.billboards.BookingDetails;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BillboardMethodsTest {
    BookingDetails bookingDetails = new BookingDetails();
    BillboardMethods billboardMethods = new BillboardMethods();
    ArrayList<Billboard> test = new ArrayList<>();

    @Test
    void returnsAllMethods(){
        assertNotEquals(test,billboardMethods.viewAvailableBillboards());
        System.out.println(billboardMethods.viewAvailableBillboards());
    }

    @Test
    void bookBillboardMethodTest(){
        bookingDetails.setCustomer("Test custommer");
        bookingDetails.setUploadedFile("test ad");
        bookingDetails.setDurationOfBooking(12);
//        assertEquals(" >> Transaction completed",billboardMethods.bookBillboard(bookingDetails,'y',7));
        System.out.println(billboardMethods.bookBillboard(bookingDetails,'y',7));
//        System.out.println(billboardMethods.bookBillboard(bookingDetails,'y',7));
//        billboardMethods.bookBillboard();
    }
}