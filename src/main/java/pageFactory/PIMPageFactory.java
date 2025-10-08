package pageFactory;

import common.BasePage;
import interfaces.pageUIs.PIMPageUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;

public class PIMPageFactory extends BasePage {
    private WebDriver driver;
   @FindBy(xpath = "//a[text()='Add Employee']")
    private WebElement addEmployeeButton;
    @FindBy(xpath = "//a[text()='Employee List']")
    private WebElement employeeListButton;

    private AddEmployeePageFactory addEmployee;
    private EmployeeListPageFactory employeeList;

    public PIMPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.addEmployee = new AddEmployeePageFactory(driver);
        this.employeeList = new EmployeeListPageFactory();
    }

    public AddEmployeePageFactory getAddEmployeePage() {
        return addEmployee;
    }

    public EmployeeListPageFactory getEmployeeListPage() {
        return employeeList;
    }

    public void clickOnAddEmployee() {
        System.out.println("Clicked on Add Employee");
        clickToElement(addEmployeeButton);
    }

    public void clickOnEmployeeList() {
        System.out.println("Clicked on Employee List");
        clickToElement(employeeListButton);
    }
}

