package com.orangehrm;

//import com.relevantcodes.extentreports.LogStatus;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.aventstack.extentreports.Status;
import common.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.orangeHRM.*;
import reportConfig.ExtentManager;

public class ChainTestReport extends BaseTest {
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
        ChainTestListener.log("Login to OrangeHRM");
        ChainTestListener.log("Step 01: Enter to Username textbox with value is '" + "hoanglong98" + "'");
        loginPage.enterToUsername("hoanglong98");
        ChainTestListener.log("Step 02: Enter to Password textbox with value is '" + "Long01101998@" + "'");
        loginPage.enterToPassword("Long01101998@");
        ChainTestListener.log("Step 03: Click to Login button");
        loginPage.clickToLoginButton();
        // Failed verification example, but test will continue, and failure will be logged (not stop the test), using MethodListener and BaseTest verify methods
        ChainTestListener.log("Step 04: Verify Spinner loading is invisible");
        verifyTrue(homePage.isSpinnerLoadingInvisible());
    }

    @Test(priority = 2)
    public void AddNewEmployee() {
        ChainTestListener.log("Step 01: Open PIM page");
        homePage.openPageByPageName("PIM");
        ChainTestListener.log("Step 02: Verify Spinner loading is invisible");
        verifyTrue(homePage.isSpinnerLoadingInvisible());
        ChainTestListener.log("Step 03: Click on Add Employee button");
        pimPage.clickOnAddEmployee();
        ChainTestListener.log("Step 04: Input to First Name textbox with value is '" + firstName + "'");
        pimPage.getAddEmployeePage().setFirstName(firstName);
        ChainTestListener.log("Step 05: Input to Last Name textbox with value is '" + lastName + "'");
        pimPage.getAddEmployeePage().setLastName(lastName);
        ChainTestListener.log("Step 06: Get Employee ID");
        employeeId = pimPage.getAddEmployeePage().getEmployeeId();
        ChainTestListener.log("Step 06: Click on Save button");
        pimPage.getAddEmployeePage().clickSaveButton();
    }

    @Test(priority = 3)
    public void VerifyNewEmployee() {
        ChainTestListener.log("Step 01: Verify new employee information is displayed correctly");
        String actualFirstName = personalDetailPage.getFirstName();
        String actualLastName = personalDetailPage.getLastName();
        String actualEmployeeId = personalDetailPage.getEmployeeId();
        ChainTestListener.log("First Name - Expected: '" + firstName + "' - Actual: '" + actualFirstName + "'");
        Assert.assertEquals(actualFirstName, lastName);
        ChainTestListener.log("Last Name - Expected: '" + lastName + "' - Actual: '" + actualLastName + "'");
        Assert.assertEquals(actualLastName, firstName);
        ChainTestListener.log("Employee ID - Expected: '" + employeeId + "' - Actual: '" + actualEmployeeId + "'");
        Assert.assertEquals(actualEmployeeId, employeeId);


    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }
}
