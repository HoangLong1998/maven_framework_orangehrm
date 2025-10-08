package pageObjects;

import common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import interfaces.pageUIs.LoginPageUI;

public class LoginPagePageObject extends BasePage {
    private WebDriver driver;
    LoginPageUI loginPageUI = new LoginPageUI();

    public LoginPagePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void enterToUsername(String username) {
        waitForElementVisible(driver.findElement(By.xpath(loginPageUI.USERNAME)));
        setTextToElement(driver.findElement(By.xpath(loginPageUI.USERNAME)), username);
    }

    public void enterToPassword(String password) {
        waitForElementVisible(driver.findElement(By.xpath(loginPageUI.PASSWORD)));
        setTextToElement(driver.findElement(By.xpath(loginPageUI.PASSWORD)), password);
    }

    public void clickToLoginButton() {
        waitForElementVisible(driver.findElement(By.xpath(loginPageUI.LOGIN_BUTTON)));
        clickToElement(driver.findElement(By.xpath(loginPageUI.LOGIN_BUTTON)));
    }

}
