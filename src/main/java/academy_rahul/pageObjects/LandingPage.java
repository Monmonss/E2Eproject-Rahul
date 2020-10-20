package academy_rahul.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LandingPage {

    public WebDriver driver;

    public LandingPage(WebDriver driver) {
        this.driver = driver;
    }

    private By signInButton = By.xpath("//a[contains(@href,'sign_in')]");
    private By featureText = By.xpath("//section[@id='content']//div[@class='text-center']");
    private By bar = By.xpath("//nav[@class='navbar-collapse collapse']");
    private By popUp = By.xpath("//button[text()='NO THANKS']");

    public WebElement getLoginButton() {

        return driver.findElement(signInButton);
    }

    public WebElement getPopUp() {

        return driver.findElement(popUp);
    }

    public LoginPage clickLoginButton() {
        driver.findElement(signInButton).click();
        return new LoginPage(driver);
    }

    public WebElement getFeatureText() {

        return driver.findElement(featureText);
    }

    public WebElement getBar() {

        return driver.findElement(bar);
    }

    public int getPopUpSize() {
        return driver.findElements(popUp).size();
    }
}
