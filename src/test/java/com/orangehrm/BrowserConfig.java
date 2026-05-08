package com.orangehrm;

import common.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.orangeHRM.*;


public class BrowserConfig extends BaseTest {
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

    @Test
    public void LoginToOrangeHRM() {
        loginPage.enterToUsername("hoanglong98");
        loginPage.enterToPassword("Long01101998@");
        loginPage.clickToLoginButton();
    }

    @Test(dependsOnMethods = "LoginToOrangeHRM")
    public void AddNewEmployee() {
        homePage.openPageByPageName("PIM");
        pimPage.clickOnAddEmployee();
        pimPage.getAddEmployeePage().setFirstName(firstName);
        pimPage.getAddEmployeePage().setLastName(lastName);
        employeeId = pimPage.getAddEmployeePage().getEmployeeId();
        pimPage.getAddEmployeePage().clickSaveButton();
    }

    @Test(dependsOnMethods = "AddNewEmployee")
    public void VerifyNewEmployee() {
        String actualFirstName = personalDetailPage.getFirstName();
        String actualLastName = personalDetailPage.getLastName();
        String actualEmployeeId = personalDetailPage.getEmployeeId();
        Assert.assertEquals(actualFirstName, firstName);
        Assert.assertEquals(actualLastName, lastName);
        Assert.assertEquals(actualEmployeeId, employeeId);
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }
}
