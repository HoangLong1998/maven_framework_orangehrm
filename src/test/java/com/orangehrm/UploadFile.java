package com.orangehrm;

import common.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.orangeHRM.*;


public class UploadFile extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;
    PIMPageObject pimPage;
    LoginPagePageObject loginPage;
    MyInfoPageObject myInfoPage;


    @Parameters({"url", "browser"})
    @BeforeClass
    public void initPage(String url, String browser) {
        driver = getBrowserDriver(url, browser);
        loginPage = new LoginPagePageObject(driver);
        homePage = new HomePageObject(driver);
        pimPage = new PIMPageObject(driver);
        myInfoPage = new MyInfoPageObject(driver);

    }

    @Test
    public void LoginToOrangeHRM() {
        loginPage.enterToUsername("hoanglong98");
        loginPage.enterToPassword("Long01101998@");
        loginPage.clickToLoginButton();
    }

    @Test(dependsOnMethods = "LoginToOrangeHRM")
    public void uploadAvatar(){
        homePage.openPageByPageName("My Info");
        myInfoPage.clickAvatar();
        myInfoPage.clickToUploadAvatarButton();
        myInfoPage.uploadAvatar("Screenshot 2025-06-23 163251.png");
        myInfoPage.savePageInfo();
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }
}
