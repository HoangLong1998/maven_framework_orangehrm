package pageObjects;

import common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import interfaces.pageUIs.AddEmployeePageUI;

public class AddEmployeePageObject extends BasePage {
    private WebDriver driver;

    public AddEmployeePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void setFirstName(String firstName) {
        setTextToElement(driver.findElement(By.xpath(AddEmployeePageUI.FIRSTNAME)), firstName);
    }

    public void setLastName(String lastName) {
        setTextToElement(driver.findElement(By.xpath(AddEmployeePageUI.LASTNAME)), lastName);
    }

    public String getEmployeeId() {
        return getElementAttribute(driver.findElement(By.xpath(AddEmployeePageUI.EMPLOYEE_ID)), "value");
    }

    public void clickSaveButton() {
        clickToElement(driver.findElement(By.xpath(AddEmployeePageUI.SAVE_BUTTON)));

    }
}
