package test;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import pojo.LounchBrowser;
import pom.LogOutPage;
import pom.LoginPage;
import utility.Report;
import utility.Screenshot;
import utility.TestData;

@org.testng.annotations.Listeners

public class LogOutPageTest extends BaseClass {
	ExtentReports report;
	ExtentTest test;
	
	@BeforeTest
	public void configreport() {
		 report = Report.report();
	}
    
    @BeforeMethod
    public void logintoSwagLab() throws EncryptedDocumentException, IOException {
    	 driver=LounchBrowser.OpenChrome();
    	    LoginPage loginPage=new LoginPage(driver);
			String username=TestData.getData(1, 1);
			loginPage.enterUsername(username);
			String password=TestData.getData(2, 1);
			loginPage.enterPassword(password);
			loginPage.clickOnLoginButton();
      }
    
	@Test(priority=1)
    public void verifyUserAbletoLogout() {
		test=report.createTest("VerifyUserAbletoLogout");
    	LogOutPage logOutPage=new LogOutPage(driver);
    	logOutPage.clickOnMenuButton();
    	logOutPage.clickOnLogOutButton(driver);
    	boolean b=logOutPage.verifyiflogindisplayed();
    	Assert.assertEquals(true, b);
    
	}
	@Test(priority=2)
	public void verifyTappingOnAbout() {
		test=report.createTest("VerifyUserAbletoLogout");
    	LogOutPage logOutPage=new LogOutPage(driver);
    	logOutPage.clickOnMenuButton();
    	logOutPage.clickOnAboutButton(driver);
    	String tname=logOutPage.verifyUserNavigateToAboutPage(driver);
    	String pageTitle="Sauce Labs: Cross Browser Testing, Selenium Testing & Mobile Testing";
    	Assert.assertEquals(tname, pageTitle);
	}
	
	@AfterMethod
    public void closebrowser (ITestResult result) throws IOException {
    	if(result.getStatus()==ITestResult.SUCCESS) {
    		test.log(Status.PASS, result.getName());
    	}
    	else if(result.getStatus()==ITestResult.FAILURE) {
    		test.log(Status.FAIL, result.getName());
    		Screenshot.takepicture(driver, result.getName());
    	}
    	else { 
    		test.log(Status.SKIP, result.getName());
    	}
    	driver.close();
    }
    
    @AfterTest
    public void publish() {
    	report.flush();
    }

}
