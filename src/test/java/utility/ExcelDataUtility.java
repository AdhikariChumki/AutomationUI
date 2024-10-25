package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import configuration.Base;

public class ExcelDataUtility extends Base {

	static Workbook w = null;
	static Sheet s = null;
	static Row r = null;
	static Cell c = null;
	static FileInputStream f = null;

	public static Workbook initializeDataSource(String filename) throws Exception {

		f = new FileInputStream(new File(filename));
		w = new XSSFWorkbook(f);
		return w;
	}

	public static int excelTotalRowCount(String filename, String sheetname) throws Exception {

		w = initializeDataSource(filename);
		s = w.getSheet(sheetname);
		int rowcount = s.getLastRowNum();
		return rowcount;
	}

	public static int excelTotalColumnCount(String filename, String sheetname) throws Exception {

		w = initializeDataSource(filename);
		s = w.getSheet(sheetname);
		int colcount = s.getRow(0).getLastCellNum();
		return colcount;
	}
	
	public static int excelTotalColumnCountOfParticularRow(String filename, String sheetname, int i) throws Exception {

		w = initializeDataSource(filename);
		s = w.getSheet(sheetname);
		int colcount = s.getRow(i).getLastCellNum();
		return colcount;
	}

	public static String getCellData(String filename, String sheetname, int rownum, int colcum) throws Exception {

		w = initializeDataSource(filename);
		s = w.getSheet(sheetname);
		r = s.getRow(rownum);
		c = r.getCell(colcum);
		DataFormatter formatter = new DataFormatter();
		String value;
		try {
			value = formatter.formatCellValue(c);
		} catch (Exception e) {

			value = "NA";
			System.out.println(e.getMessage());
		}
		if (value.isEmpty()) {
			value = "NA";
		}

		// String value = s.getRow(rownum).getCell(colcum).getStringCellValue();
		return value;
	}

	public static String getCellData(String filename, String sheetname, int rownum, String colname) throws Exception {

		w = initializeDataSource(filename);
		s = w.getSheet(sheetname);
		int desiredcolnum = columnNumByColName(filename, sheetname, colname);
		r = s.getRow(rownum);
		c = r.getCell(desiredcolnum);
		DataFormatter formatter = new DataFormatter();
		String value;
		try {

			value = formatter.formatCellValue(c);
		} catch (Exception e) {
			value = "NA";
			System.out.println(e.getMessage());
		}

		if (value.isEmpty()) {
			value = "NA";
		}

		// String value = s.getRow(rownum).getCell(desiredcolnum).getStringCellValue();
		return value;
	}

	public static int columnNumByColName(String filename, String sheetname, String colname) throws Exception {

		int desiredcolnum = 0;
		int colcount = excelTotalColumnCount(filename, sheetname);

		for (int i = 0; i < colcount; i++) {

			String colval = s.getRow(0).getCell(i).getStringCellValue();
			if (colval.equalsIgnoreCase(colname)) {
				desiredcolnum = i;
				break;
			}
		}

		return desiredcolnum;
	}

	public static int rowNumByColValue(String filename, String sheetname, String colname, String colvalue)
			throws Exception {

		int desiredcolnum = columnNumByColName(filename, sheetname, colname);
		int totalrow = excelTotalRowCount(filename, sheetname);
		int desiredrownum = 0;

		for (int i = 1; i <= totalrow; i++) {
			String cellval = s.getRow(i).getCell(desiredcolnum).getStringCellValue();
			if (cellval.equalsIgnoreCase(colvalue)) {
				desiredrownum = i;
				break;
			}
		}

		return desiredrownum;
	}

	public static void writeToExcelByRef(String filename, String sheetname, String writablecolname, String writevalue,
			String refcol, String refcolvalue) throws Exception {
		int desiredwritablecolnum = columnNumByColName(filename, sheetname, writablecolname);
		int desiredrownum = rowNumByColValue(filename, sheetname, refcol, refcolvalue);
		s.getRow(desiredrownum).createCell(desiredwritablecolnum, CellType.STRING).setCellValue(writevalue);
		FileOutputStream fwrite = new FileOutputStream(new File(filename));
		w.write(fwrite);
		fwrite.close();
		f.close();

	}

