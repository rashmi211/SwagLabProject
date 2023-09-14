package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	  @FindBy (xpath="//input[@placeholder='Username']") private WebElement username;
	  @FindBy (xpath="//input[@placeholder='Password']") private WebElement password;
	  @FindBy (xpath="//input[@id='login-button']")      private WebElement loginButton;
	  
	  public LoginPage(WebDriver driver) {
		  PageFactory.initElements(driver, this);
	  }
	  
	  public void enterUsername(String userid) {
		  username.sendKeys(userid);
	  }
	  public void enterPassword(String pass) {
		  password.sendKeys(pass);
	  }
	  public void clickOnLoginButton() {
		 loginButton.click();
	  }
	  
	  public void clearUserNameField() {
		  username.clear();
	  }
	  
	  public void clearPasswordField() {
		  password.clear();
	  }
	  	
	  public boolean verifyUserNameGetRemoved() {
		 String user=username.getText();
		 boolean b=user.isEmpty();
		 return b;
	  }
	  
	  public boolean verifyPasswordGetRemoved() {
			 String pass=password.getText();
			 boolean b=pass.isEmpty();
			 return b;
	 }
	


}

