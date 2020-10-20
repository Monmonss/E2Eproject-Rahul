package academy_rahul.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private By loginField = By.id("user_email");
    private By passwordField = By.id("user_password");
    private By loginButton = By.name("commit");

    public void sendLogin(String login){
        driver.findElement(loginField).sendKeys(login);
    }

    public void sendPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
    }
    public PortalHp confirmLogin(){
        driver.findElement(loginButton).click();
        return new PortalHp(driver);
    }
}
