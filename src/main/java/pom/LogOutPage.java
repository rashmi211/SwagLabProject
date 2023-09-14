package pom;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogOutPage {
	@FindBy (xpath="//div[@class='bm-burger-button']") private WebElement menu;
	@FindBy (xpath="//a[text()='Logout']")private WebElement logout;
	@FindBy (xpath="//input[@id='login-button']")private WebElement loginbutton;
	@FindBy (xpath="//a[@id='about_sidebar_link']")private WebElement  aboutButton;
	
	
	public LogOutPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	public void clickOnMenuButton() {
		menu.click();
	}
	public void clickOnLogOutButton(WebDriver driver) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofMillis(2000));
		wait.until(ExpectedConditions.visibilityOf(logout));
		logout.click();
	}
	
	public boolean verifyiflogindisplayed() {
		boolean login=loginbutton.isDisplayed();
		return login;
	}
	
	public void clickOnAboutButton(WebDriver driver) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofMillis(2000));
		wait.until(ExpectedConditions.visibilityOf(aboutButton));
		aboutButton.click();
	}
	
	
	public String verifyUserNavigateToAboutPage(WebDriver driver) {
		String title=driver.getTitle();
		return title;
	}

}
