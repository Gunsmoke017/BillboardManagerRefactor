package interfacesimpliment;

import entity.billboards.Billboard;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AdminBillboardMethodsTest {
    AdminBillboardMethods adminBillboardMethods = new AdminBillboardMethods();
    ArrayList<Billboard> test = new ArrayList();
    Billboard billboard = new Billboard();

    @Test
    void getAllBillboards(){
        assertNotEquals(test,adminBillboardMethods.viewAllBillboards());
        System.out.println(adminBillboardMethods.viewAllBillboards());
    }

    @Test
    void registerBillboardTest(){
        assertFalse(adminBillboardMethods.registerNewBillboard(billboard));
    }

    @Test
    void returnBillboardsByLocation(){
        assertNotEquals(test,adminBillboardMethods.viewBillboardByLocation("woji"));
        System.out.println(adminBillboardMethods.viewBillboardByLocation("woji"));
    }

    @Test
    void returnBillboardBySerialNumber(){
        assertNotEquals(billboard,adminBillboardMethods.viewBillboardById(4));
        System.out.println(adminBillboardMethods.viewBillboardById(4));
    }
}