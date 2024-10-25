package stepDefinations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import configuration.Base;
import cucumber.api.java.en.Then;
import repeated_Steps.Repeated_Steps;
import utility.ExcelDataUtility;
import utility.ExplicitWait;
import utility.TestUtil;

public class Home_Building_And_Content_Insurance_Product_Quote_Creation_Steps extends Base{
	
	@Then("^Search Client and create Quote,Get All Data from the excel$")
	public void search_Client_and_create_Quote_Get_All_Data_from_the_excel() throws Throwable {
		
		 String excelpath = System.getProperty("user.dir") + prop.getProperty("ExcelfilePath");
			ArrayList<HashMap<String, String>> data = ExcelDataUtility.storeExcelDataToHashMap(excelpath,
					prop.getProperty("Home&Content_Product_Sheet"));
			Iterator<HashMap<String, String>> i = data.iterator();
			while (i.hasNext()) {
				HashMap<String, String> dataset = i.next();

				
					if (dataset.get("Run_Status").equalsIgnoreCase("Yes")) {				
						logger.info("The 'Run Status' column is 'Yes'.");
						
						logger.info("Now click on the client search");
						TestUtil.click(driver.findElement(By.xpath(prop.getProperty("clientsearch"))));
						Thread.sleep(900);
						logger.info("Now search the "+dataset.get("Client")+" client");
						Repeated_Steps.client_search(dataset.get("Client"));
						logger.info("Now click on the Start Quote button");
						
						TestUtil.click(driver.findElement(By.xpath(prop.getProperty("startquote1"))));

						ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("CommonExplicitWaitTime")),
								By.xpath(prop.getProperty("htmlbodyloading")), driver);
						
						
						String Policy_Start_Date = Repeated_Steps.fillup_generalinfo1(dataset.get("Product_Name"), 
								dataset.get("Policy_Term"),dataset.get("Policy_Start_Date"));
						

						Thread.sleep(900);
						TestUtil.click(driver.findElement(By.xpath(prop.getProperty("NextBtn"))));
						logger.info("Click on the 'Next' button on General Information' page.");
						
						Repeated_Steps.handlePagePopup();
						ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("CommonExplicitWaitTime")), By.cssSelector(prop.getProperty("preloader")), driver);
						Thread.sleep(900);
						ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("CommonExplicitWaitTime")), By.xpath(prop.getProperty("htmlbodyloading")), driver);
						//==============if 1st policy enter phone number========================================
						
						
						//==============================================RISK PAGE DETAILS==============================================================
						
						//UK resident?
						if (dataset.get("UK_resident").equalsIgnoreCase("Yes")) {
							
							TestUtil.click(driver.findElement(By.xpath(prop.getProperty("ukResidentYes"))));
							logger.info("UK resident? - Yes");
						}
						
						else {
							
							
							TestUtil.click(driver.findElement(By.xpath(prop.getProperty("ukResidentNo"))));
							logger.info("UK resident? - No");
						}
						Thread.sleep(500);
						
						//Does any resident have any convictions other than motor related?
						
						if (dataset.get("Motor_related").equalsIgnoreCase("No")) {
							
							TestUtil.click(driver.findElement(By.xpath(prop.getProperty("motor_relatedNo"))));
							Thread.sleep(500);
							logger.info("Does any resident have any convictions other than motor related? - No");
						}
						
						
						else {
							TestUtil.click(driver.findElement(By.xpath(prop.getProperty("motor_relatedYes"))));
							Thread.sleep(500);
							logger.info("Does any resident have any convictions other than motor related? - Yes");
						}
						
						// Has any resident had insurance declined, cancelled or special terms imposed?
						
						if (dataset.get("Insurance_declined").equalsIgnoreCase("No")) {
							
							TestUtil.click(driver.findElement(By.xpath(prop.getProperty("Insurance_declinedNo"))));
							Thread.sleep(500);
							logger.info("Has any resident had insurance declined, cancelled or special terms imposed? - No");
						}
						
						
						else {
							TestUtil.click(driver.findElement(By.xpath(prop.getProperty("Insurance_declinedYes"))));
							Thread.sleep(500);
							logger.info("Has any resident had insurance declined, cancelled or special terms imposed? - Yes");
						}
							
						
						//Has any resident been declared bankrupt or had any unsatisfied CCJs? 
						
						if (dataset.get("Unsatisfied_CCJs").equalsIgnoreCase("No")) {
							
							TestUtil.click(driver.findElement(By.xpath(prop.getProperty("unsatisfied_CCJsNo"))));
							Thread.sleep(500);
							logger.info ("Has any resident been declared bankrupt or had any unsatisfied CCJs? - No");
						}
						
						
						else {
							TestUtil.click(driver.findElement(By.xpath(prop.getProperty("unsatisfied_CCJsYes"))));
							Thread.sleep(500);
							logger.info ("Has any resident been declared bankrupt or had any unsatisfied CCJs? - Yes");
						}
						
						//Employment Status
						String empStatus = dataset.get("Employment_Status");
						TestUtil.click(driver.findElement(By.xpath(prop.getProperty("Employment_Status"))));
						Thread.sleep(300);
						TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("empTextBox"))), empStatus);
						Thread.sleep(500);
						TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("empTextBox"))),Keys.ENTER );
						logger.info("Select Employment Status as - " +empStatus);
						
						Thread.sleep(500);
							
						//Occupation 
							
						String occupation=dataset.get("OccupationType");
						TestUtil.click(driver.findElement(By.xpath(prop.getProperty("Occupation"))));
						Thread.sleep(300);
						TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("empTextBox"))), occupation);
						Thread.sleep(500);
						TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("empTextBox"))),Keys.ENTER );
						logger.info("Select Occupation as - " +occupation);
						
						Thread.sleep(500);
							
						//Employers Business
						
						String empbusiness=dataset.get("Employers_Business");
						TestUtil.click(driver.findElement(By.xpath(prop.getProperty("Employers_Business"))));
						Thread.sleep(300);
						TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("empTextBox"))), empbusiness);
						Thread.sleep(500);
						TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("empTextBox"))),Keys.ENTER );
						logger.info("Select Employers Business as - " +empbusiness);
							
						//Type of property
						
						String propertyType = dataset.get("Type_of_property");
						TestUtil.click(driver.findElement(By.xpath(prop.getProperty("property"))));
						TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("empTextBox"))), propertyType);
						Thread.sleep(500);
						TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("empTextBox"))),Keys.ENTER );
						logger.info("Select Type of property as - " +propertyType);
						
						
						//Date Built
						
						String datebuilt = dataset.get("Date_Built");
						TestUtil.click(driver.findElement(By.xpath(prop.getProperty("dateBuilt"))));
						TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("empTextBox"))), datebuilt);
						Thread.sleep(500);
						TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("empTextBox"))),Keys.ENTER );
						logger.info("Select Date Built as - " +datebuilt);
						
						//Are the walls made only of Brick, Stone or Concrete?
						if (dataset.get("Stone_or_Concrete").equalsIgnoreCase("Yes")) {
							String stoneCon = dataset.get("Stone_or_Concrete");
									
							TestUtil.SelectByText(driver.findElement(By.xpath(prop.getProperty("StoneOrConcrete"))), stoneCon);
							Thread.sleep(500);
							logger.info("Are the walls made only of Brick, Stone or Concrete? - " +stoneCon);
						}
						else {
							
							TestUtil.SelectByText(driver.findElement(By.xpath(prop.getProperty("StoneOrConcrete"))), "No");
							
							Thread.sleep(500);
							logger.info("Are the walls made only of Brick, Stone or Concrete? - No ");
						}
						
					
						//Is the roof only made of slate or tile? *
					
						if (dataset.get("Slate_or_tile").equalsIgnoreCase("Yes")) {
							String slateorTile = dataset.get("Slate_or_tile");
						TestUtil.SelectByText(driver.findElement(By.xpath(prop.getProperty("slateOrtile"))), slateorTile);
						
						logger.info("Is the roof only made of slate or tile?? - "+slateorTile);
						}
						

						else {
							String RooftCons = dataset.get("Roof_construction");
							String slateorTileNo = "No";
							
							TestUtil.SelectByText(driver.findElement(By.xpath(prop.getProperty("slateOrtile"))), slateorTileNo);
							Thread.sleep(500);
							logger.info("Is the roof only made of slate or tile?? - "+slateorTileNo);
							TestUtil.SelectByText(driver.findElement(By.xpath(prop.getProperty("Roof_const"))), RooftCons);
							logger.info("Roof construction - "+RooftCons);
						}
						Thread.sleep(500);
						//Does the property have a flat roof?
						if (dataset.get("Flat_roof").equalsIgnoreCase("No")) {
							
							TestUtil.click(driver.findElement(By.xpath(prop.getProperty("flatRoofNo"))));
							logger.info("Does the property have a flat roof? - No");
						}
						else {
							String WhatflatRooft = dataset.get("What%");
							TestUtil.click(driver.findElement(By.xpath(prop.getProperty("flatroofYes"))));
							Thread.sleep(500);
							logger.info("Does the property have a flat roof? - Yes");
							TestUtil.SelectByText(driver.findElement(By.xpath(prop.getProperty("flatRoofWhat%"))), WhatflatRooft);
							logger.info("What % ? - "+WhatflatRooft);
							
						}
						
						//Any building work in progress?
						if (dataset.get("Building work").equalsIgnoreCase("No")) {
							TestUtil.click(driver.findElement(By.xpath(prop.getProperty("buildingworkNo"))));
							logger.info("Any building work in progress? - No");
						}
						else {
							
							TestUtil.click(driver.findElement(By.xpath(prop.getProperty("buildingworkYes"))));
							Thread.sleep(300);
							logger.info("Any building work in progress? - Yes");
							String BuildingWvalue = dataset.get("Building_work_value");
							TestUtil.SelectByText(driver.findElement(By.xpath(prop.getProperty("Building_workvalue"))), BuildingWvalue);
							logger.info("Building work value - "+BuildingWvalue);
						}
						
						//Is this a listed building?
						String Lbuilding = dataset.get("Listed_building");
						TestUtil.SelectByText(driver.findElement(By.xpath(prop.getProperty("listedbuilding"))), Lbuilding);
						logger.info("Is this a listed building? - "+Lbuilding);
						
						//Number of Bedrooms 
						String NoOfBedroom = dataset.get("No_of_Bedrooms");
						TestUtil.SelectByText(driver.findElement(By.xpath(prop.getProperty("Bedrooms"))), NoOfBedroom);
						logger.info("Number of Bedrooms  - "+NoOfBedroom);
						
						//Number of bathrooms/ensuites
						String NoOfbathroom = dataset.get("No_Of_Bathrooms");
						TestUtil.SelectByText(driver.findElement(By.xpath(prop.getProperty("Bathroom"))), NoOfbathroom);
						logger.info("Number of bathrooms/ensuites  - "+NoOfbathroom);
						
						//Is the property used for business?
						if (dataset.get("property_used_for_business").equalsIgnoreCase("No")) {
							TestUtil.click(driver.findElement(By.xpath(prop.getProperty("usedForBusinessNo"))));
							logger.info("Is the property used for business? - No");
						}
						else {
							TestUtil.click(driver.findElement(By.xpath(prop.getProperty("usedForBusinessYes"))));
							logger.info("Is the property used for business? - Yes");
							//Is the property used for AirBnB?
							TestUtil.click(driver.findElement(By.xpath(prop.getProperty("AirBnBnO"))));
							logger.info("Is the property used for AirBnB? - No");
							
							//In what capacity is the property used as AirBnB? *
						}
						//Is the property shared by non-family members?
						if (dataset.get("Shared_by_non-family_members").equalsIgnoreCase("No")) {
							TestUtil.click(driver.findElement(By.xpath(prop.getProperty("non-familymembersNo"))));
							logger.info("Is the property shared by non-family members? - No");
						}
						else {
							TestUtil.click(driver.findElement(By.xpath(prop.getProperty("non-familymembersYes"))));
							logger.info("Are the non-family members lodgers? - Yes");
							
							//Question missing here add later
						}
						
						//On what basis is the property left unoccupied? 
						if (dataset.get("left_unoccupied").equalsIgnoreCase("Occupied")) {
						String leftunoccupied = dataset.get("left_unoccupied");
						TestUtil.SelectByText(driver.findElement(By.xpath(prop.getProperty("leftunoccupied"))), leftunoccupied);
						logger.info("On what basis is the property left unoccupied - "+leftunoccupied);
						}
						
						//Is the building in a good state of repair?
						
						TestUtil.click(driver.findElement(By.xpath(prop.getProperty("goodstateofrepairYes"))));
						logger.info("Is the building in a good state of repair? - Yes");
						
						// Is the building free from flooding? 
						
						TestUtil.click(driver.findElement(By.xpath(prop.getProperty("from_floodingYes"))));
						logger.info("Is the building free from flooding? - Yes");
						
						//Is the building free from subsidence, heave, landslip, or past or present subsidence monitoring?
						
						TestUtil.click(driver.findElement(By.xpath(prop.getProperty("subsidence_monitoringYes"))));
						logger.info("Is the building free from subsidence, heave, landslip, or past or present subsidence monitoring? - Yes");
						
						//Do you own or rent your property?
						
						TestUtil.SelectByText(driver.findElement(By.xpath(prop.getProperty("rentOrOwn"))), "Owned (mortgaged or owned outright)");
						logger.info("Do you own or rent your property? - Owned (mortgaged or owned outright)");
						
						//Have you had any claims in the last 3 years?
						String ClaimYears = dataset.get("Claim_free_years");
						TestUtil.SelectByText(driver.findElement(By.xpath(prop.getProperty("Claimfreeyears"))), ClaimYears);
						logger.info("Have you had any claims in the last 3 years?? - Owned (mortgaged or owned outright)");
						
						//Buildings Insurance required?
						if (dataset.get("Buildings_Insurance").equalsIgnoreCase("Yes")) {
							TestUtil.click(driver.findElement(By.xpath(prop.getProperty("BuildingInsuranceYes"))));
							Thread.sleep(300);
							logger.info("Buildings Insurance required?- Yes");
						}
						//Accidental Building Damage Included
						TestUtil.click(driver.findElement(By.xpath(prop.getProperty("BuildingDamageYes"))));
						logger.info("Accidental Building Damage Included - Yes");
						
						//Buildings Voluntary Excess
						String Building_Voluntary = dataset.get("Building_Voluntary_Excess");
						TestUtil.SelectByText(driver.findElement(By.xpath(prop.getProperty("BuildingsVoluntary"))), Building_Voluntary);
						logger.info("Buildings Voluntary Excess - "+Building_Voluntary);
						
						//Contents Insurance required?
						if (dataset.get("Content_Insurance").equalsIgnoreCase("Yes")) {
							TestUtil.click(driver.findElement(By.xpath(prop.getProperty("ContentsInsuranceYes"))));
							Thread.sleep(300);
							logger.info("Contents Insurance required?- Yes");
						}
						
						//Contents Accidental Damage 
						
						TestUtil.click(driver.findElement(By.xpath(prop.getProperty("ContentsAccidentalYes"))));
						logger.info("Contents Accidental Damage ?- Yes");
						
						//Contents Voluntary Excess
						String ContentsVolExcess = dataset.get("Contents_Voluntary Excess");
						TestUtil.SelectByText(driver.findElement(By.xpath(prop.getProperty("Contents_Voluntary"))), ContentsVolExcess);
						logger.info("Select Contents Voluntary Excess as - " +ContentsVolExcess);
						
						//Are door and window locks fitted? 
						if (dataset.get("Window_locks_fitted").equalsIgnoreCase("Yes")) {
							TestUtil.click(driver.findElement(By.xpath(prop.getProperty("windowlocksYes"))));
							logger.info("Are door and window locks fitted? - Yes");
							Thread.sleep(300);
							//Which type of lock do you have on your main door?
							String locktype = dataset.get("Type_of_lock");
							TestUtil.SelectByText(driver.findElement(By.xpath(prop.getProperty("mainDoorlockType"))), locktype);
							logger.info("Which type of lock do you have on your main door? - "+locktype);
						}
						else {
							TestUtil.click(driver.findElement(By.xpath(prop.getProperty("windowlocksNo"))));
							logger.info("Are door and window locks fitted? - No");
						}
						
						//Do you have a Burglar Alarm?
						String burglarAlarm = dataset.get("Burglar_Alarm");
						TestUtil.SelectByText(driver.findElement(By.xpath(prop.getProperty("BurglarAlarm"))), burglarAlarm);
						logger.info("Do you have a Burglar Alarm? - "+burglarAlarm);
						
						//======================================================================================================================
						
						//================Personal Belongings, Military Uniform, Equipment and Personally Purchased Kit – worldwide cover=======================
						
						
						//Would you like Worldwide Personal Belongings cover? 
						
						if (dataset.get("Window_locks_fitted").equalsIgnoreCase("Yes")) {
							TestUtil.click(driver.findElement(By.xpath(prop.getProperty("PersonalPossessionsYes"))));
							logger.info("Would you like Worldwide Personal Belongings cover? - Yes");
							TestUtil.click(driver.findElement(By.xpath(prop.getProperty("Unspecified_ItemCov"))));
							String UnspecifiedCover = dataset.get("Unspecified_Item_Cover");
							TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("Unspecified_ItemCov"))), UnspecifiedCover);
							logger.info("Additional Unspecified Item Cover -"+UnspecifiedCover);
							
							String SpecifiedItem1 = dataset.get("Specified_Item1");
							TestUtil.click(driver.findElement(By.xpath(prop.getProperty("Unspecified_ItemDropdown"))));
							logger.info("click on Specified Items Dropdown");
							TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("empTextBox"))), SpecifiedItem1);
							Thread.sleep(500);
							logger.info("Search Item name as - "+SpecifiedItem1);
							TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("empTextBox"))),Keys.ENTER );
							
							String Item1Name = dataset.get("Item1_Name");
							TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("specified_item"))), Item1Name);
							logger.info("Enter Specified Item Name - " +Item1Name);
							String ItemValue = dataset.get("Item1_Value");
							TestUtil.click(driver.findElement(By.xpath(prop.getProperty("specified_value"))));
							Thread.sleep(300);
							TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("specified_value"))), ItemValue);
							logger.info("Enter Specified Item Value - " +ItemValue);
							
						}
						else {
							TestUtil.click(driver.findElement(By.xpath(prop.getProperty("PersonalPossessionsNo"))));
							logger.info("Would you like Worldwide Personal Belongings cover? - No");
						}
						
						//===================================================================================================================
						//==============================PEDAL_CYCLE==========================================================================
						
						//Would you like Pedal Cycle cover
						if (dataset.get("Pedal_Cycle").equalsIgnoreCase("Yes")) {
							String cycletype = dataset.get("Cycle_Type");
							String cyclevalue= dataset.get("Cycle_Value");
							
							TestUtil.click(driver.findElement(By.xpath(prop.getProperty("pedalCycleYes"))));
							logger.info("Would you like Pedal Cycle cover - Yes");
							TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("cycleTYpe"))), cycletype);
							logger.info("Cycle Type -"+cycletype);
							TestUtil.click(driver.findElement(By.xpath(prop.getProperty("cycleValue"))));
							TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("cycleValue"))), cyclevalue);
							logger.info("Cycle Type -"+cyclevalue);
							
							
						}
						else {
							TestUtil.click(driver.findElement(By.xpath(prop.getProperty("pedalCycleNo"))));
						}
						//========================================================================================================================
						
						//=====================Add On Covers=====================================================================================
						
						//Legal Cover required 
						if (dataset.get("Legal_Cover").equalsIgnoreCase("Yes")) {
							
							TestUtil.click(driver.findElement(By.xpath(prop.getProperty("legalCovyes"))));
							logger.info("Legal Cover required - Yes");
						}
						else {
							TestUtil.click(driver.findElement(By.xpath(prop.getProperty("legalCovNo"))));
							logger.info("Legal Cover required - No");
						}
						
						//Home Emergency Cover
						if (dataset.get("Home_Emergency_Cover").equalsIgnoreCase("Yes")) {
							
							TestUtil.click(driver.findElement(By.xpath(prop.getProperty("homeCovYes"))));
							logger.info("Home Emergency Cover - Yes");
						}
						else {
							TestUtil.click(driver.findElement(By.xpath(prop.getProperty("homeCovNo"))));
							logger.info("Home Emergency Cover - No");
						}
						
						// Click On next button to submit risk page
						TestUtil.click(driver.findElement(By.xpath(prop.getProperty("RiskPageSubmit"))));
						logger.info("click on Next button to navigate Premium page");
						
						Thread.sleep(900);
						TestUtil.insert_image_doc(driver);
						//=============================================Extracting Quote number and input into excell sheet========================================================================================================
						ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("CommonExplicitWaitTime")),
								By.xpath(prop.getProperty("htmlbodyloading")), driver);
						
						
						String quotenumdiv = TestUtil.Gettext(driver.findElement(By.xpath(prop.getProperty("quotenumberdiv"))));
						logger.info("text from quote div : "+quotenumdiv);
						
						String getQuote=quotenumdiv;
						String findQuote=getQuote.replaceAll("Quote Reference Number: ","");
						logger.info("after replace quote number is : "+findQuote);
						//String quotenum = findQuote.substring(21, 40);
						//logger.info("after trim quote number is : "+quotenum);
						
						
						
						TestUtil.Append_to_doc("New " + dataset.get("Product_Name") + " quote created and the quote number is " + findQuote);
						TestUtil.insert_image_doc(driver);
		
						int row_run_result = ExcelDataUtility.excelTotalRowCount(excelpath, prop.getProperty("Home&Content_ProductOutPut_Sheet"));					
						logger.info("total row in Home&Content_ProductOutPut_Sheet : "+row_run_result);
						
						ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("Home&Content_ProductOutPut_Sheet"), findQuote, (row_run_result+1), "Quote_Number");
						logger.info("Quote Number '"+findQuote+"' is added on the Home&Content_ProductOutPut_Sheet");
						Thread.sleep(900);
						
						//==============================================Premium_Breakup=========================================================================
						
					
						
						//===================================================Email quote============================================================================
						
						TestUtil.click(driver.findElement(By.xpath(prop.getProperty("EmailQuote"))));
						logger.info("click on Email Quot in premium page");
						
						ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("RareExplicitWaitTime")),
								By.xpath(prop.getProperty("htmlbodyloading")), driver);
						
						Thread.sleep(900);
						ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("RareExplicitWaitTime")),
								By.xpath(prop.getProperty("mailto_id")), driver);
						
						TestUtil.clear(driver.findElement(By.xpath(prop.getProperty("mailto_id"))));
						
						TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("mailto_id"))), "chumki.adhikari@markit-systems.com");
						logger.info("Enter Email id in to Mail To Text box");
						
						
						TestUtil.click(driver.findElement(By.xpath(prop.getProperty("bind_sendmail"))));
						logger.info("Now click on Send the mail");
						Thread.sleep(600);
						
						TestUtil.insert_image_doc(driver);
						
	}
}
	}
}
