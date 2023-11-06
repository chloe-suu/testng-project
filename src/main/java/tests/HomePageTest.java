package tests;

import base.baseTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomePage;
import utilities.Constants;
import utilities.DataProviders;

public class HomePageTest extends baseTest {

    HomePage home ;
    @BeforeTest(alwaysRun = true)
    public void beforeTest(){
        home = new HomePage(getDriver());
    }
    @Test(priority = 1, groups = {"Regression", "Smoke Test"})
    public void loginTest(){
        home.verifyLoginSuccessfully();
    }
    @Test(priority = 2, dataProvider = "navigation", dataProviderClass = DataProviders.class)
    public void verifyOpeningProperPagesWhenClickingNavigation(String option){
        home.verifyOpeningProperPage(option);
    }

}