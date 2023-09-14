package pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDisplayPage {
	@FindBy (xpath="//div[@class='inventory_item_description']")           private List<WebElement> products;
	@FindBy (xpath="//div[@class='inventory_item_name']")                  private List<WebElement> productsname;
	@FindBy (xpath="//div[text()='Sauce Labs Fleece Jacket']")             private WebElement validname;
	@FindBy (xpath="//button[@id='add-to-cart-sauce-labs-fleece-jacket']") private WebElement addToCart;
	@FindBy (xpath="//button[text()='Remove']")                            private WebElement removebutton;
	@FindBy (xpath="//button[@name='back-to-products']")                   private WebElement backButton;
	@FindBy (xpath="//span[text()='Products']")                            private WebElement productPage;
	
	
	public ProductDisplayPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	
	public boolean verifyListOfProductIsShownOnProductDeatilsPage() {
		int n=products.size();
		boolean result = false;
		if(n >1) {
			 result  =true;
		}
		return result;
	}
	
	public void clickonProduct(String name) {
		for(int i=0; i<productsname.size(); i++)
		{
		 WebElement e=productsname.get(i);
		 String prodname= e.getText();
		 if(name.equals(prodname)) {
			 e.click();
		 }}
	}

	public String matchProductName() {
		String actualname=validname.getText();
		return actualname;	
	}
	
	public void clickOnAddtoCart() {
		addToCart.click();
	}
	
	public boolean displayRemoveButton() {
	boolean b=	removebutton.isDisplayed();
	return b;
	}
	
	public void clickOnBackButton() {
		backButton.click();
	}
	
	public boolean verifyProductPageDisplayed() {
		boolean b=productPage.isDisplayed();
		return b;
	}
}	
	