	public static void writeToExcel(String filename, String sheetname, String writevalue, int rownum, int colnum)
			throws Exception {

		w = initializeDataSource(filename);
		s = w.getSheet(sheetname);
		if (colnum == 0) {
			s.createRow(rownum).createCell(colnum, CellType.STRING).setCellValue(writevalue);
		} else {
			s.getRow(rownum).createCell(colnum, CellType.STRING).setCellValue(writevalue);
		}
		FileOutputStream fwrite = new FileOutputStream(new File(filename));
		w.write(fwrite);
		fwrite.close();
		f.close();

	}

	public static void writeToExcel(String filename, String sheetname, String writevalue, int rownum, String colname)
			throws Exception {

		w = initializeDataSource(filename);
		s = w.getSheet(sheetname);
		int colnum = columnNumByColName(filename, sheetname, colname);
		if (colnum == 0) {
			s.createRow(rownum).createCell(colnum, CellType.STRING).setCellValue(writevalue);
		} else {
			s.getRow(rownum).createCell(colnum, CellType.STRING).setCellValue(writevalue);
		}
		FileOutputStream fwrite = new FileOutputStream(new File(filename));
		w.write(fwrite);
		fwrite.close();
		f.close();

	}

	// This function will mainly required for filling the nb adjustment sheet of
	// alesco project

	public static void writeToExcel(String filename, String sheetname, String writevalue, String row, String colname)
			throws Exception {

		w = initializeDataSource(filename);
		s = w.getSheet(sheetname);
		int colnum = columnNumByColName(filename, sheetname, colname);
		int rownum = Integer.parseInt(row);
		s.getRow(rownum).createCell(colnum, CellType.STRING).setCellValue(writevalue);
		FileOutputStream fwrite = new FileOutputStream(new File(filename));
		w.write(fwrite);
		fwrite.close();
		f.close();

	}

	public static ArrayList<HashMap<String, String>> storeExcelDataToHashMap(String filename, String sheetname)
			throws Exception {

		w = initializeDataSource(filename);
		/* s=w.getSheetAt(sheet_index); */
		s = w.getSheet(sheetname);
		int totalrow = excelTotalRowCount(filename, sheetname);
//		System.out.println(totalrow);
		logger.info("The total row in "+sheetname+" sheet is "+totalrow);
		int totalcol = excelTotalColumnCount(filename, sheetname);
//		System.out.println(totalcol);
		logger.info("The total column in "+sheetname+" sheet is "+totalcol);
		ArrayList<HashMap<String, String>> records = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> data;

		for (int i = 1; i <= totalrow; i++) {
			data = new HashMap<String, String>();
			for (int j = 0; j < totalcol; j++) {

				String value = getCellData(filename, sheetname, i, j);
				String key = getCellData(filename, sheetname, 0, j);
				data.put(key, value);
			}
			records.add(data);
		}
		w.close();
		return records;

	}

	public static void deleteColumnData(String filename, String sheetname, String colname) throws Exception {

		w = initializeDataSource(filename);
		s = w.getSheet(sheetname);
		int totalrow = excelTotalRowCount(filename, sheetname);
		int colnum = columnNumByColName(filename, sheetname, colname);
//		System.out.println("The total row in sheet "+totalrow);
		logger.info("The total row in sheet"+totalrow);
		for (int i = 1; i <= totalrow; i++) {

			s.getRow(i).createCell(colnum, CellType.STRING).setCellValue(" ");

		}

		FileOutputStream fwrite = new FileOutputStream(new File(filename));
		w.write(fwrite);
		fwrite.close();
		f.close();

	}

