package repeated_Steps;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import configuration.Base;
import utility.ExcelDataUtility;
import utility.ExplicitWait;
import utility.JSLibrary;
import utility.JavaUtility;
import utility.TestUtil;

public class Repeated_Steps extends Base {

	static String excelpath = System.getProperty("user.dir") + prop.getProperty("ExcelfilePath");
	
	public static String fillup_generalinfo(String currency, String broker, String product, String term,
			String Policy_Start_Date) throws Exception {

		
		
		// Select broker
		if (!((broker.equalsIgnoreCase("NA")) || (broker.equalsIgnoreCase("N/A")))) {

			Thread.sleep(1000);
			TestUtil.SelectByText(driver.findElement(By.cssSelector(prop.getProperty("broker"))), broker);

			ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("CommonExplicitWaitTime")),
					By.cssSelector(prop.getProperty("preloader")), driver);
			
			logger.info("Select the '"+broker+"' on the 'Select Broker' field.");

		}

		// Select Product
		if (!((product.equalsIgnoreCase("NA")) || (product.equalsIgnoreCase("N/A")))) {

			Thread.sleep(3000);
			TestUtil.SelectByText(driver.findElement(By.cssSelector(prop.getProperty("product"))), product);

			ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("CommonExplicitWaitTime")),
					By.cssSelector(prop.getProperty("preloader")), driver);
			
			logger.info("Select the '"+product+"' on the 'Select Product' field.");

		}

		// Select Currency
		if (!((currency.equalsIgnoreCase("NA")) || (currency.equalsIgnoreCase("N/A")))) {

			TestUtil.SelectByText(driver.findElement(By.cssSelector(prop.getProperty("currency"))), currency);
			
			logger.info("Select the '"+currency+"' on the 'Select Currency' field.");
			
		}

		// Select Policy Term
		if (!((term.equalsIgnoreCase("NA")) || (term.equalsIgnoreCase("N/A")))) {

			TestUtil.SelectByText(driver.findElement(By.cssSelector(prop.getProperty("term"))), term);
			ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("CommonExplicitWaitTime")),
					By.cssSelector(prop.getProperty("preloader")), driver);
			
			logger.info("Select the '"+term+"' on the 'Policy Term' field.");

		}

		// Select Start Date
		if (!((Policy_Start_Date.equalsIgnoreCase("NA")) || (Policy_Start_Date.equalsIgnoreCase("N/A")))) 
		{		
			
////////// Add the below line because current year have binder issues
//			driver.findElement(By.cssSelector(prop.getProperty("DateTextBox"))).clear();
//			driver.findElement(By.cssSelector(prop.getProperty("DateTextBox"))).sendKeys("06092021");
			
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		/*		  
		    TestUtil.click(driver.findElement(By.cssSelector(prop.getProperty("DateTextBox"))));

			Thread.sleep(1000);
			TestUtil.SelectByText(driver.findElement(By.cssSelector(prop.getProperty("StartYear"))),
					JavaUtility.returnYearfromDate(Policy_Start_Date));
			TestUtil.SelectByText(driver.findElement(By.cssSelector(prop.getProperty("StartMonth"))),
					JavaUtility.returnMonthfromDate(Policy_Start_Date));

			driver.findElement(By.xpath(prop.getProperty("StartDateFirstPart")
					+ JavaUtility.returnDayhfromDate(Policy_Start_Date) + prop.getProperty("StartDateSecondPart")))
					.click();
		*/	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
			if (Policy_Start_Date.equalsIgnoreCase("After_3Months_from_Cur_Date")) 
			{
				Policy_Start_Date = JavaUtility.getDate_from_certain_interval(90);
			} 
			else if (Policy_Start_Date.equalsIgnoreCase("After_6Months_from_Cur_Date")) 
			{
				Policy_Start_Date = JavaUtility.getDate_from_certain_interval(180);
			}
			else if (Policy_Start_Date.equalsIgnoreCase("After_1Month_from_Cur_Date")) 
			{
				Policy_Start_Date = JavaUtility.getDate_from_certain_interval(30);
			}
			else if (Policy_Start_Date.equalsIgnoreCase("Before_1Month_from_Cur_Date")) 
			{
				Policy_Start_Date = JavaUtility.getDate_from_certain_interval(-30);
			}
			else if (Policy_Start_Date.equalsIgnoreCase("Before_6Month_from_Cur_Date")) 
			{
				Policy_Start_Date = JavaUtility.getDate_from_certain_interval(-180);
			}
			else if (Policy_Start_Date.equalsIgnoreCase("Before_9Month_from_Cur_Date")) 
			{
				Policy_Start_Date = JavaUtility.getDate_from_certain_interval(-270);
			}
			else if (Policy_Start_Date.equalsIgnoreCase("Before_1Year_from_Cur_Date")) 
			{
				Policy_Start_Date = JavaUtility.getDate_from_certain_interval(-365);
			}
			else if (Policy_Start_Date.equalsIgnoreCase("Current_Date")) 
			{
				Policy_Start_Date = JavaUtility.getDate_from_certain_interval(0);
			}
			else
			{
				logger.info("Please check the Policy Start date");
			}
			
	/*
			else 
			{
				Policy_Start_Date="Current_Date";
				logger.info("The Cance_Date curent===================>"+Policy_Start_Date);
			}
	*/
			TestUtil.click(driver.findElement(By.cssSelector(prop.getProperty("DateTextBox"))));
			Thread.sleep(1000);
						
			TestUtil.SelectByText(driver.findElement(By.cssSelector(prop.getProperty("StartYear"))), JavaUtility.returnYearfromDate(Policy_Start_Date));
			TestUtil.SelectByText(driver.findElement(By.cssSelector(prop.getProperty("StartMonth"))), JavaUtility.returnMonthfromDate(Policy_Start_Date));
/*
			driver.findElement(By.xpath(prop.getProperty("StartDateFirstPart")
					+ JavaUtility.returnDayhfromDate(Policy_Start_Date)
					+ prop.getProperty("StartDateSecondPart"))).click();
	*/		
			driver.findElement(By.xpath(prop.getProperty("StartDateFirstPart")
					+ JavaUtility.returnDayhfromDate(Policy_Start_Date) + prop.getProperty("StartDateSecondPart")))
					.click();
			
			
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			
			logger.info("Select the '"+Policy_Start_Date+"' on the 'Policy Start Date' field.");

			Thread.sleep(2000);

			TestUtil.Append_to_doc("New " + product + " type quote creation process initiated ");
			TestUtil.insert_image_doc(driver);

		}
		return Policy_Start_Date;
	}
//-----------------------------------------for D&O Product only--------------------------------------------------------
	
	
	public static String fillup_generalinfo1( String product, String term,
			String Policy_Start_Date) throws Exception {

		

		// Select Product
		if (!((product.equalsIgnoreCase("NA")) || (product.equalsIgnoreCase("N/A")))) {

			Thread.sleep(3000);
			TestUtil.SelectByText(driver.findElement(By.cssSelector(prop.getProperty("product"))), product);

			ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("CommonExplicitWaitTime")),
					By.cssSelector(prop.getProperty("preloader")), driver);
			
			logger.info("Select the '"+product+"' on the 'Select Product' field.");

		}

		

		// Select Policy Term
		if (!((term.equalsIgnoreCase("NA")) || (term.equalsIgnoreCase("N/A")))) {

			TestUtil.SelectByText(driver.findElement(By.cssSelector(prop.getProperty("term"))), term);
			ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("CommonExplicitWaitTime")),
					By.cssSelector(prop.getProperty("preloader")), driver);
			
			logger.info("Select the '"+term+"' on the 'Policy Term' field.");

		}
		

		// Select Start Date
		if (!((Policy_Start_Date.equalsIgnoreCase("NA")) || (Policy_Start_Date.equalsIgnoreCase("N/A")))) 
		{		
			
////////// Add the below line because current year have binder issues
//			driver.findElement(By.cssSelector(prop.getProperty("DateTextBox"))).clear();
//			driver.findElement(By.cssSelector(prop.getProperty("DateTextBox"))).sendKeys("06092021");
			
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		/*		  
		    TestUtil.click(driver.findElement(By.cssSelector(prop.getProperty("DateTextBox"))));

			Thread.sleep(1000);
			TestUtil.SelectByText(driver.findElement(By.cssSelector(prop.getProperty("StartYear"))),
					JavaUtility.returnYearfromDate(Policy_Start_Date));
			TestUtil.SelectByText(driver.findElement(By.cssSelector(prop.getProperty("StartMonth"))),
					JavaUtility.returnMonthfromDate(Policy_Start_Date));

			driver.findElement(By.xpath(prop.getProperty("StartDateFirstPart")
					+ JavaUtility.returnDayhfromDate(Policy_Start_Date) + prop.getProperty("StartDateSecondPart")))
					.click();
		*/	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
			if (Policy_Start_Date.equalsIgnoreCase("After_3Months_from_Cur_Date")) 
			{
				Policy_Start_Date = JavaUtility.getDate_from_certain_interval(90);
			} 
			else if (Policy_Start_Date.equalsIgnoreCase("After_6Months_from_Cur_Date")) 
			{
				Policy_Start_Date = JavaUtility.getDate_from_certain_interval(180);
			}
			else if (Policy_Start_Date.equalsIgnoreCase("After_1Month_from_Cur_Date")) 
			{
				Policy_Start_Date = JavaUtility.getDate_from_certain_interval(30);
			}
			else if (Policy_Start_Date.equalsIgnoreCase("Before_1Month_from_Cur_Date")) 
			{
				Policy_Start_Date = JavaUtility.getDate_from_certain_interval(-30);
			}
			else if (Policy_Start_Date.equalsIgnoreCase("Before_6Month_from_Cur_Date")) 
			{
				Policy_Start_Date = JavaUtility.getDate_from_certain_interval(-180);
			}
			else if (Policy_Start_Date.equalsIgnoreCase("Before_9Month_from_Cur_Date")) 
			{
				Policy_Start_Date = JavaUtility.getDate_from_certain_interval(-270);
			}
			else if (Policy_Start_Date.equalsIgnoreCase("Before_1Year_from_Cur_Date")) 
			{
				Policy_Start_Date = JavaUtility.getDate_from_certain_interval(-365);
			}
			else if (Policy_Start_Date.equalsIgnoreCase("Current_Date")) 
			{
				Policy_Start_Date = JavaUtility.getDate_from_certain_interval(0);
			}
			else
			{
				logger.info("Please check the Policy Start date");
			}
			
	/*
			else 
			{
				Policy_Start_Date="Current_Date";
				logger.info("The Cance_Date curent===================>"+Policy_Start_Date);
			}
	*/
			TestUtil.click(driver.findElement(By.cssSelector(prop.getProperty("DateTextBox"))));
			Thread.sleep(1000);
						
			TestUtil.SelectByText(driver.findElement(By.cssSelector(prop.getProperty("StartYear"))), JavaUtility.returnYearfromDate(Policy_Start_Date));
			TestUtil.SelectByText(driver.findElement(By.cssSelector(prop.getProperty("StartMonth"))), JavaUtility.returnMonthfromDate(Policy_Start_Date));
/*
			driver.findElement(By.xpath(prop.getProperty("StartDateFirstPart")
					+ JavaUtility.returnDayhfromDate(Policy_Start_Date)
					+ prop.getProperty("StartDateSecondPart"))).click();
	*/		
			driver.findElement(By.xpath(prop.getProperty("StartDateFirstPart")
					+ JavaUtility.returnDayhfromDate(Policy_Start_Date) + prop.getProperty("StartDateSecondPart")))
					.click();
			
			
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			
			logger.info("Select the '"+Policy_Start_Date+"' on the 'Policy Start Date' field.");

			Thread.sleep(2000);

			TestUtil.Append_to_doc("New " + product + " type quote creation process initiated ");
			TestUtil.insert_image_doc(driver);

		}
		return Policy_Start_Date;
	}
	
	
