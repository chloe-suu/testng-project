package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestNGListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import base.baseTest;

public class ExtentReportManager extends TestListenerAdapter implements ITestNGListener{
    public ExtentSparkReporter spark;
    public ExtentReports extent;
    public ExtentTest test;

    String rpName;

    public void onStart(ITestContext testContext){
        //timestamp
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        rpName = "Test-Report-" + timestamp + ".html";

        //specify location for the report
        spark = new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/" + rpName);

        spark.config().setDocumentTitle("testngProject");
        spark.config().setReportName("UI Automation");
        spark.config().setCss("css-string");

        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Operation System", System.getProperty("os.name"));
        extent.setSystemInfo("Environment", "QA");

    }

    public void onTestSuccess(ITestResult result){
        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());

        //ExtentReports log operation for passed tests.
        test.log(Status.PASS, "Test passed");

    }
    public void onTestFailure (ITestResult result){
        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());

        //ExtentReports log operation for passed tests.
        test.log(Status.FAIL, "Test failed");
        test.log(Status.FAIL, result.getThrowable().getMessage());

        //Take screenshot
        String fileName= System.getProperty("user.dir")+ File.separator+"screenshots"
                +File.separator+result.getMethod().getMethodName();
        File fi = ((TakesScreenshot) baseTest.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(fi, new File(fileName+".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onTestSkipped (ITestResult result){
        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());

        //ExtentReports log operation for passed tests.
        test.log(Status.SKIP, "Test skipped");
        test.log(Status.SKIP, result.getThrowable().getMessage());
    }

    public void onFinish (ITestContext testContext){
        extent.flush();
    }
}
