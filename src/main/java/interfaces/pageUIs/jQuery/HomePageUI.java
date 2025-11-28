package interfaces.pageUIs.jQuery;

public class HomePageUI {
    public static final String PAGE_NUMBER = "//ul[@class=\"qgrd-pagination-ul\"]//li//a[text()=\"%s\"]";
    public static final String IS_ACTIVE_PAGE = "//ul[@class=\"qgrd-pagination-ul\"]//li//a[contains(@class, \"active\")][text()=\"%s\"]";
    public static final String COLUMN_NAME = "";
    public static final String PAGE_NUMBER_lIST ="//li[@class=\"qgrd-pagination-page\"]//a";
    public static final String VALUES_lIST_BY_COLUMN_NAME = "//td[@data-key=\"%s\"]";
}