//---------------------------------------------------------------------------------------------------------------------
	
	
	
	
	
	public static String policyStartDate(String Policy_Start_Date)
	{
	//	logger.info("Logger ============================================ 2");
		if (!((Policy_Start_Date.equalsIgnoreCase("NA")) || (Policy_Start_Date.equalsIgnoreCase("N/A")))) 
		{	
		//	logger.info("Logger ============================================ 3");
			if (Policy_Start_Date.equalsIgnoreCase("After_3Months_from_Cur_Date")) 
			{
				Policy_Start_Date = JavaUtility.getDate_from_certain_interval(90);
			} 
			else if (Policy_Start_Date.equalsIgnoreCase("After_6Months_from_Cur_Date")) 
			{
				Policy_Start_Date = JavaUtility.getDate_from_certain_interval(180);
							}
			else if (Policy_Start_Date.equalsIgnoreCase("After_1Month_from_Cur_Date")) 
			{
				Policy_Start_Date = JavaUtility.getDate_from_certain_interval(30);
			}
			else if (Policy_Start_Date.equalsIgnoreCase("Before_6Month_from_Cur_Date")) 
			{
				Policy_Start_Date = JavaUtility.getDate_from_certain_interval(-180);
			}
			else if (Policy_Start_Date.equalsIgnoreCase("Before_9Month_from_Cur_Date")) 
			{
				Policy_Start_Date = JavaUtility.getDate_from_certain_interval(-270);
			}
			else if (Policy_Start_Date.equalsIgnoreCase("Before_1Year_from_Cur_Date")) 
			{
				Policy_Start_Date = JavaUtility.getDate_from_certain_interval(-365);
			}
			else if (Policy_Start_Date.equalsIgnoreCase("Current_Date")) 
			{
			//	logger.info("Logger ============================================ 4");
				Policy_Start_Date = JavaUtility.getDate_from_certain_interval(0);
			//	logger.info("Logger ============================================ 5");
			}
			else
			{
				logger.info("Please check the Policy Start date");				
			}
		}
	//	logger.info("Logger ============================================ 6"+Policy_Start_Date);
		return Policy_Start_Date;
	}

	public static void client_search(String client) throws Exception {

//		ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("MediumExplicitWaitTime")),
//				By.cssSelector(prop.getProperty("preloader")), driver);

		ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("MediumExplicitWaitTime")),
				By.xpath(prop.getProperty("htmlbodyloading")), driver);

		logger.info("Current url is : " + driver.getCurrentUrl());

		TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("clientname"))), client);

		Thread.sleep(4000);
				
		TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("clientname"))), Keys.ENTER);

		ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("MediumExplicitWaitTime")),
				By.xpath(prop.getProperty("clientlistloading")), driver);

		ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("MediumExplicitWaitTime")),
				By.xpath(prop.getProperty("clientlistpagination")), driver);
		
		logger.info("Enter the Client name '"+client+"' on the client serch field and search");

		TestUtil.click(driver.findElement(By.xpath(prop.getProperty("edit"))));

		ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("MediumExplicitWaitTime")),
				By.cssSelector(prop.getProperty("preloader")), driver);
		ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("CommonExplicitWaitTime")),
				By.xpath(prop.getProperty("htmlbodyloading")), driver);
		
		logger.info("Click on the edit icon");

	}

	public static void policy_search(String policyNumber) throws Exception {

		ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("MediumExplicitWaitTime")),
				By.cssSelector(prop.getProperty("PolicypageLoader")), driver);

		ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("MediumExplicitWaitTime")),
				By.xpath(prop.getProperty("htmlbodyloading")), driver);

		TestUtil.Entertext(driver.findElement(By.id(prop.getProperty("policynotextbox_ID"))), policyNumber);

		Thread.sleep(4000);

		TestUtil.Entertext(driver.findElement(By.id(prop.getProperty("policynotextbox_ID"))), Keys.ENTER);

		ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("MediumExplicitWaitTime")),
				By.cssSelector(prop.getProperty("PolicypageLoader")), driver);

		ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("MediumExplicitWaitTime")),
				By.xpath(prop.getProperty("clientlistpagination")), driver);

		TestUtil.click(driver.findElement(By.xpath(prop.getProperty("edit"))));

		ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("MediumExplicitWaitTime")),
				By.xpath(prop.getProperty("htmlbodyloading")), driver);

	}

	public static void handlePagePopup() throws Exception {

		int credit_check_popup_count = TestUtil.CountElement(driver,
				By.cssSelector(prop.getProperty("credit_check_popup_skip")));
//		logger.info("Credit check popup count:" + credit_check_popup_count);
		if (credit_check_popup_count > 0) {

			driver.findElement(By.cssSelector(prop.getProperty("credit_check_popup_skip"))).click();
			ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("CommonExplicitWaitTime")),
					By.cssSelector(prop.getProperty("preloader")), driver);
			TestUtil.click(driver.findElement(By.cssSelector(prop.getProperty("Next"))));

		}
		int count = TestUtil.CountElement(driver, By.xpath(prop.getProperty("DuplicateAlert")));
