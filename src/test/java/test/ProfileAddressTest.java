package test;

import model.Address;
import model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.ProfilePage;
import service.AddressCreator;
import service.UserCreator;

public class ProfileAddressTest extends CommonConditions {

    MainPage mainPage;
    ProfilePage profilePage;

    @BeforeMethod
    public void loginAndGoToProfile() {
        mainPage = new MainPage(driver);
        mainPage.openPage().closeAds();
        profilePage = mainPage.login(UserCreator.withCredentialsFromProperty()).
                clickAndGoToProfile();
    }

    @Test
    public void testAddAddress() {
        Address address = AddressCreator.withCredentialsFromProperties();
        profilePage.addAddress(address);
        Assert.assertTrue(address.equalsIgnoreCase(profilePage.getLastAddedAddress()), "Last added address is not right");
    }

    @Test
    public void testRemoveAddress() {
        Address address = AddressCreator.withCredentialsFromProperties();
        profilePage.addAddress(address);
        int beginAddressesCount = profilePage.getAddressesCount();
        profilePage.removeLastAddress();
        int endAddressesCount = profilePage.getAddressesCount();
        Assert.assertEquals(endAddressesCount, beginAddressesCount - 1, "Addresses list didn't get reduced");
    }

}
