package academy_rahul.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PortalHp {
    WebDriver driver;

    public PortalHp(WebDriver driver) {
        this.driver = driver;
    }

    private By loginField = By.id("loggedWindow");

    public WebElement verifyLogin(){

       return driver.findElement(loginField);
    }


}
