package pageObjects.orangeHRM;
import common.BasePage;
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
    public boolean isSpinnerLoadingInvisible() {
        return isSpinnerLoadingDisAppeared(driver.findElement(By.xpath("//div[@class='oxd-loading-spinner']")));
    }

    /**
     * Opens a specific sub-page by clicking on its name.
     * @param pageName The name of the sub-page to open.
     */
    public void openPageByPageName(String pageName) {
        System.out.println("Clicked on Sub Page by Page Name");
        clickToElement(driver.findElement(By.xpath(String.format(HomePageUI.PAGE_LOCATOR_BY_NAME, pageName))));
    }


}
