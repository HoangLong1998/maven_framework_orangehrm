package pageGeneratorManager.OrangeHRM;

import common.BasePage;
import interfaces.pageUIs.OrangeHRM.PIMPageUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class PIMPage extends BasePage {
    private WebDriver driver;

    private AddEmployeePage addEmployee;
    private EmployeeListPage employeeList;

    public PIMPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.addEmployee = new AddEmployeePage(driver);
        this.employeeList = new EmployeeListPage(driver);
    }

    public AddEmployeePage getAddEmployeePage() {
        return addEmployee;
    }

    public EmployeeListPage getEmployeeListPage() {
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

