package com.orangehrm;

import common.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.orangeHRM.*;


public class ElementDisplayed_UndisplayedVerification extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;
    PIMPageObject pimPage;
    AddEmployeePageObject addEmployee;
    PersonalDetailPageObject personalDetailPage;
    LoginPagePageObject loginPage;

    String firstName, lastName, employeeId, employeeUsername, employeePassword;


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
        employeeUsername = "employee.username" + pimPage.generateRandomNumber() + "@gmail.com";
        employeePassword = "Password@123123456";

    }

    @Test(priority = 1)
    public void LoginToOrangeHRM() {
        loginPage.enterToUsername("hoanglong98");
        loginPage.enterToPassword("Long01101998@");
        loginPage.clickToLoginButton();
        verifyTrue(homePage.isSpinnerLoadingInvisible());
    }

    @Test(priority = 2)
    public void VerifyMenuItemsAreDisplayed() {
        verifyTrue(homePage.isPageLinkDisplayedByName("Admin"));
        verifyTrue(homePage.isPageLinkDisplayedByName("PIM"));
        verifyTrue(homePage.isPageLinkDisplayedByName("Recruitment"));
        verifyTrue(homePage.isPageLinkDisplayedByName("Maintenance"));

    }

    @Test(priority = 3)
    public void AddNewEmployee() {
        homePage.openPageByPageName("PIM");
        verifyTrue(homePage.isSpinnerLoadingInvisible());
        pimPage.clickOnAddEmployee();
        pimPage.getAddEmployeePage().setFirstName(firstName);
        pimPage.getAddEmployeePage().setLastName(lastName);
        employeeId = pimPage.getAddEmployeePage().getEmployeeId();
        pimPage.getAddEmployeePage().clickToCreateLoginDetailsCheckbox();
        pimPage.getAddEmployeePage().setUsername(employeeUsername);
        pimPage.getAddEmployeePage().setPassword(employeePassword);
        pimPage.getAddEmployeePage().setConfirmPassword(employeePassword);
        pimPage.getAddEmployeePage().clickSaveButton();
    }

    @Test(priority = 4)
    public void VerifyNewEmployee() {
        personalDetailPage.waitForSpinnerLoadingDisAppeared();
        String actualFirstName = personalDetailPage.getFirstName();
        String actualLastName = personalDetailPage.getLastName();
        String actualEmployeeId = personalDetailPage.getEmployeeId();
        verifyEquals(actualFirstName, firstName);
        verifyEquals(actualLastName, lastName);
        verifyEquals(actualEmployeeId, employeeId);
    }

    @Test(priority = 5)
    public void Logout() {
        homePage.clickToLogOut();
        loginPage.waitForSpinnerLoadingDisAppeared();
        loginPage.enterToUsername(employeeUsername);
        loginPage.enterToPassword(employeePassword);
        loginPage.clickToLoginButton();
        verifyTrue(homePage.isSpinnerLoadingInvisible());
    }

    @Test(priority = 6)
    public void VerifyMenuItemsAreUndisplayed() {
        verifyTrue(homePage.isPageLinkUndisplayedByName("Admin"));
        verifyTrue(homePage.isPageLinkUndisplayedByName("PIM"));
        verifyTrue(homePage.isPageLinkUndisplayedByName("Recruitment"));
        verifyTrue(homePage.isPageLinkUndisplayedByName("Maintenance"));
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }
}
