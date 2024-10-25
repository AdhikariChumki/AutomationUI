package stepDefinations;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import configuration.Base;
import cucumber.api.java.en.Then;
import utility.ExcelDataUtility;
import utility.ExplicitWait;
import utility.TestUtil;

public class Add_Client_Address_Steps extends Base{

	String excelpath = System.getProperty("user.dir") + prop.getProperty("ExcelfilePath");
	String projectpath = System.getProperty("user.dir");
	
	
	@Then("^Search the client and add address to the client$")
    public void search_the_client_and_add_address_to_the_client() throws Throwable {
		
				    ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("MediumExplicitWaitTime")),
						By.xpath(prop.getProperty("htmlbodyloading")), driver);

					ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("MediumExplicitWaitTime")),
							By.cssSelector(prop.getProperty("preloader")), driver);

					ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("MediumExplicitWaitTime")),
							By.xpath(prop.getProperty("client_loading_complete")), driver);
		
					String NameofTheClient = ExcelDataUtility.getCellData(excelpath, prop.getProperty("clientNames_Sheet"), 1, "Client_Names");
		
					logger.info("Search the Client as :"+NameofTheClient);
					
					TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("searchClientName"))), NameofTheClient);
					TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("searchClientName"))), Keys.ENTER);
					Thread.sleep(5000);
					TestUtil.insert_image_doc(driver);
					
					TestUtil.click(driver.findElement(By.xpath(prop.getProperty("EditBtn"))));
					Thread.sleep(9000);
					
					logger.info("Click on Address list to Add address ");
					//JSLibrary.ScrollintoView(driver, driver.findElement(By.xpath(prop.getProperty("AddressLst"))));
					Thread.sleep(9000);
					
					ExplicitWait.wait_for_element_clickable(Long.parseLong(prop.getProperty("MediumExplicitWaitTime")),
							By.xpath(prop.getProperty("AddressLst")), driver);

			    	TestUtil.click(driver.findElement(By.xpath(prop.getProperty("AddressLst"))));
			    	Thread.sleep(1000);
					
					logger.info("Click on Add New Address");
					ExplicitWait.wait_for_element_clickable(Long.parseLong(prop.getProperty("MediumExplicitWaitTime")),
							By.xpath(prop.getProperty("AddNewOffice")), driver);
			    	TestUtil.click(driver.findElement(By.xpath(prop.getProperty("AddNewOffice"))));
			    	Thread.sleep(1000);
			    	
			    	logger.info("Fill all required details from Excel");
			    	for(int row=1; row<=1; row++) {
			    		
			    		String Ofcname=ExcelDataUtility.getCellData(excelpath, prop.getProperty("ClientAdress_Sheet"), row, "Office_name");
				    	
				        String phNum=ExcelDataUtility.getCellData(excelpath, prop.getProperty("ClientAdress_Sheet"), row, "Phone_Number");
				        String faxNum=ExcelDataUtility.getCellData(excelpath, prop.getProperty("ClientAdress_Sheet"), row, "Fax _Number");
				        String headOf=ExcelDataUtility.getCellData(excelpath, prop.getProperty("ClientAdress_Sheet"), row, "Head_Office");
				        String actv=ExcelDataUtility.getCellData(excelpath, prop.getProperty("ClientAdress_Sheet"), row, "Active");
				        String Country=ExcelDataUtility.getCellData(excelpath, prop.getProperty("ClientAdress_Sheet"), row, "Country");
				        String Postcode=ExcelDataUtility.getCellData(excelpath, prop.getProperty("ClientAdress_Sheet"), row, "Postcode");
				        String HomeName=ExcelDataUtility.getCellData(excelpath, prop.getProperty("ClientAdress_Sheet"), row, "Home_Name");
				        
				        String Street=ExcelDataUtility.getCellData(excelpath, prop.getProperty("ClientAdress_Sheet"), row, "Street");
				       // String addressLine1=ExcelDataUtility.getCellData(excelpath, prop.getProperty("ClientAdress_Sheet"), row, "Address_line1");
				       // String addressLine2=ExcelDataUtility.getCellData(excelpath, prop.getProperty("ClientAdress_Sheet"), row, "Address_line2");
				        String City=ExcelDataUtility.getCellData(excelpath, prop.getProperty("ClientAdress_Sheet"), row, "City");
				        String County=ExcelDataUtility.getCellData(excelpath, prop.getProperty("ClientAdress_Sheet"), row, "County");
			    		
				        TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("textOfficeName"))), Ofcname);
				        TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("txtPhoneNo"))), phNum);
				        TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("txtFaxNo"))), faxNum);
				       
				        TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("ZipCode"))), Postcode);
				       // TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("AddressLine"))), addressLine1);
				        TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("HomeName"))), HomeName);
				        TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("Street"))), Street);
				        TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("City"))), City);
				        TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("City"))), County);
				        TestUtil.SelectByText(driver.findElement(By.xpath(prop.getProperty("NewPointcounty"))), County);
				        
			    		
			    	}
			    	
			    	TestUtil.click(driver.findElement(By.xpath(prop.getProperty("addressSaveBTN"))));
			    	
			    	String actualmsg="Address saved successfully.";
			    	String expectedmsg = TestUtil.Gettext(driver.findElement(By.xpath(prop.getProperty("uploadAddressSuccessfullmsg"))));
			    	
			        Assert.assertEquals(actualmsg, expectedmsg);
			        TestUtil.insert_image_doc(driver);
			    	
}
	
		}
	
	
