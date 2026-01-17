package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import enums.BrowserType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import common.VerificationFailures;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import java.io.File;
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
//    protected final Logger log;
//
//    public BaseTest() {
//        log = LogManager.getLogger(getClass());
//    }


    protected WebDriver driver;


    public WebDriver getBrowserDriver(String url, String browserName) {
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
        //    log.info("Launch " + browserName + " browser and navigate to: " + url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
        return driver;
    }

    protected WebDriver getBrowserDriver(String browserName) {
        return driver;
    }

    public WebDriver getDriver() {
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
            //          log.info("------------------------------PASSED------------------------------");

        } catch (Throwable e) {
            pass = false;
            //        log.info("------------------------------FAILED------------------------------");
            //        log.info(e.getMessage());

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
            //        log.info("--------------------------------PASSED------------------------------");
        } catch (Throwable e) {
            pass = false;
            //        log.info("-------------------------------FAILED------------------------------");
            //       log.info(e.getMessage());
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
            //        log.info("-------------------------------PASSED------------------------------");
        } catch (Throwable e) {
            pass = false;
            //      log.info("--------------------------------FAILED------------------------------");
            //       log.info(e.getMessage());
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    // Used to delete all files in ReportNG and Allure report before suite starts
    @BeforeSuite
    public void deleteFileInReport() {
        // Remove all file in ReportNG screenshot (image)
        deleteAllFileInFolder("reportNGImage");

        // Remove all file in Allure attachment (json file)
        deleteAllFileInFolder("allure-json");
    }

    public void deleteAllFileInFolder(String folderName) {
        try {
            String pathFolderDownload = GlobalConstants.PROJECT_PATH + folderName;
            File file = new File(pathFolderDownload);
            File[] listOfFiles = file.listFiles();
            if (listOfFiles != null && listOfFiles.length != 0) {
                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile() && !listOfFiles[i].getName().equals("environment.properties")) {
                        new File(listOfFiles[i].toString()).delete();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

