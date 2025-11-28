package pageObjects.JqueryPage;

import common.BasePage;
import interfaces.pageUIs.jQuery.HomePageUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the page object for the Home Page in the jQuery application.
 * It provides methods to interact with and retrieve data from the Home Page.
 */
public class HomePageObject extends BasePage {
    private WebDriver driver;

    /**
     * Constructor for HomePageObject.
     *
     * @param driver the WebDriver instance used to interact with the browser
     */
    public HomePageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    /**
     * Opens a specific page by its number.
     *
     * @param pageNumber the number of the page to open
     */
    public void openPageByNumber(String pageNumber) {
        WebElement pageElement = driver.findElement(By.xpath(String.format(HomePageUI.PAGE_NUMBER, pageNumber)));
        waitForElementVisible(pageElement);
        clickToElement(pageElement);
    }

    /**
     * Checks if a specific page is currently active.
     *
     * @param pageNumber the number of the page to check
     * @return true if the page is active, false otherwise
     */
    public boolean isActivePageDisplayed(String pageNumber) {
        WebElement pageElement = driver.findElement(By.xpath(String.format(HomePageUI.IS_ACTIVE_PAGE, pageNumber)));
        waitForElementVisible(pageElement);
        return isElementDisplayed(pageElement);
    }

    /**
     * Retrieves a list of country names from the table across all pages.
     *
     * @return a list of country names as strings
     */
    public List<String> getListColumnValues(String columnName) {
        List<String> allValues = new ArrayList<String>();
        List<WebElement> pageNumber = getListElements(HomePageUI.PAGE_NUMBER_lIST);

        for (WebElement page : pageNumber) {
            clickToElement(page);
            List<String> listColumnValues = getListValuesByColumnName(String.format(HomePageUI.VALUES_lIST_BY_COLUMN_NAME,columnName));
            allValues.addAll(listColumnValues);
        }
        return allValues;
    }
    /**
     * Retrieves a list of country names from the table across all pages.
     *
     * @return a list of country names based on column index
     */
    //    public List<String> getListValueInColumnByName() {
//        List<String> allValues = new ArrayList<String>();
    //   List<WebElement> pageNumber = getListWebElement(driver, HomePageUI.PAGE_NUMBER_lIST);
    //  int columnIndex = getListElementSize(driver, HomePageUI.COLUMN_INDEX_BY_COLUMN_NAME, columnName) + 1;

//        for (WebElement page : pageNumber) {
//            clickToElement(page);
//            List<WebElement> allColumnValues = getListElements(String.format(allColumnValue, columnIndex));
//            for (WebElement item : allColumnValues) {
//                allValues.add(item.getText());
//            }
//        }
//
//        return allValues;
//    }
}
