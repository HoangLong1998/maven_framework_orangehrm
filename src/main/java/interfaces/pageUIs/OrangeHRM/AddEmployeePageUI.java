package interfaces.pageUIs.OrangeHRM;

public class AddEmployeePageUI {
    public static final String FIRSTNAME = "//input[contains(@class,'firstname')]";
    public static final String LASTNAME = "//input[contains(@class,'lastname')]";
    public static final String EMPLOYEE_ID = "//label[contains(text(), 'Employee Id')]/following::input[1]";
    public static final String SAVE_BUTTON = "//button[@type='submit']";
    public static final String CREATE_LOGIN_DETAILS_CHECKBOX = "//div[contains(@class,\"user-form-header\")]//span";
    public static final String USERNAME_TEXTBOX = "//label[text()='Username']/following::input[contains(@class, 'oxd-input')][1]";
    public static final String PASSWORD_TEXTBOX = "(//label[text()='Password']/following::input[contains(@class, 'oxd-input')])[1]";
    public static final String CONFIRM_PASSWORD_TEXTBOX = "(//label[text()='Password']/following::input[contains(@class, 'oxd-input')])[2]";

}
