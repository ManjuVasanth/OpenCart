package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);
		
	}
	@FindBy(name="email")
	WebElement txtEmailAddresss;
	
	@FindBy(name="password")
	WebElement txtPassword;

	@FindBy(xpath="//input[@value='Login']")
	WebElement btnLogin;
	
	public void setEmail(String email) {
		txtEmailAddresss.sendKeys(email);
	}
	
	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
		
	}
	
	public void clickLogin() {
		btnLogin.click();
		
	}
}
