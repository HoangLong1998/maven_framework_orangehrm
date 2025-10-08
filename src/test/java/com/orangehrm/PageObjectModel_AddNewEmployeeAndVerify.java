package com.orangehrm;

import common.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.LoginPagePageObject;
import pageObjects.MenuListPageObject;
import pageObjects.PIMPageObject;
import pageObjects.AddEmployeePageObject;
import pageObjects.PersonalDetailPageObject;


public class PageObjectModel_AddNewEmployeeAndVerify extends BaseTest {
    WebDriver driver;
    MenuListPageObject menuListPage;
    PIMPageObject pimPage;
    AddEmployeePageObject addEmployee;
    PersonalDetailPageObject personalDetailPage;
    LoginPagePageObject loginPage;

    String firstName = "Robert";
    String lastName = "Hoang";
    String employeeId ;


    @Parameters({"url", "browser"})
    @BeforeClass
    public void initPage(String url, String browser) {
        driver = getBrowserDriver(url, browser);
        loginPage = new LoginPagePageObject(driver);
        menuListPage = new MenuListPageObject(driver);
        pimPage = new PIMPageObject(driver);
        personalDetailPage = new PersonalDetailPageObject(driver);

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
