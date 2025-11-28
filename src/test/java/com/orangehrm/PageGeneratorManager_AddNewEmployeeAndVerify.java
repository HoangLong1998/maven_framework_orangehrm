package com.orangehrm;

import common.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageGeneratorManager.OrangeHRM.*;


public class PageGeneratorManager_AddNewEmployeeAndVerify extends BaseTest {
    WebDriver driver;
    HomePage homePage;
    PIMPage pimPage;
    AddEmployeePage addEmployee;
    PersonalDetailPage personalDetailPage;
    LoginPagePage loginPage;

    String firstName;
    String lastName;
    String employeeId;


    @Parameters({"url", "browser"})
    @BeforeClass
    public void initPage(String url, String browser) {
        driver = getBrowserDriver(url, browser);
        loginPage = PageGeneratorManager.getPage(LoginPagePage.class, driver);
        firstName = "Robert";
        lastName = "Hoang";
    }

    @Test
    public void LoginToOrangeHRM() {
        loginPage.enterToUsername("hoanglong98");
        loginPage.enterToPassword("Long01101998@");
        homePage = loginPage.clickToLoginButton();
    }

    @Test(dependsOnMethods = "LoginToOrangeHRM")
    public void AddNewEmployee() {
        pimPage = homePage.openPageByPageName("PIM");
        pimPage.clickOnAddEmployee();
        pimPage.getAddEmployeePage().setFirstName(firstName);
        pimPage.getAddEmployeePage().setLastName(lastName);
        employeeId = pimPage.getAddEmployeePage().getEmployeeId();
        personalDetailPage = pimPage.getAddEmployeePage().clickSaveButton();
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
