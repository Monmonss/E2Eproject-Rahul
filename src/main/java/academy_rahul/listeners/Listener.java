package academy_rahul.listeners;

import academy_rahul.base.Base;
import academy_rahul.utilities.ExtentReporterNG;
import academy_rahul.utilities.MonitoringMail;
import academy_rahul.utilities.TestConfig;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Date;

public class Listener extends Base implements ITestListener {
    ExtentTest test;
    ExtentReports extent = ExtentReporterNG.getReportObject();
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {

        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test passed");
    }


    @Override
    public void onTestFailure(ITestResult result) { //dotyczy każdego z anotacją @Test
        WebDriver driver = null;
        Date date = new Date();
        String nameP = date.toString().replaceAll(":", "_").replaceAll(" ", "_");
        extentTest.get().fail(result.getThrowable());
        String name = result.getMethod().getMethodName() + nameP;
        String fName = name + ".png";

        //result przechowuje info o teście, SPOSOB NA DOSTANIE SIE DO POLA JAKIEJKOLWIEK KLASY
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        try {
            extentTest.get().addScreenCaptureFromPath(getScreenshot(name, driver), name);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onTestSkipped(ITestResult result) {

    }


    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }


    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        MonitoringMail monitoringMail = new MonitoringMail();
        String message = "Test completed";
        try {
            monitoringMail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    }

