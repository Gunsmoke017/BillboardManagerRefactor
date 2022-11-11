package interfacesimpliment;

import entity.billboards.Billboard;
import enums.State;
import interfacesimpliment.admin.AdminBillboardMethods;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AdminBillboardMethodsTest {
    AdminBillboardMethods adminBillboardMethods = new AdminBillboardMethods();
    ArrayList<Billboard> test = new ArrayList();
    Billboard billboard = new Billboard();

    @Test
    void getAllBillboardsTest(){
        assertNotEquals(test,adminBillboardMethods.viewAllBillboards());
//        System.out.println(adminBillboardMethods.viewAllBillboards());
    }
    @Test
    void registerBillboardTestOne(){
        billboard.setSerialNumber(007);
        billboard.setLocation("test location");
        billboard.setState(State.Available);
        billboard.setDimension("test x test");
        billboard.setPricePerHr(12000);
        assertEquals(true,adminBillboardMethods.registerNewBillboard(billboard));
    }

    @Test
    void registerBillboardTestTwo(){
        assertFalse(adminBillboardMethods.registerNewBillboard(billboard));
    }

    @Test
    void returnBillboardsByLocationTest(){
        assertNotEquals(test,adminBillboardMethods.viewBillboardByLocation("woji"));
//        System.out.println(adminBillboardMethods.viewBillboardByLocation("woji"));
    }

    @Test
    void returnBillboardBySerialNumberTest(){
        assertNotEquals(billboard,adminBillboardMethods.viewBillboardById(7));
//        System.out.println(adminBillboardMethods.viewBillboardById(4));
    }

    @Test
    void adTakeDownTestOne(){
        assertEquals(" >> Operation aborted",adminBillboardMethods.takeDownAd(7,'n'));
//        System.out.println(adminBillboardMethods.takeDownAd(2,'n'));
    }

    @Test
    void adTakeDownTestTwo(){
        assertEquals(" >> Ad has been taken down",adminBillboardMethods.takeDownAd(7,'y'));
    }
}