	public static void deleteFullDataExceptHeading(String filename, String sheetname) throws Exception {

		w = initializeDataSource(filename);
		s = w.getSheet(sheetname);
		int totalrow = excelTotalRowCount(filename, sheetname);
//		System.out.println("The total row in sheet "+totalrow);
		logger.info("The total row in "+sheetname+" sheet: "+totalrow);
		for (int i = 1; i <= totalrow; i++) {

			s.removeRow(s.getRow(i));

		}

		FileOutputStream fwrite = new FileOutputStream(new File(filename));
		w.write(fwrite);
		fwrite.close();
		f.close();

	}

	public static void copyDataFromOtherSheet(String filename, String target_sheetname, String origin_sheetname)
			throws Exception {

		w = initializeDataSource(filename);
		s = w.getSheet(origin_sheetname);
		int totalrow = excelTotalRowCount(filename, origin_sheetname);
		int totalcol = excelTotalColumnCount(filename, origin_sheetname);
//		System.out.println("Total row is " + totalrow);
//		System.out.println("Total column is " + totalcol);
		logger.info("Total row is " + totalrow);
		logger.info("Total column is " + totalcol);
		for (int i = 1; i <= totalrow; i++) {

			for (int j = 0; j < totalcol; j++) {

				String data = getCellData(filename, origin_sheetname, i, j);
				String colname = getCellData(filename, origin_sheetname, 0, j);

				writeToExcel(filename, target_sheetname, data, i, colname);

			}

		}

	}

