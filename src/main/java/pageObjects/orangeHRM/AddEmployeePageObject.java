package pageObjects.orangeHRM;

import common.BasePage;
import common.BasePageUI;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import interfaces.pageUIs.OrangeHRM.AddEmployeePageUI;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddEmployeePageObject extends BasePage {
    private WebDriver driver;

    /**
     * Constructor for the AddEmployeePageObject class.
     * Initializes the WebDriver instance used to interact with the Add Employee page.
     *
     * @param driver The WebDriver instance used to interact with the web page.
     */
    public AddEmployeePageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    /**
     * Sets the first name of the employee in the Add Employee form.
     *
     * @param firstName The first name of the employee to be entered.
     */
    @Step("Enter to First Name with value: {0}")
    public void setFirstName(String firstName) {
        setTextToElement(driver.findElement(By.xpath(AddEmployeePageUI.FIRSTNAME)), firstName);
    }

    /**
     * Sets the last name of the employee in the Add Employee form.
     *
     * @param lastName The last name of the employee to be entered.
     */
    @Step("Enter to Last Name with value: {0}")
    public void setLastName(String lastName) {
        setTextToElement(driver.findElement(By.xpath(AddEmployeePageUI.LASTNAME)), lastName);
    }

    @Step("Click to Create Login Details Checkbox")
    public void clickToCreateLoginDetailsCheckbox() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Wait for loader to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-form-loader")));
        clickToElement(driver.findElement(By.xpath(AddEmployeePageUI.CREATE_LOGIN_DETAILS_CHECKBOX)));
    }

    /**
     * Sets the username for the employee's login details in the Add Employee form.
     *
     * @param username The username to be entered.
     */
    @Step("Enter to Username with value: {0}")
    public void setUsername(String username) {
        setTextToElement(driver.findElement(By.xpath(AddEmployeePageUI.USERNAME_TEXTBOX)), username);
    }

    /**
     * Sets the password for the employee's login details in the Add Employee form.
     *
     * @param password The password to be entered.
     */
    @Step("Enter to Password with value: {0}")
    public void setPassword(String password) {
        setTextToElement(driver.findElement(By.xpath(AddEmployeePageUI.PASSWORD_TEXTBOX)), password);
    }

    /**
     * Sets the confirmation password for the employee's login details in the Add Employee form.
     *
     * @param confirmPassword The confirmation password to be entered.
     */
    @Step("Enter to Confirm Password with value: {0}")
    public void setConfirmPassword(String confirmPassword) {
        setTextToElement(driver.findElement(By.xpath(AddEmployeePageUI.CONFIRM_PASSWORD_TEXTBOX)), confirmPassword);
    }

    /**
     * Retrieves the employee ID from the Add Employee form.
     *
     * @return The employee ID as a String.
     */
    @Step("Get Employee ID")
    public String getEmployeeId() {
        return getElementAttribute(driver.findElement(By.xpath(AddEmployeePageUI.EMPLOYEE_ID)), "value");
    }

    /**
     * Clicks the "Save" button on the Add Employee form.
     */
    @Step("Click to Save Button")
    public void clickSaveButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Wait for loader to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-form-loader")));
        clickToElement(driver.findElement(By.xpath(AddEmployeePageUI.SAVE_BUTTON)));
        waitForSpinnerLoadingDisAppeared();
        //sleepInSecond(30);
    }


}
