package examples.testng;

import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG implements IReporter {
    ExtentReports extent;
    ExtentSparkReporter spark;
    ExtentTest test;

    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
	extent = new ExtentReports();
	spark = new ExtentSparkReporter("ExtentReports.html");

	for (ISuite suite : suites) {
	    Map<String, ISuiteResult> result = suite.getResults();

	    for (ISuiteResult r : result.values()) {
		ITestContext context = r.getTestContext();

		buildTestNodes(context.getPassedTests(), Status.PASS);
		buildTestNodes(context.getFailedTests(), Status.FAIL);
		buildTestNodes(context.getSkippedTests(), Status.SKIP);
	    }
	}

	extent.attachReporter(spark);
	extent.flush();
    }

    private void buildTestNodes(IResultMap tests, Status status) {
	if (tests.size() > 0) {
	    for (ITestResult result : tests.getAllResults()) {
		test = extent.createTest(result.getMethod().getMethodName());

		for (String group : result.getMethod().getGroups())
		    test.assignCategory(group);

		if (result.getThrowable() != null) {
		    test.log(status, "The Test Has " + status.toString().toLowerCase() + "ed!");
		    test.log(status, result.getThrowable());
		} else {
		    test.log(status, "The Test Has " + status.toString().toLowerCase() + "ed!");
		}
	    }
	}
    }

}