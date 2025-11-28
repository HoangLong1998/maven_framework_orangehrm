package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import enums.BrowserType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import common.VerificationFailures;
import org.testng.Reporter;

import java.util.Locale;


public class BaseTest {
    /**
     * Initializes a WebDriver instance based on the specified browser name and navigates to the given URL.
     *
     * @param url         The URL to navigate to.
     * @param browserName The name of the browser to use (e.g., "CHROME", "EDGE").
     * @return A WebDriver instance for the specified browser.
     * @throws RuntimeException If the browser name is invalid.
     */
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

    /**
     * Verifies that a condition is true. If the condition is false, the failure is logged.
     *
     * @param condition The condition to verify.
     * @return True if the condition is true, false otherwise.
     */
    protected boolean verifyTrue(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertTrue(condition);
        } catch (Throwable e) {
            pass = false;

            // Logs the failure for the current test result and associates the throwable error.
            // This method adds the failure to the VerificationFailures instance and sets the throwable
            // for the current test result in the TestNG Reporter.
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);

        }
        return pass;
    }

    /**
     * Verifies that a condition is false. If the condition is true, the failure is logged.
     *
     * @param condition The condition to verify.
     * @return True if the condition is false, false otherwise.
     */
    protected boolean verifyFalse(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertFalse(condition);
        } catch (Throwable e) {
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    /**
     * Verifies that two objects are equal. If they are not equal, the failure is logged.
     *
     * @param actual   The actual object.
     * @param expected The expected object.
     * @return True if the objects are equal, false otherwise.
     */
    protected boolean verifyEquals(Object actual, Object expected) {
        boolean pass = true;
        try {
            Assert.assertEquals(actual, expected);
        } catch (Throwable e) {
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

}