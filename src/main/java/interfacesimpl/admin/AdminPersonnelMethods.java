package interfacesimpl.admin;

import database.BillboardDb;
import entity.person.Personnel;
import interfaces.admin.IAdminPersonnelMethods;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminPersonnelMethods implements IAdminPersonnelMethods {
    BillboardDb billboardDb = new BillboardDb();
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    @Override
    public boolean registerNewAdmin(Personnel personnel) {
        boolean outcome = false;
        int upd;
        String INSERT = "INSERT INTO admin (email, firstname, lastname, password) VALUES (?,?,?,?)";
        if(billboardDb.connectToBillboardDb()) {
            try{
                preparedStatement = billboardDb.getConnections().prepareStatement(INSERT);
                preparedStatement.setString(1, personnel.getEmail());
                preparedStatement.setString(2, personnel.getFirstName());
                preparedStatement.setString(3, personnel.getLastName());
                preparedStatement.setString(4,personnel.getPassword());
                upd = preparedStatement.executeUpdate();

                if(upd == 0){
//                    Add exception for failure to insert into database
                } else{
                    System.out.println(" >> Personnel registered successfully");
                    outcome = true;
                }
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
        return outcome;
    }

    @Override
    public Personnel viewPersonnelByEmail(String email) {
        Personnel personnel = new Personnel();
        String SEARCH = " SELECT * FROM admin WHERE email = ?";
        if(billboardDb.connectToBillboardDb()){
            try{
                preparedStatement = billboardDb.getConnections().prepareStatement(SEARCH);
                preparedStatement.setString(1, email);
                System.out.println(" >> Email entered is: " + email);
                resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    personnel.setEmail(email);
                    personnel.setFirstName(resultSet.getString("firstname"));
                    personnel.setLastName(resultSet.getString("lastname"));
                    personnel.setPassword(resultSet.getString("password"));
                }
            } catch (SQLException e ){
                e.printStackTrace();
            }
        }
        return personnel;
    }

    @Override
    public List viewAllPersonnel() {
        ArrayList<Personnel> personnels = new ArrayList<>();
        String SEARCH = "SELECT * FROM admin";
        if(billboardDb.connectToBillboardDb()){
            try{
                preparedStatement = billboardDb.getConnections().prepareStatement(SEARCH);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    Personnel personnel = new Personnel();
                    personnel.setEmail(resultSet.getString("email"));
                    personnel.setFirstName(resultSet.getString("firstname"));
                    personnel.setLastName(resultSet.getString("lastname"));
                    personnel.setPassword(resultSet.getString("password"));
                    personnels.add(personnel);
                }
            } catch(SQLException e ){
                e.printStackTrace();
            }
        }
        return personnels;
    }

    @Override
    public String deletePersonnelById(String email) {
        return null;
    }

    @Override
    public boolean validateAdmin(String email, String password) {
        return false;
    }
}
