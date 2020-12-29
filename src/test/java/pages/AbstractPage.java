package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class AbstractPage {

    protected WebDriver driver;
    protected final int WAIT_TIMEOUT_SECONDS = 10;

    @FindBy(className = "modal__close")
    private WebElement btnCloseAds;

    private final By btnAdsClose = By.className("modal__close");

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected abstract AbstractPage openPage();

    public void closePage() {
        driver.quit();
    }

    public AbstractPage closeAds() {
        try {
            waitAndGet(btnAdsClose).click();
        }
        catch (NoSuchElementException ignored) {}
        return this;
    }

    protected boolean isJavascriptExecuted() {
        return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
    }

    protected AbstractPage waitJavascriptIsExecuted(long timeout) {
        new WebDriverWait(driver, timeout).until(d->isJavascriptExecuted());
        return this;
    }

    protected void setImplicitlyTimeouts(long time, TimeUnit timeUnit) {
        driver.manage().timeouts().implicitlyWait(time, timeUnit);
    }

    protected void resetImplicitlyTimeouts() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    protected void waitTime(long timeInMillis) {
        try {
            Thread.sleep(timeInMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected WebElement waitAndGet(By by, long timeoutInSeconds, long sleepTimeInMillis) {
        return new WebDriverWait(driver, timeoutInSeconds, sleepTimeInMillis).until(d->driver.findElement(by));
    }

    protected List<WebElement> waitAndGets(By by, long timeoutInSeconds) {
        return new WebDriverWait(driver, timeoutInSeconds).until(d->driver.findElements(by));
    }

    protected List<WebElement> waitAndGets(By by) {
        return waitAndGets(by, WAIT_TIMEOUT_SECONDS);
    }

    protected List<WebElement> waitAndGets(WebElement parent, By by) {
        return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(d->parent.findElements(by));
    }

    protected WebElement waitAndGet(By by, long timeoutInSeconds) {
        return new WebDriverWait(driver, timeoutInSeconds).until(d->driver.findElement(by));
    }

    protected WebElement waitAndGet(By by) {
        return waitAndGet(by, WAIT_TIMEOUT_SECONDS);
    }

    protected WebElement waitAndGet(WebElement parent, By by) {
        return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(d->parent.findElement(by));
    }

}
