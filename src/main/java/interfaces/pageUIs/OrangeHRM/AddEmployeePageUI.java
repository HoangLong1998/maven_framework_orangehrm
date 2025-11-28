package interfaces.pageUIs.OrangeHRM;

public class AddEmployeePageUI {
    public static final String FIRSTNAME = "//input[contains(@class,'firstname')]";
    public static final String LASTNAME = "//input[contains(@class,'lastname')]";
    public static final String EMPLOYEE_ID = "//label[contains(text(), 'Employee Id')]/following::input[1]";
    public static final String SAVE_BUTTON = "//button[@type='submit']";
}
