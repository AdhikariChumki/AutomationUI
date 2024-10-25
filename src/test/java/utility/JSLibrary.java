package utility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JSLibrary {

	public static void SelectDateByJs(WebDriver driver, WebElement element, String dateValue) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].setAttribute('value','" + dateValue + "');", element);
	}

	public static void ScrollintoView(WebDriver driver, WebElement element) {

		JavascriptExecutor js = ((JavascriptExecutor) driver);

		js.executeScript("arguments[0].scrollIntoView(true);", element);

	}

	public static void scroll(WebDriver driver, int c) {

		JavascriptExecutor js = ((JavascriptExecutor) driver);

		js.executeScript("window.scrollBy(0," + c + ")", "");
	}

	public static void scrollToTop(WebDriver driver) {

		JavascriptExecutor js = ((JavascriptExecutor) driver);

		js.executeScript("window.scrollTo(0, 0)");
	}
	
	public static void scrollToButtom(WebDriver driver) {

		JavascriptExecutor js = ((JavascriptExecutor) driver);

		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	
	public static void HighlightElement(WebDriver driver,WebElement element) {

		JavascriptExecutor js = ((JavascriptExecutor) driver);

		js.executeScript("arguments[0].style.border='3px solid red'",element);
	}
	
	public static void clickElement(WebDriver driver,WebElement element) {
        
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }

}
