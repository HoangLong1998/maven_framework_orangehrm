package pageObjects.orangeHRM;

import common.BasePage;
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
    public String getFirstName() {
        return getElementAttribute(driver.findElement(By.xpath(PersonalDetailPageUI.FIRST_NAME)), "value");
    }

    /**
     * Retrieves the last name of the employee from the Personal Details page.
     *
     * @return The last name of the employee as a String.
     */
    public String getLastName() {
        return getElementAttribute(driver.findElement(By.xpath(PersonalDetailPageUI.LAST_NAME)), "value");
    }

    /**
     * Retrieves the employee ID from the Personal Details page.
     *
     * @return The employee ID as a String.
     */
    public String getEmployeeId() {
        return getElementAttribute(driver.findElement(By.xpath(PersonalDetailPageUI.EMPLOYEE_ID)), "value");
    }


}
