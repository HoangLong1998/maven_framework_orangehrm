package pageGeneratorManager;

import common.BasePage;
import interfaces.pageUIs.PersonalDetailPageUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class PersonalDetailPage extends BasePage {
    private WebDriver driver;
    public PersonalDetailPage(WebDriver driver) {
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
