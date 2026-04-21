package pageObjects.orangeHRM;

import common.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import interfaces.pageUIs.OrangeHRM.PersonalDetailPageUI;
import org.openqa.selenium.WebElement;


public class PersonalDetailPageObject extends BasePage {
    private WebDriver driver;

    /**
     * Constructor for the PersonalDetailPageObject class.
     * Initializes the WebDriver instance used to interact with the Personal Details page.
     *
     * @param driver The WebDriver instance used to interact with the web page.
     */
    public PersonalDetailPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    /**
     * Retrieves the first name of the employee from the Personal Details page.
     *
     * @return The first name of the employee as a String.
     */
    @Step("Get First Name")
    public String getFirstName() {
        waitForElementVisible(driver.findElement(By.xpath(PersonalDetailPageUI.FIRST_NAME)));
        return getElementAttribute(driver.findElement(By.xpath(PersonalDetailPageUI.FIRST_NAME)), "value");
    }

    /**
     * Retrieves the last name of the employee from the Personal Details page.
     *
     * @return The last name of the employee as a String.
     */
    @Step("Get Last Name")
    public String getLastName() {
        waitForElementVisible(driver.findElement(By.xpath(PersonalDetailPageUI.LAST_NAME)));
        return getElementAttribute(driver.findElement(By.xpath(PersonalDetailPageUI.LAST_NAME)), "value");
    }

    /**
     * Retrieves the employee ID from the Personal Details page.
     *
     * @return The employee ID as a String.
     */
    @Step("Get Employee ID")
    public String getEmployeeId() {
        waitForElementVisible(driver.findElement(By.xpath(PersonalDetailPageUI.EMPLOYEE_ID)));
        return getElementAttribute(driver.findElement(By.xpath(PersonalDetailPageUI.EMPLOYEE_ID)), "value");
    }

    /**
     * Sets the middle name of the employee in the Personal Details page.
     * @param middleName The middle name to be entered into the middle name field.
     */
    public void setMiddleName(String middleName) {
        waitForElementVisible(driver.findElement(By.xpath(PersonalDetailPageUI.MIDDLE_NAME)));
        setTextToElement(driver.findElement(By.xpath(PersonalDetailPageUI.MIDDLE_NAME)), middleName);
    }

    /**
     * Sets the driver license number of the employee in the Personal Details page.
     * @param driverLicenseNumber The driver license number to be entered into the field.
     */

    /**
     * Selects the License Expiry Date using the date picker calendar.
     *
     * @param year  the target year (e.g. "2025")
     * @param month the target month name (e.g. "May")
     * @param day   the target day (e.g. "15")
     */
    @Step("Select License Expiry Date: {0}-{1}-{2}")
    public void selectLicenseExpiryDate(String year, String month, String day) {
        selectDateInDatePicker("License Expiry Date", year, month, day);
    }

    /**
     * Selects the Date of Birth using the date picker calendar.
     *
     * @param year  the target year (e.g. "1990")
     * @param month the target month name (e.g. "December")
     * @param day   the target day (e.g. "25")
     */
    @Step("Select Date of Birth: {0}-{1}-{2}")
    public void selectDateOfBirth(String year, String month, String day) {
        selectDateInDatePicker("Date of Birth", year, month, day);
    }

}
