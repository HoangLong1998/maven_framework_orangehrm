package pageObjects;

import org.openqa.selenium.By;
import common.BasePage;
import org.openqa.selenium.WebDriver;
import interfaces.pageUIs.PIMPageUI;


public class PIMPageObject extends BasePage {
    private WebDriver driver;

    private AddEmployeePageObject addEmployee;
    private EmployeeListPageObject employeeList;

    public PIMPageObject(WebDriver driver) {
        this.driver = driver;
        this.addEmployee = new AddEmployeePageObject(driver);
        this.employeeList = new EmployeeListPageObject();
    }

    public AddEmployeePageObject getAddEmployeePage() {
        return addEmployee;
    }

    public EmployeeListPageObject getEmployeeListPage() {
        return employeeList;
    }

    public void clickOnAddEmployee() {
        System.out.println("Clicked on Add Employee");
        clickToElement(driver.findElement(By.xpath(PIMPageUI.ADD_EMPLOYEE)));
    }

    public void clickOnEmployeeList() {
        System.out.println("Clicked on Employee List");
        clickToElement(driver.findElement(By.xpath(PIMPageUI.EMPLOYEE_LIST)));
    }
}

