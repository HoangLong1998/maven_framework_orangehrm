package common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;

import java.util.Set;

import org.openqa.selenium.support.ui.Select;

import java.util.List;


/**
 * BasePage class provides common methods for interacting with web elements using Selenium WebDriver.
 */
public class BasePage {
    protected WebDriver driver;
    protected Actions action;
    protected JavascriptExecutor jsExecutor;
    protected long shortTimeout = 5;
    protected long longTimeout = 30;

    /*--------------------------------------------------------------------------------------------------------------COMMON ACTIONS--------------------------------------------------------------------------------------*/



    public void openUrl(String url) {
        driver.get(url);
    }

    /**
     * Clicks on the specified web element.
     *
     * @param element the web element to click
     */

    public void clickToElement(WebElement element) {
        waitForElementClickable(element);
        element.click();
    }

    /**
     * Retrieves the text of the specified web element.
     *
     * @param element the web element to get text from
     * @return the text of the web element
     */
    public String getElementText(WebElement element) {
        waitForElementVisible(element);
        return element.getText();
    }

    /**
     * Sends keys to the specified web element after clearing its current content.
     *
     * @param element the web element to send keys to
     * @param value   the value to send to the web element
     */
    public void setTextToElement(WebElement element, String value) {
        waitForElementVisible(element);
        element.clear();
        element.sendKeys(value);
    }

    /**
     * Checks if the specified web element is displayed.
     *
     * @param element the web element to check
     * @return true if the element is displayed, false otherwise
     */
    public boolean isElementDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    /**
     * Checks if the specified web element is enabled.
     *
     * @param element the web element to check
     * @return true if the element is enabled, false otherwise
     */
    public boolean isElementEnabled(WebElement element) {
        return element.isEnabled();
    }

    /**
     * Checks if the specified web element is selected.
     *
     * @param element the web element to check
     * @return true if the element is selected, false otherwise
     */
    public boolean isElementSelected(WebElement element) {
        return element.isSelected();
    }


    /**
     * Retrieves the value of the specified attribute of the web element.
     *
     * @param element       the web element to get the attribute from
     * @param attributeName the name of the attribute to retrieve
     * @return the value of the attribute
     */
    public String getElementAttribute(WebElement element, String attributeName) {
        return element.getAttribute(attributeName);
    }


    /**
     * Navigates back to the previous page in the browser's history.
     *
     * @param driver the WebDriver instance used to control the browser
     */
    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    /**
     * Navigates forward to the next page in the browser's history.
     *
     * @param driver the WebDriver instance used to control the browser
     */
    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    /**
     * Refreshes the current page in the browser.
     *
     * @param driver the WebDriver instance used to control the browser
     */
    public void refreshCurrentPage(WebDriver driver) {
        driver.navigate().refresh();
    }


/*
    ----------------------------------------------------------------------------------------------WAITS FUNCTIONS---------------------------------------------------------------------------------------------
*/

    /**
     * Waits until the specified web element is visible.
     *
     * @param element the web element to wait for
     */
    public void waitForElementVisible(WebElement element) {
        WebDriverWait waitExplicit = new WebDriverWait(driver, java.time.Duration.ofSeconds(30));
        waitExplicit.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits until the specified web element is clickable.
     *
     * @param element the web element to wait for
     */
    public void waitForElementClickable(WebElement element) {
        WebDriverWait waitExplicit = new WebDriverWait(driver, java.time.Duration.ofSeconds(30));
        waitExplicit.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits until the specified web element is invisible.
     *
     * @param element the web element to wait for
     */
    public void waitForElementInvisible(WebElement element) {
        WebDriverWait waitExplicit = new WebDriverWait(driver, java.time.Duration.ofSeconds(30));
        waitExplicit.until(ExpectedConditions.invisibilityOf(element));
    }

    /**
     * Waits until the specified web element is not visible.get
     *
     * @param element the web element to wait for
     */
    public void waitForElementNotVisible(WebElement element) {
        WebDriverWait waitExplicit = new WebDriverWait(driver, java.time.Duration.ofSeconds(30));
        waitExplicit.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(element)));
    }

