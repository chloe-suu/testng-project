package tests;

import base.baseTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.AdminPage;
import pages.HomePage;
import utilities.DataProviders;


public class AdminPageTest extends baseTest {
    AdminPage admin;

    @BeforeTest
    public void before() {
        admin = new AdminPage(getDriver());
        home.clickOptionInMenu("Admin");
    }
    @Test(groups = {"Smoke Test"})
    public void test() throws InterruptedException {
        String role = "Admin";
        String name = "a";
        String status = "enabled";
        String username = "user123";
        String pwd ="user12345";
        admin.clickAdd();
        admin.clickUserRoleBox();
        admin.selectUserRole(role);
        admin.clickStatusBox();
        admin.selectStatus(status);
        admin.enterUsername(username);
        admin.enterPwdAndConfirm(pwd);
        String fullName = admin.enterEmployeeName(name);
        admin.clickSave();
        admin.verifyAddedUserDisplayedInTable( username,  role, fullName,  status);

    }

//    @Test(dataProvider = "users", dataProviderClass = DataProviders.class)
//    public void verifyAddUser(String role, String name, String status, String username, String password) throws InterruptedException {
//
//        admin.clickAdd();
//        admin.clickUserRoleBox();
//        admin.selectUserRole(role);
//        admin.clickStatusBox();
//        admin.selectStatus(status);
//        admin.enterEmployeeName(name);
//        admin.enterUsername(username);
//        admin.enterPwdAndConfirm(password);
//        admin.clickSave();
//        admin.verifyAddedUserDisplayedInTable(role,status,username);
//    }

}
