package pageObjects.orangeHRM;

import common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import interfaces.pageUIs.OrangeHRM.AddEmployeePageUI;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddEmployeePageObject extends BasePage {
    private WebDriver driver;

    /**
     * Constructor for the AddEmployeePageObject class.
     * Initializes the WebDriver instance used to interact with the Add Employee page.
     * @param driver The WebDriver instance used to interact with the web page.
     */
    public AddEmployeePageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    /**
     * Sets the first name of the employee in the Add Employee form.
     * @param firstName The first name of the employee to be entered.
     */
    public void setFirstName(String firstName) {
        setTextToElement(driver.findElement(By.xpath(AddEmployeePageUI.FIRSTNAME)), firstName);
    }

    /**
     * Sets the last name of the employee in the Add Employee form.
     * @param lastName The last name of the employee to be entered.
     */
    public void setLastName(String lastName) {
        setTextToElement(driver.findElement(By.xpath(AddEmployeePageUI.LASTNAME)), lastName);
    }

    /**
     * Retrieves the employee ID from the Add Employee form.
     * @return The employee ID as a String.
     */
    public String getEmployeeId() {
        return getElementAttribute(driver.findElement(By.xpath(AddEmployeePageUI.EMPLOYEE_ID)), "value");
    }

    /**
     * Clicks the "Save" button on the Add Employee form.
     */
    public void clickSaveButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Wait for loader to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-form-loader")));
        clickToElement(driver.findElement(By.xpath(AddEmployeePageUI.SAVE_BUTTON)));
    }


}
