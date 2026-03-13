package jQuery;

import common.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.JqueryPage.HomePageObject;
import common.BasePage;

import java.util.List;

public class HandleDataTable_DynamicLocator extends BaseTest {
    WebDriver driver;
    HomePageObject homePageObject;
    BasePage basePage;


    @Parameters({"url", "browser"})
    @BeforeClass
    public void initPage(String url, String browser) {
        driver = getBrowserDriver(url, browser);
        homePageObject = new HomePageObject(driver);
        basePage = new BasePage(driver);
    }


    @Test()
    public void navigateToPageNumber() {
        // Navigate to page
        homePageObject.openPageByNumber("2");
        Assert.assertTrue(homePageObject.isActivePageDisplayed("2"));
        basePage.takeSnap(driver, "page2");
    }

    @Test
    public void getListColumnValue() {
        List<String> countriesValues = homePageObject.getListColumnValues("country");
        System.out.println(countriesValues);
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }
}
