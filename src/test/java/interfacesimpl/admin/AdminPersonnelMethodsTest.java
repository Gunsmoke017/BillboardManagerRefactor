package interfacesimpl.admin;

import entity.person.Personnel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminPersonnelMethodsTest {
    Personnel personnel = new Personnel("test@gmail.com","Luffy","Monkey","niku");
    Personnel personnel2 = new Personnel("test2@gmail.com","Zoro","Roronoa","sake");
    AdminPersonnelMethods adminPersonnelMethods = new AdminPersonnelMethods();
    @Test
    void registerNewAdmin() {
        assertEquals(true,adminPersonnelMethods.registerNewAdmin(personnel2));
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
        assertEquals(" >> Operation aborted",adminPersonnelMethods.deletePersonnelById("test@gmail.com",'n'));
    }

    @Test
    void validateAdmin() {
        assertEquals(true,adminPersonnelMethods.validateAdmin("test2@gmail.com","sake"));
    }
}