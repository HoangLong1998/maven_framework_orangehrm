package com.orangehrm;

import common.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.orangeHRM.LoginPagePageObject;


public class MultipleBrowser_LoginToPage extends BaseTest{
    LoginPagePageObject loginPage;
    WebDriver driver;
    @Parameters({"url","browser"})
    @BeforeClass
    public void initPage(String url, String browser) {
        driver = getBrowserDriver(url,browser);
        loginPage = new LoginPagePageObject(driver);
    }

    @Test
    public void LoginToOrangeHRM() {
        loginPage.enterToUsername("hoanglong98");
        loginPage.enterToPassword("Long01101998@");
        loginPage.clickToLoginButton();
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }
}
