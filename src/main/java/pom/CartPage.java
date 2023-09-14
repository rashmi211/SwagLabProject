package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
	
	@FindBy (xpath="//a[@class='shopping_cart_link']") private WebElement cartbutton;
	@FindBy (xpath="//span[@class='title']") private WebElement cartPageTitle;
	@FindBy (xpath="//button[@name='checkout']") private WebElement checkoutButton;
	@FindBy (xpath="//span[@class='title']") private WebElement checkoutPageTitle;
	@FindBy (xpath="//button[@id='continue-shopping']") private WebElement continueShopping;
	
	
	
	public CartPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnAddtoCart() {
		cartbutton.click();
	}
	
	public boolean checkifAddCartDisplayed() {
		boolean b=cartPageTitle.isDisplayed();
		return b;
	}
	
	public void clickonCheckout() {
		checkoutButton.click();
	}
	
	public String verifyIfCheckoutpageDisplayed() {
		String title=checkoutPageTitle.getText();
		return title;
	}
	
	public void clickOnContinueShopping() {
		continueShopping.click();
	}
}
