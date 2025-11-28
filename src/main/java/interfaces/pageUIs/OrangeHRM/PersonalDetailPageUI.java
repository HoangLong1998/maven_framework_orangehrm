package interfaces.pageUIs.OrangeHRM;

public class PersonalDetailPageUI {
    public static final String FIRST_NAME = "//input[@name=\"firstName\"]";
    public static final String LAST_NAME = "//input[@name=\"lastName\"]";
    public static final String EMPLOYEE_ID = "//label[text()='Employee Id']/following::input[1]";
    public static final String UPLOAD_AVATAR = "//input[@type='file']/ancestor::form//button[contains(@class,\"image\")]";
    public static final String UPLOAD_FILE_TYPE = "//input[@type='file']";
    public static final String SAVE_BUTTON = "//button[@type=\"submit\"]";
}