//		logger.info("The No Of Alert Element:" + count);
		Thread.sleep(2000);

		if (count > 0) {
			driver.findElement(By.xpath(prop.getProperty("Cancel"))).click();
		}

	}

	public static Boolean verify_property(String Country, String Postcode, String Home_Name, String Address_line_1,
			String Address_line_2, String Street, String City, String county_address, int count) {

		Boolean flag = true;

		if (!((Country.equalsIgnoreCase("NA")) || (Country.equalsIgnoreCase("N/A")))) {

			String con = TestUtil.returnSelectedOption(
					driver.findElement(By.xpath(prop.getProperty("multiple_property_initials_start") + (count)
							+ prop.getProperty("multiple_property_middle") + prop.getProperty("countrydropdown"))));
			if (!con.equalsIgnoreCase(Country)) {

				flag = false;
			}

		}

		if (!((Postcode.equalsIgnoreCase("NA")) || (Postcode.equalsIgnoreCase("N/A")))) {

			if (flag == true) {

				String pcode = TestUtil.GetAttributeValue(
						driver.findElement(By.xpath(prop.getProperty("multiple_property_initials_start") + (count)
								+ prop.getProperty("multiple_property_middle") + prop.getProperty("postcode_new"))),
						"value");
				if (!pcode.equalsIgnoreCase(Postcode)) {

					flag = false;
				}

			}

		}

		if (!((Home_Name.equalsIgnoreCase("NA")) || (Home_Name.equalsIgnoreCase("N/A")))) {

			if (flag == true) {

				String hname = TestUtil.GetAttributeValue(
						driver.findElement(By.xpath(prop.getProperty("multiple_property_initials_start") + (count)
								+ prop.getProperty("multiple_property_middle") + prop.getProperty("house_name"))),
						"value");
				if (!hname.equalsIgnoreCase(Home_Name)) {

					flag = false;
				}
			}
		}

		if (!((Street.equalsIgnoreCase("NA")) || (Street.equalsIgnoreCase("N/A")))) {

			if (flag == true) {

				String str = TestUtil.GetAttributeValue(
						driver.findElement(By.xpath(prop.getProperty("multiple_property_initials_start") + (count)
								+ prop.getProperty("multiple_property_middle") + prop.getProperty("street_new"))),
						"value");
				if (!str.equalsIgnoreCase(Street)) {

					flag = false;
				}

			}

		}

		if (!((Address_line_1.equalsIgnoreCase("NA")) || (Address_line_1.equalsIgnoreCase("N/A")))) {

			if (flag == true) {

				String aline1 = TestUtil.GetAttributeValue(
						driver.findElement(By.xpath(prop.getProperty("multiple_property_initials_start") + (count)
								+ prop.getProperty("multiple_property_middle") + prop.getProperty("Address_line_1"))),
						"value");
				if (!aline1.equalsIgnoreCase(Address_line_1)) {

					flag = false;
				}
			}

		}

		if (!((Address_line_2.equalsIgnoreCase("NA")) || (Address_line_2.equalsIgnoreCase("N/A")))) {

			if (flag == true) {

				String aline2 = TestUtil.GetAttributeValue(
						driver.findElement(By.xpath(prop.getProperty("multiple_property_initials_start") + (count)
								+ prop.getProperty("multiple_property_middle") + prop.getProperty("Address_line_2"))),
						"value");
				if (!aline2.equalsIgnoreCase(Address_line_2)) {

					flag = false;
				}
			}

		}

		if (!((City.equalsIgnoreCase("NA")) || (City.equalsIgnoreCase("N/A")))) {

			if (flag == true) {

				String ct = TestUtil.GetAttributeValue(
						driver.findElement(By.xpath(prop.getProperty("multiple_property_initials_start") + (count)
								+ prop.getProperty("multiple_property_middle") + prop.getProperty("city_new"))),
						"value");
				if (!ct.equalsIgnoreCase(City)) {

					flag = false;
				}

			}

		}

		if (!((county_address.equalsIgnoreCase("NA")) || (county_address.equalsIgnoreCase("N/A")))) {

			if (flag == true) {

				String cou = TestUtil.returnSelectedOption(
						driver.findElement(By.xpath(prop.getProperty("multiple_property_initials_start") + (count)
								+ prop.getProperty("multiple_property_middle") + prop.getProperty("county_address"))));
				if (!cou.equalsIgnoreCase(county_address)) {

					flag = false;
				}

			}
		}

		return flag;

	}

	public static void Enter_property_other_details(String Property_Name, String Property_Damage_Value,
			String Business_interruption_value, String Zone, String MD_Trade, String Excess, int count) {

		if (!((Property_Name.equalsIgnoreCase("NA")) || (Property_Name.equalsIgnoreCase("N/A")))) {

			TestUtil.Entertext(
					driver.findElement(By.xpath(prop.getProperty("multiple_property_initials_start") + (count)
							+ prop.getProperty("multiple_property_middle") + prop.getProperty("propertyname_new"))),
					Property_Name);

		}

		if (!((MD_Trade.equalsIgnoreCase("NA")) || (MD_Trade.equalsIgnoreCase("N/A")))) {

			TestUtil.SelectByText(driver.findElement(By.xpath(prop.getProperty("multiple_property_initials_start")
					+ (count) + prop.getProperty("multiple_property_middle") + prop.getProperty("MD_Trade"))),
					MD_Trade);

		}

		if (!((Excess.equalsIgnoreCase("NA")) || (Excess.equalsIgnoreCase("N/A")))) {

			TestUtil.SelectByText(driver.findElement(By.xpath(prop.getProperty("multiple_property_initials_start")
					+ (count) + prop.getProperty("multiple_property_middle") + prop.getProperty("Excess"))), Excess);

		}

		if (!((Property_Damage_Value.equalsIgnoreCase("NA")) || (Property_Damage_Value.equalsIgnoreCase("N/A")))) {

			TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("multiple_property_initials_start")
					+ (count) + prop.getProperty("multiple_property_middle")
					+ prop.getProperty("Property_Damage_Value"))), Property_Damage_Value);

		}

		if (!((Business_interruption_value.equalsIgnoreCase("NA"))
				|| (Business_interruption_value.equalsIgnoreCase("N/A")))) {

			TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("multiple_property_initials_start")
					+ (count) + prop.getProperty("multiple_property_middle")
					+ prop.getProperty("Business_interruption_value"))), Business_interruption_value);

		}

		if (!((Zone.equalsIgnoreCase("NA")) || (Zone.equalsIgnoreCase("N/A")))) {

			TestUtil.SelectByText(driver.findElement(By.xpath(prop.getProperty("multiple_property_initials_start")
					+ (count) + prop.getProperty("multiple_property_middle") + prop.getProperty("Zone"))), Zone);

		}

	}

	public static void Enter_property_details(String Country, String Postcode, String Home_Name, String Address_line_1,
			String Address_line_2, String Street, String City, String county_address, int count) throws Exception {

		if (!((Country.equalsIgnoreCase("NA")) || (Country.equalsIgnoreCase("N/A")))) {

			TestUtil.SelectByText(
					driver.findElement(By.xpath(prop.getProperty("multiple_property_initials_start") + (count)
							+ prop.getProperty("multiple_property_middle") + prop.getProperty("countrydropdown"))),
					Country);

			Thread.sleep(5000);

		}

		if (!((Postcode.equalsIgnoreCase("NA")) || (Postcode.equalsIgnoreCase("N/A")))) {

			TestUtil.Entertext(
					driver.findElement(By.xpath(prop.getProperty("multiple_property_initials_start") + (count)
							+ prop.getProperty("multiple_property_middle") + prop.getProperty("postcode_new"))),
					Postcode);
		}

		if (!((Home_Name.equalsIgnoreCase("NA")) || (Home_Name.equalsIgnoreCase("N/A")))) {

			TestUtil.Entertext(
					driver.findElement(By.xpath(prop.getProperty("multiple_property_initials_start") + (count)
							+ prop.getProperty("multiple_property_middle") + prop.getProperty("house_name"))),
					Home_Name);
		}

		if (!((Street.equalsIgnoreCase("NA")) || (Street.equalsIgnoreCase("N/A")))) {

			TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("multiple_property_initials_start")
					+ (count) + prop.getProperty("multiple_property_middle") + prop.getProperty("street_new"))),
					Street);

		}

		if (!((Address_line_1.equalsIgnoreCase("NA")) || (Address_line_1.equalsIgnoreCase("N/A")))) {

			TestUtil.Entertext(
					driver.findElement(By.xpath(prop.getProperty("multiple_property_initials_start") + (count)
							+ prop.getProperty("multiple_property_middle") + prop.getProperty("Address_line_1"))),
					Address_line_1);

		}

		if (!((Address_line_2.equalsIgnoreCase("NA")) || (Address_line_2.equalsIgnoreCase("N/A")))) {

			TestUtil.Entertext(
					driver.findElement(By.xpath(prop.getProperty("multiple_property_initials_start") + (count)
							+ prop.getProperty("multiple_property_middle") + prop.getProperty("Address_line_2"))),
					Address_line_2);
		}

		if (!((City.equalsIgnoreCase("NA")) || (City.equalsIgnoreCase("N/A")))) {

			TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("multiple_property_initials_start")
					+ (count) + prop.getProperty("multiple_property_middle") + prop.getProperty("city_new"))), City);

		}

		if (!((county_address.equalsIgnoreCase("NA")) || (county_address.equalsIgnoreCase("N/A")))) {
			TestUtil.SelectByText(
					driver.findElement(By.xpath(prop.getProperty("multiple_property_initials_start") + (count)
							+ prop.getProperty("multiple_property_middle") + prop.getProperty("county_address"))),
					county_address);
		}

	}

	public static void perform_flex_comm(String Flex_Broker_Com, String Flex_UW_Com) throws Exception {

		Thread.sleep(5000);
		ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("CommonExplicitWaitTime")),
				By.cssSelector(prop.getProperty("preloader")), driver);

		if (!((Flex_Broker_Com.equalsIgnoreCase("NA")) || (Flex_Broker_Com.equalsIgnoreCase("N/A")))) {
			TestUtil.Entertext(driver.findElement(By.id(prop.getProperty("Flex_Broker_Com_id"))), Flex_Broker_Com);
			logger.info("Enter the Flex_Broker_Com as '"+Flex_Broker_Com+"'");
		}

		if (!((Flex_UW_Com.equalsIgnoreCase("NA")) || (Flex_UW_Com.equalsIgnoreCase("N/A")))) {
			TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("Flex_UW_Com"))), Flex_UW_Com);
			logger.info("Enter the Flex_UW_Com as '"+Flex_UW_Com+"'");
		}

	}

	public static Boolean verify_address(String Country, String Postcode, String Home_Name, String add_line1,
			String add_line2, String Street, String City, String County) throws Exception {

		int loc_count;
		Boolean verifystatus = true;
		loc_count = TestUtil.CountElement(driver, By.xpath(prop.getProperty("total_existing_address_list")));
		Thread.sleep(2000);
		if (loc_count > 0) {
			for (int i = 1; i < loc_count; i++) {
				JSLibrary.clickElement(driver, driver.findElement(By.xpath(prop.getProperty("access_existing_address_start") + i
						+ prop.getProperty("access_existing_address_end"))));
				verifystatus = verify_each_address(Country, Postcode, Home_Name, add_line1, add_line2, Street, City, County);
				if (verifystatus) {
					break;
				}
			}
		}
		return verifystatus;
	}

	public static Boolean verify_each_address(String Country, String Postcode, String Home_Name, String add_line1,
			String add_line2, String Street, String City, String County) throws Exception {

		Boolean flag = true;

		if (!((Country.equalsIgnoreCase("NA")) || (Country.equalsIgnoreCase("N/A")))) {
			Thread.sleep(5000);
			String con = TestUtil.returnSelectedOption(driver.findElement(By.xpath(prop.getProperty("countrydropdown"))));
			if (!con.equalsIgnoreCase(Country)) {
				flag = false;
			}
		}

		if (!((Postcode.equalsIgnoreCase("NA")) || (Postcode.equalsIgnoreCase("N/A")))) {
			if (flag == true) {
				String pcode = TestUtil.GetAttributeValue(driver.findElement(By.xpath(prop.getProperty("postcode_new"))), "value");
				if (!pcode.equalsIgnoreCase(Postcode)) {
					flag = false;
				}
			}
		}

		if (!((Home_Name.equalsIgnoreCase("NA")) || (Home_Name.equalsIgnoreCase("N/A")))) {
			if (flag == true) {
				String hname = TestUtil.GetAttributeValue(driver.findElement(By.xpath(prop.getProperty("house_name"))), "value");
				if (!hname.equalsIgnoreCase(Home_Name)) {
					flag = false;
				}
			}
		}

		if (!((Street.equalsIgnoreCase("NA")) || (Street.equalsIgnoreCase("N/A")))) {
			if (flag == true) {
				String str = TestUtil.GetAttributeValue(driver.findElement(By.xpath(prop.getProperty("street_new"))), "value");
				if (!str.equalsIgnoreCase(Street)) {
					flag = false;
				}
			}
		}

		if (!((add_line1.equalsIgnoreCase("NA")) || (add_line1.equalsIgnoreCase("N/A")))) {
			if (flag == true) {
				String add1 = TestUtil.GetAttributeValue(driver.findElement(By.xpath(prop.getProperty("Address_line_1"))), "value");
				if (!add1.equalsIgnoreCase(add_line1)) {
					flag = false;
				}
			}
		}

		if (!((add_line2.equalsIgnoreCase("NA")) || (add_line2.equalsIgnoreCase("N/A")))) {
			if (flag == true) {
				String add2 = TestUtil.GetAttributeValue(driver.findElement(By.xpath(prop.getProperty("Address_line_2"))), "value");
				if (!add2.equalsIgnoreCase(add_line2)) {
					flag = false;
				}
			}
		}

		if (!((City.equalsIgnoreCase("NA")) || (City.equalsIgnoreCase("N/A")))) {
			if (flag == true) {
				String ct = TestUtil.GetAttributeValue(driver.findElement(By.xpath(prop.getProperty("city_new"))), "value");
				if (!ct.equalsIgnoreCase(City)) {
					flag = false;
				}
			}
		}

		if (!((County.equalsIgnoreCase("NA")) || (County.equalsIgnoreCase("N/A")))) {
			if (flag == true) {
				String county = TestUtil.returnSelectedOption(driver.findElement(By.xpath(prop.getProperty("county_address"))));
				if (!county.equalsIgnoreCase(County)) {
					flag = false;
				}
			}
		}
		return flag;		
	}

	public static void enter_new_address(String Country, String Postcode, String Home_Name, String add_line1,
			String add_line2, String Street, String City, String County) throws Exception {

		int loc_count;
		loc_count = TestUtil.CountElement(driver, By.xpath(prop.getProperty("total_existing_address_list")));
//		TestUtil.click(driver.findElement(By.xpath(prop.getProperty("access_existing_address_start") + loc_count + prop.getProperty("access_existing_address_end"))));
		
		JSLibrary.clickElement(driver, driver.findElement(By.xpath(prop.getProperty("access_existing_address_start") + loc_count + prop.getProperty("access_existing_address_end"))));
		Thread.sleep(4000);

		if (!((Country.equalsIgnoreCase("NA")) || (Country.equalsIgnoreCase("N/A")))) 
		{
			TestUtil.SelectByText(driver.findElement(By.xpath(prop.getProperty("countrydropdown"))), Country);
			Thread.sleep(4000);
			logger.info("Select the Country as '"+Country+"'");
		}

		if (!((Postcode.equalsIgnoreCase("NA")) || (Postcode.equalsIgnoreCase("N/A")))) 
		{
			TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("postcode_new"))), Postcode);
			logger.info("Enter the Postcode as '"+Postcode+"'");
		}

		if (!((Home_Name.equalsIgnoreCase("NA")) || (Home_Name.equalsIgnoreCase("N/A")))) {
			TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("house_name"))), Home_Name);
			logger.info("Enter the Home Name as '"+Home_Name+"'");
		}

		if (!((Street.equalsIgnoreCase("NA")) || (Street.equalsIgnoreCase("N/A")))) {
			TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("street_new"))), Street);
			logger.info("Enter the Street as '"+Street+"'");
		}

		if (!((add_line1.equalsIgnoreCase("NA")) || (add_line1.equalsIgnoreCase("N/A")))) {
			TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("Address_line_1"))), add_line1);
			logger.info("Enter the add_line1 as '"+add_line1+"'");
		}

		if (!((add_line2.equalsIgnoreCase("NA")) || (add_line2.equalsIgnoreCase("N/A")))) {
			TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("Address_line_2"))), add_line2);
			logger.info("Enter the add_line2 as '"+add_line2+"'");
		}

		if (!((City.equalsIgnoreCase("NA")) || (City.equalsIgnoreCase("N/A")))) {
			Thread.sleep(2000);
			TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("city_new"))), City);
			logger.info("Enter the City as '"+City+"'");
		}

		if (!((County.equalsIgnoreCase("NA")) || (County.equalsIgnoreCase("N/A")))) {
			TestUtil.SelectByText(driver.findElement(By.xpath(prop.getProperty("county_address"))), County);
			logger.info("Enter the County as '"+County+"'");
		}
	}
	
	
	public static void select_cover(String cover) throws Exception 
	{
		TestUtil.Append_to_doc("Cover type selected : " + cover);
		TestUtil.insert_image_doc(driver);

		TestUtil.click(driver.findElement(By.xpath(prop.getProperty("cover_start") + cover + prop.getProperty("cover_end"))));
	}
	
	
	public static void send_Policy_Email() throws Exception {

		ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("RareExplicitWaitTime")), By.cssSelector(prop.getProperty("preloader")), driver);
		ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("RareExplicitWaitTime")), By.id(prop.getProperty("mailto_id")), driver);

		JSLibrary.ScrollintoView(driver, driver.findElement(By.id(prop.getProperty("mailto_id"))));
		Thread.sleep(2000);

		TestUtil.Entertext(driver.findElement(By.id(prop.getProperty("mailto_id"))),prop.getProperty("Policy_Email_Send_Address"));
		logger.info("Enter the mail id '"+prop.getProperty("Policy_Email_Send_Address")+"' on 'Mail To' field.");

		JSLibrary.ScrollintoView(driver, driver.findElement(By.xpath(prop.getProperty("bind_sendmail"))));

		TestUtil.click(driver.findElement(By.xpath(prop.getProperty("bind_sendmail"))));

		Thread.sleep(3000);

	}
	
	public static int surveryValue(String SurveyInput)
	{
		int i = 0;
		if(SurveyInput.equalsIgnoreCase("Yes"))
		{
			i =1;
			return i;
		}
		else if(SurveyInput.equalsIgnoreCase("No"))
		{
			i =2;
			return i;
		}
		return i;		
	}
		
	public static String captchTheBinderPercentage(String binderName) throws InterruptedException
	{
		TestUtil.click(driver.findElement(By.xpath(prop.getProperty("Premium_Breakdown"))));
		Thread.sleep(2000);
		
		String openPopup = TestUtil.Gettext(driver.findElement(By.xpath(prop.getProperty("Premium_Breakdown_Popup_Heding"))));
		logger.info("After click on the 'Premium Breakdown' button then "+openPopup+" popup open.");
		
		JSLibrary.scrollToButtom(driver);
		Thread.sleep(2000);
				
		String binderPercenatgeValue = TestUtil.Gettext(driver.findElement(By.xpath(prop.getProperty("BinderPercentageStart")+binderName+prop.getProperty("BinderPercentageEnd"))));
		logger.info("The '"+binderName+"' binder percentage is "+binderPercenatgeValue);
		
		Thread.sleep(4000);
		TestUtil.click(driver.findElement(By.xpath(prop.getProperty("PremiumBreakdownPopupClose"))));
		
		return binderPercenatgeValue;
		
	}
	
	public static String captchTheBrokerPercentage(String binderName, String BinderStartName, String BinderEndName) throws InterruptedException
	{
		String binderPercenatgeValue = TestUtil.Gettext(driver.findElement(By.xpath(BinderStartName+binderName+BinderEndName)));			
		String binderPercenatgeValueWithoutPercentage = binderPercenatgeValue.replaceAll("%", "");
		logger.info("The '"+binderName+"' percentage is "+binderPercenatgeValueWithoutPercentage);
		Thread.sleep(4000);		
		return binderPercenatgeValueWithoutPercentage;		
	}
	
	public static void update_overall_test_scenario_status(String scenario, String status) throws Exception
	{
		if (scenario.trim().contains("Create a Corporate Client")) 
		{
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Executed", 1, "Test_Run_Status");
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), status.toUpperCase(), 1, "Test_Status");
		}
		
		else if (scenario.trim().contains("Add address  to the Client")) 
		{
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Executed", 2, "Test_Run_Status");
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), status.toUpperCase(), 2, "Test_Status");
		}
		
		else if (scenario.trim().contains("Create an initial Quote for D&O Standard Product")) 
		{
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Executed", 3, "Test_Run_Status");
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), status.toUpperCase(), 3, "Test_Status");
		}
		
		else if (scenario.trim().contains("Search D&O_Standard_Product quote and Override broker commssion")) 
		{
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Executed", 4, "Test_Run_Status");
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), status.toUpperCase(), 4, "Test_Status");
		}
		
		else if (scenario.trim().contains("Search D&O_Standard_Product quote and Override premium")) 
		{
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Executed", 5, "Test_Run_Status");
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), status.toUpperCase(), 5, "Test_Status");
		}
		
		else if (scenario.trim().contains("Search D&O_Standard_Product quote and Add an Endorsement")) 
		{
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Executed", 6, "Test_Run_Status");
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), status.toUpperCase(), 6, "Test_Status");
		}
		
		else if (scenario.trim().contains("Search D&O_Standard_Product quote and bind it")) 
		{
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Executed", 7, "Test_Run_Status");
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), status.toUpperCase(), 7, "Test_Status");
		}
		
		else if (scenario.trim().contains("Create an initial Quote for Professional Indemnity Product")) 
		{
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Executed", 8, "Test_Run_Status");
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), status.toUpperCase(), 8, "Test_Status");
		}
		
		else if (scenario.trim().contains("Search Professional Indemnity Product quote and bind it")) 
		{
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Executed", 9, "Test_Run_Status");
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), status.toUpperCase(), 9, "Test_Status");
		}
		
		else if (scenario.trim().contains("Perform Commercial Combined - T1E NB Adjustment on Policies")) 
		{
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Executed", 10, "Test_Run_Status");
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), status.toUpperCase(), 10, "Test_Status");
		}
		
		else if (scenario.trim().contains("Perform Commercial Combined - T1E MTA on Policies")) 
		{
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Executed", 11, "Test_Run_Status");
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), status.toUpperCase(), 11, "Test_Status");
		}
		
		else if (scenario.trim().contains("Perform Commercial Combined - T1E Cancellation of Policies")) 
		{
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Executed", 12, "Test_Run_Status");
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), status.toUpperCase(), 12, "Test_Status");
		}
		
		else if (scenario.trim().contains("Liability T3ACC product quote and Buy Journey Testing")) 
		{
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Executed", 13, "Test_Run_Status");
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), status.toUpperCase(), 13, "Test_Status");
		}
		
		else if (scenario.trim().contains("Perform Liability T3ACC NB Adjustment on Policies")) 
		{
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Executed", 14, "Test_Run_Status");
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), status.toUpperCase(), 14, "Test_Status");
		}
		
		else if (scenario.trim().contains("Perform Liability T3ACC MTA on Policies")) 
		{
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Executed", 15, "Test_Run_Status");
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), status.toUpperCase(), 15, "Test_Status");
		}
		
		else if (scenario.trim().contains("Perform Liability T3ACC Cancellation of Policies")) 
		{
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Executed", 16, "Test_Run_Status");
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), status.toUpperCase(), 16, "Test_Status");
		}	
				
		else if (scenario.trim().contains("Terrorism product quote and Buy Journey Testing")) 
		{
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Executed", 17, "Test_Run_Status");
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), status.toUpperCase(), 17, "Test_Status");
		}
		
		else if (scenario.trim().contains("Perform T2 NB Adjustment on Policies")) 
		{
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Executed", 18, "Test_Run_Status");
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), status.toUpperCase(), 18, "Test_Status");
		}
		
		else if (scenario.trim().contains("Perform T2 MTA on Policies")) 
		{
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Executed", 19, "Test_Run_Status");
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), status.toUpperCase(), 19, "Test_Status");
		}
		
		else if (scenario.trim().contains("Perform T2 Cancellation of Policies")) 
		{
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Executed", 20, "Test_Run_Status");
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), status.toUpperCase(), 20, "Test_Status");
		}
	
		else if (scenario.trim().contains("T3 MGAM product quote and Buy Journey Testing")) 
		{
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Executed", 21, "Test_Run_Status");
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), status.toUpperCase(), 21, "Test_Status");
		}
		
		else if (scenario.trim().contains("Perform T3_MGAM NB Adjustment on Policies")) 
		{
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Executed", 22, "Test_Run_Status");
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), status.toUpperCase(), 22, "Test_Status");
		}
		
		else if (scenario.trim().contains("Perform T3_MGAM MTA on Policies")) 
		{
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Executed", 23, "Test_Run_Status");
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), status.toUpperCase(), 23, "Test_Status");
		}
		
		else if (scenario.trim().contains("Perform T3_MGAM Cancellation of Policies")) 
		{
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Executed", 24, "Test_Run_Status");
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), status.toUpperCase(), 24, "Test_Status");
		}
				
		else if (scenario.trim().contains("T4 Unoccupied Property product quote and Buy Journey Testing")) 
		{
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Executed", 25, "Test_Run_Status");
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), status.toUpperCase(), 25, "Test_Status");
		}
		
		else if (scenario.trim().contains("Perform T4 NB Adjustment on Policies")) 
		{
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Executed", 26, "Test_Run_Status");
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), status.toUpperCase(), 26, "Test_Status");
		}
		
		else if (scenario.trim().contains("Perform T4 MTA on Policies")) 
		{
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Executed", 27, "Test_Run_Status");
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), status.toUpperCase(), 27, "Test_Status");
		}
		
		else if (scenario.trim().contains("Perform T4 Cancellation of Policies")) 
		{
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Executed", 28, "Test_Run_Status");
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), status.toUpperCase(), 28, "Test_Status");
		}
		
		else if (scenario.trim().contains("T5 C.A.R product quote and Buy Journey Testing")) 
		{
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Executed", 29, "Test_Run_Status");
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), status.toUpperCase(), 29, "Test_Status");
		}
		
		else if (scenario.trim().contains("Perform T5 C.A.R NB Adjustment on Policies")) 
		{
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Executed", 30, "Test_Run_Status");
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), status.toUpperCase(), 30, "Test_Status");
		}
		
		else if (scenario.trim().contains("Perform T5 C.A.R MTA on Policies")) 
		{
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Executed", 31, "Test_Run_Status");
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), status.toUpperCase(), 31, "Test_Status");
		}
		
		else if (scenario.trim().contains("Perform T5 C.A.R Cancellation of Policies")) 
		{
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Executed", 32, "Test_Run_Status");
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), status.toUpperCase(), 32, "Test_Status");
		}
				
		else if (scenario.trim().contains("T7 Legal Expenses product quote and Buy Journey Testing")) 
		{
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Executed", 33, "Test_Run_Status");
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), status.toUpperCase(), 33, "Test_Status");
		}
		
		else if (scenario.trim().contains("Perform T7 NB Adjustment on Policies")) 
		{
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Executed", 34, "Test_Run_Status");
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), status.toUpperCase(), 34, "Test_Status");
		}
		
		else if (scenario.trim().contains("Perform T7 MTA on Policies")) 
		{
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Executed", 35, "Test_Run_Status");
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), status.toUpperCase(), 35, "Test_Status");
		}
		
		else if (scenario.trim().contains("Perform T7 Cancellation of Policies")) 
		{
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Executed", 36, "Test_Run_Status");
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), status.toUpperCase(), 36, "Test_Status");
		}
		
		else if (scenario.trim().contains("T8 product quote and Buy Journey Testing")) 
		{
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Executed", 37, "Test_Run_Status");
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), status.toUpperCase(), 37, "Test_Status");
		}
		
		else if (scenario.trim().contains("Perform T8 NB Adjustment on Policies")) 
		{
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Executed", 38, "Test_Run_Status");
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), status.toUpperCase(), 38, "Test_Status");
		}
		
		else if (scenario.trim().contains("Perform T8 MTA on Policies")) 
		{
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Executed", 39, "Test_Run_Status");
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), status.toUpperCase(), 39, "Test_Status");
		}
		
		else if (scenario.trim().contains("Perform T8 Cancellation of Policies")) 
		{
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Executed", 40, "Test_Run_Status");
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), status.toUpperCase(), 40, "Test_Status");
		}
					
		else if (scenario.trim().contains("Liability product quote and Buy Journey Testing")) 
		{
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Executed", 41, "Test_Run_Status");
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), status.toUpperCase(), 41, "Test_Status");
		}
				
		else if (scenario.trim().contains("T6 Combined Excess of Loss Liability product quote and Buy Journey Testing")) 
		{
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), "Executed", 42, "Test_Run_Status");
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_Test_Overview_Report"), status.toUpperCase(), 42, "Test_Status");
		//	ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("sheet_T5_Run_Result"), "Test_Run_Status", "Executed", "Quote_reference", "T6 Combined Excess of Loss Liability product quote and Buy Journey Testing");
			
		}			
		
	}
	
	
	public static String get_Date(String startdate, String expecteddate) throws Exception {

		String d = null;
		if (expecteddate.equalsIgnoreCase("Current_Date")) {

			d = JavaUtility.currentdate();

		} else if (expecteddate.equalsIgnoreCase("Last_Year")) {

			d = JavaUtility.getDate_within_interval_from_another_date(-360, startdate);

		} else if (expecteddate.equalsIgnoreCase("6_Months_Ago")) {

			d = JavaUtility.getDate_within_interval_from_another_date(-180, startdate);

		} else if (expecteddate.equalsIgnoreCase("3_Months_Ago")) {

			d = JavaUtility.getDate_within_interval_from_another_date(-90, startdate);

		} else if (expecteddate.equalsIgnoreCase("1_Month_Ago")) {

			d = JavaUtility.getDate_within_interval_from_another_date(-30, startdate);

		} else if (expecteddate.equalsIgnoreCase("1_Day_After_Start_Date")) {

			d = JavaUtility.getDate_within_interval_from_another_date(1, startdate);

		} else if (expecteddate.equalsIgnoreCase("2_Days_Before_Start_Date")) {

			d = JavaUtility.getDate_within_interval_from_another_date(-2, startdate);

		} else if (expecteddate.equalsIgnoreCase("1_Day_Before_Start_Date")) {

			d = JavaUtility.getDate_within_interval_from_another_date(-1, startdate);

		} else if (expecteddate.equalsIgnoreCase("1_Year_Before_Start_Date")) {

			d = JavaUtility.getDate_within_interval_from_another_date(-365, startdate);
		}

		else if (expecteddate.equalsIgnoreCase("2_Years_Before_Start_Date")) {

			d = JavaUtility.getDate_within_interval_from_another_date(-730, startdate);

		} else if (expecteddate.equalsIgnoreCase("1_Month_After_Start_Date")) {

			d = JavaUtility.getDate_within_interval_from_another_date(30, startdate);

		} else if (expecteddate.equalsIgnoreCase("9_Months_After_Start_Date")) {

			d = JavaUtility.getDate_within_interval_from_another_date(270, startdate);

		}else if (expecteddate.equalsIgnoreCase("6_Months_After_Start_Date")) {

			d = JavaUtility.getDate_within_interval_from_another_date(180, startdate);

		} else if (expecteddate.equalsIgnoreCase("3_Months_After_Start_Date")) {

			d = JavaUtility.getDate_within_interval_from_another_date(90, startdate);

		} else {
			d = expecteddate;
		}
		return d;
	}
	
	public static void enterAllDataOnAllPolicyResultSheet(String policynum, String Product, String quotenum, String premiumval,
			 String Expected_Policy_Premium, String binderName, String binderPercentage, String Policy_Start_Date) throws Exception
	{
		int row_number_All_Result_Sheet = ExcelDataUtility.excelTotalRowCount(excelpath, prop.getProperty("sheet_All_Policy_Results"));
				
		ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_All_Policy_Results"), policynum,(row_number_All_Result_Sheet+1), "Policy_Number");
		ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("sheet_All_Policy_Results"), "Product_Name", Product,"Policy_Number", policynum);		
		ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("sheet_All_Policy_Results"), "Quote_reference", quotenum,"Policy_Number", policynum);		
		ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("sheet_All_Policy_Results"), "Actual_Policy_Premium", premiumval,"Policy_Number", policynum);	
		ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("sheet_All_Policy_Results"), "Expected_Policy_Premium", Expected_Policy_Premium,"Policy_Number", policynum);	
		ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("sheet_All_Policy_Results"), "Insurers_Name", binderName,"Policy_Number", policynum);		
		ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("sheet_All_Policy_Results"), "Insurers_Percentage", binderPercentage,"Policy_Number", policynum);				
		ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("sheet_All_Policy_Results"), "NB-Adj_Required", "No","Policy_Number", policynum);
		ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("sheet_All_Policy_Results"), "UpdatingFiledForNB", "N/A","Policy_Number", policynum);
		ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("sheet_All_Policy_Results"), "Update_NB_Field_Value", "N/A","Policy_Number", policynum);
				
