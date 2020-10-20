package academy_rahul;

import academy_rahul.base.Base;
import academy_rahul.pageObjects.LandingPage;
import academy_rahul.pageObjects.LoginPage;
import academy_rahul.utilities.DataProviderClass;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CheckingTextTest extends Base {


    private LoginPage loginPage;
    private boolean first = true;
    private LandingPage landingPage;
    private final Logger log = Logger.getLogger("devpinoyLogger"); //dla test

    @BeforeTest
    public void setUp() {
        driver = initialization();
    }




    @Test()
    public void CheckingBar() {
        landingPage = new LandingPage(driver);
        Assert.assertTrue(landingPage.getBar().isDisplayed());
        logger.info("Bar has shown successfully");
    }

    @Test()
    public void CheckingText() {
        landingPage = new LandingPage(driver);
        log.info("Landing page has been created.");
        Assert.assertEquals(landingPage.getFeatureText().getText(), "FEATURED COURSES1");
        log.debug("Bar displayed");
    }
    @Test(dataProviderClass = DataProviderClass.class, dataProvider = "Details")
    public void SignIn(String login, String password) throws InterruptedException {
        if (first) {
            landingPage = new LandingPage(driver);
            loginPage = landingPage.clickLoginButton();
            DataProviderClass dataProviderClass = new DataProviderClass();
            first = false;
        }

        loginPage.sendLogin(login);
        loginPage.sendPassword(password);
        loginPage.confirmLogin();

    }



    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }


}
