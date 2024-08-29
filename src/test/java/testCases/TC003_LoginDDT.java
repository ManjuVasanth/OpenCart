package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/* 
Data is valid -> Login success ->test pass -> logout
Data is valid -> Login failed ->test fail

Data is invalid -> Login success ->test fail -> logout
Data is invalid -> Login failed ->test pass

 */
//dataProviderClass=DataProviders.class is used since DataProviders are in another package
@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="DataDriven")
public class TC003_LoginDDT extends BaseClass{

	public void verify_loginDDT(String email,String pwd, String expResult) {
		logger.info("*****Starting TC003_LoginDDT*****");
		try {
		//HomePage
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		//LoginPage
		LoginPage lp= new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		//MyAccountPage
		MyAccountPage myaccount = new MyAccountPage(driver);
		boolean targetPage =myaccount.isMyAccountPageExists();
		//targetPage true means login is successful
		
		/* 
		Data is valid -> Login success ->test pass -> logout
		Data is valid -> Login failed ->test fail  */
		
		if(expResult.equalsIgnoreCase("Valid")) {
			if(targetPage==true) {
				myaccount.clickLogout();
				Assert.assertTrue(true);
				
			}else {
				Assert.assertTrue(false);
			}
		}
		/* Data is invalid -> Login success ->test fail -> logout
			Data is invalid -> Login failed ->test pass */
		if(expResult.equalsIgnoreCase("Invalid")) {
			if(targetPage==true) {
				myaccount.clickLogout();
				Assert.assertTrue(false);
				
			}else {
				Assert.assertTrue(true);
			}
		}
		}catch(Exception e) {
			Assert.fail();
		}
		logger.info("*****Finished TC003_LoginDDT*****");
	}
}
