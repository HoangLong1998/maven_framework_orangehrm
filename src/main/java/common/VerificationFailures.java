package common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.ITestResult;

/**
 * A singleton class that extends HashMap to manage verification failures for test results.
 * This class associates a list of Throwable instances with each ITestResult.
 */
public class VerificationFailures extends HashMap<ITestResult, List<Throwable>> {
    private VerificationFailures() {
        super();
    }

    /**
     * Retrieves the singleton instance of VerificationFailures.
     * If the instance does not exist, it is created.
     *
     * @return The singleton instance of VerificationFailures.
     */
    public static VerificationFailures getFailures() {
        if (failures == null) {
            failures = new VerificationFailures();
        }
        return failures;
    }

    /**
     * Retrieves the list of Throwable instances associated with a specific test result.
     * If no failures are associated with the test result, an empty list is returned.
     *
     * @param result The ITestResult for which to retrieve failures.
     * @return A list of Throwable instances associated with the test result.
     */
    public List<Throwable> getFailuresForTest(ITestResult result) {
        List<Throwable> exceptions = get(result);
        return exceptions == null ? new ArrayList<Throwable>() : exceptions;
    }

    /**
     * Adds a Throwable instance to the list of failures for a specific test result.
     * If no list exists for the test result, a new list is created.
     *
     * @param result    The ITestResult to which the failure should be added.
     * @param throwable The Throwable instance representing the failure.
     */
    public void addFailureForTest(ITestResult result, Throwable throwable) {
        List<Throwable> exceptions = getFailuresForTest(result);
        exceptions.add(throwable);
        put(result, exceptions);
    }

    // Serialization identifier for the class.
    private static final long serialVersionUID = 1L;

    // Singleton instance of VerificationFailures.
    private static VerificationFailures failures;
}
