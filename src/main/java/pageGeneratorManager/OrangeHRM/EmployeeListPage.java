package pageGeneratorManager.OrangeHRM;

import common.BasePage;
import org.openqa.selenium.WebDriver;

public class EmployeeListPage extends BasePage {
    private WebDriver driver;

    public EmployeeListPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


}
