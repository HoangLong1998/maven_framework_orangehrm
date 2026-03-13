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

     public void setMiddleName(String middleName) {
         waitForElementVisible(driver.findElement(By.xpath(PersonalDetailPageUI.MIDDLE_NAME)));
         setTextToElement(driver.findElement(By.xpath(PersonalDetailPageUI.MIDDLE_NAME)), middleName);
     }

     public void setDriverLicenseNumber(String driverLicenseNumber) {
         waitForElementVisible(driver.findElement(By.xpath(PersonalDetailPageUI.DRIVER_LICENSE_NUMBER)));
         setTextToElement(driver.findElement(By.xpath(PersonalDetailPageUI.DRIVER_LICENSE_NUMBER)), driverLicenseNumber);
     }
     public void setLicenseExpiryDate(String licenseExpiryDate) {
         waitForElementVisible(driver.findElement(By.xpath(PersonalDetailPageUI.LICENSE_EXPIRY_DATE)));
         setTextToElement(driver.findElement(By.xpath(PersonalDetailPageUI.LICENSE_EXPIRY_DATE)), licenseExpiryDate);
     }


}
