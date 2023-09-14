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

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import pojo.LounchBrowser;
import pom.CartPage;
import pom.ChekoutPage;
import pom.LoginPage;
import pom.ProductDisplayPage;
import utility.Report;
import utility.Screenshot;
import utility.TestData;
@org.testng.annotations.Listeners

public class CheckoutPageTest extends BaseClass {
	ExtentReports report;
	ExtentTest test;
	
	@BeforeTest
	public void configreport() {
		 report = Report.report();
	}
    
	
	@BeforeMethod
	 public void openChrome() throws EncryptedDocumentException, IOException {
	    driver=LounchBrowser.OpenChrome();
	    LoginPage loginPage=new LoginPage(driver);
		String username=TestData.getData(1, 1);
		loginPage.enterUsername(username);
		String password=TestData.getData(2, 1);
		loginPage.enterPassword(password);
		loginPage.clickOnLoginButton();
		
		ProductDisplayPage productDisplayPage=new ProductDisplayPage(driver);
		productDisplayPage.clickonProduct("Sauce Labs Fleece Jacket");
		productDisplayPage.clickOnAddtoCart();
		
		CartPage cartPage=new CartPage(driver);
		cartPage.clickOnAddtoCart();
    	cartPage.clickonCheckout();	
	}
	
	
	@Test(priority=1)
	public void verifyUserAbletoGotoOverviewPage() {
		test=report.createTest("verifyUserAbletoGotoOverviewPage");
		ChekoutPage chekoutPage=new ChekoutPage(driver);
		chekoutPage.enterFirstName("Rashmi");
		chekoutPage.enterLastName("Salunke");
		chekoutPage.enterZipCode("411023");
		chekoutPage.clickOnContinueButton();
		boolean b=chekoutPage.verifyTotalcostShown();
		Assert.assertEquals(b, true);
	}

	@Test(priority=2)
	public void verifyUserAbletoFinishOrder() {
		test=report.createTest("verifyUserAbletoFinishOrder");
		ChekoutPage chekoutPage=new ChekoutPage(driver);
		chekoutPage.enterFirstName("Rashmi");
		chekoutPage.enterLastName("Salunke");
		chekoutPage.enterZipCode("411023");
		chekoutPage.clickOnContinueButton();
		chekoutPage.clickOnFinish(driver);
		boolean b=chekoutPage.verifyThanksmsShown();
		Assert.assertEquals(b, true);
	}
	
	@Test(priority=3)
	public void verifyClickonCancellingOrder() {
		test=report.createTest("verifyClickonCancellingOrder");
		ChekoutPage chekoutPage=new ChekoutPage(driver);
		chekoutPage.enterFirstName("Rashmi");
		chekoutPage.enterLastName("Salunke");
		chekoutPage.enterZipCode("411023");
		chekoutPage.clickOnContinueButton();
		chekoutPage.clickOnCancelCheckout();
		boolean b=chekoutPage.verifyProductPageDisplayed();
		Assert.assertEquals(b, true);
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
