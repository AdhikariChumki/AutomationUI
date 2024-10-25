package repeated_Steps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.openqa.selenium.By;

import configuration.Base;
import cucumber.api.java.en.Then;
import utility.ExcelDataUtility;
import utility.ExplicitWait;
import utility.JSLibrary;
import utility.TestUtil;

public class EPL_Cover_Steps extends Base{
	
	
	public static void Add_EPL_Cover() throws Throwable {
	 String excelpath = System.getProperty("user.dir") + prop.getProperty("ExcelfilePath");
		ArrayList<HashMap<String, String>> data = ExcelDataUtility.storeExcelDataToHashMap(excelpath,
				prop.getProperty("EPL_Cover_Input_Data_Sheet"));
		Iterator<HashMap<String, String>> i = data.iterator();
		while (i.hasNext()) {
			HashMap<String, String> dataset = i.next();

			
				if (dataset.get("Run_Status").equalsIgnoreCase("Y")) {				
					logger.info("The 'Run Status' column is 'Yes'.");
					Thread.sleep(1200);
					TestUtil.click(driver.findElement(By.xpath(prop.getProperty("EPL_coverBtn"))));
					Thread.sleep(1200);
					
					ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("CommonExplicitWaitTime")),
							By.xpath(prop.getProperty("htmlbodyloading")), driver);
					
					JSLibrary.ScrollintoView(driver, driver.findElement(By.xpath(prop.getProperty("EPLRatingQues"))));
					String subLmt=dataset.get("Sub_Limit");
					logger.info("Select EPL sublimit :"+subLmt);
					TestUtil.SelectByText(driver.findElement(By.xpath(prop.getProperty("EPL_Sub-Limit"))), subLmt);
					Thread.sleep(900);
					
					String empNum=dataset.get("Employee_Numbers");
					logger.info("Select EPL Employee Numbers: "+empNum);
					TestUtil.SelectByText(driver.findElement(By.xpath(prop.getProperty("EPL_EmployeeNo"))), empNum);
					Thread.sleep(900);
					
					String excess=dataset.get("Excess");
					logger.info("Select EPL Excess :"+excess);
					TestUtil.SelectByText(driver.findElement(By.xpath(prop.getProperty("EPL_Excess"))), excess);
					Thread.sleep(900);
					
					
					TestUtil.click(driver.findElement(By.xpath(prop.getProperty("riskDetailsNextBtn"))));
					logger.info("Now click on next button to go Premium page");

//===============================================Premium Breakup==================================================================================
					
				/*	String EplSublmt = TestUtil.Gettext(driver.findElement(By.xpath(prop.getProperty("EplSublmt"))));
					logger.info("EPL Sub limit : "+EplSublmt);
						
					String EplEmpNum = TestUtil.Gettext(driver.findElement(By.xpath(prop.getProperty("EplEmpNum"))));
					logger.info("EPL Employee Numbers : "+EplEmpNum);
					
					String EplPremium1 = TestUtil.Gettext(driver.findElement(By.xpath(prop.getProperty("EplPremium1"))));
					logger.info("EPL Premium : "+EplPremium1);
					
					*/
					
					
					
//==========================================================================================================================================================				
					
					
					
					Thread.sleep(900);
					TestUtil.insert_image_doc(driver);
					logger.info("EPL cover added");
					
				}
		}
}



}
