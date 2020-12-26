package pages;

import model.Address;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;

public class ProfilePage extends AbstractPage {

    public static final String PROFILE_PAGE_URL = "https://dominos.by/user";
    private static final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//input[@type=\"email\"]")
    private WebElement inputEmail;

    @FindBy(xpath = "//a[@class='profile__title-link active']")
    private WebElement aLogout;

    @FindBy(xpath = "//button[text()='\u0414\u043e\u0431\u0430\u0432\u0438\u0442\u044c \u0430\u0434\u0440\u0435\u0441']")
    private WebElement btnAddAddress;

    @FindBy(tagName = "select")
    private WebElement sltCity;

    @FindBy(className = "custom-field-text--disabled")
    private WebElement divSelectStreet;

    @FindBy(xpath = "//div[./div/text()='\u041d\u043e\u043c\u0435\u0440 \u0434\u043e\u043c\u0430']/input")
    private WebElement inputHouseNumber;

    @FindBy(xpath = "//div[contains(@class, 'delivery-address__field--suite')]/input")
    private WebElement inputApartmentNumber;

    @FindBy(xpath = "//div[contains(@class, 'delivery-address__field--floor')]/input")
    private WebElement inputFloor;

    @FindBy(xpath = "//div[contains(@class, 'delivery-address__field--entrance')]/input")
    private WebElement inputEntrance;

    @FindBy(xpath = "//button[@type='submit'][text()='\u0414\u043e\u0431\u0430\u0432\u0438\u0442\u044c']")
    private WebElement btnSubmitAddress;

    @FindBy(className = "notification")
    private WebElement notification;

    @FindBy(xpath = "//div[@class='user-streets']//li")
    private List<WebElement> liAddressesDivs;

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public String getInputEmailText() {
        waitTime(2000);
        return inputEmail.getAttribute("value");
    }

    public ProfilePage selectCity(String cityName) {
        ((Select) sltCity).selectByValue(cityName);
        return this;
    }

    public MainPage logout() {
        waitTime(1000);
        aLogout.click();
        logger.info("You are logged out");
        return new MainPage(driver);
    }

    public ProfilePage addAddress(Address address) {
        waitTime(1000);
        btnAddAddress.click();

        new WebDriverWait(driver, 10).until(d->driver.findElement(By.xpath("//div[div/text()='\u0423\u043b\u0438\u0446\u0430']"))).click();

        WebElement searchInput = new WebDriverWait(driver, 10).
                until(d->driver.findElement(By.xpath("//div[@class='search-street__modal-content']//input")));
        searchInput.sendKeys(address.getStreet());
        WebElement choiceBtn = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(d -> driver
                        .findElement(By.xpath("//button[./div/text()=\"" + address.getStreet() + "\" and ./div/text()=\"" + address.getCity() + "\"]")));
        choiceBtn.click();

        inputHouseNumber.sendKeys(address.getHouseNumber());
        inputApartmentNumber.sendKeys(address.getApartmentNumber());
        inputFloor.sendKeys(address.getFloor());
        inputEntrance.sendKeys(address.getEntrance());

        btnSubmitAddress.click();

        waitTime(1000);
        notification.findElement(By.className("notification__close-button")).click();

        logger.info("Address is added: " + address.toString());
        return this;
    }

    public boolean isAddressAdded() {
        return notification.findElement(By.className("notification__content")).
                getText().equals("\u0410\u0434\u0440\u0435\u0441 \u0434\u043e\u0431\u0430\u0432\u043b\u0435\u043d");
    }

    public int getAddressesCount() {
        waitTime(500);
        return liAddressesDivs.size();
    }

    public Address getLastAddedAddress() {
        waitTime(2000);
        System.out.println(liAddressesDivs.size());

        return getAddressFromAddressLi(liAddressesDivs.get(0));
    }

    public Address getAddressFromAddressLi(WebElement li) {
        WebElement divContent = li.findElement(By.className("custom-list__item-content"));
        String cityAndStreet = divContent.findElement(By.className("custom-list__item-content-primary")).getText();
        String houseNumberAndApartmentNumberAndEntranceAndFloor = divContent.findElement(By.className("custom-list__item-content-secondary")).getText();
        List<String>cityAndStreetList = Arrays.asList(cityAndStreet.trim().split(", "));
        List<String>houseNumberAndApartmentNumberAndEntranceAndFloorList = Arrays.asList(
                houseNumberAndApartmentNumberAndEntranceAndFloor.replaceAll("[^0-9]+", " ")
                .trim().split("\\s+"));
        String city = cityAndStreetList.get(0);
        String street = cityAndStreetList.get(1);
        String houseNumber = houseNumberAndApartmentNumberAndEntranceAndFloorList.get(0);
        String apartmentNumber = houseNumberAndApartmentNumberAndEntranceAndFloorList.get(1);
        String entrance = houseNumberAndApartmentNumberAndEntranceAndFloorList.get(2);
        String floor = houseNumberAndApartmentNumberAndEntranceAndFloorList.get(3);

        return new Address(city, street, houseNumber, apartmentNumber, floor, entrance);
    }

    @Override
    public ProfilePage openPage() {
        driver.get(PROFILE_PAGE_URL);
        waitJavascriptIsExecuted(WAIT_TIMEOUT_SECONDS);
        return this;
    }
}
