package utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class JavaUtility {

	public static String substring(String PolicyNo) {

		String txt = PolicyNo;
		// String text = txt.substring(txt.indexOf(":") + 1, txt.indexOf("Version"));
		String text = txt.substring(txt.indexOf(":") + 1, txt.indexOf("/01"));
		String quote_policy_num = text.trim();
		String polno = quote_policy_num.replace("QUOTE", "POLICY");

		/*
		 * String text2 =quote_policy_num; String text3
		 * =text2.substring(text2.indexOf(":")+1,text2.indexOf("/01")); String
		 * policy_num =text3.trim(); String polno = policy_num.replace("QUOTE",
		 * "POLICY"); System.out.println(polno);
		 */
		return polno;

	}

	public static String returnYearfromDate(String d) {

		String date[] = d.split("/");
		String year = date[2];
		return year;

	}

	public static String returnMonthfromDate(String d) {

		String date[] = d.split("/");
		String month = date[1];
		return month;

	}

	public static String returnDayhfromDate(String d) {

		String date[] = d.split("/");
		String day = date[0];
		return day;

	}

	public static String getQuoteNumber(String text) {
		
		String s = text;

		int index1 = s.indexOf(':');
		int index2 = s.lastIndexOf("Q"); 

		// System.out.println(index2);
		String subtext = s.substring(index1 + 1, index2);
		String quotenum = subtext.trim();

		return quotenum;

	}

	public static String getPolicyNumber(String text) {

		String s = text;
		int index = s.lastIndexOf("/Q");
		String subtext = s.substring(0, index);
		String policy_num = subtext.trim();
		return policy_num;	


	}
	
	
	public static String getPolicyNumberInitials(String text) {

		String s = text;
		String subtext = s.substring(0,3);
		String initials = subtext.trim();
		return initials;

	}
	
	public static int getinputCount(String text) {

		String s = text;		
		String[] subtext=s.split(",");
		int count=subtext.length;
		return count;

	}
	
	public static int convertInputToInt(String text) {

		String s = text;		
		int intval=Integer.parseInt(s);
		return intval;

	}
	
	
	public static int actualLoopCount(String text1,String text2) {

		String s1 = text1;	
		String s2 = text2;
		int intval1=Integer.parseInt(s1);
		int intval2=Integer.parseInt(s2);
		int diff=(intval2-intval1)+1;
		return diff;

	}
	
	public static String current_unix_timestamp() {

		long unixTime = Instant.now().getEpochSecond();
		System.out.println(unixTime);
		String uts = String.valueOf(unixTime);
		return uts;

	}
	
	
	public static int current_day_in_week() {

		Calendar calendar = Calendar.getInstance();
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		return dayOfWeek;

	}
	
	public static String getDate_from_certain_interval(int days) {

		SimpleDateFormat sdf = new SimpleDateFormat("d/MMM/yyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, days); // geeting old date
		String expected_date = sdf.format(c.getTime());
		return expected_date;
	}
	
	public static String getDate_from_certain_interval_With_TwoDigitDate(int days) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, days); // geeting old date
		String expected_date = sdf.format(c.getTime());
		return expected_date;
	}
	
	public static String dateFormatAsDDmmYYYYForPurchase()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateMonthYear = new Date();
		String dateMonthYearOnly = dateFormat.format(dateMonthYear);
//		System.out.println("Current Time stamp is - "+date1);
		return dateMonthYearOnly;
	}
	
	public static String dateFormatAsYYYY_mm_DD(String policyDate)
	{
		String year = JavaUtility.returnYearfromDate(policyDate);
		String monthName = JavaUtility.returnMonthfromDate(policyDate);
		DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("MMM").withLocale(Locale.ENGLISH);
		TemporalAccessor temporalAccessor = dtFormatter.parse(monthName);
		int monthNumber = temporalAccessor.get(ChronoField.MONTH_OF_YEAR);		
		String monthNumberOnly = returnMonthNumber(monthNumber);		
		String date = JavaUtility.returnDayhfromDate(policyDate);		
		String dateMonthYearOnly = year+"-"+monthNumberOnly+"-"+date;
	    return dateMonthYearOnly;		
	}
	
	public static String returnMonthNumber(int monthNumber)
	{
		String monthNumberOnly = null;		
		if(monthNumber<10)
		{
			String monthNumber1 = String.valueOf("0"+monthNumber);
			return monthNumber1;
		}
		else if(monthNumber>=10)
		{
			String monthNumber2 = String.valueOf(monthNumber);
			return monthNumber2;
		}		
		return monthNumberOnly;		
	}
	
	public static String currentdate() {

		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("d/MMM/yyy");
		String Date = sdf.format(d);
//		System.out.println("Current date: " + Date);
		return Date;

	}
	
	
	public static String getDate_within_interval_from_another_date(int days, String givendate) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("d/MMM/yyy");
		Date date1 = new SimpleDateFormat("d/MMM/yyy").parse(givendate);
		Calendar c = Calendar.getInstance();
		c.setTime(date1);
		c.add(Calendar.DATE, days);
		String expected_date = sdf.format(c.getTime());
//		System.out.println("New Date " + expected_date);
		return expected_date;

	}
	
	public static String get_data_random_value(String data) {

		String datawithrandomvalue = get_current_date_time().trim();
		String cname = data + datawithrandomvalue;
		System.out.println(cname);
		return cname;

	}
	
	
	
	private static String get_current_date_time() {
		// TODO Auto-generated method stub
		return null;
	}

		//For random name
		public static String generateRandom(String aToZ) {
		    Random rand=new Random();
		    StringBuilder res=new StringBuilder();
		    for (int i = 0; i <8; i++) {
		       int randIndex=rand.nextInt(aToZ.length()); 
		       res.append(aToZ.charAt(randIndex));            
		    }
		    return res.toString();
		}
	
	// For current date time (2022.05.16 11.25.16)
		public static String currentdateTime() {
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			return timeStamp;
			
		}

}

