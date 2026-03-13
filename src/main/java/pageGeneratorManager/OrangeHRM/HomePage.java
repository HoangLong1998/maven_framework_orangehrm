package pageGeneratorManager.OrangeHRM;

import common.BasePage;
import common.BasePageUI;
import interfaces.pageUIs.OrangeHRM.HomePageUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // Click PIM locator to navigate to PIM page from Home Page
    public <T> T openPageAndReturnByName(String pageName) {
        System.out.println("Open Page by Page Name");
        clickToElement(driver.findElement(By.xpath(String.format(BasePageUI.PAGE_LOCATOR_BY_NAME, pageName))));
        return (T) PageGeneratorManager.getPage(PIMPage.class, driver);
    }
}