	public static void backupDataToOtherSheet(String filename, String origin_sheetname, String backup_sheetname)
			throws Exception {

		w = initializeDataSource(filename);
		s = w.getSheet(origin_sheetname);
		int totalrow = excelTotalRowCount(filename, origin_sheetname);
		int totalcol = excelTotalColumnCount(filename, origin_sheetname);
//		System.out.println("Total row is " + totalrow);
//		System.out.println("Total column is " + totalcol);
		logger.info("Total row is " + totalrow);
		logger.info("Total column is " + totalcol);

		int totalrow_backup = excelTotalRowCount(filename, backup_sheetname);
//		System.out.println("Bckupsheet Total Row Count is " + totalrow_backup);
		logger.info("Bckupsheet Total Row Count is " + totalrow_backup);

		for (int i = 0; i <= totalrow; i++) {

			for (int j = 0; j < totalcol; j++) {

				String data = getCellData(filename, origin_sheetname, i, j);

				if (totalrow_backup == 0) {
					writeToExcel(filename, backup_sheetname, data, i, j);

				} else {

					writeToExcel(filename, backup_sheetname, data, i + totalrow_backup + 3, j);

				}

			}

		}
	}
	
		
	public static void enterDataOnAllResultSheet(String filename, String sheetname, int rowNumber, String quoteNo, String policyNo, String premiumValue) throws Exception
	{
		String currentDate = JavaUtility.dateFormatAsDDmmYYYYForPurchase();
		
//		ExcelDataUtility.deleteFullDataExceptHeading(filename, prop.getProperty("sheet_All_Results"));
						
		ExcelDataUtility.writeToExcel(filename, sheetname, quoteNo, rowNumber, "Quote_reference");
		logger.info("Quote reference '"+quoteNo+"' is added on the All result sheet");
		
		ExcelDataUtility.writeToExcel(filename, sheetname, currentDate, rowNumber, "Execution_Date");
		logger.info("The current date '"+currentDate+"' is added on the All result sheet");
		
		ExcelDataUtility.writeToExcel(filename, sheetname, policyNo, rowNumber, "Policy_Number");
		logger.info("Policy Number '"+policyNo+"' is added on the All result sheet");
		
		ExcelDataUtility.writeToExcel(filename, sheetname, premiumValue, rowNumber, "Actual_Policy_Premium");
		logger.info("Policy Premium '"+premiumValue+"' is added on the All result sheet");
		
/////////*********************************************************************************************************
/*		
		ExcelDataUtility.writeToExcelByRef(filename, sheetname, "Execution_Date", currentDate, "Quote_reference", quoteNo);
		logger.info("The current date '"+currentDate+"' is added on the All result sheet");


//		String ProductName = ExcelDataUtility.getCellData(filename, TakeDataFromSheet, TakeDataFromRowNumber, "Product");
//		String ExpectedPolicyPremiumValue = ExcelDataUtility.getCellData(filename, TakeDataFromSheet, TakeDataFromRowNumber, "Expected_Policy_Premium");
		
		
/*		
		ExcelDataUtility.writeToExcelByRef(filename, sheetname,
				"Product_Name", ProductName, "Quote_reference", quoteNo);
		logger.info("Product Name '"+ProductName+"' is added on the All result sheet");
		
		ExcelDataUtility.writeToExcelByRef(filename, sheetname,
				"Expected_Policy_Premium", ExpectedPolicyPremiumValue, "Quote_reference", quoteNo);
		logger.info("Expected Policy Premium Value '"+ProductName+"' is added on the All result sheet");	
		
*/		
		
/*
		ExcelDataUtility.writeToExcelByRef(filename, sheetname, "Policy_Number", policyNo, "Quote_reference", quoteNo);
		logger.info("Policy Number '"+policyNo+"' is added on the All result sheet");
		
		ExcelDataUtility.writeToExcelByRef(filename, sheetname, "Actual_Policy_Premium", premiumValue, "Quote_reference", quoteNo);						
		logger.info("Policy Premium '"+premiumValue+"' is added on the All result sheet");
*/		
/////////*********************************************************************************************************
		
		String ExpectedPolicyPremiumValue = ExcelDataUtility.getCellData(filename, sheetname, rowNumber, "Expected_Policy_Premium");
		String ActualPolicyPremiumValue = ExcelDataUtility.getCellData(filename, sheetname, rowNumber, "Actual_Policy_Premium");
		
		if(ActualPolicyPremiumValue.equalsIgnoreCase(ExpectedPolicyPremiumValue))
		{
			logger.info("The Actual Premium value is same as the Expected Premium value, So that the Status will be PASS.");
			ExcelDataUtility.writeToExcel(filename, sheetname, "PASS", rowNumber, "Status");						
		}
		else if(ActualPolicyPremiumValue.equalsIgnoreCase(null))
		{
			logger.error("This product is not run, So that the Status will be NOT RUN.");
			ExcelDataUtility.writeToExcel(filename, sheetname, "NOT RUN", rowNumber, "Status");			
		}
		else
		{			
			logger.error("The Actual Premium value is not same as the Expected Premium value, So that the Status will be FAIL.");
			ExcelDataUtility.writeToExcel(filename, sheetname, "FAIL", rowNumber, "Status");					
		}		
	}
	
	public static void enterInsurerNamePrecentageOnAllResultSheet(String filename, String sheetname, int rowNumber, String insurerName, String insurerPercentage) throws Exception
	{
		ExcelDataUtility.writeToExcel(filename, sheetname, insurerName, rowNumber, "Insurers_Name");
		logger.info("Insurer name '"+insurerName+"' is added on the All result sheet");
		
		ExcelDataUtility.writeToExcel(filename, sheetname, insurerPercentage, rowNumber, "Insurers_Percentage");
		logger.info("Insurer percenatge '"+insurerPercentage+"' is added on the All result sheet");				
	}
	
	
	public static void enterProductExpPolicyPremiumInsurerNamePrecentageOnAllResultSheet(String filename, String sheetname, int rowNumber, 
			String ProductName, String ExpectedPolicyPremium, String insurerName, String insurerPercentage) throws Exception {
	
		ExcelDataUtility.writeToExcel(filename, sheetname, ProductName, rowNumber, "Product_Name");
		logger.info("'"+ProductName+"' product is added on the All result sheet");		
		
		ExcelDataUtility.writeToExcel(filename, sheetname, ExpectedPolicyPremium, rowNumber, "Expected_Policy_Premium");
		logger.info("'"+ExpectedPolicyPremium+"' expected policy premium is added on the All result sheet");	
					
		ExcelDataUtility.writeToExcel(filename, sheetname, insurerName, rowNumber, "Insurers_Name");
		logger.info("Insurer name '"+insurerName+"' is added on the All result sheet");
		
		ExcelDataUtility.writeToExcel(filename, sheetname, insurerPercentage, rowNumber, "Insurers_Percentage");
		logger.info("Insurer percenatge '"+insurerPercentage+"' is added on the All result sheet");		
			
	}
	
