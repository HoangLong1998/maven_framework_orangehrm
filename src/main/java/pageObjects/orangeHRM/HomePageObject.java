package pageObjects.orangeHRM;
import common.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import interfaces.pageUIs.OrangeHRM.HomePageUI;


public class HomePageObject extends BasePage {
    private WebDriver driver;
    public HomePageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    /**
     * Checks if the loading spinner is no longer visible on the page.
     * @return true if the spinner is no longer visible, false otherwise.
     */
    @Step("Is Spinner Loading Invisible")
    public boolean isSpinnerLoadingInvisible() {
        return isSpinnerLoadingDisAppeared(driver.findElement(By.xpath("//div[@class='oxd-loading-spinner']")));
    }


}
