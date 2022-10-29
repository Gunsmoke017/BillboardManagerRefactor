package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BillboardDb {
    Connection connection;

    public boolean connectToBillboardDb(){
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/billboards","root","");
            return true;
        }
        catch (SQLException exception){
            exception.printStackTrace();
            return false;
        }
    }

    public Connection getConnections(){
        return connection;
    }
}
