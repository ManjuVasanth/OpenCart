package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String reportName;

	public void onStart(ITestContext testContext) {

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
		reportName = "Test-Report-" + timeStamp + ".html";

	//	sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/" + reportName);// specify
																												// location
																												// of
																												// the
																												// report
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+ reportName);
		sparkReporter.config().setDocumentTitle("OpenCart Automation Report"); // Tile of report
		sparkReporter.config().setReportName("OpenCart Functional Test Automation Report"); // name of the report
		sparkReporter.config().setTheme(Theme.DARK);

		/*
		 * try { sparkReporter.loadXMLConfig(System.getProperty("user.dir")+
		 * "/extent-config.xml"); } catch (IOException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 */

		extent = new ExtentReports();

		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "OpenCart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("SubModule", "Customers");
	//	extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("UserName", "Manju Vasanth");
		extent.setSystemInfo("Environment", "QA");

		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName()); // create new entry in the report
		test.assignCategory(result.getMethod().getGroups());// to display groups in the report
		test.log(Status.PASS, result.getName() + " got successfully executed");
	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getClass().getName()); // create new entry in th report
		test.assignCategory(result.getMethod().getGroups());// to display groups in the report
		test.log(Status.FAIL, result.getName() + " got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());

		try {
			String imagePath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imagePath);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());// to display groups in the report
		test.log(Status.SKIP, result.getName() + " got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext testContext) {
		extent.flush();
		
		  // As soon as the report got generated, it will automatically open the report
		  String pathOfExtentReport =
		  System.getProperty("user.dir")+"\\reports\\"+reportName; File extentReport =
		  new File(pathOfExtentReport); try {
		  Desktop.getDesktop().browse(extentReport.toURI()); }catch(IOException e){
		  
		  e.printStackTrace();
		  
		  }
		 
		/*
		 * if (!extentReport.exists()) {
		 * System.out.println("The report file does not exist: " + pathOfExtentReport);
		 * return; } try { if (Desktop.isDesktopSupported() &&
		 * Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
		 * Desktop.getDesktop().browse(extentReport.toURI()); } else {
		 * System.out.println("Desktop operations are not supported on this platform.");
		 * } }catch(IOException e){ System.err.println("Error opening the report: " +
		 * e.getMessage()); e.printStackTrace(); }*?
		 * 
		 * } /* try{ URL url = new
		 * URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+reportName); //
		 * Create the email message ImageHtmlEmail email = new ImageHtmlEmail();
		 * email.setDataSourceResolver(new DataSourceUrlResolver(url));
		 * email.setHostName("smtp.googlemail.com"); email.setSmtpPort(465);
		 * email.setAuthenticator(new
		 * DefaultAuthenticator("xyz@gmail.com","password"));
		 * email.setSSLOnConnect(true);
		 * email.setFrom("xyz@gmail.com");//sender
		 * email.setSubject("Test Results");
		 * email.setMsg("Please find the attached Report....");
		 * email.addTo("ai@gmail.com");//Receiver
		 * email.attach(url,"extent report","please check the report...");
		 * email.send();//send the email } catch(Exception e){ e.printStackTrace();}
		 * 
		 * 
		 * }
		 */

	}
}
