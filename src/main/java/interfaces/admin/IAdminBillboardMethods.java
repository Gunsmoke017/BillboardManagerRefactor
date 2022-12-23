package interfaces.admin;

import entity.billboards.Billboard;

import java.util.List;

public interface IAdminBillboardMethods {
    boolean registerNewBillboard(Billboard billboard, boolean connection);
    List<Billboard> viewAllBillboards(boolean connection);
    List<Billboard> viewBillboardByLocation(String location, boolean connection);
    Billboard viewBillboardById(long serialNumber, boolean connection);
    String takeDownAd(long serialNumber, char confirm, boolean connection);
}
