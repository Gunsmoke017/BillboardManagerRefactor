package interfacesimpl.admin;

import database.BillboardDb;
import entity.person.Personnel;
import interfaces.admin.IAdminPersonnelMethods;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Base64;

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
                preparedStatement.setString(4, Base64.getEncoder().encodeToString(personnel.getPassword().getBytes()));
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
        } else{
//            Add connection not found exception
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
    public String deletePersonnelById(String email, char confirm) {
        int upd = 0;
        String message ="";
        viewPersonnelByEmail(email);
        String DELETE = "DELETE FROM admin WHERE email = ?";
        if(confirm == 'y' || confirm == 'Y'){
            if(billboardDb.connectToBillboardDb()){ // Refer to this point to check how to use a boolean variable to work in place of the connection role
                try{
                    preparedStatement = billboardDb.getConnections().prepareStatement(DELETE);
                    preparedStatement.setString(1,email);
                    upd = preparedStatement.executeUpdate();
                    message = " >> Personnel deleted successfully";
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        } else if(confirm =='n' || confirm == 'N'){
            message = " >> Operation aborted";
        } else{
//            Add exception to handle incorrect input for the confirm action
        }
        return message;
    }

    @Override
    public boolean validateAdmin(String email, String password) {
        String holder,result;
        boolean validate = false;
        String SEARCH = "SELECT email FROM admin WHERE email =?";
        if(billboardDb.connectToBillboardDb()){
            try{
                preparedStatement = billboardDb.getConnections().prepareStatement(SEARCH);
                preparedStatement.setString(1,email.toLowerCase());
                preparedStatement.executeQuery();
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    String PASSWORD__RETRIEVE = "SELECT password FROM admin WHERE email = ?";
                    preparedStatement = billboardDb.getConnections().prepareStatement(PASSWORD__RETRIEVE);
                    preparedStatement.setString(1,email.toLowerCase());
                    resultSet = preparedStatement.executeQuery();
                    if(resultSet.next()){
                        holder = resultSet.getString("password");
                        byte[] temp = Base64.getDecoder().decode(holder);
                        result = new String(temp);
                        if(password.equals(result)){
                            validate = true;
                        } else{
//                            Throw new password mismatch exception
                        }
                    } else{
//                        password not correct exception
                    }
                } else{
//                    throw email  does not exist exception
                }
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
        return validate;
    }
}
