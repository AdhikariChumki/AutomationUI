package repeated_Steps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import configuration.Base;
import utility.ExcelDataUtility;
import utility.ExplicitWait;
import utility.JSLibrary;
import utility.TestUtil;

public class Policy_Bind_Steps extends Base {

	public static void Bind_Quote() throws Throwable {
		 String excelpath = System.getProperty("user.dir") + prop.getProperty("ExcelfilePath");
			ArrayList<HashMap<String, String>> data = ExcelDataUtility.storeExcelDataToHashMap(excelpath,
					prop.getProperty("AllQuoteNumbers_Sheet"));
			Iterator<HashMap<String, String>> i = data.iterator();
			while (i.hasNext()) {
				HashMap<String, String> dataset = i.next();

				
				
				if (dataset.get("Run_Status").equalsIgnoreCase("Yes")) {				
					logger.info("The 'Run Status' column is 'Yes'.");
					
					logger.info("Now click on Quote Records link");
					Thread.sleep(6000);
					TestUtil.click(driver.findElement(By.xpath(prop.getProperty("quote_link"))));
					Thread.sleep(6000);
					String Quote_Number = dataset.get("Quote_Numbers");
					logger.info("search quote number as : "+Quote_Number);
					TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("quoteName"))), Quote_Number);
					Thread.sleep(3000);
					TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("quoteName"))),Keys.ENTER);
				
					Thread.sleep(900);
					
					ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("RareExplicitWaitTime")),
							By.xpath(prop.getProperty("htmlbodyloading")), driver);
					
					logger.info("edit quote number ");
					TestUtil.click(driver.findElement(By.xpath(prop.getProperty("Editpolicy"))));
					Thread.sleep(900);
					
					ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("RareExplicitWaitTime")),
							By.xpath(prop.getProperty("PolicyBindButton")), driver);
					
					logger.info("Click on Bind Button ");
					TestUtil.click(driver.findElement(By.xpath(prop.getProperty("PolicyBindButton"))));
					Thread.sleep(900);
					
					ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("RareExplicitWaitTime")),
							By.xpath(prop.getProperty("htmlbodyloading")), driver);
					
					JSLibrary.ScrollintoView(driver, driver.findElement(By.xpath(prop.getProperty("confirmpaymentBtn"))));	
					
					//ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("confirmpaymentBtn")),
							//By.xpath(prop.getProperty("PolicyBindButton")), driver);
					Thread.sleep(500);
					logger.info("Click on confirm payment Button");
					TestUtil.click(driver.findElement(By.xpath(prop.getProperty("confirmpaymentBtn"))));
					
					ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("RareExplicitWaitTime")),
							By.xpath(prop.getProperty("htmlbodyloading")), driver);
					
					logger.info("Enter mail Id :chumki.adhikari@markit-systems.com");
					TestUtil.clear((driver.findElement(By.xpath(prop.getProperty("mailto_id")))));
					TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("mailto_id"))), "chumki.adhikari@markit-systems.com");
					Thread.sleep(600);
					
					JSLibrary.ScrollintoView(driver, driver.findElement(By.xpath(prop.getProperty("bind_sendmail"))));	
					logger.info("Send the mail and Bind The policy");
					TestUtil.click(driver.findElement(By.xpath(prop.getProperty("bind_sendmail"))));
					Thread.sleep(900);
					
					ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("RareExplicitWaitTime")),
							By.xpath(prop.getProperty("htmlbodyloading")), driver);
					
					//String policynumber = TestUtil.Gettext(driver.findElement(By.xpath(prop.getProperty("getPolicyNumber"))));
					//String policynumber=driver.findElement(By.xpath(prop.getProperty("getPolicyNumber"))).getAttribute("innerHTML");
					//ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("D&O_Premium_Override_Result_Sheet"), "PolicyNumber",policynumber, "Quote_Numbers", Quote_Number);
					
					//logger.info("Policy Number is : "+policynumber);
					
					TestUtil.insert_image_doc(driver);
//====================================================Update QuoteNumbers sheet===========================================================================================
					ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("AllQuoteNumbers_Sheet"), "Bind_Quote","Yes", "Quote_Numbers", Quote_Number);
					
					
					}
			}
	}



	}
