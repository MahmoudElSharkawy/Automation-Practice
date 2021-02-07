package utils;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.internal.ConfigurationMethod;

public class InvokedMethodListener implements IInvokedMethodListener {
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
	ITestNGMethod testMethod = method.getTestMethod();
	System.out.println("\n" + "*******************************************");
	if (testMethod instanceof ConfigurationMethod) {
	    System.out.println("Starting Configuration Method (Setup or Teardown): " + testResult.getName());
	} else {
	    System.out.println("Starting Test Case: " + testResult.getName());
	}
	System.out.println("*******************************************" + "\n");
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
	ITestNGMethod testMethod = method.getTestMethod();
	System.out.println("\n" + "*******************************************");
	if (testMethod instanceof ConfigurationMethod) {
	    System.out.println("Finished Configuration Method (Setup or Teardown): " + testResult.getName());
	} else {
	    System.out.println("Finished Test Case: " + testResult.getName());
	}
	System.out.println("*******************************************" + "\n");
    }
}
