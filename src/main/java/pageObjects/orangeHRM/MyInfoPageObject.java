package pageObjects.orangeHRM;

import common.BasePage;
import interfaces.pageUIs.OrangeHRM.MyInfoPageUI;
import interfaces.pageUIs.OrangeHRM.PersonalDetailPageUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyInfoPageObject extends BasePage {
    private WebDriver driver;

    private PersonalDetailPageObject personalDetailPage;

    /**
     * Constructor for the MyInfoPageObject class.
     * Initializes the WebDriver instance and the PersonalDetailPageObject.
     *
     * @param driver The WebDriver instance used to interact with the web page.
     */
    public MyInfoPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.personalDetailPage = new PersonalDetailPageObject(driver);
    }

    /**
     * Retrieves the PersonalDetailPageObject instance.
     *
     * @return The PersonalDetailPageObject instance.
     */
    public PersonalDetailPageObject getPersonalDetailPage() {
        return personalDetailPage;
    }

//    /**
//     * Opens a specific page by clicking on its name.
//     * The page name is used to locate the corresponding web element.
//     *
//     * @param pageName The name of the page to open.
//     */
//    public void openPageByPageName(String pageName) {
//        System.out.println("Open the Page by click on Page Name");
//        clickToElement(driver.findElement(By.xpath(String.format(MyInfoPageUI.PAGE_LOCATOR_BY_NAME, pageName))));
//    }

    /**
     * Clicks on the employee avatar on the My Info page.
     * Waits for the avatar element to be visible before interacting with it.
     */
    public void clickAvatar() {
        waitForElementVisible(driver.findElement(By.xpath(MyInfoPageUI.EMPLOYEE_AVATAR)));
        clickToElement(driver.findElement(By.xpath(MyInfoPageUI.EMPLOYEE_AVATAR)));
        sleepInSecond(10);
    }

    /**
     * Clicks on the "Upload Avatar" button on the Personal Details page.
     * Waits for the button element to be visible before interacting with it.
     */
    public void clickToUploadAvatarButton() {
        waitForElementVisible(driver.findElement(By.xpath(PersonalDetailPageUI.UPLOAD_AVATAR)));
        clickToElement(driver.findElement(By.xpath(PersonalDetailPageUI.UPLOAD_AVATAR)));
        sleepInSecond(10);
    }

    /**
     * Uploads an avatar image file.
     * Waits for the file upload input to be visible, uploads the file, and takes a snapshot.
     *
     * @param fileName The name of the file to upload.
     */
    public void uploadAvatar(String fileName) {
        waitForElementVisible(driver.findElement(By.xpath(PersonalDetailPageUI.UPLOAD_FILE_TYPE)));
        uploadMultipleFiles(driver.findElement(By.xpath(PersonalDetailPageUI.UPLOAD_FILE_TYPE)), fileName);
        takeSnap(driver, "avatar1");
    }

    public String getUploadFileErrorMessage() {
        waitForElementVisible(driver.findElement(By.xpath(MyInfoPageUI.INVALID_FILE_TYPE_ERROR_MESSAGE)));
        return driver.findElement(By.xpath(MyInfoPageUI.INVALID_FILE_TYPE_ERROR_MESSAGE)).getText();
    }

    /**
     * Saves the information on the current page.
     * Waits for the "Save" button to be visible before clicking it.
     */
    public void savePageInfo() {
        waitForElementVisible(driver.findElement(By.xpath(PersonalDetailPageUI.SAVE_BUTTON)));
        clickToElement(driver.findElement(By.xpath(PersonalDetailPageUI.SAVE_BUTTON)));
    }


}
