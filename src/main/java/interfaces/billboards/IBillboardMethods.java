package interfaces.billboards;

import entity.billboards.BookingDetails;

import java.util.List;

public interface IBillboardMethods {
    List viewAvailableBillboards();
    String bookBillboard(BookingDetails bookingDetails);
}
