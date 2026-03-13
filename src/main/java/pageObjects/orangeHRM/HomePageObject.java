package pageObjects.orangeHRM;

import common.BasePage;
import common.BasePageUI;
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
     *
     * @return true if the spinner is no longer visible, false otherwise.
     */
    @Step("Is Spinner Loading Invisible")
    public boolean isSpinnerLoadingInvisible() {
        return isSpinnerLoadingDisAppeared(driver.findElement(By.xpath(BasePageUI.LOADING_ICON)));
    }

    /**
     * Checks if a page link with the specified name is displayed on the home page.
     *
     * @param pageName The name of the page link to check.
     * @return true if the page link is displayed, false otherwise.
     */
    @Step("Is Page Link Displayed By Name: {0}")
    public boolean isPageLinkDisplayedByName(String pageName) {
        waitForElementVisible(driver.findElement(By.xpath(String.format(BasePageUI.PAGE_LOCATOR_BY_NAME, pageName))));
        return isElementDisplayed(driver.findElement(By.xpath(String.format(BasePageUI.PAGE_LOCATOR_BY_NAME, pageName))));
    }

    @Step("Is Page Link Undisplayed By Name: {0}")
    public boolean isPageLinkUndisplayedByName(String pageName) {
        By locator = By.xpath(String.format(BasePageUI.PAGE_LOCATOR_BY_NAME, pageName));
        return isElementUndisplayed(locator);
    }


}
