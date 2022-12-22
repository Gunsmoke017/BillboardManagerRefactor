package interfacesimpl.admin;

import entity.person.Personnel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminPersonnelMethodsTest {
    Personnel personnel = new Personnel("test@gmail.com","Luffy","Monkey","niku");
    AdminPersonnelMethods adminPersonnelMethods = new AdminPersonnelMethods();
    @Test
    void registerNewAdmin() {
        assertEquals(true,adminPersonnelMethods.registerNewAdmin(personnel));
    }

    @Test
    void viewPersonnelByEmail() {
        String holder =
                "\n***********************************************\n" +
                        " >> Email: " + "test@gmail.com" + "\n" +
                        "First Name: " + "Luffy" + "\n" +
                        "Last Name: " + "Monkey" + "\n" +
                        "***********************************************"
                ;
        assertNotEquals(holder,adminPersonnelMethods.viewPersonnelByEmail("test@gmail.com"));
    }

    @Test
    void viewAllPersonnel() {
        String holder =
                "\n***********************************************\n" +
                        " >> Email: " + "test@gmail.com" + "\n" +
                        "First Name: " + "Luffy" + "\n" +
                        "Last Name: " + "Monkey" + "\n" +
                        "***********************************************"
                ;
        assertNotEquals(holder,adminPersonnelMethods.viewPersonnelByEmail("test@gmail.com"));
    }

    @Test
    void deletePersonnelById() {
    }

    @Test
    void validateAdmin() {
    }
}