package interfaces.admin;

import entity.person.Personnel;

import java.util.List;

public interface IAdminPersonnelMethods {
    boolean registerNewAdmin(Personnel personnel, boolean connection);
    Personnel viewPersonnelByEmail(String email,boolean connection);
    List viewAllPersonnel(boolean connection);
    String deletePersonnelById(String email, char confirm,boolean connection);
    boolean validateAdmin(String email, String password,boolean connection);
}
