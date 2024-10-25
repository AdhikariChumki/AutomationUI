package stepDefinations;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.cucumber.listener.Reporter;

import configuration.Base;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import repeated_Steps.Repeated_Steps;
import utility.ExcelDataUtility;
import utility.TestUtil;

public class Hooks extends Base {

	static int execution = 0;

	@Before(order = 1)
	public void beforeScenarioStart(Scenario scenario) throws Exception {

		System.out.println("-----------------Start of '"+scenario.getName()+"' Scenario-----------------");
		
		

		driver = Base.getDriver();
		driver.get(prop.getProperty("Dev"));
		driver.get(prop.getProperty("Trinity_Url"));
		Thread.sleep(1000);
		
		if (Hooks.execution == 0) {
			FileUtils.cleanDirectory(new File(System.getProperty("user.dir") + prop.getProperty("EvidenceDirectory")));
			FileUtils.cleanDirectory(new File(System.getProperty("user.dir") + prop.getProperty("ScreenshotDirectory")));
			
		//	FileUtils.cleanDirectory(new File(System.getProperty("user.dir")+prop.getProperty("CurrentBDXReport_Repository")));
			
			String excelpath = System.getProperty("user.dir") + prop.getProperty("ExcelfilePath");
		//	ExcelDataUtility.deleteFullDataExceptHeading(excelpath, prop.getProperty("D&O_Premium_Override_Result_Sheet"));
		//	ExcelDataUtility.deleteFullDataExceptHeading(excelpath, prop.getProperty("D&O_Product_Run_Result_Sheet"));
		//	ExcelDataUtility.deleteFullDataExceptHeading(excelpath, prop.getProperty("AllQuoteNumbers_Sheet"));
		//	ExcelDataUtility.deleteFullDataExceptHeading(excelpath, prop.getProperty("D&O_Product_CommissionChange_sheet"));
		//	ExcelDataUtility.deleteFullDataExceptHeading(excelpath, prop.getProperty("PI_Product_Run_Result_Sheet"));
			
			
			
			//String inputOutputexcelpath = System.getProperty("user.dir") + prop.getProperty("InputOutput_ExcelfilePath");
			//ExcelDataUtility.deleteFullDataExceptHeading(inputOutputexcelpath, prop.getProperty("sheet_BDX_Report_Summary"));
			
		//	This code is added to update the test overview report test execution status and test result status to rest as not executed
		
			int record_count = ExcelDataUtility.excelTotalRowCount(excelpath, prop.getProperty("sheet_Test_Overview_Report"));
			for (int i = 1; i <=record_count; i++)
			{
				ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Not Executed", i, "Test_Run_Status");
				ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "NA", i, "Test_Status");				
			}
			
		}
		Hooks.execution++;
		TestUtil.Append_to_doc("System is starting executing the Scenario: \"" + scenario.getName() + "\"");
		TestUtil.Append_to_doc("---------------------------------------");

	}

	@After(order = 1)
	public void Take_Screenshot_afterScenarioFinish(Scenario scenario) throws Exception {
		if (scenario.isFailed()) {
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			try {
				// This takes a screenshot from the driver at save it to the specified location
				final byte[] sourcePathbyte = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

				// Building up the destination path for the screenshot to save
				// Also make sure to create a folder 'screenshots' with in the cucumber-report
				// folder
				File destinationPath = new File(System.getProperty("user.dir") + prop.getProperty("ScreenshotDirectory")
						+ "/" + screenshotName + ".png");

				// Copy taken screenshot from source location to destination location
				FileUtils.copyFile(sourcePath, destinationPath);

				// This attach the specified screenshot to the test
				Reporter.addScreenCaptureFromPath(destinationPath.toString());
				scenario.embed(sourcePathbyte, "image/png");

				TestUtil.Append_to_doc("Scenario: \"" + scenario.getName()
						+ "\" got \"FAILED\", Screenshot for the failed scenario displayed below ");
				TestUtil.insert_image_doc(driver);

			} catch (IOException e) {
			}
		}
	}

	@After(order = 0)
	public void afterScenarioFinish(Scenario scenario) throws Exception {

		
		TestUtil.Append_to_doc("Test Status for the Scenario:  \"" + scenario.getName() + "\" is: \""
				+ scenario.getStatus().toUpperCase() + "\"");
		TestUtil.Append_to_doc("---------------------------------------");
		
		Repeated_Steps.update_overall_test_scenario_status(scenario.getName(), scenario.getStatus());
		
// The below line is hardcoded		
		/*String excelpath = System.getProperty("user.dir") + prop.getProperty("ExcelfilePath");
		ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Test_Run_Status", "Abandoned", "Test_Scenario", "Terrorism product quote and Buy Journey Testing");
		ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Test_Status", "Not Run", "Test_Scenario", "Terrorism product quote and Buy Journey Testing");
		
		ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Test_Run_Status", "Abandoned", "Test_Scenario", "Perform T2 NB Adjustment on Policies");
		ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Test_Status", "Not Run", "Test_Scenario", "Perform T2 NB Adjustment on Policies");
		
		ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Test_Run_Status", "Abandoned", "Test_Scenario", "Perform T2 MTA on Policies");
		ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Test_Status", "Not Run", "Test_Scenario", "Perform T2 MTA on Policies");
		
		ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Test_Run_Status", "Abandoned", "Test_Scenario", "Perform T2 Cancellation of Policies");
		ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Test_Status", "Not Run", "Test_Scenario", "Perform T2 Cancellation of Policies");
		*/

	
		//driver.close();
		Thread.sleep(5000);
		//driver.quit(); 
		logger.info("Now bowser is closed.");

		System.out.println("-----------------End of '"+scenario.getName()+"' Scenario-----------------");

	}
}
