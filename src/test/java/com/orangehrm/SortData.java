package com.orangehrm;

import common.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.orangeHRM.*;


public class SortData extends BaseTest {
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
        firstName = "Zayn";
        lastName = "Zing";

    }

    @Test
    public void LoginToOrangeHRM() {
        loginPage.enterToUsername("hoanglong98");
        loginPage.enterToPassword("Long01101998@");
        loginPage.clickToLoginButton();
    }

    @Test()
    public void SortDataOnColumns() {
        homePage.openPageByPageName("PIM");
        pimPage.clickOnEmployeeList();
        pimPage.getEmployeeListPage().sortAscendingDataByColumn("Last Name");
        Assert.assertTrue(pimPage.getEmployeeListPage().isDataSortedAscendingByColumn("Last Name"));
        pimPage.getEmployeeListPage().sortDescendingDataByColumn("Last Name");
        Assert.assertTrue(pimPage.getEmployeeListPage().isDataSortedDescendingByColumn("Last Name"));
    }
    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }
}
