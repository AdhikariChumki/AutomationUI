package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.Units;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import configuration.Base;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class TestUtil extends Base {

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 5;

	public static Boolean isActionRequired(String value) {

		Boolean flag = true;
		if (((value.equalsIgnoreCase("NA")) || (value.equalsIgnoreCase("N/A")))) {

			flag = false;
		}

		return flag;
	}

	public static void clear(WebElement ele) {

		ele.clear();

	}

	public static void Entertext(WebElement ele, String value) {

		ele.clear();
		ele.sendKeys(value);

	}

	public static void Entertext(WebElement ele, Keys k) {

		ele.sendKeys(k);

	}

	public static void click(WebElement ele, String value) {

		ele.click();

	}

	public static void click(WebElement ele) {

		ele.click();

	}

	public static void SelectByText(WebElement ele, String text) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (!((text.equalsIgnoreCase("NA")) || (text.equalsIgnoreCase("N/A"))))

		{

			Select select = new Select(ele);
			select.selectByVisibleText(text);

		}

	}

	public static void SelectByValue(WebElement ele, String value) {

		Select select = new Select(ele);
		select.selectByValue(value);

	}

	public static void SelectByIndex(WebElement ele, int index) {
		Select select = new Select(ele);
		select.selectByIndex(index);

	}

	public static String returnSelectedOption(WebElement ele) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Select select = new Select(ele);
		WebElement dropdown = select.getFirstSelectedOption();
		String selectedoption = dropdown.getText();
		return selectedoption;

	}

	public static void ChooseElementBasedOnValue(By by, String value) {

		List<WebElement> elements = driver.findElements(by);
		for (WebElement e : elements) {

			if (e.getText().equalsIgnoreCase(value)) {

				e.click();
				break;
			}

		}

	}

	public static String Gettext(WebElement ele) {

		String text_raw = ele.getText();
		String text = text_raw.trim();
		return text;
	}

	public static String GetAttributeValue(WebElement ele, String attributename) {

		String attributevalue = ele.getAttribute(attributename);
		return attributevalue;
	}

	public static void moveToElement(WebDriver driver, WebElement ele) {
		Actions actions = new Actions(driver);
		actions.moveToElement(ele).build().perform();

	}

	public static void moveToElementAndClick(WebDriver driver, WebElement ele) {
		Actions actions = new Actions(driver);
		actions.moveToElement(ele).click().build().perform();

	}

	public static ArrayList<HashMap<String, String>> storeExcelDataToHashMap(String filepath, String sheetname)
			throws Exception {

		Workbook w;
		Sheet s;

		FileInputStream f = new FileInputStream(filepath);
		w = new XSSFWorkbook(f);
		/* s=w.getSheetAt(sheet_index); */
		s = w.getSheet(sheetname);
		int totalrow = s.getLastRowNum();
		/* System.out.println(totalrow); */
		int totalcol = s.getRow(0).getLastCellNum();

		ArrayList<HashMap<String, String>> records = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> data;

		for (int i = 1; i <= totalrow; i++) {
			data = new HashMap<String, String>();
			for (int j = 0; j < totalcol; j++) {

				String value = s.getRow(i).getCell(j).getStringCellValue();
				String key = s.getRow(0).getCell(j).getStringCellValue();
				data.put(key, value);
			}
			records.add(data);
		}
		w.close();
		return records;

	}

	public static int CountElement(WebDriver driver, By b) {
		List<WebElement> li = driver.findElements(b);
		int count = li.size();
		return count;
	}

	public static void refreshPage(WebDriver driver) {
		driver.navigate().refresh();

	}

	public static void selectOptionFromList(WebDriver driver, By b, String option) {
		List<WebElement> li = driver.findElements(b);
		for (WebElement ele : li) {

			String txt = ele.getText();
//			System.out.println("The Option Value is :" + txt);
			logger.info("The Option Value is :" + txt);
			if (txt.equalsIgnoreCase(option)) {
				ele.click();
				break;
			}
		}
	}

	public static void Append_to_doc(String text) throws Exception {
		File file = new File(System.getProperty("user.dir") + prop.getProperty("EvidencefilePath"));

		double bytes;
		if (file.exists()) {
			bytes = file.length();
//			logger.info("File size is " + bytes);
		} else {

			bytes = 0.0;
		}

		if (bytes > 0.0) {

			XWPFDocument document = new XWPFDocument(
					OPCPackage.open(System.getProperty("user.dir") + prop.getProperty("EvidencefilePath")));
			// create Paragraph
			XWPFParagraph paragraph = document.createParagraph();
			XWPFRun run = paragraph.createRun();
			run.setText(text);
			// run.addBreak();
			Write_to_doc(document);
			document.close();
		}

		else {

			create_doc(text);

		}

	}

	public static void Write_to_doc(XWPFDocument doc) throws Exception {

		String filename = System.getProperty("user.dir") + prop.getProperty("EvidencefilePath");
		String tempfilename = System.getProperty("user.dir") + prop.getProperty("TempEvidencefilePath");

		FileOutputStream out = new FileOutputStream(new File(tempfilename));
		doc.write(out);
		out.flush();
		out.close();
		Files.delete(Paths.get(filename));
		new File(tempfilename).renameTo(new File(filename));

	}

	public static void create_doc(String text) throws Exception {

		// Blank Document
		XWPFDocument document = new XWPFDocument();
		// Write the Document in file system
		FileOutputStream out = new FileOutputStream(
				new File(System.getProperty("user.dir") + prop.getProperty("EvidencefilePath")));

		// create Paragraph
		XWPFParagraph paragraph = document.createParagraph();
		XWPFRun run = paragraph.createRun();
		// run.addBreak();
		run.setText(text);
		document.write(out);
		out.flush();
		out.close();
		document.close();

	}

	public static void delete_doc() throws Exception {

		File file = new File(System.getProperty("user.dir") + prop.getProperty("EvidencefilePath"));

		String filename = System.getProperty("user.dir") + prop.getProperty("EvidencefilePath");
		if (file.exists()) {
			Files.delete(Paths.get(filename));
		}
	}

	public static String take_screenshot(WebDriver driver) throws Exception {

		File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String filename = System.getProperty("user.dir") + prop.getProperty("ScreenshotDirectory") + "/"
				+ System.currentTimeMillis() + ".png";
		File destinationPath = new File(filename);
		FileUtils.copyFile(sourcePath, destinationPath);
		return filename;

	}
	
	public static void createNewFolder(WebDriver driver, String Product, String folderLocation)
	{
//		String folderLocation = "/FloodCheckScreenshots/Flood_Check-Screenshots-For-All_Products/";
		String folderName = Product;
		String NewFolderName = System.getProperty("user.dir") + folderLocation + "/" + folderName;
		File directory = new File(NewFolderName);
		directory.mkdir();
		logger.info("A folder '"+folderName+"' is created under the '"+folderLocation+"' location.");
		
	}
	
	public static String takeFloodCheckScreenshot(WebDriver driver, String quotenum, String Product) throws IOException
	{
		File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		String folderName1 = "/FloodCheckScreenshots/Flood_Check-Screenshots-For-All_Products/";
		String folderName2 = Product;
		String folderLocation = folderName1.concat(folderName2);
//		logger.info("The image path location "+folderLocation);
		String imageNameWithoutRemoveSlash = quotenum + ".png";
//		logger.info("The image name is "+imageNameWithoutRemoveSlash);
		String imageName = imageNameWithoutRemoveSlash.replaceAll("/", "");
		String fileName = System.getProperty("user.dir") + folderLocation + "/" + imageName;
		File destinationPath = new File(fileName);
		FileUtils.copyFile(sourcePath, destinationPath);
		logger.info("The '"+imageNameWithoutRemoveSlash+"' screenshot is added under the '"+folderLocation+"' folder.");
		return fileName;
	}

	public static String take_fullpage_screenshot(WebDriver driver) throws Exception {

		String filename = System.getProperty("user.dir") + prop.getProperty("ScreenshotDirectory") + "/"
				+ System.currentTimeMillis() + ".png";

		Screenshot fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100))
				.takeScreenshot(driver);
		ImageIO.write(fpScreenshot.getImage(), "PNG", new File(filename));

		return filename;

	}

	public static void insert_image_doc(WebDriver driver) throws Exception {

		String filename = take_screenshot(driver);
		FileInputStream pic = new FileInputStream(new File(filename));

		XWPFDocument document = new XWPFDocument(
				OPCPackage.open(System.getProperty("user.dir") + prop.getProperty("EvidencefilePath")));
		// create Paragraph
		XWPFParagraph paragraph = document.createParagraph();
		XWPFRun run = paragraph.createRun();
		run.addBreak();
		run.addPicture(pic, XWPFDocument.PICTURE_TYPE_PNG, filename, Units.toEMU(250), Units.toEMU(250));
		Write_to_doc(document);
		document.close();

	}

	public static boolean waitForNewWindow(WebDriver driver, int attempt) {
		boolean flag = false;
		int counter = 0;
		while (!flag) {
			try {
				Set<String> winId = driver.getWindowHandles();
				if (winId.size() > 1) {
//					System.out.println("got the new window at" + counter);
					logger.info("Got the new window at" + counter);
					flag = true;
					return flag;
				}
				Thread.sleep(1000);
//				System.out.println("trying :" + counter);
//				System.out.println("flag :" + flag);
				logger.info("Trying :" + counter);
				logger.info("Flag :" + flag);
				counter++;
				if (counter > attempt) {
					return flag;
				}
			} catch (Exception e) {
//				System.out.println(e.getMessage());
				logger.error(e.getMessage());
				return false;
			}
		}
		return flag;
	}
	
	public static void select_Previous_Text_and_Enter_new_text(WebElement ele, String value) {
		ele.click();
		ele.sendKeys(Keys.chord(Keys.CONTROL,"a"));
		ele.sendKeys(value);	
	}
	

}
