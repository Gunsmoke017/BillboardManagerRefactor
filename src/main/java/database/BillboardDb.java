package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BillboardDb {
    Connection connection;

    public boolean connectToBillboardDb(){
        try{
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/billboards","postgres","gunsmoke017");
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
