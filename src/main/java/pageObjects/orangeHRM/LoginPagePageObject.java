package pageObjects.orangeHRM;

import common.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import interfaces.pageUIs.OrangeHRM.LoginPageUI;

public class LoginPagePageObject extends BasePage {
    private WebDriver driver;
    LoginPageUI loginPageUI = new LoginPageUI();

    public LoginPagePageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    /**
     * Enters the provided username into the username field on the login page.
     * @param username The username to be entered into the username field.
     */
    @Step("Enter to Username with value: {0}")
    public void enterToUsername(String username) {
        waitForElementVisible(driver.findElement(By.xpath(loginPageUI.USERNAME)));
        setTextToElement(driver.findElement(By.xpath(loginPageUI.USERNAME)), username);
    }

    /**
     * Enters the provided password into the password field on the login page.
     * @param password The password to be entered into the password field.
     */
    @Step("Enter to Password with value: {0}")
    public void enterToPassword(String password) {
        waitForElementVisible(driver.findElement(By.xpath(loginPageUI.PASSWORD)));
        setTextToElement(driver.findElement(By.xpath(loginPageUI.PASSWORD)), password);
    }

    /**
     * Clicks the login button on the login page.
     */
    @Step("Click to Login Button")
    public void clickToLoginButton() {
        waitForElementVisible(driver.findElement(By.xpath(loginPageUI.LOGIN_BUTTON)));
        clickToElement(driver.findElement(By.xpath(loginPageUI.LOGIN_BUTTON)));
    }

}
