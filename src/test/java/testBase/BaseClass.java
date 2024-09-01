package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClass {
	public static WebDriver driver;
	public Logger logger;
	public Properties prop;

	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setUp(@Optional("defaultOS")String os,@Optional("chrome")String br) throws MalformedURLException {
		//Loading config.properties file
		//FileReader or FileInputStream can be used to read the file
		try {
			FileReader file = new FileReader("./src//test//resources//config.properties");
			prop = new Properties();
			prop.load(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger = LogManager.getLogger(this.getClass());  //log4j2

		if(prop.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();

			//os
			if(os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("linux")) {
				capabilities.setPlatform(Platform.LINUX);
			}
			else if(os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			}
			else {
				System.out.println("No matching os");
				return;
			}	


			//browser
			switch(br.toLowerCase()) {


			case "chrome" : capabilities.setBrowserName("chrome");
			break;
			case "edge" : capabilities.setBrowserName("MicrosoftEdge");
			break;
			case "firefox" : capabilities.setBrowserName("firefox");
			break;
			default: System.out.println("No matching browser");
			return;
			}
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
		}
		if(prop.getProperty("execution_env").equalsIgnoreCase("local")) {


			switch(br.toLowerCase()) {
			case "chrome": driver = new ChromeDriver();
			break;
			case "edge": driver = new EdgeDriver();
			break;
			case "firefox": driver = new FirefoxDriver();
			break;
			//If browser is invalid we no need to execute all test cases
			default:System.out.print("Invalid browser name...");
			return;
			}

			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			//driver.get("http://localhost/opencat/upload/index.php");
			//driver.get("https://tutorialsninja.com/demo/");
			driver.get(prop.getProperty("appURL"));//reading url from properties file
			driver.manage().window().maximize();
			}
	}
		@AfterClass(groups= {"Sanity","Regression","Master"})
		public void tearDown() {
			driver.quit();
		}

		public String randomString() {
			String generatedString = RandomStringUtils.randomAlphabetic(5);
			return generatedString;
		}

		public String randomNumber() {
			String generatedNumber = RandomStringUtils.randomNumeric(5);
			return generatedNumber;
		}

		public String randomAlphaNumeric() {
			String generatedString = RandomStringUtils.randomAlphabetic(3);
			String generatedNumber = RandomStringUtils.randomNumeric(5);
			// String generatedAlphaNumeric = RandomStringUtils.randomAlphanumeric(8);
			return (generatedString + "&" + generatedNumber);
		}

		public String captureScreen(String tname) throws IOException {
			String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());// time stamp

			TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
			File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

			String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
			File targetFile = new File(targetFilePath);
			sourceFile.renameTo(targetFile);
			return targetFilePath;

		}
	}
