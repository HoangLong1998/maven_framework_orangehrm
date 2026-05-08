package pageObjects.orangeHRM;

import common.BasePage;
import interfaces.pageUIs.OrangeHRM.EmployeeListPageUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import interfaces.pageUIs.OrangeHRM.EmployeeListPageUI;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class EmployeeListPageObject extends BasePage {
    private WebDriver driver;

    public EmployeeListPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void sortAscendingDataByColumn(String columnName) {
        clickToElement(driver.findElement(By.xpath(String.format(EmployeeListPageUI.SORT_BY_COLUMN, columnName))));
        waitForElementVisible(driver.findElement(By.xpath(String.format(EmployeeListPageUI.SORT_PARAMS_ASCENDING, columnName))));
        clickToElement(driver.findElement(By.xpath(String.format(EmployeeListPageUI.SORT_PARAMS_ASCENDING, columnName))));
    }

    public void sortDescendingDataByColumn(String columnName) {
        clickToElement(driver.findElement(By.xpath(String.format(EmployeeListPageUI.SORT_BY_COLUMN, columnName))));
        waitForElementVisible(driver.findElement(By.xpath(String.format(EmployeeListPageUI.SORT_PARAMS_DESCENDING, columnName))));
        clickToElement(driver.findElement(By.xpath(String.format(EmployeeListPageUI.SORT_PARAMS_DESCENDING, columnName))));
    }


}
