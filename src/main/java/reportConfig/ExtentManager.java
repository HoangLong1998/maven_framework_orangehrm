package reportConfig;

import java.util.HashMap;
import java.util.Map;


// ExtentReports v2

//import com.relevantcodes.extentreports.ExtentReports;
//import com.relevantcodes.extentreports.ExtentTest;
//
//import common.GlobalConstants;
//
//public class ExtentManager {
//    private static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
//    private static ExtentReports extent = ExtentManager.getReporter();
//
//    public synchronized static ExtentReports getReporter() {
//        if (extent == null) {
//            extent = new ExtentReports(GlobalConstants.EXTENT_PATH + "/ExtentReportV2.html", true);
//        }
//        return extent;
//    }
//
//    public static synchronized ExtentTest getTest() {
//        return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
//    }
//
//    public static synchronized void endTest() {
//        extent.endTest((ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId())));
//    }
//
//    public static synchronized ExtentTest startTest(String testName, String desc) {
//        ExtentTest test = extent.startTest(testName, desc);
//        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
//        return test;
//    }
//}

// Extent Reports v5
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import com.aventstack.extentreports.reporter.configuration.Theme;
import common.GlobalConstants;

import java.util.HashMap;
import java.util.Map;

public class ExtentManager {
    public static final ExtentReports extentReports = new ExtentReports();
    public static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
    public static ExtentReports extent = ExtentManager.createExtentReports();


    public synchronized static ExtentReports createExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter(GlobalConstants.EXTENT_PATH + "Report.html");
        reporter.config().setReportName("Orange HRM HTML Report");
        reporter.config().setDocumentTitle("Orange HRM HTML Report");
        reporter.config().setTimelineEnabled(true);
        reporter.config().setEncoding("utf-8");
        reporter.config().setTheme(Theme.STANDARD);

        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Company", "Automation FC");
        extentReports.setSystemInfo("Project", "Orange HRM");
        extentReports.setSystemInfo("Team", "Automation VN");
        extentReports.setSystemInfo("JDK", GlobalConstants.JAVA_VERSION);
        extentReports.setSystemInfo("OS", GlobalConstants.OS_NAME);
        return extentReports;
    }


    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) Thread.currentThread().getId());
    }

    public static synchronized ExtentTest startTest(String testName, String desc) {
        ExtentTest test = extent.createTest(testName, desc);
        extentTestMap.put((int) Thread.currentThread().getId(), test);
        return test;
    }
}