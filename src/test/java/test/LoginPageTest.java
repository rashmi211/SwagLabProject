package test;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import pojo.LounchBrowser;
import pom.LoginPage;
import utility.Report;
import utility.Screenshot;
import utility.TestData;

@org.testng.annotations.Listeners

public class LoginPageTest extends BaseClass {
	ExtentReports report;
	ExtentTest test;
	
	@BeforeTest
	public void configReport() {
		 report = Report.report();
	}
    
    @BeforeMethod
    public void openChrome() {
    	 driver=LounchBrowser.OpenChrome();
    }
    
	@Test(priority=1)
	public void verifyUserAbleToLoginToSwagLabWithValidCreadential() throws EncryptedDocumentException, IOException {
		test=report.createTest("verifyUserAbletoLoginToSwagLabwithValidCreadential");
		LoginPage loginPage=new LoginPage(driver);
		String username=TestData.getData(1, 1);
		loginPage.enterUsername(username);
		String password=TestData.getData(2, 1);
		loginPage.enterPassword(password);
		loginPage.clickOnLoginButton();
		String title=driver.getTitle();
		Assert.assertEquals(title,"Swag Labs");	
	}
	
	@Test(priority=2)
	public void verifyUserAbleToRemoveUserName() {
		 test=report.createTest("verifyUserAbletoRemoveUserName");
		LoginPage loginPage=new LoginPage(driver);
		loginPage.clearUserNameField();
		boolean v=loginPage.verifyUserNameGetRemoved();
		SoftAssert softAssert=new SoftAssert();
		softAssert.assertTrue(v,"UserName not empty");
		softAssert.assertAll();
	}
	
	@Test(priority=3)
	public void verifyUserAbleToRemovePassword() {
		test=report.createTest("verifyUserAbletoRemovePassword");
		LoginPage loginPage=new LoginPage(driver);
		loginPage.clearPasswordField();
		boolean v=loginPage.verifyPasswordGetRemoved();
		SoftAssert softAssert=new SoftAssert();
		softAssert.assertTrue(v,"password not empty");
		softAssert.assertAll();
	}
	
	
	@AfterMethod
    public void closeBrowser (ITestResult result) throws IOException {
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