    /**
     * Waits until the specified web element is not present in the DOM.
     *
     * @param element the web element to wait for
     */
    public void waitForElementNotPresent(WebElement element) {
        WebDriverWait waitExplicit = new WebDriverWait(driver, java.time.Duration.ofSeconds(30));
        waitExplicit.until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated((By) element)));
    }

    /**
     * Waits until the specified web element is present in the DOM.
     *
     * @param element the web element to wait for
     */
    public void waitForElementPresence(WebElement element) {
        WebDriverWait waitExplicit = new WebDriverWait(driver, java.time.Duration.ofSeconds(30));
        waitExplicit.until(ExpectedConditions.presenceOfElementLocated((By) element));
    }

    /**
     * Waits until the specified web element is stale.
     *
     * @param element the web element to wait for
     */
    public void waitForElementStaleness(WebElement element) {
        WebDriverWait waitExplicit = new WebDriverWait(driver, java.time.Duration.ofSeconds(30));
        waitExplicit.until(ExpectedConditions.stalenessOf(element));
    }

    /**
     * Waits until an alert is present.
     */
    public void waitForAlertPresence() {
        WebDriverWait waitExplicit = new WebDriverWait(driver, java.time.Duration.ofSeconds(30));
        waitExplicit.until(ExpectedConditions.alertIsPresent());
    }

/*
-----------------------------------------------------------------------------------------------------------------DropDown ---------------------------------------------------------------------------------------------
*/

    public void selectItemInDefaultDropdown(WebDriver driver, WebElement element, String textItem) {
        waitForElementVisible(element);
        Select select = new Select(element);
        select.selectByVisibleText(textItem);
    }

    /**
     * Retrieves the selected item from a default dropdown.
     *
     * @param element the dropdown web element
     * @return the selected item text
     */
    public String getSelectedItemInDefaultDropdown(WebDriver driver, WebElement element) {
        waitForElementVisible(element);
        Select select = new Select(element);
        return select.getFirstSelectedOption().getText();
    }

    /**
     * Checks if a dropdown allows multiple selections.
     *
     * @param element the dropdown web element
     * @return true if the dropdown allows multiple selections, false otherwise
     */
    public boolean isDropdownMultiple(WebElement element) {
        Select select = new Select(element);
        return select.isMultiple();
    }

    /**
     * Selects an item from a custom dropdown menu.
     *
     * @param parentElement  the WebElement representing the dropdown's parent element
     * @param childItemXpath the XPath string used to locate all child items in the dropdown
     * @param expectedItem   the text of the item to be selected
     */
    public void selectItemInCustomDropdown(WebElement parentElement, String childItemXpath, String expectedItem) {
        // Click to open the dropdown
        clickToElement(parentElement);
        sleepInSecond(1);

        // Wait for all items to be present
        waitForElementPresence(parentElement);
        // Get all items
        List<WebElement> allItems = getListElements(childItemXpath);

        // Loop through items and click the expected one
        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedItem)) {
                item.click();
            } else {
                // Scroll to the item if it's not visible
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", item);
                sleepInSecond(1);
                item.click();
            }
            break;
        }
    }

    /**
     * Retrieves all items in a custom dropdown menu based on the provided XPath.
     *
     * @param childItemXpath the XPath string used to locate all child items in the dropdown
     * @return a list of WebElements representing all items in the dropdown
     */
    public List<WebElement> getListElements(String childItemXpath) {
        return driver.findElements(By.xpath(childItemXpath));
    }

    /**
     * Sleeps for a specified number of seconds.
     *
     * @param timeInSecond the number of seconds to sleep
     */
    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

/*
-----------------------------------------------------------------------------------------------------------------ALERT---------------------------------------------------------------------------------------------
*/


    /**
     * Accepts the currently displayed alert.
     */
    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    /**
     * Dismisses the currently displayed alert.
     */
    public void cancelAlert(WebDriver driver) {
        driver.switchTo().alert().dismiss();
    }

    /**
     * Retrieves the text of the currently displayed alert.
     *
     * @return the text of the alert
     */
    public String getAlertText() {
        return driver.switchTo().alert().getText();
    }

    /**
     * Sends keys to the currently displayed alert.
     *
     * @param value the value to send to the alert
     */
    public void sendKeyToAlert(String value) {
        driver.switchTo().alert().sendKeys(value);
    }

    /**
     * Switches to a window by its ID.
     *
     * @param parentID the ID of the parent window
     */
    public void switchToWindowByID(String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String id : allWindows) {
            if (!id.equals(parentID)) {
                driver.switchTo().window(id);
                break;
            }
        }
    }


