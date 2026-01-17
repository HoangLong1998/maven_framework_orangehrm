package pageObjects.orangeHRM;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import common.BasePage;
import org.openqa.selenium.WebDriver;
import interfaces.pageUIs.OrangeHRM.PIMPageUI;

/**
 * Represents the PIM (Personnel Information Management) page object in the OrangeHRM application.
 * This class provides methods to interact with the PIM page and its sub-pages, such as Add Employee,
 * Employee List, and Configuration.
 */
public class PIMPageObject extends BasePage {
    // WebDriver instance used to interact with the web elements on the page.
    private WebDriver driver;

    // Page object for the Add Employee section.
    private AddEmployeePageObject addEmployee;

    // Page object for the Employee List section.
    private EmployeeListPageObject employeeList;

    // Page object for the Configuration section.
    private ConfigurationPageObject configuration;

    /**
     * Constructor for the PIMPageObject class.
     * Initializes the WebDriver instance and the sub-page objects.
     *
     * @param driver The WebDriver instance used to interact with the web page.
     */
    public PIMPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.addEmployee = new AddEmployeePageObject(driver);
        this.employeeList = new EmployeeListPageObject();
        this.configuration = new ConfigurationPageObject(driver);
    }

    /**
     * Retrieves the Add Employee page object.
     *
     * @return The AddEmployeePageObject instance.
     */
    public AddEmployeePageObject getAddEmployeePage() {
        return addEmployee;
    }

    /**
     * Retrieves the Employee List page object.
     *
     * @return The EmployeeListPageObject instance.
     */
    public EmployeeListPageObject getEmployeeListPage() {
        return employeeList;
    }

    /**
     * Retrieves the Configuration page object.
     *
     * @return The ConfigurationPageObject instance.
     */
    public ConfigurationPageObject getConfigurationPage() {
        return configuration;
    }

    /**
     * Clicks on the "Add Employee" option on the PIM page.
     * Logs the action and interacts with the corresponding web element.
     */
    @Step("Click on Add Employee on PIM page")
    public void clickOnAddEmployee() {
        System.out.println("Clicked on Add Employee");
        clickToElement(driver.findElement(By.xpath(PIMPageUI.ADD_EMPLOYEE)));
    }

    /**
     * Clicks on the "Employee List" option on the PIM page.
     * Logs the action and interacts with the corresponding web element.
     */
    @Step("Click on Employee List on PIM page")
    public void clickOnEmployeeList() {
        System.out.println("Clicked on Employee List");
        clickToElement(driver.findElement(By.xpath(PIMPageUI.EMPLOYEE_LIST)));
    }

    /**
     * Clicks on the "Configuration" option on the PIM page.
     * Logs the action and interacts with the corresponding web element.
     */
    @Step("Click on Configuration on PIM page")
    public void clickOnConfiguration() {
        System.out.println("Click on Configuration on PIM page");
        clickToElement(driver.findElement(By.xpath(PIMPageUI.CONFIGURATION)));
    }
}

