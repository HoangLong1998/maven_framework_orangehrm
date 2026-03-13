package interfaces.pageUIs.OrangeHRM;

public class PersonalDetailPageUI {
    public static final String FIRST_NAME = "//input[@name=\"firstName\"]";
    public static final String LAST_NAME = "//input[@name=\"lastName\"]";
    public static final String MIDDLE_NAME = "//input[@name=\"middleName\"]";
    public static final String EMPLOYEE_ID = "//label[text()='Employee Id']/following::input[1]";
    public static final String  DRIVER_LICENSE_NUMBER = "//label[contains(text(),\"Driver's License Number\")]/following::input[1]";
    public static final String LICENSE_EXPIRY_DATE = "//label[contains(text(),\"License Expiry\")]/following::input[1]";
    public static final String NATIONALITY = "(//label[contains(text(),\"Nationality\")]/following::div[contains(@class,\"select-text-input\")])[1]";
    public static final String MARITAL_STATUS = "(//label[contains(text(),\"Marital Status\")]/following::div[contains(@class,\"select-text-input\")])[1]" ;
    public static final String DATE_OF_BIRTH = "//label[contains(text(),\"Birth\")]/following::input[@placeholder=\"yyyy-mm-dd\"]";

    public static final String UPLOAD_AVATAR = "//input[@type='file']/ancestor::form//button[contains(@class,\"image\")]";
    public static final String UPLOAD_FILE_TYPE = "//input[@type='file']";
    public static final String SAVE_BUTTON = "//button[@type=\"submit\"]";
}
