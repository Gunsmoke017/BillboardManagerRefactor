package interfaces.admin;

import entity.billboards.Billboard;

import java.util.List;

public interface IAdminBillboardMethods {
    boolean registerNewBillboard(Billboard billboard);
    List<Billboard> viewAllBillboards();
    List<Billboard> viewBillboardByLocation(String location);
    Billboard viewBillboardById(long serialNumber);
    String takeDownAd(long serialNumber, char confirm);
}