/////// String currentDate = JavaUtility.dateFormatAsDDmmYYYYForPurchase();									
		ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("sheet_All_Policy_Results"), "Execution_Date", Policy_Start_Date,"Policy_Number", policynum);
									
		String ExpectedPolicyPremiumValue = Expected_Policy_Premium;
		String ActualPolicyPremiumValue = premiumval;
		
		if(ActualPolicyPremiumValue.equalsIgnoreCase(ExpectedPolicyPremiumValue))
		{
			logger.info("The Actual Premium value '"+premiumval+"' is same as the Expected Premium value '"+ExpectedPolicyPremiumValue+"', So that the Status will be PASS.");
			ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("sheet_All_Policy_Results"), "Status", "PASS","Policy_Number", policynum);					
		}
		else if(ActualPolicyPremiumValue.equalsIgnoreCase(null))
		{
			logger.error("This product is not run, So that the Status will be NOT RUN.");
			ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("sheet_All_Policy_Results"), "Status", "Not Run","Policy_Number", policynum);
		}
		else
		{
			String actualPolicyPremiumValueRemoveGBP = ActualPolicyPremiumValue.replaceAll("GBP  ", "");
			String[] actualPolicyPremiumAmtparts = actualPolicyPremiumValueRemoveGBP.split(Pattern.quote("."));
			String actualPolicyPremiumAmtIntValue = actualPolicyPremiumAmtparts[0];
			
			
			String expectedPolicyPremiumValueRemoveGBP = ExpectedPolicyPremiumValue.replaceAll("GBP  ", "");
			String[] expectedPolicyPremiumAmtparts = expectedPolicyPremiumValueRemoveGBP.split(Pattern.quote("."));
			String expectedPolicyPremiumAmtIntValue = expectedPolicyPremiumAmtparts[0];
			
			if(expectedPolicyPremiumAmtIntValue.equalsIgnoreCase(actualPolicyPremiumAmtIntValue))
			{
				ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("sheet_All_Policy_Results"), "Status", "PASS (Only decimal value mismatch)", "Policy_Number", policynum);
				logger.error("The only integer amount of Actual Policy Premium amount "+actualPolicyPremiumAmtIntValue+" is same as the expected interger amount "+expectedPolicyPremiumAmtIntValue+"");
			}
			else
			{
				logger.error("The Actual Premium value '"+premiumval+"' is not same as the Expected Premium value '"+ExpectedPolicyPremiumValue+"', So that the Status will be FAIL.");
				ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("sheet_All_Policy_Results"), "Status", "FAIL","Policy_Number", policynum);
			}								
		}	
	}
	
	public static void confirm_Cancel_Policy_Alert() throws Exception 
	{
		Thread.sleep(1500);
		int alert_count = TestUtil.CountElement(driver, By.xpath(prop.getProperty("cancel_policy_confirm")));
		if (alert_count > 0) 
		{
			TestUtil.click(driver.findElement(By.xpath(prop.getProperty("cancel_policy_confirm"))));
			ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("MediumExplicitWaitTime")), By.cssSelector(prop.getProperty("preloader")), driver);
			ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("MediumExplicitWaitTime")), By.xpath(prop.getProperty("htmlbodyloading")), driver);
		}
	}
	
	public static void initiateMTA() throws Exception {

//		TestUtil.click(driver.findElement(By.xpath(prop.getProperty("MTA"))));
		JSLibrary.clickElement(driver, driver.findElement(By.xpath(prop.getProperty("MTA"))));
		Thread.sleep(3000);
		ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("RareExplicitWaitTime")),
				By.xpath(prop.getProperty("htmlbodyloading")), driver);
		logger.info("Go to the MTA section.");
		
		JSLibrary.ScrollintoView(driver, driver.findElement(By.xpath(prop.getProperty("NewMtabutton"))));
