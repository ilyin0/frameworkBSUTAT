package pages;

import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends AbstractPage {

    public static final String MAIN_PAGE_URL = "https://dominos.by";
    private final Logger logger = LogManager.getRootLogger();

    @FindBy(className = "authorization-cta")
    private WebElement divAuthorizationAndProfileButton;

    @FindBy(name = "email")
    private WebElement inputEmail;

    @FindBy(name = "password")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[@type=\"submit\"]")
    private WebElement btnLogin;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage login(User user) {
        divAuthorizationAndProfileButton.findElement(By.tagName("button")).click();
        inputEmail.sendKeys(user.getEmail());
        inputPassword.sendKeys(user.getPassword());
        btnLogin.click();
        logger.info("You are logged in");
        return this;
    }

    public String getAuthorizationAndProfileButtonText() {
        return driver.findElement(By.xpath("//div[@class='authorization-cta']/*")).getText();
    }

    public ProfilePage clickAndGoToProfile() {
        waitTime(500);
        divAuthorizationAndProfileButton.findElement(By.tagName("a")).click();
        return new ProfilePage(driver);
    }

    @Override
    public MainPage openPage() {
        driver.get(MAIN_PAGE_URL);
        waitJavascriptIsExecuted(WAIT_TIMEOUT_SECONDS);
        return this;
    }
}