/*
    -------------------------------------------------------------------------------------------------------------WINDOW FUNCTIONS---------------------------------------------------------------------------------------------
*/

    /**
     * Switches to a window by its title.
     *
     * @param title the title of the window to switch to
     */
    public void switchToWindowByTitle(WebDriver driver, String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String id : allWindows) {
            driver.switchTo().window(id);
            String windowTitle = driver.getTitle();
            if (windowTitle.equals(title)) {
                break;
            }
        }
    }

    /**
     * Closes all windows except the parent window.
     *
     * @param parentID the ID of the parent window
     */
    public void closeAllWindowsWithoutParent(String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String id : allWindows) {
            if (!id.equals(parentID)) {
                driver.switchTo().window(id);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }

    /**
     * Switches to an iframe by its web element.
     *
     * @param element the iframe web element to switch to
     */
    public void switchToIframe(WebElement element) {
        driver.switchTo().frame(element);
    }

    /**
     * Switches back to the top window.
     */
    public void backToToWindow() {
        driver.switchTo().defaultContent();
    }


/*
    ---------------------------------------------------------------------------------------------SELENIUM ACTIONS---------------------------------------------------------------------------------------------
*/

    /**
     * Hovers over the specified web element.
     *
     * @param element the web element to hover over
     */
    public void hoverToElement(WebElement element) {
        action.moveToElement(element).perform();
    }

    /**
     * Double-clicks on the specified web element.
     *
     * @param element the web element to double-click
     */
    public void doubleClickToElement(WebElement element) {
        action.doubleClick(element).perform();
    }

    /**
     * Right-clicks on the specified web element.
     *
     * @param element the web element to right-click
     */
    public void rightClickToElement(WebElement element) {
        action.contextClick(element).perform();
    }

    /**
     * Drags and drops the source element to the target element.
     *
     * @param sourceElement the source web element to drag
     * @param targetElement the target web element to drop to
     */
    public void dragAndDropElement(WebElement sourceElement, WebElement targetElement) {
        action.dragAndDrop(sourceElement, targetElement).perform();
    }

    /**
     * Uploads multiple files to the specified web element.
     *
     * @param element   the web element to upload files to
     * @param fileNames the names of the files to upload
     */
    public void uploadMultipleFiles(WebElement element, String... fileNames) {
        String filePath = System.getProperty("user.dir") + File.separator + "uploadFiles" + File.separator;
        String fullFileName = "";
        for (String file : fileNames) {
            fullFileName = fullFileName + filePath + file + "\n";
        }
        fullFileName = fullFileName.trim();
        element.sendKeys(fullFileName);
    }

    /**
     * Uploads a file using the Robot class.
     *
     * @param fileName the name of the file to upload
     */
    public void uploadFileByRobot(String fileName) {
        String filePath = System.getProperty("user.dir") + File.separator + "uploadFiles" + File.separator + fileName;
        StringSelection select = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
        try {
            Robot robot = new Robot();
            Thread.sleep(1000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            Thread.sleep(1000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
/*
 -------------------------------------------------------------------------------------------------------------JavascriptExecutor ---------------------------------------------------------------------------------------------
*/

    /**
     * Highlights the specified web element by changing its border style.
     *
     * @param element the web element to highlight
     */
    public void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='6px groove red'", element);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        js.executeScript("arguments[0].style.border=''", element);
    }


    /**
     * Verifies if the specified text is present in the inner text of the document.
     *
     * @param textExpected the text to verify
     * @return true if the text is present, false otherwise
     */
    public boolean verifyTextInInnerText(String textExpected) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String textActual = (String) js.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
        return textActual.equals(textExpected);
    }

    /**
     * Scrolls to the bottom of the page.
     */
    public void scrollToBottomPage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    /**
     * Scrolls the specified web element into view using JavaScript.
     *
     * @param element the web element to scroll into view
     */
    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Generates a unique email address using the current timestamp.
     *
     * @return a unique email address in the format "automation<timestamp>@gmail.com"
     */
    public String generateEmail() {
        return "automation" + System.currentTimeMillis() + "@gmail.com";
    }

//-----------------------------------------------------------------------------Switch Page object --------------------------------------------------------------------------------------


}