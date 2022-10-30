package interfacesimpliment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class AdminBillboardMethodsTest {
    AdminBillboardMethods adminBillboardMethods = new AdminBillboardMethods();
    ArrayList test = new ArrayList();

    @Test
    public void getAllBillboards(){
        assertNotEquals(test,adminBillboardMethods.viewAllBillboards());
        System.out.println(adminBillboardMethods.viewAllBillboards());
    }
}