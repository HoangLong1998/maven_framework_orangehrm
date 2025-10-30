package pageGeneratorManager;

import common.BasePage;
import interfaces.pageUIs.MenuListPageUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class HomePage extends BasePage {
    private WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    //Click PIM locator to navigate to PIM page from Home Page
    public PIMPage clickOnPIM() {
        System.out.println("Clicked on PIM menu");
        clickToElement(driver.findElement(By.xpath(MenuListPageUI.PIM)));
        return PageGeneratorManager.getPage(PIMPage.class, driver);
    }

}
