package test;

import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;
import service.UserCreator;

public class LogoutTest extends CommonConditions {

    @Test
    public void testLogout() {
        User user = UserCreator.withCredentialsFromProperty();
        MainPage page = new MainPage(driver);

        page.openPage().closeAds();
        page.login(user).
                clickAndGoToProfile().
                logout();
        Assert.assertFalse(page.isAuthorized(),"User is authorized");
    }
}
