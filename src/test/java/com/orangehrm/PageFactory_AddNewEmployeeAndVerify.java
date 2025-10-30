package com.orangehrm;

import common.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageFactory.HomePageFactory;
import pageFactory.PIMPageFactory;
import pageFactory.PersonalDetailPageFactory;
import pageFactory.AddEmployeePageFactory;
import pageFactory.LoginPageFactory;


public class PageFactory_AddNewEmployeeAndVerify extends BaseTest {
    WebDriver driver;
    HomePageFactory menuListPage;
    PIMPageFactory pimPage;
    AddEmployeePageFactory addEmployee;
    PersonalDetailPageFactory personalDetailPage;
    LoginPageFactory loginPage;

    String firstName;
    String lastName;
    String employeeId;


    @Parameters({"url", "browser"})
    @BeforeClass
    public void initPage(String url, String browser) {
        driver = getBrowserDriver(url, browser);
        loginPage = new LoginPageFactory(driver);
        menuListPage = new HomePageFactory(driver);
        pimPage = new PIMPageFactory(driver);
        personalDetailPage = new PersonalDetailPageFactory(driver);
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
        menuListPage.clickOnPIM();
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
