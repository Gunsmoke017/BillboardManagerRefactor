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
}