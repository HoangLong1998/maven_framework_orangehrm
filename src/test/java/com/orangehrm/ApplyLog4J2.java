package com.orangehrm;

import common.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.orangeHRM.*;

public class ApplyLog4J2 extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;
    PIMPageObject pimPage;
    AddEmployeePageObject addEmployee;
    PersonalDetailPageObject personalDetailPage;
    LoginPagePageObject loginPage;

    String firstName;
    String lastName;
    String employeeId;


    @Parameters({"url", "browser"})
    @BeforeClass
    public void initPage(String url, String browser) {
        driver = getBrowserDriver(url, browser);
        loginPage = new LoginPagePageObject(driver);
        homePage = new HomePageObject(driver);
        pimPage = new PIMPageObject(driver);
        personalDetailPage = new PersonalDetailPageObject(driver);
        firstName = "Robert";
        lastName = "Hoang";

    }

    @Test(priority = 1)
    public void LoginToOrangeHRM() {
      //  log.info("Login - Step 01: Enter to Username textbox with value is '" + "hoanglong98" + "'");
        loginPage.enterToUsername("hoanglong98");
      //  log.info("Login - Step 02: Enter to Password textbox with value is '" + "Long01101998@" + "'");
        loginPage.enterToPassword("Long01101998@");
      //  log.info("Login - Step 03: Click to Login button");
        loginPage.clickToLoginButton();
        // Failed verification example, but test will continue, and failure will be logged (not stop the test), using MethodListener and BaseTest verify methods
      //  log.info("Login - Step 04: Verify Spinner loading is invisible");
        verifyFalse(homePage.isSpinnerLoadingInvisible());
    }

    @Test(priority = 2)
    public void AddNewEmployee() {
      //  log.info("Add New Employee - Step 01: Open PIM page");
        homePage.openPageByPageName("PIM");
      //  log.info("Add New Employee - Step 02: Verify Spinner loading is invisible");
        verifyTrue(homePage.isSpinnerLoadingInvisible());
       // log.info("Add New Employee - Step 03: Click on Add Employee button");
        pimPage.clickOnAddEmployee();
      //  log.info("Add New Employee - Step 04: Input to First Name textbox with value is '" + firstName + "'");
        pimPage.getAddEmployeePage().setFirstName(firstName);
       // log.info("Add New Employee - Step 05: Input to Last Name textbox with value is '" + lastName + "'");
        pimPage.getAddEmployeePage().setLastName(lastName);
        employeeId = pimPage.getAddEmployeePage().getEmployeeId();
      //  log.info("Add New Employee - Step 06: Click on Save button");
        pimPage.getAddEmployeePage().clickSaveButton();
    }

    @Test(priority = 3)
    public void VerifyNewEmployee() {
      //  log.info("Verify New Employee - Step 01: Verify new employee information is displayed correctly");
        String actualFirstName = personalDetailPage.getFirstName();
        String actualLastName = personalDetailPage.getLastName();
        String actualEmployeeId = personalDetailPage.getEmployeeId();
     //   log.info("First Name - Expected: '" + firstName + "' - Actual: '" + actualFirstName + "'");
        verifyEquals(actualFirstName, firstName);
     //   log.info("Last Name - Expected: '" + lastName + "' - Actual: '" + actualLastName + "'");
        verifyEquals(actualLastName, lastName);
      //  log.info("Employee ID - Expected: '" + employeeId + "' - Actual: '" + actualEmployeeId + "'");
        verifyEquals(actualEmployeeId, employeeId);
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }
}
