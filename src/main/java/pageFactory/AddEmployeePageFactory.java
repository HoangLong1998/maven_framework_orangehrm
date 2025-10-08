package pageFactory;

import common.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;

public class AddEmployeePageFactory extends BasePage {
    private WebDriver driver;

    @FindBy(xpath = "//input[contains(@class,'firstname')]")
    private WebElement firstNameField;
    @FindBy(xpath = "//input[contains(@class,'lastname')]")
    private WebElement lastNameField;
    @FindBy(xpath = "//label[contains(text(), 'Employee Id')]/following::input[1]")
    private WebElement employeeIdField;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;


    public AddEmployeePageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setFirstName(String firstName) {
        setTextToElement(firstNameField, firstName);
    }

    public void setLastName(String lastName) {
        setTextToElement(lastNameField, lastName);
    }

    public String getEmployeeId() {
        return getElementAttribute(employeeIdField, "value");
    }

    public void clickSaveButton() {
        clickToElement(saveButton);

    }
}
