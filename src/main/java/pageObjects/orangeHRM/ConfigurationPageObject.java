package pageObjects.orangeHRM;

import common.BasePage;
import interfaces.pageUIs.OrangeHRM.AddEmployeePageUI;
import interfaces.pageUIs.OrangeHRM.ConfigurationPageUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConfigurationPageObject extends BasePage {
    private WebDriver driver;

    private DataImportPageObject dataImport;

    public ConfigurationPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.dataImport = new DataImportPageObject(driver);

    }

    public DataImportPageObject getDataImportPage(){
        return dataImport;
    }
    public void openPageByPageName(String pageName){
        System.out.println("Click on page name to open the page");
        clickToElement(driver.findElement(By.xpath(String.format(ConfigurationPageUI.SubPageByName,pageName))));
    }
}
