package com.orangehrm;

import common.BaseTest;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.orangeHRM.*;

@Epic("Regression Tests")
@Feature("Employee Management Feature")
public class AllureReport extends BaseTest {
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
    @Description("Login to OrangeHRM Application")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Login Story")
    @Test(priority = 1)
    public void LoginToOrangeHRM() {
        loginPage.enterToUsername("hoanglong98");
        loginPage.enterToPassword("Long01101998@");
        loginPage.clickToLoginButton();
        // Failed verification example, but test will continue, and failure will be logged (not stop the test), using MethodListener and BaseTest verify methods
        verifyTrue(homePage.isSpinnerLoadingInvisible());
    }
    @Description("Add new Employee to OrangeHRM Application")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Add Employee")
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
    @Description("Verify new created Employee in OrangeHRM Application")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Add Employee")
    @Test(priority = 3)
    public void VerifyNewEmployee() {
        String actualFirstName = personalDetailPage.getFirstName();
        String actualLastName = personalDetailPage.getLastName();
        String actualEmployeeId = personalDetailPage.getEmployeeId();
        Assert.assertEquals(actualFirstName, lastName);
        Assert.assertEquals(actualLastName, firstName);
        Assert.assertEquals(actualEmployeeId, employeeId);


    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }
}
