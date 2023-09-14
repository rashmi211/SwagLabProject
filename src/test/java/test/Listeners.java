package test;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {
	
	public void onTestStart(ITestResult result) {
		System.out.println("Started test"+result.getName());
	}
	
	public void onTestFailure(ITestResult result) {
		System.out.println("Failed test"+result.getName());
	}
	
	public void onTestSuccess(ITestResult result) {
		System.out.println("Passed test"+result.getName());
	}
	
	public void onTestSkipped(ITestResult result) {
		System.out.println("Skipped test"+result.getName());
	}

}
