package steps;

import academy_rahul.base.Base;
import academy_rahul.pageObjects.LandingPage;
import academy_rahul.pageObjects.LoginPage;
import academy_rahul.pageObjects.PortalHp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;


public class MyStepdefs extends Base {
    private LoginPage loginPage;
    private LandingPage landingPage;
    private PortalHp portalHp;

    @Given("Initialize the browser")
    public void initialize_the_browser() {
        driver = initialization();
    }


    @Given("click on site Login page")
    public void click_on_site_login_page() {
        landingPage = new LandingPage(driver);
        boolean isDisplayed = landingPage.getPopUpSize() > 0;
        if (isDisplayed) {
            landingPage.getPopUp().click();
        }
            loginPage = landingPage.clickLoginButton();

    }

    //    @When("User login to using login {string} and password {string}")
//    public void user_login_to_using_login_and_password(String login, String password) {
//        loginPage.sendLogin(login);
//        loginPage.sendPassword(password);
//        portalHp = loginPage.confirmLogin();
//
//    }
    @Then("Verify if user is successfully logged in")
    public void verify_if_user_is_successfully_logged_in() {
        Assert.assertTrue(portalHp.verifyLogin().isDisplayed());

    }

    @When("User login to using login {string} and password {string}")
    public void userLoginToUsingLoginAndPassword(String login, String password) {
        loginPage.sendLogin(login);
        loginPage.sendPassword(password);
        portalHp = loginPage.confirmLogin();
    }

    @Then("close the browser")
    public void closeTheBrowser() {
        driver.close();
        driver.quit();
    }
}
