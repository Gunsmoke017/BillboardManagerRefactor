package interfacesimpliment;

import entity.billboards.Billboard;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class AdminBillboardMethodsTest {
    AdminBillboardMethods adminBillboardMethods = new AdminBillboardMethods();
    ArrayList test = new ArrayList();

    @Test
    void getAllBillboards(){
        assertNotEquals(test,adminBillboardMethods.viewAllBillboards());
        System.out.println(adminBillboardMethods.viewAllBillboards());
    }

    @Test
    void registerBillboardTest(){
        Billboard billboard = new Billboard();
        assertEquals(false, adminBillboardMethods.registerNewBillboard(billboard));
    }

    @Test
    void returnBillboardsByLocation(){
        assertNotEquals(test,adminBillboardMethods.viewBillboardByLocation("woji"));
        System.out.println(adminBillboardMethods.viewBillboardByLocation("woji"));
    }
}