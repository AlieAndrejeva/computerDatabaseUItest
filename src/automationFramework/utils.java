package automationFramework;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class utils {
//	add util for generating random text from current time
//	add notification creation
//	add error catching
	public static String createNrString(int numberz)
	{
		Date dt = new Date();
		DateFormat format = new SimpleDateFormat("ddHHssSS");
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.add(Calendar.DATE, numberz);
		String formattedDateTime = format.format(cal.getTime());
		return formattedDateTime;
		
	}
	public static String createyyyMMddValue(int numbero)
	{
		Date d = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calen = Calendar.getInstance();
		calen.setTime(d);
		calen.add(Calendar.DATE, numbero);
		String formattedDateTime = format.format(calen.getTime());
		return formattedDateTime;
		
	}

}
