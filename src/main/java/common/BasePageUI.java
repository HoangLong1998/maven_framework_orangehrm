package common;

public class BasePageUI {
    public static final String LOADING_ICON = "//div[@class=\"oxd-loading-spinner\"]";
    public  static final String PAGE_LOCATOR_BY_NAME = "//ul[@class=\"oxd-main-menu\"]//li//span[text()='%s']";
    public static final String USER_DROPDOWN = "//span[contains(@class, 'userdropdown')]";
    public static final String USER_DROPDOWN_OPTION_LIST = "//li[contains(@class,\"userdropdown\")]//li";
    // Date Picker locators
    public static final String DATE_INPUT_BY_LABEL = "//label[contains(text(),'%s')]/ancestor::div[contains(@class,'oxd-input-group')]//input[contains(@class,'oxd-input')]";
    public static final String DATE_PICKER_CALENDAR = "//div[contains(@class,'oxd-calendar-wrapper')]";
    public static final String DATE_PICKER_MONTH_DISPLAY = "//div[contains(@class,'oxd-calendar-selector-month-selected')]";
    public static final String DATE_PICKER_YEAR_DISPLAY = "//div[contains(@class,'oxd-calendar-selector-year-selected')]";
    public static final String DATE_PICKER_PREVIOUS_BUTTON = "//div[contains(@class,'oxd-calendar-header')]//button[1]";
    public static final String DATE_PICKER_NEXT_BUTTON = "//div[contains(@class,'oxd-calendar-header')]//button[2]";
    public static final String DATE_PICKER_DAY_CELLS = "//div[contains(@class,'oxd-calendar-dates-grid')]//div[contains(@class,'oxd-calendar-date-wrapper') and not(contains(@class,'--offset'))]//div[contains(@class,'oxd-calendar-date')]";
}
