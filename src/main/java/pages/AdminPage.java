package pages;

import base.basePage;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import utilities.Constants;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

import static java.lang.Thread.sleep;

public class AdminPage extends basePage {
    public AdminPage(WebDriver driver){
        super(driver);
    }
    //Locators
    By addBtn = By.xpath("//button[normalize-space()='Add']");
    By userRoleBox = By.xpath("//label[contains(text(),'User Role')]//ancestor::div[contains(@class,'oxd-grid-item oxd')]//div[@class='oxd-select-wrapper']");
    By statusBox = By.xpath("//label[contains(text(),'Status')]//ancestor::div[contains(@class,'oxd-grid-item oxd')]//div[@class='oxd-select-wrapper']");
    By options = By.xpath("//div[@role = 'option']//span");
    By password = By.xpath("//input[@type='password']");
    By employeeName = By.xpath("//label[contains(text(),'Employee Name')]//ancestor::div[contains(@class,'oxd-grid-item oxd')]//input");
    By eNameOptions =By.xpath("//div[@role= 'listbox']//div[@role = 'option']");
    By username = By.xpath("//label[contains(text(),'Username')]//ancestor::div[contains(@class,'oxd-grid-item oxd')]//input");
    By saveBtn = By.xpath("//button[@type ='submit' and normalize-space()='Save']");

    By userTable = By.xpath("//div[@class='oxd-table-body']");
    By rowsInTable = By.xpath(".//div[@role = 'row']");
    By cellsInRow = By.xpath(".//div[@role = 'cell']");
    //Methods
    public void clickAdd(){
        driver.findElement(addBtn).click();
    }
    public void clickUserRoleBox(){
        driver.findElement(userRoleBox).click();
    }
    public void selectUserRole(String role){
        List<WebElement> roles = driver.findElements(options);
        for (WebElement e:roles) {
            if (e.getText().equalsIgnoreCase(role)){
                e.click();
            break;}
        }
    }
    public void clickStatusBox(){
        //executor.executeScript("arguments[0].click();", driver.findElement(statusBox));
        driver.findElement(statusBox).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(options));
    }
    public void selectStatus(String state){
        List<WebElement> status = driver.findElements(options);
        for (WebElement e:status) {
            if (e.getText().equalsIgnoreCase(state))
            {   e.click();
            break;}
        }
    }
    public String enterEmployeeName(String key) throws InterruptedException {
        WebElement employee = driver.findElement(employeeName);
        employee.clear();
        employee.sendKeys(key);
        employee.sendKeys(Keys.ENTER);
        sleep(3000);
        List<WebElement> suggestion = driver.findElements(eNameOptions);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(eNameOptions));
        key = suggestion.get(0).getText();
        suggestion.get(0).click();
        return key;
    }

    public void enterUsername(String key){
        WebElement userName = driver.findElement(username);
        userName.clear();
        userName.sendKeys(key);
    }

    public void enterPwdAndConfirm(String key){
        List<WebElement> pwd = driver.findElements(password);
        for (WebElement e : pwd) {
            e.clear();
            e.sendKeys(key);
        }
    }

    public void clickSave(){
        driver.findElement(saveBtn).click();
    }

    public void verifyAddedUserDisplayedInTable(String username, String role,String name, String status ){
        boolean test = false;
        WebElement table = driver.findElement(userTable);
        List<WebElement> rows = table.findElements(rowsInTable);
        for (WebElement e: rows){
            List<WebElement> cells = e.findElements(cellsInRow);
            if (cells.get(1).getText().equalsIgnoreCase(username)
                    |cells.get(2).getText().equalsIgnoreCase(role)
                    |cells.get(3).getText().equalsIgnoreCase(name)
                    |cells.get(4).getText().equalsIgnoreCase(status)){
                test = true;
            }
            break;
        }
        Assert.assertTrue(test);
    }
}
