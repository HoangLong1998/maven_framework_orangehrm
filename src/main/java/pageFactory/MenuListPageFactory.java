package pageFactory;

import common.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;



public class MenuListPageFactory extends BasePage {
    private WebDriver driver;

    @FindBy(xpath = "//ul[@class=\"oxd-main-menu\"]//li//span[text()='PIM']")
    private WebElement PIMMenu;


    public MenuListPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnPIM() {
        System.out.println("Clicked on PIM menu");
        clickToElement(PIMMenu);
    }
}
