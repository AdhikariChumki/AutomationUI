package repeated_Steps;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

import com.microsoft.edge.seleniumtools.EdgeOptions;

import configuration.Base;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Browser_Settings  extends Base {

	public static WebDriver selectBrowser(WebDriver driver, String browser, String browserSelectionType, String runmode,
			String os, int day) {

		if ((browserSelectionType.equalsIgnoreCase("Custom")) && (os.equalsIgnoreCase("Linux"))) {

			if (browser.equalsIgnoreCase("Chrome")) {
				driver = selectHeadlessChrome(driver);

			}
			if (browser.equalsIgnoreCase("Edge")) {
				driver = selectHeadlessEdge(driver);

			}

		}

		if ((browserSelectionType.equalsIgnoreCase("Default")) && (os.equalsIgnoreCase("Linux"))) {

			if ((day == 1) || (day == 3) || (day == 5)||(day == 7)) {
				driver = selectHeadlessChrome(driver);

			}
			if ((day == 2) || (day == 4) || (day == 6)) {
				//driver = selectHeadlessEdge(driver);
				driver = selectHeadlessChrome(driver);
			}

		}

		if ((!os.equalsIgnoreCase("Linux")) && (runmode.equalsIgnoreCase("headless"))) {

			if (browser.equalsIgnoreCase("Chrome")) {
				driver = selectHeadlessChrome(driver);

			}
			if (browser.equalsIgnoreCase("Edge")) {
				driver = selectHeadlessEdge(driver);

			}

		}

		if ((!os.equalsIgnoreCase("Linux")) && (runmode.equalsIgnoreCase("normal"))) {

			if (browser.equalsIgnoreCase("Chrome")) {
				driver = selectChrome(driver);
			//	driver = selectHeadlessChrome(driver);
			}
			if (browser.equalsIgnoreCase("Edge")) {
				driver = selectEdge(driver);

			}

		}

		return driver;

	}
/*
	public static WebDriver selectHeadlessChrome(WebDriver driver) {

		WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.silentOutput", "true");
		ChromeOptions options = new ChromeOptions();
		// For Head Less See Below Three Line
		options.addArguments("headless");
		options.addArguments("window-size=1200x600");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--no-sandbox");
		driver = new ChromeDriver(options);
		return driver;

	}
*/
	public static WebDriver selectHeadlessChrome(WebDriver driver) 
	{
		WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.silentOutput", "true");
	    //The below line for set the download directory ////////////////////// 		
		Map<String, Object> prefs = new HashMap<String, Object>();
	    prefs.put("download.default_directory",System.getProperty("user.dir") + File.separator + "Reports" + File.separator + "CurrentReport"); 
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		// For Head Less See Below Three Line
		options.addArguments("headless");
		options.addArguments("window-size=1200x600");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--no-sandbox");
		driver = new ChromeDriver(options);
		return driver;

	}
	
	public static WebDriver selectChrome(WebDriver driver) {
		WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.silentOutput", "true");

		/////The below line for set the download directory ////////////////////// 		
		Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("download.default_directory",System.getProperty("user.dir") + File.separator + "BdxReport" + File.separator + "CurrentBDXReport");      
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options);
		/////////////////////////////////////////////////////////////////
		
//		driver = new ChromeDriver();
		return driver;

	}

	public static WebDriver selectHeadlessEdge(WebDriver driver) {

		WebDriverManager.edgedriver().setup();
		EdgeOptions options = new EdgeOptions();
		options.addArguments("headless");
		options.addArguments("disable-gpu");
		options.addArguments("window-size=1200x600");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--no-sandbox");
		driver = new EdgeDriver(options);
		return driver;

	}

	public static WebDriver selectEdge(WebDriver driver) {

		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		return driver;

	}

}
