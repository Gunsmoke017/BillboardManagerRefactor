package interfacesimpl.billboards;

import entity.billboards.Billboard;
import entity.billboards.BookingDetails;
import interfacesimpl.admin.AdminBillboardMethods;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BillboardMethodsTest {
    BookingDetails bookingDetails = new BookingDetails();
    BillboardMethods billboardMethods = new BillboardMethods();
    ArrayList<Billboard> test = new ArrayList<>();

    @Test
    void returnsAllMethods(){
        assertNotEquals(test,billboardMethods.viewAvailableBillboards(true));
        System.out.println(billboardMethods.viewAvailableBillboards(true));
    }

    @Test
    void bookBillboardMethodTest(){
        AdminBillboardMethods adminBillboardMethods = new AdminBillboardMethods();
        bookingDetails.setCustomer("Test custommer");
        bookingDetails.setUploadedFile("test ad");
        bookingDetails.setDurationOfBooking(12);
        bookingDetails.setPrice(bookingDetails.getDurationOfBooking() * adminBillboardMethods.viewBillboardById(7, true).getPricePerHr());
        assertEquals(" >> Transaction completed",billboardMethods.bookBillboard(bookingDetails,'y',7,true ));
    }
}