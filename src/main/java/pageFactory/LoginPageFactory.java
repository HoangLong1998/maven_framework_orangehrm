package pageFactory;

import common.BasePage;
import interfaces.pageUIs.LoginPageUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;


public class LoginPageFactory extends BasePage {
    private WebDriver driver;
    LoginPageUI loginPageUI = new LoginPageUI();

    @FindBy(xpath = "//input[@name='username']")
    private WebElement usernameField;
    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordField;
    @FindBy(xpath = "//button[contains(@class,\"orangehrm-login-button\")]")
    private WebElement loginButton;


    public LoginPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterToUsername(String username) {
        waitForElementVisible(usernameField);
        setTextToElement(usernameField, username);
    }

    public void enterToPassword(String password) {
        waitForElementVisible(passwordField);
        setTextToElement(passwordField, password);
    }

    public void clickToLoginButton() {
        waitForElementVisible(loginButton);
        clickToElement(loginButton);
    }

}
