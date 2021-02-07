package utils;

import org.testng.ISuite;
import org.testng.ISuiteListener;

public class SuiteListener implements ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
	System.out.println("\n" + "*******************************************");
	System.out.println("Starting Test Suite; By Mahmoud El-Sharkawy!");
	System.out.println("*******************************************" + "\n");
    }

    @Override
    public void onFinish(ISuite suite) {
	System.out.println("\n" + "*******************************************");
	System.out.println("Finished Test Suite; By Mahmoud El-Sharkawy!");
	System.out.println("*******************************************" + "\n");
    }
}
