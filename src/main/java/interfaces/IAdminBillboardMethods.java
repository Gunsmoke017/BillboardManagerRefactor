package interfaces;

import entity.billboards.Billboard;

import java.util.List;
import java.util.Optional;

public interface IAdminBillboardMethods {
    boolean registerNewBillboard(Billboard billboard);
    List<Billboard> viewAllBillboards();
    Optional<Billboard> viewBillboardByLocation(String location);
    Billboard viewBillboardById(long serialNumber);
    String takeDownAd(long serialNumber, char confirm);
}
