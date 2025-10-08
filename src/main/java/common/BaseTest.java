package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import enums.BrowserType;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Locale;


public class BaseTest {
    public WebDriver getBrowserDriver(String url, String browserName) {
        WebDriver driver = null;
        BrowserType browserType = BrowserType.valueOf(browserName.toUpperCase(Locale.ROOT));
        switch (browserType) {
            case CHROME:
                driver = new ChromeDriver();
                break;
            case EDGE:
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Please enter correct browser name");
        }
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
        return driver;
    }

}

