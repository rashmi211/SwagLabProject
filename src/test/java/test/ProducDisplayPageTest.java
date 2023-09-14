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
import pom.ProductDisplayPage;
import utility.Report;
import utility.Screenshot;
import utility.TestData;
@org.testng.annotations.Listeners
public class ProducDisplayPageTest extends BaseClass {
	ExtentReports report;
	ExtentTest test;
	
	@BeforeTest
	public void configReport() {
		 report = Report.report();
	}
	
	 @BeforeMethod
	 public void loginToSwagLab() throws EncryptedDocumentException, IOException {
	    	    driver=LounchBrowser.OpenChrome();
	    	    LoginPage loginPage=new LoginPage(driver);
				String username=TestData.getData(1, 1);
				loginPage.enterUsername(username);
				String password=TestData.getData(2, 1);
				loginPage.enterPassword(password);
				loginPage.clickOnLoginButton();
    }
	  
	 @Test(priority=1)
	 public void verifyUserAbleToSeeListOfProducts() {
		 test=report.createTest("verifyUserAbletoSeeProductDetailPage");
		 ProductDisplayPage productDisplayPage=new ProductDisplayPage(driver);
		 boolean list=productDisplayPage.verifyListOfProductIsShownOnProductDeatilsPage();
		 SoftAssert softAssert=new SoftAssert();
		 softAssert.assertEquals(list, true);
		 softAssert.assertAll(); 
	 }
	 
	 @Test(priority=2)
	 public void verifyUserAbletoSeeProductDetailPage() {
		 test=report.createTest("verifyUserAbletoSeeProductDetailPage");
		 ProductDisplayPage productDisplayPage=new ProductDisplayPage(driver);
		 productDisplayPage.clickonProduct("Sauce Labs Fleece Jacket");
		 String actname=productDisplayPage.matchProductName();
		 Assert.assertEquals(actname, "Sauce Labs Fleece Jacket");		
	}
	 
	 
	 @Test(priority=3)
	 public void verifyUserAbleToAddProductToCart() {
		 test=report.createTest("verifyUserAbletoAddProductToCart");
		 ProductDisplayPage productDisplayPage=new ProductDisplayPage(driver);
		 productDisplayPage.clickonProduct("Sauce Labs Fleece Jacket");
		 productDisplayPage.clickOnAddtoCart();
		 boolean v=productDisplayPage.displayRemoveButton();
		 Assert.assertEquals(v, true);
	 }
	
	 
	 @Test(priority=4)
	 public void verifyUserAbleToGoBacktoProduct() {
		 test=report.createTest("verifyUserAbletoGoBacktoProduct");
		 ProductDisplayPage productDisplayPage=new ProductDisplayPage(driver);
		 productDisplayPage.clickonProduct("Sauce Labs Fleece Jacket");
		 productDisplayPage.clickOnAddtoCart();
		 productDisplayPage.clickOnBackButton();
		 boolean b= productDisplayPage.verifyProductPageDisplayed();
		 Assert.assertEquals(b, true); 
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