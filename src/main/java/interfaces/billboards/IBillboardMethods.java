package interfaces.billboards;

import entity.billboards.BookingDetails;

import java.util.List;

public interface IBillboardMethods {
    List viewAvailableBillboards(boolean connection);
    String bookBillboard(BookingDetails bookingDetails, char confirm, long id, boolean connection);
}
