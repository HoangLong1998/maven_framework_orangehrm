package pageGeneratorManager;

import common.BasePage;
import interfaces.pageUIs.LoginPageUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPagePage extends BasePage {
    private WebDriver driver;
    LoginPageUI loginPageUI = new LoginPageUI();

    public LoginPagePage(WebDriver driver) {
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


    // Click Login button to navigate to Home Page from Login Page
    public HomePage clickToLoginButton() {
        waitForElementVisible(driver.findElement(By.xpath(loginPageUI.LOGIN_BUTTON)));
        clickToElement(driver.findElement(By.xpath(loginPageUI.LOGIN_BUTTON)));
        return new PageGeneratorManager().getPage(HomePage.class, driver);

    }

}
