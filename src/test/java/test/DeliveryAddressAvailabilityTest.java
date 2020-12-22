package test;

import model.Address;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.RestaurantsPage;
import service.AddressCreator;

public class DeliveryAddressAvailabilityTest extends CommonConditions {

    @Test
    public void testIsAvailableDeliveryAddress() {
        SoftAssert softAssertion = new SoftAssert();

        Address address = AddressCreator.withCredentialsFromProperties();

        RestaurantsPage page = new RestaurantsPage(driver);
        page.openPage().closeAds();

        softAssertion.assertEquals(page.checkDeliveryAddress(address).getBtnCheckText(), "Адрес в зоне доставки", "Button text has NOT been changed");
        Assert.assertTrue(page.isNotificationSuccess(), "There is notification delivery is NOT available");
    }
}
