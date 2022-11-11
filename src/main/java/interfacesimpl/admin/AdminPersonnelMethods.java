package interfacesimpl.admin;

import entity.person.Personnel;
import interfaces.admin.IAdminPersonnelMethods;

import java.util.List;

public class AdminPersonnelMethods implements IAdminPersonnelMethods {
    @Override
    public boolean registerNewAdmin(Personnel personnel) {
        return false;
    }

    @Override
    public Personnel viewPersonnelByEmail(String email) {
        return null;
    }

    @Override
    public List viewAllPersonnel() {
        return null;
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
