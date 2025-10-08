package pageObjects;
import common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import interfaces.pageUIs.MenuListPageUI;


public class MenuListPageObject extends BasePage {
    private WebDriver driver;
    public MenuListPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnPIM() {
        System.out.println("Clicked on PIM menu");
        clickToElement(driver.findElement(By.xpath(MenuListPageUI.PIM)));
    }
}
