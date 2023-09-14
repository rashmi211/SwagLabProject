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

import pom.LoginPage;
import pom.ProductDisplayPage;
import utility.Report;
import utility.Screenshot;
import utility.TestData;

@org.testng.annotations.Listeners

public class CartPageTest extends BaseClass {
	ExtentReports report;
	ExtentTest test;
	
	@BeforeTest
	public void configreport() {
		 report = Report.report();
	}
    
	
	@BeforeMethod
    public void addProductTocart() throws EncryptedDocumentException, IOException {
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
 }
	
	    @Test(priority=1)
		public void verifyUserNavigatetoYourCartPage() {
	    	test=report.createTest("verifyUserNavigatetoYourCartPage");
			CartPage cartPage=new CartPage(driver);
			cartPage.clickOnAddtoCart();
			boolean b=cartPage.checkifAddCartDisplayed();
			Assert.assertEquals(b, true);
		}
         
	    @Test(priority=2)
		public void verifyIfUserAbletoCheckoutPage() {
	    	test=report.createTest("verifyIfUserAbletoCheckoutPage");
	    	CartPage cartPage=new CartPage(driver);
	    	cartPage.clickOnAddtoCart();
	    	cartPage.clickonCheckout();
			String name=cartPage.verifyIfCheckoutpageDisplayed();
			Assert.assertEquals(name, "Checkout: Your Information");
		
		}
	   
	    @Test (priority=3)
	    public void verifyUserAbletoContinueShopping() {
	    	test=report.createTest("verifyUserAbletoContinueShopping");
	    	CartPage cartPage=new CartPage(driver);
			cartPage.clickOnAddtoCart();
			cartPage.clickOnContinueShopping();
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
