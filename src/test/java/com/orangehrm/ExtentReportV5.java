package com.orangehrm;

//import com.relevantcodes.extentreports.LogStatus;

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

public class ExtentReportV5 extends BaseTest {
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
        ExtentManager.startTest("Login to system", "TC_01_NewCustomer");

        ExtentManager.getTest().log(Status.INFO, "Step 01: Enter to Username textbox with value is '" + "hoanglong98" + "'");
        loginPage.enterToUsername("hoanglong98");
        ExtentManager.getTest().log(Status.INFO, "Step 02: Enter to Password textbox with value is '" + "Long01101998@" + "'");
        loginPage.enterToPassword("Long01101998@");
        ExtentManager.getTest().log(Status.INFO, "Step 03: Click to Login button");
        loginPage.clickToLoginButton();
        // Failed verification example, but test will continue, and failure will be logged (not stop the test), using MethodListener and BaseTest verify methods
        ExtentManager.getTest().log(Status.INFO, "Step 04: Verify Spinner loading is invisible");
        verifyTrue(homePage.isSpinnerLoadingInvisible());
    }

    @Test(priority = 2)
    public void AddNewEmployee() {
        ExtentManager.startTest("Add New Employee", "TC_02_AddNewEmployee");
        ExtentManager.getTest().log(Status.INFO, "Step 01: Open PIM page");
        homePage.openPageByPageName("PIM");
        ExtentManager.getTest().log(Status.INFO, "Step 02: Verify Spinner loading is invisible");
        verifyTrue(homePage.isSpinnerLoadingInvisible());
        ExtentManager.getTest().log(Status.INFO, "Step 03: Click on Add Employee button");
        pimPage.clickOnAddEmployee();
        ExtentManager.getTest().log(Status.INFO, "Step 04: Input to First Name textbox with value is '" + firstName + "'");
        pimPage.getAddEmployeePage().setFirstName(firstName);
        ExtentManager.getTest().log(Status.INFO, "Step 05: Input to Last Name textbox with value is '" + lastName + "'");
        pimPage.getAddEmployeePage().setLastName(lastName);
        ExtentManager.getTest().log(Status.INFO, "Step 06: Get Employee ID");
        employeeId = pimPage.getAddEmployeePage().getEmployeeId();
        ExtentManager.getTest().log(Status.INFO, "Step 06: Click on Save button");
        pimPage.getAddEmployeePage().clickSaveButton();
    }

    @Test(priority = 3)
    public void VerifyNewEmployee() {
        ExtentManager.startTest("Verify New Employee", "TC_03_VerifyNewEmployee");
        ExtentManager.getTest().log(Status.INFO, "Step 01: Verify new employee information is displayed correctly");
        String actualFirstName = personalDetailPage.getFirstName();
        String actualLastName = personalDetailPage.getLastName();
        String actualEmployeeId = personalDetailPage.getEmployeeId();
        ExtentManager.getTest().log(Status.INFO, "First Name - Expected: '" + firstName + "' - Actual: '" + actualFirstName + "'");
        Assert.assertEquals(actualFirstName, lastName);
        ExtentManager.getTest().log(Status.INFO, "Last Name - Expected: '" + lastName + "' - Actual: '" + actualLastName + "'");
        Assert.assertEquals(actualLastName, firstName);
        ExtentManager.getTest().log(Status.INFO, "Employee ID - Expected: '" + employeeId + "' - Actual: '" + actualEmployeeId + "'");
        Assert.assertEquals(actualEmployeeId, employeeId);


    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }
}
