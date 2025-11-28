package pageGeneratorManager.OrangeHRM;

import common.BasePage;
import interfaces.pageUIs.OrangeHRM.AddEmployeePageUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddEmployeePage extends BasePage {
    private WebDriver driver;

    public AddEmployeePage(WebDriver driver) {
        super(driver);
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


    // Click save button to navigate to Personal Detail Page
    public PersonalDetailPage clickSaveButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Wait for loader to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-form-loader")));
        clickToElement(driver.findElement(By.xpath(AddEmployeePageUI.SAVE_BUTTON)));
        return PageGeneratorManager.getPage(PersonalDetailPage.class, driver);
    }

}
