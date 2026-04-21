package com.orangehrm;

import common.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.orangeHRM.*;


public class LiveCode extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;
    PIMPageObject pimPage;
    AddEmployeePageObject addEmployee;
    PersonalDetailPageObject personalDetailPage;
    LoginPagePageObject loginPage;
    MyInfoPageObject myInforPage;
    String firstName, lastName, employeeId, employeeUsername, employeePassword;


    @Parameters({"url", "browser"})
    @BeforeClass
    public void initPage(String url, String browser) {
        driver = getBrowserDriver(url, browser);
        loginPage = new LoginPagePageObject(driver);
        homePage = new HomePageObject(driver);
        pimPage = new PIMPageObject(driver);
        personalDetailPage = new PersonalDetailPageObject(driver);
        myInforPage = new MyInfoPageObject(driver);
        firstName = "Automation";
        lastName = "Testing";
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
    public void AddNewEmployee() {
        homePage.openPageByPageName("PIM");
        homePage.waitForSpinnerLoadingDisAppeared();
        pimPage.clickOnAddEmployee();
        pimPage.getAddEmployeePage().setFirstName(firstName);
        pimPage.getAddEmployeePage().setLastName(lastName);
        employeeId = pimPage.getAddEmployeePage().getEmployeeId();
        pimPage.getAddEmployeePage().clickToCreateLoginDetailsCheckbox();
        pimPage.getAddEmployeePage().setUsername(employeeUsername);
        pimPage.getAddEmployeePage().setPassword(employeePassword);
        pimPage.getAddEmployeePage().setConfirmPassword(employeePassword);
        pimPage.getAddEmployeePage().clickSaveButton();
        personalDetailPage.waitForSpinnerLoadingDisAppeared();
        String actualFirstName = personalDetailPage.getFirstName();
        String actualLastName = personalDetailPage.getLastName();
        String actualEmployeeId = personalDetailPage.getEmployeeId();
        verifyEquals(actualFirstName, firstName);
        verifyEquals(actualLastName, lastName);
        verifyEquals(actualEmployeeId, employeeId);
    }

//    @Test(priority = 3)
//    public void uploadAvatar() {
//        homePage.openPageByPageName("My Info");
//        myInforPage.waitForSpinnerLoadingDisAppeared();
//        //Update with Invalid File
//        myInforPage.clickAvatar();
//        myInforPage.clickToUploadAvatarButton();
//        myInforPage.uploadAvatar("importData.csv");
//        verifyEquals(myInforPage.getUploadFileErrorMessage(),"File type not allowed");
//
//        //Upload with maximum file size
//        myInforPage.uploadAvatar("Colorful abstract la.png");
//        verifyEquals(myInforPage.getUploadFileErrorMessage(),"Attachment Size Exceeded");
//
//        //Upload with valid file
//        myInforPage.uploadAvatar("Avatar.png");
//        myInforPage.savePageInfo();
//    }

    @Test(priority = 4)
    public void inputPersonalDetail() {
        myInforPage.openSubPageByPageName("Personal Details");
        personalDetailPage.waitForSpinnerLoadingDisAppeared();
        personalDetailPage.setMiddleName("Automation");
        personalDetailPage.selectLicenseExpiryDate("2025", "May", "15");
        personalDetailPage.selectDateOfBirth("2023", "October", "1");
       // personalDetailPage.savePageInfo();
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }
}
