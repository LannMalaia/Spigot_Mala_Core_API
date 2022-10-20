package laylia_core.element;

import java.util.Calendar;

public class Time_Data
{
	public boolean is_am;
	public int second;
	public int minute;
	public int hour;
	public int day;
	public int month;
	public int year;
	public int week_of_month;
	public int day_of_week;
	public long millisecond;
	
	public Time_Data(Calendar cld)
	{
		second = cld.get(Calendar.SECOND);
		minute = cld.get(Calendar.MINUTE);
		hour = cld.get(Calendar.HOUR);
		day = cld.get(Calendar.DAY_OF_MONTH);
		month = cld.get(Calendar.MONTH);
		year = cld.get(Calendar.YEAR);
		week_of_month = cld.get(Calendar.WEEK_OF_MONTH);
		day_of_week = cld.get(Calendar.DAY_OF_WEEK);
		is_am = cld.get(Calendar.AM_PM) == 1 ? false : true;
		millisecond = cld.getTimeInMillis();
	}
	
	public String Get_Day_Of_Week_Str(boolean is_korean)
	{
		String dow = "";
		
		switch(day_of_week)
		{
		case 1:
			if(is_korean) dow = "일요일";
			else dow = "Sunday";
			break;
		case 2:
			if(is_korean) dow = "월요일";
			else dow = "Monday";
			break;
		case 3:
			if(is_korean) dow = "화요일";
			else dow = "Tuesday";
			break;
		case 4:
			if(is_korean) dow = "수요일";
			else dow = "Wednesday";
			break;
		case 5:
			if(is_korean) dow = "목요일";
			else dow = "Thursday";
			break;
		case 6:
			if(is_korean) dow = "금요일";
			else dow = "Friday";
			break;
		case 7:
			if(is_korean) dow = "토요일";
			else dow = "Saturday";
			break;
		}		
		return dow;
	}
}
