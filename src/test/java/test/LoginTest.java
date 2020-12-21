package test;

import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.ProfilePage;
import service.UserCreator;

public class LoginTest extends CommonConditions {

    @Test
    public void testLogin() {
        User user = UserCreator.withCredentialsFromProperty();

        MainPage mainPage = new MainPage(driver);
        mainPage.openPage().closeAds();
        ProfilePage profilePage = mainPage.login(user).clickAndGoToProfile();
        Assert.assertEquals(user.getEmail(), profilePage.getInputEmailText(), "Input email text on the profile page isn't valid");

    }
}
