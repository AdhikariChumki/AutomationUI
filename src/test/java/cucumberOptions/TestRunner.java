package cucumberOptions;

import java.io.File;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import utility.FileReaderManager;

@RunWith(Cucumber.class)

@CucumberOptions(features = "src/test/java/feature", 
					glue  = "stepDefinations", 
							
							tags= "@Home_Building_And_Content_Insurance_Product_Quote_Create",
					
					plugin = { "pretty", "html:target/HTMLReport", "json:target/cucumber-reports/cucumber.json", "com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html" }, 
					dryRun = false, 
					strict = false, 
					monochrome =true)

public class TestRunner {
	
	
	@AfterClass
	public static void writeExtentReport() {

		//Test Test test 
		Reporter.loadXMLConfig(new File(FileReaderManager.getInstance().getConfigReader().getReportConfigPath()));

	}
	// @AddPrivateClient, @Home_Building_And_Content_Insurance_Product_Quote_Create
}