	public static void enterProductExpPolicyPremiumQuoteNoPolicyNoPremiumValueInsurerNamePrecentageOnAllResultSheet(String filename, String sheetname, int rowNumber, 
			String ProductName, String ExpectedPolicyPremium, String quoteNo, String policyNo, String premiumValue,
			String insurerName, String insurerPercentage) throws Exception {
	
		ExcelDataUtility.writeToExcel(filename, sheetname, ProductName, rowNumber, "Product_Name");
		logger.info("'"+ProductName+"' product is added on the All result sheet");		
		
		ExcelDataUtility.writeToExcel(filename, sheetname, ExpectedPolicyPremium, rowNumber, "Expected_Policy_Premium");
		logger.info("'"+ExpectedPolicyPremium+"' expected policy premium is added on the All result sheet");	
					
		ExcelDataUtility.writeToExcel(filename, sheetname, insurerName, rowNumber, "Insurers_Name");
		logger.info("Insurer name '"+insurerName+"' is added on the All result sheet");
		
		ExcelDataUtility.writeToExcel(filename, sheetname, insurerPercentage, rowNumber, "Insurers_Percentage");
		logger.info("Insurer percenatge '"+insurerPercentage+"' is added on the All result sheet");	
				
		//////////////////
		
		String currentDate = JavaUtility.dateFormatAsDDmmYYYYForPurchase();	
						
		ExcelDataUtility.writeToExcel(filename, sheetname, quoteNo, rowNumber, "Quote_reference");
		logger.info("Quote reference '"+quoteNo+"' is added on the All result sheet");
		
		ExcelDataUtility.writeToExcel(filename, sheetname, currentDate, rowNumber, "Execution_Date");
		logger.info("The current date '"+currentDate+"' is added on the All result sheet");
		
		ExcelDataUtility.writeToExcel(filename, sheetname, policyNo, rowNumber, "Policy_Number");
		logger.info("Policy Number '"+policyNo+"' is added on the All result sheet");
		
		ExcelDataUtility.writeToExcel(filename, sheetname, premiumValue, rowNumber, "Actual_Policy_Premium");
		logger.info("Policy Premium '"+premiumValue+"' is added on the All result sheet");
		
		
		String ExpectedPolicyPremiumValue = ExcelDataUtility.getCellData(filename, sheetname, rowNumber, "Expected_Policy_Premium");
		String ActualPolicyPremiumValue = ExcelDataUtility.getCellData(filename, sheetname, rowNumber, "Actual_Policy_Premium");
		
		if(ActualPolicyPremiumValue.equalsIgnoreCase(ExpectedPolicyPremiumValue))
		{
			logger.info("The Actual Premium value is same as the Expected Premium value, So that the Status will be PASS.");
			ExcelDataUtility.writeToExcel(filename, sheetname, "PASS", rowNumber, "Status");						
		}
		else if(ActualPolicyPremiumValue.equalsIgnoreCase(null))
		{
			logger.error("This product is not run, So that the Status will be NOT RUN.");
			ExcelDataUtility.writeToExcel(filename, sheetname, "NOT RUN", rowNumber, "Status");			
		}
		else
		{			
			logger.error("The Actual Premium value is not same as the Expected Premium value, So that the Status will be FAIL.");
			ExcelDataUtility.writeToExcel(filename, sheetname, "FAIL", rowNumber, "Status");					
		}	
			
	}
	
	
	

}
