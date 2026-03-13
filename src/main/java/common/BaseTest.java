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
     * Closes the browser driver and cleans up resources.
     * This method ensures that the browser driver is properly terminated and any associated processes are killed.
     * It also deletes all cookies before quitting the driver.
     */
    protected void closeBrowserDriver() {
        String browserDriverName = null;
        try {
            if (driver != null) {
                // Determine the browser driver name based on the WebDriver instance
                String driverInstanceName = driver.toString().toLowerCase();
                System.out.println("Driver instance name: " + driverInstanceName);
                if (driverInstanceName.contains("chrome")) {
                    browserDriverName = "chromedriver";
                } else if (driverInstanceName.contains("firefox")) {
                    browserDriverName = "geckodriver";
                } else if (driverInstanceName.contains("edge")) {
                    browserDriverName = "msedgedriver";
                }
                // Delete all cookies and quit the driver
                driver.manage().deleteAllCookies();
                driver.quit();
            }
        } catch (Exception e) {
            // Log the exception message (if logging is enabled)
            e.getMessage();
            // Attempt to kill the browser driver process if the driver name is known
            if (browserDriverName != null) {
                killDriverProcess(browserDriverName);
            }
        }
    }

    /**
     * Kills the browser driver process by its name.
     * This method uses platform-specific commands to terminate the driver process.
     *
     * @param driverName The name of the browser driver process to kill (e.g., "chromedriver").
     */
    private void killDriverProcess(String driverName) {
        try {
            ProcessBuilder pb;
            // Check the operating system and use the appropriate command
            if (System.getProperty("os.name").toLowerCase().contains("window")) {
                pb = new ProcessBuilder("taskkill", "/F", "/IM", driverName + ".exe");
            } else {
                pb = new ProcessBuilder("pkill", driverName);
            }
            // Start the process and wait for it to complete
            pb.start().waitFor();
        } catch (Exception e) {
            // Log the exception message (if logging is enabled)
            e.getMessage();
        }
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



    //   Delete file in report folder for ReportNG and Allure report
    /**
     * Deletes all files in specific folders before the test suite starts.
     * This method is annotated with @BeforeSuite, ensuring it runs once before any tests in the suite.
     * It targets two specific folders: "reportNGImage" and "allure-json".
     */
    @BeforeSuite
    public void deleteFileInReport() {
        // Remove all files in the ReportNG screenshot folder (images)
        deleteAllFileInFolder("reportNGImage");

        // Remove all files in the Allure attachment folder (JSON files)
        deleteAllFileInFolder("allure-json");
    }

    /**
     * Deletes all files in the specified folder.
     * This method iterates through all files in the given folder and deletes them,
     * except for files named "environment.properties".
     *
     * @param folderName The name of the folder from which files will be deleted.
     */
    public void deleteAllFileInFolder(String folderName) {
        try {
            // Construct the full path to the folder
            String pathFolderDownload = GlobalConstants.PROJECT_PATH + folderName;
            File file = new File(pathFolderDownload);

            // Get a list of all files in the folder
            File[] listOfFiles = file.listFiles();

            // Check if the folder contains files
            if (listOfFiles != null && listOfFiles.length != 0) {
                // Iterate through each file in the folder
                for (int i = 0; i < listOfFiles.length; i++) {
                    // Delete the file if it is not "environment.properties"
                    if (listOfFiles[i].isFile() && !listOfFiles[i].getName().equals("environment.properties")) {
                        new File(listOfFiles[i].toString()).delete();
                    }
                }
            }
        } catch (Exception e) {
            // Print the stack trace in case of an exception
            e.printStackTrace();
        }
    }

}

