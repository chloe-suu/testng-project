package base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Constants;

import java.time.Duration;

public class basePage {

    public WebDriver driver;
    public WebDriverWait wait;
    public JavascriptExecutor executor;

    public basePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(Constants.TIMEOUT));
        this.executor = (JavascriptExecutor) driver;
    }


}
