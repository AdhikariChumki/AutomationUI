package utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExplicitWait {

	

	public static WebDriverWait wait;

	public static void wait_for_element_present(long time, By by, WebDriver driver) {

		wait = new WebDriverWait(driver,time);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}
	
	
	public static void wait_for_element_visible(long time, By by, WebDriver driver) {

		wait = new WebDriverWait(driver,time);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		
		
	}
	
	
	public static void wait_for_element_visible(long time,WebElement ele, WebDriver driver) {

		wait = new WebDriverWait(driver,time);
		wait.until(ExpectedConditions.visibilityOf(ele));
		
	}
	
	
	public static void invisibility_element_visible(long time,WebElement ele, WebDriver driver) {

		wait = new WebDriverWait(driver,time);
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	
	public static void invisibility_element_visible(long time,By by, WebDriver driver) {

		wait = new WebDriverWait(driver,time);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}
	
	
	public static void wait_for_element_clickable(long time, By by, WebDriver driver) {

		wait = new WebDriverWait(driver,time);
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}
	
	public static void wait_for_element_clickable(long time, WebElement ele, WebDriver driver) {

		wait = new WebDriverWait(driver,time);
		wait.until(ExpectedConditions.elementToBeClickable(ele));
		
	}
	
	public static void wait_for_element_selectable(long time, WebElement ele, WebDriver driver) {

		wait = new WebDriverWait(driver,time);
		wait.until(ExpectedConditions.elementToBeSelected(ele));
		
	}
	
	public static void wait_for_element_stale(long time, WebElement ele, WebDriver driver) {

		wait = new WebDriverWait(driver,time);
		wait.until(ExpectedConditions.stalenessOf(ele));
		
	}
	
	
	public static void wait_for_element_refresh(long time, By by, WebDriver driver) {

		wait = new WebDriverWait(driver,time);
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(by)));
		
	}
	
	
	
	
	
	
	
	
}
