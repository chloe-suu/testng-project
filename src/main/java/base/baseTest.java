package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.HomePage;
import utilities.Constants;

import java.time.Duration;
import java.util.ResourceBundle;

public class baseTest {
    static WebDriver driver;
    protected static HomePage home;

    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+
                "\\src\\main\\divers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.TIMEOUT));

        openPage(Constants.homepage_url);
        home = new HomePage(getDriver());
        home.loginToWeb();
    }

    public void openPage(String url) {
        driver.get(url);
    }

//    @AfterSuite(alwaysRun = true)
//    public void tearDown() {
//        if(driver!=null) {
//            driver.close();
//            driver.quit();
//        }
//    }
    public static WebDriver getDriver(){
        return driver;

    }
    static ResourceBundle getResources(){
        //Load properties file
        return ResourceBundle.getBundle("resources");
    }
}
