package pages;

import base.basePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import utilities.Constants;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends basePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    //Locators
    By username = By.name("username");
    By password = By.name("password");
    By loginBtn = By.xpath("//button[@type=\"submit\"]");
    By header = By.xpath("//div[@class=\"oxd-topbar-header\"]");
    By navigation = By.xpath("//nav[@class=\"oxd-navbar-nav\"]");
    By breadcumHeader = By.xpath("//span[@class=\"oxd-topbar-header-breadcrumb\"]//h6");
    By menu = By.xpath("//a[contains(@class,'oxd-main-menu-item')]//span");

    //Methods
    public void loginToWeb(){
        driver.findElement(username).sendKeys(Constants.username);
        driver.findElement(password).sendKeys(Constants.password);
        driver.findElement(loginBtn).click();
    }

    public void verifyLoginSuccessfully(){
        driver.findElement(header).isDisplayed();
        driver.findElement(navigation).isDisplayed();
        Assert.assertEquals(driver.findElement(breadcumHeader).getText(),"Dashboard");
    }
    public void clickOptionInMenu(String option){
        List<WebElement> actual = driver.findElements(menu);
        for (WebElement e:actual){
            if (e.getText().equals(option)){
                e.click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(breadcumHeader));
                break;
            }
        }
    }
    public void verifyOpeningProperPage(String option){
        clickOptionInMenu(option);
        Assert.assertEquals(driver.findElement(breadcumHeader).getText(),option);
    }
}
