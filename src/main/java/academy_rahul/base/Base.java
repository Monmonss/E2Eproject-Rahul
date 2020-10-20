package academy_rahul.base;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {

    protected Properties properties;
    private FileInputStream fileInputStream;
    protected WebDriver driver;
    public static Logger logger = LogManager.getLogger(Base.class.getName());//dla main

    public WebDriver initialization() {
        if (driver == null) {
            properties = new Properties();

            try {
                fileInputStream = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\properties\\properties.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                properties.load(fileInputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String browserType = properties.getProperty("browser");
//        String browserType = System.getProperty("browser"); //ta komenda bierze wartość z komendy maven, dzięki czemu mam większą kontrolę na jakiej
        //przeglądarce będę wykonywać test. Nie musze wchodzić w plik properties i zmieniać, mvn test -Dbrowser=chrome
        if (browserType.contains("chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\java\\academy_rahul\\drivers\\chromedriver.exe");
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("profile.default_content_setting_values.notifications", 2);
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", prefs);
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-infobars");
            if(browserType.contains("headless")){ //wykona się bez otwarcia przeglądarki
                options.addArguments("headless");
            }
            driver = new ChromeDriver(options);
        } else if (browserType.contains("firefox")) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\src\\main\\java\\academy_rahul\\drivers\\geckodriver.exe");
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            if(browserType.contains("headless")){
                firefoxOptions.addArguments("headless");
            }
            driver = new FirefoxDriver(firefoxOptions);
        }
        driver.get(properties.getProperty("url"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(properties.getProperty("implicity_wait")), TimeUnit.SECONDS);
        return driver;
    }

    public String getScreenshot(String methodName, WebDriver driver) {
        //musi to byc ten konkretny driver na ktorym dany test się wykonuje
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir") + "\\reports\\" + methodName + ".png";
        try {
            FileUtils.copyFile(source, new File(destinationFile));
        } catch (IOException e) {
            logger.error("SS not created!!");
            e.printStackTrace();
        }
        return destinationFile;
    }


}
