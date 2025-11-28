package interfaces.pageUIs.OrangeHRM;

public class LoginPageUI {
    public static final String USERNAME = "//input[@name=\"username\"]";
    public static final String PASSWORD = "//input[@name=\"password\"]";
    public static final String LOGIN_BUTTON = "//button[contains(@class,\"orangehrm-login-button\")]";
    public final String EMAIL_ERROR_MESSAGE = "xpath=//span[@id='Email-error']";
    public final String UNSUCCESSFUL_ERROR_MESSAGE = "xpath=//div[contains(@class,'message-error')]//li";
}