//		TestUtil.click(driver.findElement(By.xpath(prop.getProperty("NewMtabutton"))));
		JSLibrary.clickElement(driver, driver.findElement(By.xpath(prop.getProperty("NewMtabutton"))));
		Thread.sleep(3000);
		ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("RareExplicitWaitTime")),
				By.xpath(prop.getProperty("htmlbodyloading")), driver);
		logger.info("Click on the New MTA button.");
		
	}
	
	public static void NBAdjRequired(String policynum, String Product, String UpdatingFiledForNB, String NB_Adj_Update_Field_Value_Row) throws Exception
	{
		int row_number_NB_Sheet = ExcelDataUtility.excelTotalRowCount(excelpath, prop.getProperty("sheet_NB_Adjustment"));
		
		ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("sheet_NB_Adjustment"), policynum,(row_number_NB_Sheet+1), "Policy_Number");
		ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("sheet_NB_Adjustment"), "Run_Status", "Y","Policy_Number", policynum);
		ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("sheet_NB_Adjustment"), "Product", Product, "Policy_Number", policynum);
		
		ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("sheet_NB_Adjustment"), "UpdatingFiledForNB", UpdatingFiledForNB, "Policy_Number", policynum);
		ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("sheet_NB_Adjustment"), "NB_Adj_Update_Field_Value_Row", NB_Adj_Update_Field_Value_Row, "Policy_Number", policynum);		
	}
	
	public static void PerformNBAdjByEnterValue(int desiredrow, String RiskFieldsheetName, String fieldElementName, String FieldName, String PolicyNumber) throws Exception
	{
		String Updating_ValueIs = ExcelDataUtility.getCellData(excelpath, prop.getProperty(RiskFieldsheetName), desiredrow, "Update_NB_Field_Value");
		
		if (!((Updating_ValueIs.equalsIgnoreCase("NA")) || (Updating_ValueIs.equalsIgnoreCase("N/A")))) {						
			TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty(fieldElementName))), Updating_ValueIs);
			logger.info("The '"+Updating_ValueIs+"' is update on the '"+FieldName+"' field.");
			ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("sheet_All_Policy_Results"), "UpdatingFiledForNB", FieldName, "Policy_Number", PolicyNumber);
			ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("sheet_All_Policy_Results"), "Update_NB_Field_Value", Updating_ValueIs, "Policy_Number", PolicyNumber);
			ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("sheet_All_Policy_Results"), "NB-Adj_Required", "Yes", "Policy_Number", PolicyNumber);
		}	
	}
	
	public static void PerformNBAdjBySelectValue(int desiredrow, String RiskFieldsheetName, String fieldElementName, String FieldName, String PolicyNumber) throws Exception
	{
		String Updating_ValueIs = ExcelDataUtility.getCellData(excelpath, prop.getProperty(RiskFieldsheetName), desiredrow, "Update_NB_Field_Value");

		if (!((Updating_ValueIs.equalsIgnoreCase("NA")) || (Updating_ValueIs.equalsIgnoreCase("N/A")))) {						
			TestUtil.SelectByText(driver.findElement(By.xpath(prop.getProperty(fieldElementName))), Updating_ValueIs);
			logger.info("The '"+Updating_ValueIs+"' is update on the '"+FieldName+"' field.");
			ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("sheet_All_Policy_Results"), "UpdatingFiledForNB", FieldName, "Policy_Number", PolicyNumber);
			ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("sheet_All_Policy_Results"), "Update_NB_Field_Value", Updating_ValueIs, "Policy_Number", PolicyNumber);
			ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("sheet_All_Policy_Results"), "NB-Adj_Required", "Yes", "Policy_Number", PolicyNumber);
		}	
	}
	
	public static void PerformMTAByEnterValue(String Update_MTA_Field_Value, String FieldPathName, String Update_MTA_Filed_Key, String PolicyNumber) throws Exception
	{
		logger.info("Updating the '"+Update_MTA_Filed_Key+"' field value");
		if (!((Update_MTA_Field_Value.equalsIgnoreCase("NA")) || (Update_MTA_Field_Value.equalsIgnoreCase("N/A")))) {						
			TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty(FieldPathName))), Update_MTA_Field_Value);
			logger.info("The '"+Update_MTA_Field_Value+"' is update on the '"+Update_MTA_Filed_Key+"' field for MTA.");
			ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("sheet_All_Policy_Results"), "MTA_Required", "Yes", "Policy_Number", PolicyNumber);
			ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("sheet_All_Policy_Results"), "UpdatingFiledForMTA", Update_MTA_Filed_Key, "Policy_Number", PolicyNumber);
			ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("sheet_All_Policy_Results"), "Update_MTA_Field_Value", Update_MTA_Field_Value, "Policy_Number", PolicyNumber);					
		}
	}
	
	public static void cancellationRequired(String Status, String policynum, String Product, String Cancel_Reason_type,
			 String Cancel_Reason, String Effective_From, String Cancellation_Date, String Policy_Start_Date) throws Exception
	{
		if (Status.equalsIgnoreCase("Y")) {

			int row_number_Policy_Cancel_Sheet = ExcelDataUtility.excelTotalRowCount(excelpath, prop.getProperty("Sheet_Policy_Cancel"));
			
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("Sheet_Policy_Cancel"), policynum,(row_number_Policy_Cancel_Sheet+1), "Policy_Number");
			ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("Sheet_Policy_Cancel"), "Run_Status", "Y","Policy_Number", policynum);
			ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("Sheet_Policy_Cancel"), "Product", Product, "Policy_Number", policynum);

			ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("Sheet_Policy_Cancel"), "Cancel_Reason_type", Cancel_Reason_type, "Policy_Number", policynum);
			ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("Sheet_Policy_Cancel"), "Cancel_Reason", Cancel_Reason, "Policy_Number", policynum);
			ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("Sheet_Policy_Cancel"), "Effective_From", Effective_From, "Policy_Number", policynum);
			ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("Sheet_Policy_Cancel"), "Cancellation_Date", Cancellation_Date, "Policy_Number", policynum);
			ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("Sheet_Policy_Cancel"), "Policy_Start_Date", Policy_Start_Date, "Policy_Number", policynum);
		}
		
		if (Status.equalsIgnoreCase("N")) {

			int row_number_Policy_Cancel_Sheet = ExcelDataUtility.excelTotalRowCount(excelpath, prop.getProperty("Sheet_Policy_Cancel"));
			
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("Sheet_Policy_Cancel"), policynum,(row_number_Policy_Cancel_Sheet+1), "Policy_Number");
			ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("Sheet_Policy_Cancel"), "Run_Status", "N","Policy_Number", policynum);
			ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("Sheet_Policy_Cancel"), "Product", Product, "Policy_Number", policynum);

			ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("Sheet_Policy_Cancel"), "Cancel_Reason_type", "N/A", "Policy_Number", policynum);
			ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("Sheet_Policy_Cancel"), "Cancel_Reason", "N/A", "Policy_Number", policynum);
			ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("Sheet_Policy_Cancel"), "Effective_From", "N/A", "Policy_Number", policynum);
			ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("Sheet_Policy_Cancel"), "Cancellation_Date", "N/A", "Policy_Number", policynum);
			ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("Sheet_Policy_Cancel"), "Policy_Start_Date", Policy_Start_Date, "Policy_Number", policynum);
		}
	}
	
	public static String returnActualPolicyPremiumAmount(String status, String Updated_Total_GWP_After_NB_Adj, String Actual_Policy_Premium)
	{
		if(status.equalsIgnoreCase("No"))
		{
			logger.info("The NB Adj is selected 'No' on the 'NB-Adj_Required' column");
			return Actual_Policy_Premium;
		}
		else
		{
			logger.info("The NB Adj is selected 'Yes' on the 'NB-Adj_Required' column");
			return Updated_Total_GWP_After_NB_Adj;
		}
			
	}
	
	public static void mtaRequired(String status, String policynum, String Product, String PolicyStartDate, String MTAReasonType, 
			String MTAReason, String MTAEffectiveDate, String UpdatingFiledForMTA, String Update_MTA_Field_Value, String premiumval) throws Exception {
		
		if (status.equalsIgnoreCase("Y")) {

			int row_number_Policy_MTA_Sheet = ExcelDataUtility.excelTotalRowCount(excelpath, prop.getProperty("Sheet_MTA"));
			
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("Sheet_MTA"), policynum,(row_number_Policy_MTA_Sheet+1), "Policy_Number");
			ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("Sheet_MTA"), "Run_Status", "Y","Policy_Number", policynum);
			ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("Sheet_MTA"), "Product", Product, "Policy_Number", policynum);

			ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("Sheet_MTA"), "Policy_Start_Date", PolicyStartDate, "Policy_Number", policynum);
			ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("Sheet_MTA"), "MTA_Reason_Type", MTAReasonType, "Policy_Number", policynum);
			ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("Sheet_MTA"), "MTA_Reason", MTAReason, "Policy_Number", policynum);
			ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("Sheet_MTA"), "MTA_Effective_Date", MTAEffectiveDate, "Policy_Number", policynum);
			ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("Sheet_MTA"), "UpdatingFiledForMTA", UpdatingFiledForMTA, "Policy_Number", policynum);
			ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("Sheet_MTA"), "Update_MTA_Field_Value", Update_MTA_Field_Value, "Policy_Number", policynum);
			ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("Sheet_MTA"), "Total_GWP_Before_MTA", premiumval, "Policy_Number", policynum);
		}
		
		if (status.equalsIgnoreCase("N")) {

			int row_number_Policy_MTA_Sheet = ExcelDataUtility.excelTotalRowCount(excelpath, prop.getProperty("Sheet_MTA"));
			
			ExcelDataUtility.writeToExcel(excelpath, prop.getProperty("Sheet_MTA"), policynum,(row_number_Policy_MTA_Sheet+1), "Policy_Number");
			ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("Sheet_MTA"), "Run_Status", "N","Policy_Number", policynum);
			ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("Sheet_MTA"), "Product", Product, "Policy_Number", policynum);

			ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("Sheet_MTA"), "Policy_Start_Date", PolicyStartDate, "Policy_Number", policynum);
			ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("Sheet_MTA"), "MTA_Reason_Type", "N/A", "Policy_Number", policynum);
			ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("Sheet_MTA"), "MTA_Reason", "N/A", "Policy_Number", policynum);
			ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("Sheet_MTA"), "MTA_Effective_Date", "N/A", "Policy_Number", policynum);
			ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("Sheet_MTA"), "UpdatingFiledForMTA", "N/A", "Policy_Number", policynum);
			ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("Sheet_MTA"), "Update_MTA_Field_Value", "N/A", "Policy_Number", policynum);
			ExcelDataUtility.writeToExcelByRef(excelpath, prop.getProperty("Sheet_MTA"), "Total_GWP_Before_MTA", "N/A", "Policy_Number", policynum);
		}
	}
	
	
	
	
	public static float get_Day(String mtaEffectiveDate) throws Exception {

		float d = 0;
		if (mtaEffectiveDate.equalsIgnoreCase("Current_Date")) {
			d = 0;		
			} 
		else if (mtaEffectiveDate.equalsIgnoreCase("1Month_from_Cur_Date")) 
		{
			d = 30;
		} 
		else if (mtaEffectiveDate.equalsIgnoreCase("6Months_from_Cur_Date")) 
		{
			d = 180;
		} 
		else if (mtaEffectiveDate.equalsIgnoreCase("3Months_from_Cur_Date")) 
		{
			d = 90;
		} 	
		else if (mtaEffectiveDate.equalsIgnoreCase("After_9Month_from_Policy_Start_Date")) 
		{
			d = -270;
		} 
		else if (mtaEffectiveDate.equalsIgnoreCase("After_6Month_from_Policy_Start_Date")) 
		{
			d = -180;
		} 
		else if (mtaEffectiveDate.equalsIgnoreCase("After_3Month_from_Policy_Start_Date")) 
		{
			d = -90;
		} 
		else if (mtaEffectiveDate.equalsIgnoreCase("After_1Month_from_Policy_Start_Date")) 
		{
			d = -30;
		}
		return d;
	}
	
	public static float getDayBwTwoDate(String PolicyStartDate, String mtaEffectiveDate) throws Exception {
				
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy", Locale.ENGLISH);
		Date dateBefore = sdf.parse(PolicyStartDate);
	    Date dateAfter = sdf.parse(mtaEffectiveDate);
	    
	    long timeDiff = Math.abs(dateAfter.getTime() - dateBefore.getTime());
	    long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
	  				
		return daysDiff;
	}
	
	
	public static void captureFloodCheckInformation(String Product, String QuoteNumber, String SheetName) throws Exception
	{
		TestUtil.click(driver.findElement(By.xpath(prop.getProperty("Flood_Check_Button"))));
		logger.info("Flood check button clicked");
		
		ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("CommonExplicitWaitTime")), By.xpath(prop.getProperty("htmlbodyloading")), driver);
		
		try
		{
			TestUtil.click(driver.findElement(By.xpath(prop.getProperty("Open_Property_Accordion"))));
			Thread.sleep(1000);
			
			try
			{					
				String FloodScore = driver.findElement(By.xpath(prop.getProperty("Flood_Score"))).getText();
				logger.info("The Flood score is "+FloodScore);
				
				String FloodPriority = driver.findElement(By.xpath(prop.getProperty("Flood_Priority"))).getText();
				logger.info("The Flood Priority is "+FloodPriority);
													
				TestUtil.Append_to_doc( "Flood Check of " + Product + " product of quote number " + QuoteNumber);
				TestUtil.insert_image_doc(driver);	
				
				TestUtil.takeFloodCheckScreenshot(driver, QuoteNumber, Product);
				
				ExcelDataUtility.writeToExcelByRef(excelpath, SheetName, "Flood_Check_Available", "Yes", "Quote_reference", QuoteNumber);
				ExcelDataUtility.writeToExcelByRef(excelpath, SheetName, "Flood_Score", FloodScore, "Quote_reference", QuoteNumber);
				ExcelDataUtility.writeToExcelByRef(excelpath, SheetName, "Flood_Priority", FloodPriority, "Quote_reference", QuoteNumber);
				
				TestUtil.click(driver.findElement(By.xpath(prop.getProperty("Flood_PopupClose"))));		
			}
			catch(Exception exp)
			{
				String NodataAvailable = driver.findElement(By.xpath(prop.getProperty("FloodCheckNoDataAvalibale"))).getText();
				logger.info("The Flood score have no data available and text is appearing as '"+NodataAvailable+"'");
				
				TestUtil.Append_to_doc("The Flood score have no data available and text is appearing as "+NodataAvailable);						
				TestUtil.insert_image_doc(driver);
				
				TestUtil.takeFloodCheckScreenshot(driver, QuoteNumber, Product);
				
				ExcelDataUtility.writeToExcelByRef(excelpath, SheetName, "Flood_Check_Available", NodataAvailable, "Quote_reference", QuoteNumber);
				ExcelDataUtility.writeToExcelByRef(excelpath, SheetName, "Flood_Score", "N/A", "Quote_reference", QuoteNumber);
				ExcelDataUtility.writeToExcelByRef(excelpath, SheetName, "Flood_Priority", "N/A", "Quote_reference", QuoteNumber);
				
				TestUtil.click(driver.findElement(By.xpath(prop.getProperty("Flood_PopupClose"))));	
			}
		}
		catch(Exception exp)
		{
			String NoPropertyLocationPresent = "No Property Location is present on the Flood Check";
			logger.fatal(NoPropertyLocationPresent);
			
			TestUtil.Append_to_doc(NoPropertyLocationPresent);						
			TestUtil.insert_image_doc(driver);
			
			TestUtil.takeFloodCheckScreenshot(driver, QuoteNumber, Product);
			
			ExcelDataUtility.writeToExcelByRef(excelpath, SheetName, "Flood_Check_Available", NoPropertyLocationPresent, "Quote_reference", QuoteNumber);
			ExcelDataUtility.writeToExcelByRef(excelpath, SheetName, "Flood_Score", "N/A", "Quote_reference", QuoteNumber);
			ExcelDataUtility.writeToExcelByRef(excelpath, SheetName, "Flood_Priority", "N/A", "Quote_reference", QuoteNumber);
			
			TestUtil.click(driver.findElement(By.xpath(prop.getProperty("Flood_PopupCloseBtm4NoRecord"))));	
		}
	}


 public static void directQuotePremiumPage(String PolicyNumber) throws InterruptedException
 {
	 driver.findElement(By.xpath("//span[contains(text(),'Quote Records')]")).click();
		ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("CommonExplicitWaitTime")), By.xpath(prop.getProperty("htmlbodyloading")), driver);
		driver.findElement(By.xpath("//input[@id='gs_quote_no']")).click();
		driver.findElement(By.xpath("//input[@id='gs_quote_no']")).sendKeys(PolicyNumber);
		driver.findElement(By.xpath("//input[@id='gs_quote_no']")).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		TestUtil.click(driver.findElement(By.xpath(prop.getProperty("edit"))));
		ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("MediumExplicitWaitTime")), By.cssSelector(prop.getProperty("preloader")), driver);
		ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("CommonExplicitWaitTime")), By.xpath(prop.getProperty("htmlbodyloading")), driver);
		
 }
 
 public static void fillupT7Details(int desiredrow) throws Exception {

		String Sub_Broker = ExcelDataUtility.getCellData(excelpath, prop.getProperty("sheet_T7_Setup"), desiredrow, "Sub_Broker");
		logger.info("The 'Sub Broker' is selected on T7 cover type is '"+Sub_Broker+"'");
		String Annual_Wageroll = ExcelDataUtility.getCellData(excelpath, prop.getProperty("sheet_T7_Setup"), desiredrow, "Annual_Wageroll");
		logger.info("The 'Annual Wageroll' is selected on T7 cover type is '"+Annual_Wageroll+"'");
		String C_DR_Protection = ExcelDataUtility.getCellData(excelpath, prop.getProperty("sheet_T7_Setup"), desiredrow, "C_DR_Protection");
		logger.info("The 'C&DR_Protection' is selected on T7 cover type is '"+C_DR_Protection+"'");
		
		//Repeated_Steps_T7.Annual_Wageroll_Add(Sub_Broker, Annual_Wageroll, C_DR_Protection);
		TestUtil.click(driver.findElement(By.id(prop.getProperty("riskdetailsnext"))));
	//	TestUtil.click(driver.findElement(By.cssSelector(prop.getProperty("risk_next"))));

		ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("CommonExplicitWaitTime")), By.cssSelector(prop.getProperty("preloader")), driver);
		ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("CommonExplicitWaitTime")), By.xpath(prop.getProperty("htmlbodyloading")), driver);
	}
 
 public static int removeGBPConvertInteger(String columnValue)
 {
	 String removeCurreny = columnValue.replaceAll("GBP ", "");
	 String removeComma = removeCurreny.replaceAll(",", "");
	 Integer removeCurrenyConvertInt = Integer.parseInt(removeComma);
	 return removeCurrenyConvertInt;
 }
 
 public static String addGBP(String columnValue)
 {
	 String addCurreny = "GBP "+columnValue;
	 return addCurreny;
 }
 
 public static String getPolicyExpiryDate(String policyStartDate, String dateFormat, int noOfPolicyTerms)
 {
	 String dateBefore = policyStartDate;
	// logger.info(dateBefore+" is the date before adding days");	 
	// create instance of the SimpleDateFormat that matches the given date
	 SimpleDateFormat sdf = new SimpleDateFormat(dateFormat); 	 
	//create instance of the Calendar class and set the date to the given date  
	 Calendar cal = Calendar.getInstance();  
     try
     {  
        cal.setTime(sdf.parse(dateBefore));  
     }
     catch(ParseException e)
     {  
         e.printStackTrace();  
      }       
  // use add() method to add the days to the given date  
     cal.add(Calendar.DAY_OF_MONTH, noOfPolicyTerms);  
     String dateAfter = sdf.format(cal.getTime());       
   //date after adding three days to the given date  
   //  logger.info(dateAfter+" is the date after adding "+noOfPolicyTerms+" days.");     
     return dateAfter;
 }
 
 
 
	//Newpoint_Coverinfomation date
	public static String fillup_coverinfodate(
			String cover_info_date) throws Exception {

		// Select coverinfo page dat
		if (!((cover_info_date.equalsIgnoreCase("NA")) || (cover_info_date.equalsIgnoreCase("N/A")))) 
		{		

			if (cover_info_date.equalsIgnoreCase("After_3Months_from_Cur_Date")) 
			{
				cover_info_date = JavaUtility.getDate_from_certain_interval(90);
			} 
			else if (cover_info_date.equalsIgnoreCase("After_6Months_from_Cur_Date")) 
			{
				cover_info_date = JavaUtility.getDate_from_certain_interval(180);
			}
			else if (cover_info_date.equalsIgnoreCase("After_1Month_from_Cur_Date")) 
			{
				cover_info_date = JavaUtility.getDate_from_certain_interval(30);
			}
			else if (cover_info_date.equalsIgnoreCase("Before_1Month_from_Cur_Date")) 
			{
				cover_info_date = JavaUtility.getDate_from_certain_interval(-30);
			}
			else if (cover_info_date.equalsIgnoreCase("Before_6Month_from_Cur_Date")) 
			{
				cover_info_date = JavaUtility.getDate_from_certain_interval(-180);
			}
			else if (cover_info_date.equalsIgnoreCase("Before_9Month_from_Cur_Date")) 
			{
				cover_info_date = JavaUtility.getDate_from_certain_interval(-270);
			}
			else if (cover_info_date.equalsIgnoreCase("Before_1Year_from_Cur_Date")) 
			{
				cover_info_date = JavaUtility.getDate_from_certain_interval(-365);
			}
			else if (cover_info_date.equalsIgnoreCase("Current_Date")) 
			{
				cover_info_date = JavaUtility.getDate_from_certain_interval(0);
			}
			else
			{
				logger.info("Please check the cover info date");
			}
			
	
			TestUtil.click(driver.findElement(By.cssSelector(prop.getProperty("Prior_Pending_Litigation_Date"))));
			Thread.sleep(1000);
						
			TestUtil.SelectByText(driver.findElement(By.cssSelector(prop.getProperty("StartYear"))), JavaUtility.returnYearfromDate(cover_info_date));
			TestUtil.SelectByText(driver.findElement(By.cssSelector(prop.getProperty("StartMonth"))), JavaUtility.returnMonthfromDate(cover_info_date));
		
			driver.findElement(By.xpath(prop.getProperty("StartDateFirstPart")
					+ JavaUtility.returnDayhfromDate(cover_info_date) + prop.getProperty("StartDateSecondPart")))
					.click();
			
			
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			
			logger.info("Select the '"+cover_info_date+"' on the 'Policy Start Date' field.");

			Thread.sleep(2000);

			

		}
		return cover_info_date;
	}
 
 
 
 
}
