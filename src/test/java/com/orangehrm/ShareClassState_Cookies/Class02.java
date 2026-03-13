package com.orangehrm.ShareClassState_Cookies;

import common.BaseTest;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.orangeHRM.*;

import java.util.Set;


public class Class02 extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;
    PIMPageObject pimPage;
    AddEmployeePageObject addEmployee;
    PersonalDetailPageObject personalDetailPage;
    LoginPagePageObject loginPage;

    String firstName;
    String lastName;
    String employeeId;
    Set<Cookie> cookies;


    @Parameters({"url", "browser"})
    @BeforeClass
    public void initPage(String url, String browser) {
        driver = getBrowserDriver(url, browser);
        loginPage = new LoginPagePageObject(driver);
        homePage = new HomePageObject(driver);
        pimPage = new PIMPageObject(driver);
        personalDetailPage = new PersonalDetailPageObject(driver);
        // Get cookies from ShareClassState_Cookies_01 and set to this class
        this.cookies = Class01.cookies;

    }

    @Test
    public void LoginToOrangeHRM() {
        loginPage.setCookies(this.cookies);
        // Refresh to apply cookies
        driver.navigate().refresh();
        System.out.println("Cookies is: " + this.cookies);
        Assert.assertFalse(homePage.isSpinnerLoadingInvisible());

    }


    @AfterClass
    public void closeBrowser() {
        closeBrowserDriver();
    }
}
