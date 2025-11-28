package com.orangehrm;

import common.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.orangeHRM.*;

// Use the verify methods from BaseTest instead of Assert
// Add assertions using verifyTrue, verifyFalse, and verifyEquals
// Add MethodListener annotation to use custom listener for logging (Report failed steps without stopping the test)
// If U don't use MethodListener, failed verifications will not log the failure details in the report, always showing passed tests.

public class Assert_Verify extends BaseTest {
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
        loginPage.enterToUsername("hoanglong98");
        loginPage.enterToPassword("Long01101998@");
        loginPage.clickToLoginButton();
        // Failed verification example, but test will continue, and failure will be logged (not stop the test), using MethodListener and BaseTest verify methods
        verifyFalse(homePage.isSpinnerLoadingInvisible());
    }

    @Test(priority = 2)
    public void AddNewEmployee() {
        homePage.openPageByPageName("PIM");
        verifyTrue(homePage.isSpinnerLoadingInvisible());
        pimPage.clickOnAddEmployee();
        pimPage.getAddEmployeePage().setFirstName(firstName);
        pimPage.getAddEmployeePage().setLastName(lastName);
        employeeId = pimPage.getAddEmployeePage().getEmployeeId();
        pimPage.getAddEmployeePage().clickSaveButton();
    }

    @Test(priority = 3)
    public void VerifyNewEmployee() {
        String actualFirstName = personalDetailPage.getFirstName();
        String actualLastName = personalDetailPage.getLastName();
        String actualEmployeeId = personalDetailPage.getEmployeeId();
        verifyEquals(actualFirstName, firstName);
        verifyEquals(actualLastName, lastName);
        verifyEquals(actualEmployeeId, employeeId);
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }
}
