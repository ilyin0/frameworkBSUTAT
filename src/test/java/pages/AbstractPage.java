package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public abstract class AbstractPage {

    protected WebDriver driver;
    protected final int WAIT_TIMEOUT_SECONDS = 10;

    protected abstract AbstractPage openPage();

    public void closePage() {
        driver.quit();
    }

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
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

}
