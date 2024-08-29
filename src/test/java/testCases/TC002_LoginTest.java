package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	
	@Test(groups={"Sanity","Master"})
	public void verifyLogin() {
		logger.info("*****Starting TC002_LoginTest*****");
		try {
		//HomePage
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		//LoginPage
		LoginPage lp= new LoginPage(driver);
		lp.setEmail(prop.getProperty("email"));
		lp.setPassword(prop.getProperty("password"));
		lp.clickLogin();
		//MyAccountPage
		MyAccountPage myaccount = new MyAccountPage(driver);
		boolean targetPage =myaccount.isMyAccountPageExists();
		Assert.assertEquals(targetPage, true,"Login Failed");
		}
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("*****Finished TC002_LoginTest*****");
		
		MyAccountPage myaccount = new MyAccountPage(driver);
		myaccount.clickLogout();
	}

}
