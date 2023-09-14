package pom;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChekoutPage {
	
	@FindBy (xpath="//span[@class='title']") private WebElement checkoutPageTitle;
	@FindBy (xpath="//input[@id='first-name']") private WebElement firstName;
	@FindBy (xpath="//input[@id='last-name']") private WebElement lastName;
	@FindBy (xpath="//input[@id='postal-code']") private WebElement postalcode;
	@FindBy (xpath="//input[@type='submit']") private WebElement continueButton;
	@FindBy (xpath="//div[@class='summary_info_label summary_total_label']") private WebElement totalPrice;
	@FindBy (xpath="//button[@id='finish']") private WebElement finishButton;
	@FindBy (xpath="//button[@name='cancel']") private WebElement cancelButton;
	@FindBy (xpath="//h2[text()='Thank you for your order!']") private WebElement thankyouMsg;
	@FindBy (xpath="//span[text()='Products']") private WebElement productPage;
	
	public ChekoutPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void enterFirstName(String name) {
		firstName.sendKeys(name);
	}
	
	public void enterLastName(String Lname) {
		lastName.sendKeys(Lname);
	}
	
	public void enterZipCode(String code) {
		postalcode.sendKeys(code);
	}
	
	public void clickOnContinueButton() {
		continueButton.click();
	}
	
	public boolean verifyTotalcostShown() {
		boolean b=totalPrice.isDisplayed();
		return b;
	}
	
	public void clickOnCancelCheckout() {
		cancelButton.click();
	}
	
	public void clickOnFinish(WebDriver driver) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofMillis(3000));
		wait.until(ExpectedConditions.visibilityOf(finishButton));
		finishButton.click();
	}
	
	public boolean verifyThanksmsShown() {
	 boolean b=	thankyouMsg.isDisplayed();
	 return b;
	}
	
	public boolean verifyProductPageDisplayed() {
		return productPage.isDisplayed();
	}
	
}
