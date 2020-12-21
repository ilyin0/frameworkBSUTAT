import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.ProfilePage;

public class LoginTest extends CommonConditions {

    @Test
    public void testLogin() {
        String email = "ilyintests@gmail.com";
        String password = "1722dDkK3371";

        MainPage mainPage = new MainPage(driver);
        mainPage.openPage().closeAds();
        ProfilePage profilePage = mainPage.login(email, password).clickAndGoToProfile();
        Assert.assertEquals(email, profilePage.getInputEmailText(), "Input email text on the profile page isn't valid");

    }
}
