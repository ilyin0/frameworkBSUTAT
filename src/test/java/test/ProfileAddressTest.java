package test;

import model.Address;
import model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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
        MainPage page = new MainPage(driver);
        page.openPage().closeAds();

        Address address = AddressCreator.withCredentialsFromProperties();

        ProfilePage profilePage = page.login(UserCreator.withCredentialsFromProperty()).
                clickAndGoToProfile();

        profilePage.addAddress(address);

        Assert.assertTrue(address.equalsIgnoreCase(profilePage.getLastAddedAddress()), "Last added address is not right");
    }


}
