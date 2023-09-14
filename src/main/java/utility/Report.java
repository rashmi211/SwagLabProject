package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Report {
	public static ExtentReports report() {
		ExtentSparkReporter htmlreport= new ExtentSparkReporter("report.html");
		ExtentReports report=new ExtentReports();
		report.attachReporter(htmlreport);
		report.setSystemInfo("Project name", "SwagLab");
		report.setSystemInfo("Test Type", "Regression");
		report.setSystemInfo("QA Engineer", "Rashmi Salunke");
		return report;
	}
	

}

