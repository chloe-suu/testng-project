package utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {
    @DataProvider(name = "navigation")
    public Object[][] menu(){
        return new Object[][] {
                {"Admin"}, {"PIM"}, {"Leave"}, {"Time"}, {"Recruitment"}, {"My Info"}
                , {"Performance"}, {"Directory"}, {"Claim"}, {"Buzz"}, {"Maintenance"}
        };
    }

    @DataProvider(name = "users")
    public String[][] users() throws IOException {
        String path = System.getProperty("user.dir")+ "/src/main/testdata/usersHRM.xlsx";
        String sheetName = "users";
        XLUtility excel = new XLUtility(path);
        return excel.getDataTable(sheetName);
    }
}
