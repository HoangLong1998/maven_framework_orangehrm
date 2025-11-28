package pageObjects.orangeHRM;

import common.BasePage;
import interfaces.pageUIs.OrangeHRM.DataImportPageUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DataImportPageObject extends BasePage {
    private WebDriver driver;

    public DataImportPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    public void uploadFile(String ...fileName){
        WebElement browserButton = driver.findElement(By.xpath(DataImportPageUI.BROWSER_BUTTON));
        WebElement uploadButton = driver.findElement(By.xpath(DataImportPageUI.UPLOAD_BUTTON));
        waitForElementVisible(browserButton);
        clickToElement(browserButton);
        sleepInSecond(10);
        uploadMultipleFiles(uploadButton,fileName);
        takeSnap(driver, "uploadedFileImage");
    }


}
