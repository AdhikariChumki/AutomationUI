package configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import repeated_Steps.Browser_Settings;
import utility.JavaUtility;
import utility.TestUtil;

public class Base {
	
	public static WebDriver driver;
	public static Properties prop;
	public static SoftAssert sa = new SoftAssert();
	
	public static Logger logger;

	public static WebDriver getDriver() throws IOException {
		
		logger = Logger.getLogger("Resilience");
		PropertyConfigurator.configure("log4j.properties");
		
		prop = new Properties();
		File f = new File(System.getProperty("user.dir") + "/src/test/java/configuration/global.properties");
		FileInputStream fis = new FileInputStream(f);
		prop.load(fis);

		String os = System.getProperty("os.name");
		String browser = prop.getProperty("Browser");
		String browserSelectionType = prop.getProperty("BrowserSelectionType");
		String runmode = prop.getProperty("Run_Mode");
		int day = JavaUtility.current_day_in_week();
		

		driver = Browser_Settings.selectBrowser(driver, browser, browserSelectionType, runmode, os, day);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

		return driver;
	}

}
