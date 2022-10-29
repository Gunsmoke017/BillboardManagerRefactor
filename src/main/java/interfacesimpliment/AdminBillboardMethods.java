package interfacesimpliment;

import database.BillboardDb;
import entity.billboards.Billboard;
import interfaces.IAdminBillboardMethods;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AdminBillboardMethods implements IAdminBillboardMethods {
    BillboardDb billboardDb = new BillboardDb();
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    @Override
    public boolean registerNewBillboard(Billboard billboard) {
        int upd;
        boolean outcome = false;
        String INSERT = "INSERT INTO billboardsdb (serialnumber, location, dimension, state, priceperhr) VALUES (?,?,?,?,?)";
        if(billboardDb.connectToBillboardDb()){
            try{
                preparedStatement = billboardDb.getConnections().prepareStatement(INSERT);
                preparedStatement.setLong(1,billboard.getSerialNumber());
                preparedStatement.setString(2,billboard.getLocation());
                preparedStatement.setString(3,billboard.getDimension());
                preparedStatement.setString(4,billboard.getState()+"");
                preparedStatement.setInt(5,billboard.getPricePerHr());
                upd = preparedStatement.executeUpdate();

                if(upd == 0){
//                            Add exception for failure to register billboard
                } else{
                    System.out.println(" >> Billboard registered successfully");
                    outcome = true;
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
        return outcome;
    }

    @Override
    public List<Billboard> viewAllBillboards() {
        return null;
    }

    @Override
    public Optional<Billboard> viewBillboardByLocation(String location) {
        return Optional.empty();
    }

    @Override
    public Billboard viewBillboardById(long serialNumber) {
        return null;
    }

    @Override
    public String takeDownAd(long serialNumber, char confirm) {
        return null;
    }
}
