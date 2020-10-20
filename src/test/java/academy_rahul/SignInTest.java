//package academy_rahul;
//
//import academy_rahul.base.Base;
//import academy_rahul.pageObjects.LandingPage;
//import academy_rahul.pageObjects.LoginPage;
//import academy_rahul.utilities.DataProviderClass;
//import org.openqa.selenium.WebDriver;
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Test;
//
//public class SignInTest extends Base {
//    private LoginPage loginPage;
//    public WebDriver driver;
//    private boolean first = true;
//
//    @BeforeTest
//    public void setUp() {
//        driver = initialization();
//    }
//
//    @Test(dataProviderClass = DataProviderClass.class, dataProvider = "Details")
//    public void SignIn(String login, String password) throws InterruptedException {
//        if (first) {
//            LandingPage landingPage = new LandingPage(driver);
//            loginPage = landingPage.clickLoginButton();
//            DataProviderClass dataProviderClass = new DataProviderClass();
//            first = false;
//        }
//
//        loginPage.sendLogin(login);
//        loginPage.sendPassword(password);
//        loginPage.confirmLogin();
//
//    }
//
//
//    @AfterTest
//    public void tearDown() {
//        if (driver != null) {
//            driver.close();
//            driver.quit();
//        }
//    }
//}
