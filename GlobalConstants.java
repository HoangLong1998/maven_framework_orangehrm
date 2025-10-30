public class GlobalConstants {
// System Infor
    public static final String PROJECT_PATH =System.getProperty("user.dir");
    public static final String OS_NAME =System.getProperty("os.name");

    // App Infor User
    // App Infor Admin
    // Wait For
    public static final int SHORT_TIMEOUT = 10;
    public static final int LONG_TIMEOUT = 30;

    // Download/Upload File
    public static final String UPLOAD_PATH =PROJECT_PATH + "/uploadFiles/";
    public static final String DOWNLOAD_PATH =PROJECT_PATH + "/downloadFiles/";

    // Retry Case Failed
     public static final int RETRY_NUMBER =3;

     //Browser Log/ Extension
    public static final String BROWSER_lOG_PATH = PROJECT_PATH + "/browserLogs/";
    public static final String BROWSER_EXTENSION_PATH = PROJECT_PATH + "/browserExtensions/";

    // HTML Report Folder
    public static final String REPORT_PATH = PROJECT_PATH + "/htmlReportNG/";
    public static final String EXTENT_PATH = PROJECT_PATH + "/htmlExtent/";
    public static final String ALLURE_PATH = PROJECT_PATH + "/htmlAllure/";

    // Data Test/ Environment
    public static final String DATA_TEST_PATH = PROJECT_PATH + "/dataTest/";
    public static final String ENVIRONMENT_CONFIG_PATH = PROJECT_PATH + "/environmentConfig/";




}
