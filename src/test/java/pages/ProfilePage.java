package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends AbstractPage {

    public static final String PROFILE_PAGE_URL = "https://dominos.by/user";

    @FindBy(xpath = "//input[@type=\"email\"]")
    private WebElement inputEmail;

    protected ProfilePage(WebDriver driver) {
        super(driver);
    }

    public String getInputEmailText() {
        waitTime(2000);
        return inputEmail.getAttribute("value");
    }

    @Override
    protected ProfilePage openPage() {
        driver.get(PROFILE_PAGE_URL);
        waitJavascriptIsExecuted(WAIT_TIMEOUT_SECONDS);
        return this;
    }
}
