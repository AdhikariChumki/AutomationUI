package stepDefinations;

import org.openqa.selenium.By;
import org.testng.Assert;

import configuration.Base;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import utility.ExcelDataUtility;
import utility.ExplicitWait;
import utility.JavaUtility;
import utility.TestUtil;

public class Privateclient_Creation extends Base{
 
	
	String excelpath = System.getProperty("user.dir") + prop.getProperty("ExcelfilePath");
	String projectpath = System.getProperty("user.dir");
	
	JavaUtility javaUtil = new JavaUtility();
	
	 private String clientFirstName;
	 private String clientSurname;
	
	
	@Given("^User Click Client Search Link$")
    public void user_click_client_search_link() throws Throwable {
         
		TestUtil.click(driver.findElement(By.xpath(prop.getProperty("clientsearch"))));
		
		
    }

	@Then("^Click on Add Client Button and Filled up all Private Client information$")
	public void click_on_Add_Client_Button_and_Filled_up_all_Private_Client_information() throws Throwable {
    	  
		ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("RareExplicitWaitTime")),
				By.xpath(prop.getProperty("htmlbodyloading")), driver);

		ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("RareExplicitWaitTime")),
				By.cssSelector(prop.getProperty("preloader")), driver);
		
		
		TestUtil.click(driver.findElement(By.xpath(prop.getProperty("AddClientBtn"))));
		logger.info("Click on Add client button");
		
		ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("RareExplicitWaitTime")),
				By.xpath(prop.getProperty("htmlbodyloading")), driver);
		
		// add title
		TestUtil.SelectByText(driver.findElement(By.cssSelector(prop.getProperty("Title"))), "Miss");
		logger.info("Select title");
			
		clientFirstName = "Automation";
		
		clientSurname = "Client"+javaUtil.currentdateTime();
		
		TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("Client1stName"))), clientFirstName);
		logger.info("Enter client First Name as : "+clientFirstName);
		Thread.sleep(6000);
		
		try {
			TestUtil.click(driver.findElement(By.xpath("//span[@id='divClose']"))); 
		  } catch (Exception e) {
		     System.out.println("This error we got when there are no similar client names ="+e);
		  }
		
		TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("ClientSurname"))), clientSurname);
		logger.info("Enter client Surname as : "+clientSurname);
		
		TestUtil.click(driver.findElement(By.xpath(prop.getProperty("DateOfBirth"))));
		
		
		Thread.sleep(6000);
		TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("DateOfBirth"))), "10121986");
		Thread.sleep(6000);
		logger.info("Enter client Date Of Birth");
		
		TestUtil.click(driver.findElement(By.xpath(prop.getProperty("Email"))));
		Thread.sleep(6000);
		
		
		TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("Email"))), "chumki.adhikari09@markit-systems.com");
		logger.info("Enter client Email");
		Thread.sleep(6000);
		
		TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("AlternetEmail"))), "chumki.adhikari@markit-systems.com");
		logger.info("Confirm Client Email ");
		
		Thread.sleep(9000);
		TestUtil.insert_image_doc(driver);
		
		
		 TestUtil.click(driver.findElement(By.xpath(prop.getProperty("saveTrinityClient")))); 
		 logger.info("Save The Client Details and client created");
		 
	     String expectedmsg = TestUtil.Gettext(driver.findElement(By.xpath(prop.getProperty("Alerttext"))));
	     String Actualmsg="Client information saved successfully";
	     
	      
	      String getNewClientName = TestUtil.Gettext(driver.findElement(By.xpath(prop.getProperty("getClientName"))));
	      
	      logger.info("Newly created client name is : "+getNewClientName);
	      
	      int row_run_result = ExcelDataUtility.excelTotalRowCount(excelpath,
					prop.getProperty("clientNames_Sheet"));
			logger.info("Total row present in clientNames sheet :"+row_run_result);
			
			ExcelDataUtility.writeToExcel(projectpath+"/TestData/Test_Data.xlsx", 
				"clientNames", getNewClientName, row_run_result+1,"Client_Names");
			
			logger.info("write the Client name into ExcelSheet sheet name : clientNames");
	      
	     // Assert.assertEquals(Actualmsg, expectedmsg);
	      TestUtil.insert_image_doc(driver);
		
		

		
    }

   

}
