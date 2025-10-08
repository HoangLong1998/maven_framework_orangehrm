package pageObjects;

import common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import interfaces.pageUIs.PersonalDetailPageUI;


public class PersonalDetailPageObject extends BasePage {
    private WebDriver driver;
    public PersonalDetailPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public String getFirstName() {
        return getElementAttribute(driver.findElement(By.xpath(PersonalDetailPageUI.FIRST_NAME)), "value");
    }

    public String getLastName() {
        return getElementAttribute(driver.findElement(By.xpath(PersonalDetailPageUI.LAST_NAME)), "value");
    }

    public String getEmployeeId() {
        return getElementAttribute(driver.findElement(By.xpath(PersonalDetailPageUI.EMPLOYEE_ID)), "value");
    }
}
