package common;

import interfaces.pageUIs.OrangeHRM.HomePageUI;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.openqa.selenium.support.ui.Select;


/**
 * BasePage class provides common methods for interacting with web elements using Selenium WebDriver.
 */
public class BasePage {
    protected WebDriver driver;
    protected Actions action;
    protected JavascriptExecutor jsExecutor;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

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
        scrollToElement(element);
        waitForElementClickable(element);
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }


    /**
     * Checks if a spinner (loading indicator) has disappeared from the page.
     * <p>
     * This method waits for the specified web element (spinner) to become invisible.
     * If the spinner disappears within the timeout period, the method returns true.
     * If the spinner does not disappear and a TimeoutException is thrown, the method catches
     * the exception and returns false.
     *
     * @param element the web element representing the spinner to check
     * @return true if the spinner disappears, false otherwise
     */
    public boolean isSpinnerLoadingDisAppeared(WebElement element) {
        try {
            waitForElementInvisible(element);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void waitForSpinnerLoadingDisAppeared() {
        try {
            WebElement spinner = driver.findElement(By.xpath(BasePageUI.LOADING_ICON));
            waitForElementInvisible(spinner);
        } catch (NoSuchElementException | TimeoutException e) {
            // Spinner not present or already disappeared, continue
        }
    }

    public void clickToLogOut() {
        selectItemInCustomDropdown(driver.findElement(By.xpath(BasePageUI.USER_DROPDOWN)), BasePageUI.USER_DROPDOWN_OPTION_LIST, "Logout");
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
        boolean status = true;
        try {
            if (element.isDisplayed()) {
                return true;
            }
        } catch (NoSuchElementException e) {
            status = false;
        }
        return status;
    }

    /**
     * Checks if the specified web element is not displayed on the page.
     *
     * @param locator the web element to check
     * @return true if the element is not displayed or does not exist, false otherwise
     */
    public boolean isElementUndisplayed(By locator) {
        List<WebElement> elements = driver.findElements(locator);
        if (elements.isEmpty()) {
            System.out.println("Element not in DOM");
            return true;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
            System.out.println("Element in DOM but not visible/ displayed in UI");
            return true;
        } else {
            System.out.println("Element in DOM and visible/ displayed in UI");
            return false;
        }
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
        try {
            WebDriverWait waitExplicit = new WebDriverWait(driver, java.time.Duration.ofSeconds(30));
            waitExplicit.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            e.printStackTrace();
            //  log("❌ Wait for element visible failed: " + e.getMessage());
        }

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
     * @param locator the web element to wait for
     */
    public void waitForElementNotPresent(By locator) {
        WebDriverWait waitExplicit = new WebDriverWait(driver, java.time.Duration.ofSeconds(30));
        waitExplicit.until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(locator)));
    }

    /**
     * Waits until the specified web element is present in the DOM.
     *
     * @param locator the web element to wait for
     */
    public void waitForElementPresence(By locator) {
        WebDriverWait waitExplicit = new WebDriverWait(driver, java.time.Duration.ofSeconds(30));
        waitExplicit.until(ExpectedConditions.presenceOfElementLocated(locator));
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

    public void selectItemInDefaultDropdownByText(WebElement element, String textItem) {
        waitForElementVisible(element);
        Select select = new Select(element);
        select.selectByVisibleText(textItem);
    }

    public void selectItemInDefaultDropdownByIndex(WebElement element, int index) {
        waitForElementVisible(element);
        Select select = new Select(element);
        select.selectByIndex(index);
    }

    public String getSelectedItemInDefaultDropdown(WebElement element) {
        waitForElementVisible(element);
        Select select = new Select(element);
        return select.getFirstSelectedOption().getText();
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

    // Update usage in selectItemInCustomDropdown
    public void selectItemInCustomDropdown(WebElement parentElement, String childItemXpath, String expectedItem) {
        clickToElement(parentElement);
        sleepInSecond(2);
        waitForElementPresence(By.xpath(childItemXpath));
        List<WebElement> allItems = getListElements(childItemXpath);
        System.out.println("Total items: " + allItems.size());
        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedItem)) {
                System.out.println("Selected item: " + item.getText());
                clickToElement(item);
                break;
            }
        }
    }

    /**
     * Retrieves all items in a custom dropdown menu based on the provided XPath.
     *
     * @param locator the XPath string used to locate all child items in the dropdown
     * @return a list of WebElements representing all items in the dropdown
     */
    public List<WebElement> getListElements(String locator) {
        return driver.findElements(By.xpath(locator));
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


  /*
   -------------------------------------------------------------------------------------------------------------Upload File ---------------------------------------------------------------------------------------------
  */

    /**
     * Uploads multiple files to the specified web element.
     *
     * @param element   the web element to upload files to
     * @param fileNames the names of the files to upload
     */
    public void uploadMultipleFiles(WebElement element, String... fileNames) {
        String filePath = GlobalConstants.UPLOAD_PATH;
        String fullFileName = "";
        for (String file : fileNames) {
            fullFileName = fullFileName + filePath + file + "\n";
        }
        fullFileName = fullFileName.trim();
        System.out.println(fullFileName);
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


    /**
     * Generates a random number based on the current system time in milliseconds.
     * <p>
     * This method uses the `System.currentTimeMillis()` function to retrieve the current time
     * in milliseconds since the Unix epoch (January 1, 1970, 00:00:00 GMT). The result is
     * converted to a `String` and returned. This can be useful for generating unique identifiers
     * or timestamps.
     *
     * @return A `String` representation of the current system time in milliseconds.
     */
    public String generateRandomNumber() {
        return String.valueOf(System.currentTimeMillis());
    }


    //-----------------------------------------------------------------------------Take ScreenShot --------------------------------------------------------------------------------------

    /**
     * Captures a screenshot of the current browser window and saves it to the specified file.
     *
     * @param driver   the WebDriver instance used to interact with the browser
     * @param fileName the name of the file (without extension) where the screenshot will be saved
     */
    public void takeSnap(WebDriver driver, String fileName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            new File("screenshots").mkdir(); // Create folder if it doesn't exist
            src.renameTo(new File("screenshots/" + fileName + ".png"));
        } catch (Exception e) {
            System.out.println("❌ Screenshot failed: " + e.getMessage());
        }
    }


    //-----------------------------------------------------------------------------Table --------------------------------------------------------------------------------------

    /**
     * Retrieves all text values from a specific column in a table.
     *
     * @param locator the XPath string used to locate the column values
     * @return a list of strings representing the text values in the column
     */
    public List<String> getListValuesByColumnName(String locator) {
        List<String> allValues = new ArrayList<String>();
        List<WebElement> columnValues = getListElements(locator);
        for (WebElement rowValue : columnValues) {
            allValues.add(rowValue.getText());
        }
        return allValues;
    }

    /**
     * Retrieves all text values from a specific column in a table based on the column name.
     *
     * @param driver     the WebDriver instance used to interact with the browser
     * @param columnName the name of the column to retrieve values from
     * @return a list of strings representing the text values in the specified column
     */
    public List<String> getColumnData(WebDriver driver, String columnName) {
        // get all headers
        List<WebElement> headers = driver.findElements(
                By.xpath("//div[@role='columnheader']")
        );
        int columnIndex = -1;
        // get column index by column name
        for (int i = 0; i < headers.size(); i++) {
            if (headers.get(i).getText().trim().equalsIgnoreCase(columnName)) {
                columnIndex = i;
                break;
            }
        }
        if (columnIndex == -1) {
            throw new RuntimeException(columnName + " not found in table headers");
        }
        // get all rows
        List<WebElement> rows = driver.findElements(
                By.xpath("//div[@role='row' and .//div[@role='cell']]")
        );
        List<String> result = new ArrayList<>();
        // get cell value by column index for each row
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(
                    By.xpath(".//div[@role='cell']")
            );
            if (cells.size() > columnIndex) {
                result.add(cells.get(columnIndex).getText().trim());
            }
        }
        return result;
    }

    /**
     * Checks if the data in a specific column of a table is sorted in ascending order.
     *
     * @param columnName the name of the column to check for sorting
     * @return true if the data is sorted in ascending order, false otherwise
     */
    public boolean isDataSortedAscendingByColumn(String columnName) {
        ArrayList<String> columnData = new ArrayList<String>();
        List<String> data = getColumnData(driver, columnName);
        for (String item : data) {
            columnData.add(item);

        }
        ArrayList<String> sortedList = new ArrayList<String>();
        for (String item : columnData) {
            sortedList.add(item);
        }

        Collections.sort(sortedList, String.CASE_INSENSITIVE_ORDER);
        return columnData.equals(sortedList);

    }

    /**
     * Checks if the data in a specific column of a table is sorted in descending order.
     *
     * @param columnName the name of the column to check for sorting
     * @return true if the data is sorted in descending order, false otherwise
     */
    public boolean isDataSortedDescendingByColumn(String columnName) {
        ArrayList<String> columnData = new ArrayList<String>();
        List<String> data = getColumnData(driver, columnName);
        for (String item : data) {
            columnData.add(item);

        }
        ArrayList<String> sortedList = new ArrayList<String>();
        for (String item : columnData) {
            sortedList.add(item);
        }

        Collections.sort(sortedList, String.CASE_INSENSITIVE_ORDER.reversed());
        return columnData.equals(sortedList);
    }


    /**
     * Checks if the data in a specific column of a table is sorted in ascending order based on date values.
     *
     * @param locator the XPath string used to locate the date values in the column
     * @return true if the date values are sorted in ascending order, false otherwise
     */
    public boolean isDateSortedAscending(String locator){
        ArrayList<Date> arrayList = new ArrayList<Date>();
        List<WebElement> elementList = driver.findElements(By.xpath(locator));
        for (WebElement element : elementList) {
            arrayList.add(convertStringToDate(element.getText()));
        }

        System.out.println("--------- Dữ liệu trên UI ---------");
        for (Date name : arrayList) {
            System.out.println(name);
        }

        ArrayList<Date> sortedList = new ArrayList<Date>();

        for (Date child : arrayList) {
            sortedList.add(child);
        }

        Collections.sort(sortedList);

        System.out.println("--------- Dữ liệu đã SORT ASC trong code ---------");
        for (Date name : sortedList) {
            System.out.println(name);
        }

        return sortedList.equals(arrayList);
    }
    //-----------------------------------------------------------------------------BaseFunctions --------------------------------------------------------------------------------------


    /**
     * Opens a specific page by clicking on its name. from the main menu.
     *
     * @param pageName The name of the sub-page to open.
     */
    @Step("Open Page By Page Name: {0}")
    public void openPageByPageName(String pageName) {
        System.out.println("Clicked on Sub Page by Page Name");
        clickToElement(driver.findElement(By.xpath(String.format(BasePageUI.PAGE_LOCATOR_BY_NAME, pageName))));
        sleepInSecond(3);
    }


    public Set<Cookie> getAllCookies() {
        return driver.manage().getCookies();

    }

    public void setCookies(Set<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            driver.manage().addCookie(cookie);
        }
    }

    /**
     * Converts a date string in the format "MMM dd, yyyy" (e.g., "May 15, 2025") to a Date object.
     *
     * @param  dateInString the date string to convert
     * @return a Date object representing the parsed date, or null if parsing fails
     */
    public Date convertStringToDate(String dateInString) {
        dateInString = dateInString.replace(",", "");
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yyyy");
        Date date = null;
        try {
            date = formatter.parse(dateInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    //-----------------------------------------------------------------------------Date Picker  --------------------------------------------------------------------------------------
// All 12 month names displayed in the OrangeHRM calendar header
    private static final List<String> MONTH_NAMES = Arrays.asList(
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
    );

    /**
     * Selects a date in the OrangeHRM date picker by clicking the input, navigating to the correct month/year, and clicking the day.
     * This method is reusable for any date picker field on the page (e.g. License Expiry Date, Date of Birth, etc.)
     *
     * <p>How it works:</p>
     * <ol>
     *   <li>Finds the date input by its label text and clicks it to open the calendar popup</li>
     *   <li>Reads the currently displayed month and year from the calendar header</li>
     *   <li>Calculates how many months to navigate forward or backward</li>
     *   <li>Clicks the previous/next arrow buttons to reach the target month/year</li>
     *   <li>Clicks the target day number in the calendar grid</li>
     * </ol>
     *
     * <p>Usage example:</p>
     * <pre>
     *   selectDateInDatePicker("License Expiry Date", "2025", "May", "15");
     *   selectDateInDatePicker("Date of Birth", "1990", "December", "25");
     * </pre>
     *
     * @param fieldLabel the label text of the date field (e.g. "License Expiry Date", "Date of Birth")
     * @param year       the target year as a String (e.g. "2025")
     * @param month      the target month name (e.g. "May", "December")
     * @param day        the target day as a String (e.g. "15", "7")
     */
    public void selectDateInDatePicker(String fieldLabel, String year, String month, String day) {
        // Step 1: Click the date input to open the calendar
        String dateInputXpath = String.format(BasePageUI.DATE_INPUT_BY_LABEL, fieldLabel);
        WebElement dateInput = driver.findElement(By.xpath(dateInputXpath));
        clickToElement(dateInput);
        sleepInSecond(1);

        // Step 2: Wait for calendar popup to appear
        waitForElementPresence(By.xpath(BasePageUI.DATE_PICKER_CALENDAR));

        // Step 3: Navigate to the correct month and year
        navigateToMonthYear(year, month);

        // Step 4: Click the target day
        try {
            clickDayInCalendar(day);
        } catch (RuntimeException e) {
            // Fallback for UI variants where date cells are rendered differently.
            setTextToElement(dateInput, buildDateValue(year, month, day));
            dateInput.sendKeys(Keys.TAB);
        }
    }

    /**
     * Navigates the calendar to the target month and year by clicking the left/right arrow buttons.
     *
     * @param targetYear  the target year (e.g. "2025")
     * @param targetMonth the target month name (e.g. "May")
     */
    private void navigateToMonthYear(String targetYear, String targetMonth) {
        int targetYearInt = Integer.parseInt(targetYear);
        int targetMonthIndex = MONTH_NAMES.indexOf(targetMonth);
        if (targetMonthIndex < 0) {
            throw new RuntimeException("Invalid target month: '" + targetMonth + "'");
        }

        while (true) {
            // Read current month and year from the calendar header
            String currentMonth = driver.findElement(By.xpath(BasePageUI.DATE_PICKER_MONTH_DISPLAY)).getText().trim();
            int currentYear = Integer.parseInt(driver.findElement(By.xpath(BasePageUI.DATE_PICKER_YEAR_DISPLAY)).getText().trim());
            int currentMonthIndex = MONTH_NAMES.indexOf(currentMonth);
            if (currentMonthIndex < 0) {
                throw new RuntimeException("Calendar returned unknown month label: '" + currentMonth + "'");
            }

            // Calculate total months difference
            int monthsDiff = (targetYearInt - currentYear) * 12 + (targetMonthIndex - currentMonthIndex);

            if (monthsDiff == 0) {
                break;
            } else if (monthsDiff < 0) {
                // Click the previous month button
                clickToElement(driver.findElement(By.xpath(BasePageUI.DATE_PICKER_PREVIOUS_BUTTON)));
            } else {
                // Click the next month button
                clickToElement(driver.findElement(By.xpath(BasePageUI.DATE_PICKER_NEXT_BUTTON)));
            }
            sleepInSecond(1);
        }
    }

    /**
     * Clicks the target day number in the calendar grid.
     * Only clicks day cells that belong to the current month (excludes offset days from previous/next month).
     *
     * @param day the day number to click (e.g. "15")
     */
    private void clickDayInCalendar(String day) {
        String normalizedTargetDay = normalizeDay(day);
        List<WebElement> dayCells = driver.findElements(By.xpath(BasePageUI.DATE_PICKER_DAY_CELLS));

        // Fallback locator for OrangeHRM UI variants where wrapper classes may differ.
        if (dayCells.isEmpty()) {
            dayCells = driver.findElements(By.xpath(
                    BasePageUI.DATE_PICKER_CALENDAR +
                            "//div[contains(@class,'oxd-calendar-date') and normalize-space(.)!='' and " +
                            "not(ancestor::*[contains(@class,'--offset')])]"
            ));
        }

        for (WebElement cell : dayCells) {
            String cellDay = normalizeDay(cell.getText());
            if (cellDay.equals(normalizedTargetDay)) {
                clickToElement(cell);
                return;
            }
        }
        throw new RuntimeException("Day '" + day + "' not found in the calendar");
    }

    private String normalizeDay(String dayText) {
        try {
            return String.valueOf(Integer.parseInt(dayText.trim()));
        } catch (NumberFormatException e) {
            return dayText.trim();
        }
    }

    private String buildDateValue(String year, String month, String day) {
        int monthIndex = MONTH_NAMES.indexOf(month);
        if (monthIndex < 0) {
            throw new RuntimeException("Invalid month for input fallback: '" + month + "'");
        }
        int normalizedDay = Integer.parseInt(normalizeDay(day));
        return String.format("%s-%02d-%02d", year, monthIndex + 1, normalizedDay);
    }


}
