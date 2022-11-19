package interfaces.admin;

import entity.person.Personnel;

import java.util.List;

public interface IAdminPersonnelMethods {
    boolean registerNewAdmin(Personnel personnel);
    Personnel viewPersonnelByEmail(String email);
    List viewAllPersonnel();
    String deletePersonnelById(String email, char confirm);
    boolean validateAdmin(String email, String password);
}
