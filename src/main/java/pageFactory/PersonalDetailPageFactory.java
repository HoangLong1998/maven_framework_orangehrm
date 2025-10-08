package pageFactory;

import common.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;


public class PersonalDetailPageFactory extends BasePage {
    private WebDriver driver;
    @FindBy(xpath = "//input[@name=\"firstName\"]")
    private WebElement firstNameField;
    @FindBy(xpath = "//input[@name=\"lastName\"]")
    private WebElement lastNameField;
    @FindBy(xpath = "//label[text()='Employee Id']/following::input[1]")
    private WebElement employeeIdField;

    public PersonalDetailPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getFirstName() {
        return getElementAttribute(firstNameField, "value");
    }

    public String getLastName() {
        return getElementAttribute(lastNameField, "value");
    }

    public String getEmployeeId() {
        return getElementAttribute(employeeIdField, "value");
    }
}
