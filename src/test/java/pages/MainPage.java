package pages;

import model.Pizza;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPage extends AbstractPage {

    public static final String MAIN_PAGE_URL = "https://dominos.by";
    private final Logger logger = LogManager.getRootLogger();

    private static final String pizzaProductCardStringLocator = "//div[@class='product-card__title'][text()='%s']/parent::div/parent::div";

    @FindBy(className = "authorization-cta")
    private WebElement divAuthorizationAndProfileButton;

    @FindBy(name = "email")
    private WebElement inputEmail;

    @FindBy(name = "password")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[@type=\"submit\"]")
    private WebElement btnLogin;

    @FindBy(className = "cart-button")
    private WebElement divCartButton;

    private static final String pizzaProductCardStringLocator = "//div[@class='product-card__title'][text()='%s']/parent::div/parent::div";

    private final By miniBagLocator = By.className("cart-button__mini-bag");
    private final By pizzasInMiniBagLocator = By.className("product-card--mini-bag");
    private final By pizzaNameOnCardLocator = By.className("product-card__title");
    private final By pizzaDescriptionOnCardLocator = By.className("product-card__description");
    private final By btnRemovePizzaFromCartInCardLocator = By.className("product-card__button-close");
    private final By pizzaCardTitleLocator = By.className("product-card__title");
    private final By addToCartInCardButtonLocator = By.className("product-card__actions");
    private final By loginButtonLocator = By.xpath("//button[text()='Войти']");
    private final By profileButtonDivLocator = By.xpath("//a[@href='/user']/parent::*");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage login(User user) {
        waitAndGet(loginButtonLocator).click();
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
        waitAndGet(waitAndGet(profileButtonDivLocator), By.tagName("a")).click();
        return new ProfilePage(driver);
    }

    @Override
    public MainPage openPage() {
        driver.get(MAIN_PAGE_URL);
        waitJavascriptIsExecuted(WAIT_TIMEOUT_SECONDS);
        return this;
    }

    public WebElement getPizzaProductCard(Pizza pizza) {
        return getPizzaProductCard(pizza.getName());
    }

    public WebElement getPizzaProductCard(String pizzaName) {
        return waitAndGet(By.xpath(String.format(pizzaProductCardStringLocator, pizzaName)));
    }

    public MainPage addPizzaToCard(Pizza pizza) {
        WebElement pizzaCard = getPizzaProductCard(pizza);
        List<WebElement> selectList = pizzaCard.findElements(By.tagName("select"));

        Select selectSize = new Select(selectList.get(0));
        selectSize.selectByValue(pizza.getSize());
        Select selectSidetype = new Select(selectList.get(1));
        selectSidetype.selectByValue(pizza.getSideType());

        pizzaCard.findElement(addToCartInCardButtonLocator).click();

        return this;
    }

    public MainPage openCart() {
        if (!isCartOpened()) divCartButton.click();
        return this;
    }

    public boolean isCartOpened() {
        try {
            driver.findElement(miniBagLocator);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public WebElement getLastAddedToCartPizzaCard() {
        return getInCartPizzasCards().get(0);
    }

    public List<WebElement> getInCartPizzasCards() {
        openCart();
        return driver.findElements(pizzasInMiniBagLocator);
    }

    public Pizza getPizzaFromMiniBagCard(WebElement pizzaCard) {
        String name = pizzaCard.findElement(pizzaNameOnCardLocator).getText();
        String description = pizzaCard.findElement(pizzaDescriptionOnCardLocator).getText();
        String[] sidetypeAndSize = description.split(", ");
        String sidetype = sidetypeAndSize[0];
        String size = sidetypeAndSize[1];

        return new Pizza(name, size, sidetype);
    }

    public Pizza getLastAddedToCartPizza() {
        return getPizzaFromMiniBagCard(getLastAddedToCartPizzaCard());
    }

    public int pizzasInCartCount() {
        return getInCartPizzasCards().size();
    }

    public WebElement getPizzaMiniBagCard(Pizza pizza) {
        return getPizzaMiniBagCard(pizza.getName());
    }

    public String getPizzaNameFromMiniBagCard(WebElement pizzaCard) {
        return pizzaCard.findElement(pizzaCardTitleLocator).getText();
    }

    public WebElement getPizzaMiniBagCard(String name) {
        List<WebElement> pizzasList = getInCartPizzasCards();
        for (WebElement pizzaCard : pizzasList)
            if (getPizzaNameFromMiniBagCard(pizzaCard).equalsIgnoreCase(name)) return pizzaCard;
        return null;
    }

    public MainPage removePizzaFromCart(String name) {
        WebElement pizzaCard = getPizzaMiniBagCard(name);
        pizzaCard.findElement(btnRemovePizzaFromCartInCardLocator).click();
        return this;
    }

    public MainPage removePizzaFromCart(Pizza pizza) {
        return removePizzaFromCart(pizza.getName());
    }
}
