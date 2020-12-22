package pages;

import model.Address;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RestaurantsPage extends AbstractPage {

    public static final String RESTAURANTS_PAGE_URL = "https://dominos.by/restaurants";

    @FindBy(xpath = "//div[@class=\"search-street__modal-content\"]//input[@class=\"custom-field-text__input\"]")
    private WebElement inputStreetName;

    @FindBy(xpath = "//input[..//div/text()=\"Номер дома\"]")
    private WebElement inputHouseNumber;

    @FindBy(xpath = "//*[@class='store-locator__form']/div[2]")
    private WebElement divStreetInput;

    @FindBy(className = "store-locator__button")
    private WebElement btnCheck;

    @FindBy(className = "notification")
    private WebElement divNotification;

    @FindBy(className = "notification__title")
    private WebElement divNotificationTitle;

    public RestaurantsPage(WebDriver driver) {
        super(driver);
    }

    public String getBtnCheckText() {
        waitTime(100);
        return btnCheck.getText();
    }

    public String getDivNotificationTitleText() {
        return divNotificationTitle.getText();
    }

    @Override
    public RestaurantsPage openPage() {
        driver.get(RESTAURANTS_PAGE_URL);
        waitJavascriptIsExecuted(WAIT_TIMEOUT_SECONDS);
        return this;
    }

    public boolean isNotificationSuccess() {
        WebElement notificationBanner = divNotification.findElement(By.className("notification__banner--success"));
        System.out.println(notificationBanner.getAttribute("class"));
        return notificationBanner.getAttribute("class").contains("notification__banner--success");
    }

    public RestaurantsPage checkDeliveryAddress(Address address) {
        divStreetInput.click();
        WebElement searchInput = driver.findElement(By.xpath("//input[../div/text()=\"Поиск\"]"));
        searchInput.sendKeys(address.getStreet());

        WebElement choiceBtn = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(d -> driver
                        .findElement(By.xpath("//button[./div/text()=\"" + address.getStreet() + "\" and ./div/text()=\"" + address.getCity() + "\"]")));
        choiceBtn.click();

        inputHouseNumber.sendKeys(address.getHouseNumber());

        btnCheck.click();

        return this;
    }


}
