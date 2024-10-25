package stepDefinations;

import org.openqa.selenium.By;

import configuration.Base;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import utility.ExplicitWait;
import utility.TestUtil;

public class Backgroud_common_Steps extends Base {

	static int execution = 0;

	@Given("^User is on Login Page$")
	public void user_in_on_Login_Page() throws Throwable {
		logger.info("The title of the page is ========================");
		ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("CommonExplicitWaitTime")), By.xpath(prop.getProperty("body")), driver);
	}
	  @Then("^Login to the site with \"(.*)\" and \"(.*)\"$")
	//@Then("Login to the site with {string} and {string}")
	public void user_Login_Login_In_to_Application_with_and(String strArg1, String strArg2) throws Throwable {

		TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("username"))), strArg1);
		TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("password"))), strArg2);
		TestUtil.click(driver.findElement(By.xpath(prop.getProperty("loginBtn"))));
		ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("CommonExplicitWaitTime")),
				By.xpath(prop.getProperty("htmlbodyloading")), driver);
		
		int change_noti_count=TestUtil.CountElement(driver,By.xpath(prop.getProperty("change_notification_close")));
		if(change_noti_count>0) {
			
			TestUtil.click(driver.findElement(By.xpath(prop.getProperty("change_notification_close"))));
			
		} 

		if (Backgroud_common_Steps.execution == 0) {
			TestUtil.Append_to_doc("Logged in to site with the following user id " + strArg1);
			Backgroud_common_Steps.execution++;
		}
	}

	@Then("Home Page was populated")
	public void home_page_was_populated() throws Throwable {
		String title = driver.getTitle();
		System.out.println(title);
		//logger.info("The title of the page is "+title);
	}

	/*@Given("^user Click Client Search Link$")
	public void user_Click_Client_Search_Link() throws Throwable {
		TestUtil.click(driver.findElement(By.xpath(prop.getProperty("clientsearch"))));
		if (((Hooks.execution == 1) && (Base.os.equalsIgnoreCase("Linux")))
				|| ((Hooks.execution == 1) && (prop.getProperty("Dynamic_user_required").equalsIgnoreCase("Yes")))) {
			logger.info("Now create a new client");
			Repeated_Steps.createNewClientData();
		}
	}

	@When("^user Click on Policy Records Link$")
	public void click_policyRecordsLink() throws Throwable {
		TestUtil.click(driver.findElement(By.xpath(prop.getProperty("policyrecord"))));

	}

	@Then("^clicks the logout button$")
	public void logout() throws Throwable {
		TestUtil.click(driver.findElement(By.xpath(prop.getProperty("logout_link"))));
		ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("CommonExplicitWaitTime")),
				By.xpath(prop.getProperty("htmlbodyloading")), driver);
		ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("CommonExplicitWaitTime")),
				By.cssSelector(prop.getProperty("preloader")), driver);		
		driver.manage().deleteAllCookies();
		Thread.sleep(3000);
		TestUtil.refreshPage(driver);
		ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("CommonExplicitWaitTime")),
				By.xpath(prop.getProperty("htmlbodyloading")), driver);
		ExplicitWait.wait_for_element_present(Long.parseLong(prop.getProperty("CommonExplicitWaitTime")),
				By.cssSelector(prop.getProperty("preloader")), driver);
		Thread.sleep(2000);
	}*/

}
