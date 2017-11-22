package com.tianjian.util.comm;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TimeZone;

public class DateUtil {

	/**
	 * 
	 */
	public static Date stringToDate(String strDate, String oracleFormat) {
		// SimpleDateFormat df=new SimpleDateFormat(javaFormat,new
		// DateFormatSymbols());
		// SimpleDateFormat df = new SimpleDateFormat(javaFormat);
		if (strDate == null)
			return null;
		Hashtable<Integer, String> h = new Hashtable<Integer, String>();
		String javaFormat = new String();
		String s = oracleFormat.toLowerCase();
		if (s.indexOf("yyyy") != -1)
			h.put(new Integer(s.indexOf("yyyy")), "yyyy");
		else if (s.indexOf("yy") != -1)
			h.put(new Integer(s.indexOf("yy")), "yy");
		if (s.indexOf("mm") != -1)
			h.put(new Integer(s.indexOf("mm")), "MM");
		if (s.indexOf("dd") != -1)
			h.put(new Integer(s.indexOf("dd")), "dd");
		if (s.indexOf("hh24") != -1)
			h.put(new Integer(s.indexOf("hh24")), "HH");
		if (s.indexOf("mi") != -1)
			h.put(new Integer(s.indexOf("mi")), "mm");
		if (s.indexOf("ss") != -1)
			h.put(new Integer(s.indexOf("ss")), "ss");
		int intStart = 0;
		while (s.indexOf("-", intStart) != -1) {
			intStart = s.indexOf("-", intStart);
			h.put(new Integer(intStart), "-");
			intStart++;
		}
		intStart = 0;
		while (s.indexOf("/", intStart) != -1) {
			intStart = s.indexOf("/", intStart);
			h.put(new Integer(intStart), "/");
			intStart++;
		}
		intStart = 0;
		while (s.indexOf(" ", intStart) != -1) {
			intStart = s.indexOf(" ", intStart);
			h.put(new Integer(intStart), " ");
			intStart++;
		}
		intStart = 0;
		while (s.indexOf(":", intStart) != -1) {
			intStart = s.indexOf(":", intStart);
			h.put(new Integer(intStart), ":");
			intStart++;
		}
		
		if (s.indexOf("year") != -1)
			h.put(new Integer(s.indexOf("year")), "year");
		if (s.indexOf("month") != -1)
			h.put(new Integer(s.indexOf("month")), "month");
		if (s.indexOf("day") != -1)
			h.put(new Integer(s.indexOf("day")), "day");
		if (s.indexOf("hour") != -1)
			h.put(new Integer(s.indexOf("hour")), "hour");
		if (s.indexOf("minute") != -1)
			h.put(new Integer(s.indexOf("minute")), "minute");
		if (s.indexOf("seconds") != -1)
			h.put(new Integer(s.indexOf("seconds")), "seconds");
		int i = 0;
		while (h.size() != 0) {
			Enumeration e = h.keys();
			int n = 0;
			while (e.hasMoreElements()) {
				i = ((Integer) e.nextElement()).intValue();
				if (i >= n)
					n = i;
			}
			String temp = (String) h.get(new Integer(n));
			h.remove(new Integer(n));
			javaFormat = temp + javaFormat;
		}
		// System.out.println(javaFormat);
		SimpleDateFormat df = new SimpleDateFormat(javaFormat);
		Date myDate = new Date();
		try {
			myDate = df.parse(strDate);
		} catch (Exception e) {
			return null;
		}
		return myDate;
	}

	public static Date string2DateTime(String date, String dateTimeFormat) {
		SimpleDateFormat formatter = new SimpleDateFormat(dateTimeFormat);
		ParsePosition pos = new ParsePosition(0);
		Date dt = formatter.parse(date, pos);
		return dt;
	}
	
	public static java.sql.Date getDate() {
		  return new java.sql.Date(System.currentTimeMillis());
	}
	
	/**
	 * 计算某天之后N天的日期
	 * @param strDate
	 * @param i_day
	 * @return
	 */
	public static String getLastDay(String strDate, int i_day) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		Date date = stringToDate(strDate,"yyyy/MM/dd");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, i_day);
		return sdf.format(c.getTime());
	}
	
	
	/*计算年龄*/
	public static int getAge(Date birthDay) throws Exception {
        Calendar cal = Calendar.getInstance();

        if (cal.before(birthDay)) {
            throw new IllegalArgumentException(
                "The birthDay is before Now.It's unbelievable!");
        }

        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                //monthNow==monthBirth
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                } else {
                    //do nothing
                }
            } else {
                //monthNow>monthBirth
                age--;
            }
        } else {
            //monthNow<monthBirth
            //donothing
        }

        return age;
    }
	
	
	public static Date getNowDate()
	  {
	    Date currentTime = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String dateString = formatter.format(currentTime);
	    ParsePosition pos = new ParsePosition(8);
	    Date currentTime_2 = formatter.parse(dateString, pos);
	    return currentTime_2;
	  }
	  public static Date getNowDate2()
	  {
	    Date currentTime = new Date();
	    return currentTime; }

	  public static Date getNowDateShort()
	  {
	    Date currentTime = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    String dateString = formatter.format(currentTime);
	    ParsePosition pos = new ParsePosition(8);
	    Date currentTime_2 = formatter.parse(dateString, pos);
	    return currentTime_2;
	  }

	  public static String getStringDate()
	  {
	    Date currentTime = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    String dateString = formatter.format(currentTime);
	    return dateString;
	  }

	  public static String getStringTimestamp()
	  {
	    Date currentTime = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String dateString = formatter.format(currentTime);
	    return dateString;
	  }

	  public static String getStringDateShort()
	  {
	    Date currentTime = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    String dateString = formatter.format(currentTime);
	    return dateString;
	  }

	  public static Date strToDate(String strDate)
	  {
	    if ((strDate == null) || (strDate.trim().equals("")))
	      strDate = "1900/01/01";
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
	    ParsePosition pos = new ParsePosition(0);
	    Date strtodate = formatter.parse(strDate, pos);
	    return strtodate;
	  }

	  public static Date strToTimestamp(String strDate)
	  {
	    if ((strDate == null) || (strDate.trim().equals("")))
	      strDate = "01/01/1900 00:00:00";
	    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	    ParsePosition pos = new ParsePosition(0);
	    Date strtodate = formatter.parse(strDate, pos);
	    return strtodate;
	  }

	  public static String dateToStr(Date dateDate)
	  {
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    String dateString = formatter.format(dateDate);
	    return dateString != null ? dateString : "1900-01-01";
	  }

	  public static String timestampToStr(Date dateDate)
	  {
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String dateString = formatter.format(dateDate);
	    return dateString != null ? dateString : "1900-01-01";
	  }

	  public static Date strToBirthday(String strDate)
	  {
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    ParsePosition pos = new ParsePosition(0);
	    Date strtodate = formatter.parse(strDate, pos);
	    return strtodate;
	  }

	  public static Date getNow()
	  {
	    Date currentTime = new Date();
	    return currentTime;
	  }

	  public static long getS(String strDate)
	  {
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    ParsePosition pos = new ParsePosition(0);
	    Date strtodate = formatter.parse(strDate, pos);
	    return strtodate.getTime();
	  }

	  public static void getLastTheDate()
	  {
	    Date date = strToDate("2009/08/10");
	    long date_3_hm = date.getTime() + -1577803776L;
	    Date date_3_hm_date = new Date(date_3_hm);
	    System.out.println(dateToStr(date_3_hm_date));
	  }

	  public static Date getLastDate(long day)
	  {
	    Date date = new Date();
	    long date_3_hm = date.getTime() - 122400000L * day;
	    Date date_3_hm_date = new Date(date_3_hm);
	    return date_3_hm_date;
	  }

	  public static int getNowDay(String StrDate)
	  {
	    Date Time1 = strToDate(StrDate);
	    Date Time2 = new Date();
	    long day = Time1.getTime() - Time2.getTime();
	    return (int)day / 86400000;
	  }

	  public static int getNowDay(Date StrDate)
	  {
	    Date Time1 = StrDate;
	    Date Time2 = new Date();
	    long day = Time1.getTime() - Time2.getTime();
	    return (int)day / 86400000;
	  }

	  public static String CalendarToStr(Calendar cal)
	  {
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    if (cal != null)
	    {
	      Date date = cal.getTime();
	      return format.format(date);
	    }

	    return "";
	  }

	  public static Calendar starCalOfWeek(Calendar day)
	  {
	    int temp = day.get(7);
	    switch (temp)
	    {
	    case 1:
	      return day;
	    case 2:
	      day.add(5, -1);
	      return day;
	    case 3:
	      day.add(5, -2);
	      return day;
	    case 4:
	      day.add(5, -3);
	      return day;
	    case 5:
	      day.add(5, -4);
	      return day;
	    case 6:
	      day.add(5, -5);
	      return day;
	    case 7:
	      day.add(5, -6);
	      return day;
	    }
	    return day;
	  }

	  public static Calendar endCalOfWeek(Calendar day)
	  {
	    int temp = day.get(7);
	    switch (temp)
	    {
	    case 1:
	      day.add(5, 6);
	      return day;
	    case 2:
	      day.add(5, 5);
	      return day;
	    case 3:
	      day.add(5, 4);
	      return day;
	    case 4:
	      day.add(5, 3);
	      return day;
	    case 5:
	      day.add(5, 2);
	      return day;
	    case 6:
	      day.add(5, 1);
	      return day;
	    case 7:
	      return day;
	    }
	    return day;
	  }

	  public static Calendar StrToCalendar(String str)
	  {
	    if (str.length() == 0)
	    {
	      return null;
	    }

	    int start = str.indexOf('/');
	    start++;
	    int start1 = str.indexOf('/', start);
	    String month = str.substring(start, start1);
	    String day = str.substring(start1 + 1);
	    Date date = new Date();
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.set(2, Integer.parseInt(month) - 1);
	    cal.set(5, Integer.parseInt(day));
	    return cal;
	  }

	  public static String dayOfWeek(Calendar day)
	  {
	    int temp = day.get(7);
	    switch (temp)
	    {
	    case 1:
	      return "Sun";
	    case 2:
	      return "Mon";
	    case 3:
	      return "Tue";
	    case 4:
	      return "Wed";
	    case 5:
	      return "Thu";
	    case 6:
	      return "Fri";
	    case 7:
	      return "Sat";
	    }
	    return "";
	  }

	  public static String dayOfWeek(String inDay)
	  {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(strToDate(inDay));
	    int temp = cal.get(7);
	    switch (temp)
	    {
	    case 1:
	      return "Sun";
	    case 2:
	      return "Mon";
	    case 3:
	      return "Tue";
	    case 4:
	      return "Wed";
	    case 5:
	      return "Thu";
	    case 6:
	      return "Fri";
	    case 7:
	      return "Sat";
	    }
	    return "";
	  }

	  public static Date StrToTimestamp(String timestampStr, String pattern)
	  {
	    SimpleDateFormat formatter = new SimpleDateFormat(pattern);
	    ParsePosition pos = new ParsePosition(0);
	    Date strtodate = formatter.parse(timestampStr, pos);
	    return strtodate;
	  }

	  public static String TimestampToStr(Date dateDate, String pattern)
	  {
	    SimpleDateFormat formatter = new SimpleDateFormat(pattern);
	    String dateString = formatter.format(dateDate);
	    return dateString != null ? dateString : "1900-01-01";
	  }

	  public static String StrToStr(String oldstr, String patternold, String patternnew)
	  {
	    SimpleDateFormat formatterold = new SimpleDateFormat(patternold);
	    ParsePosition pos = new ParsePosition(0);
	    Date dateDate = formatterold.parse(oldstr, pos);
	    SimpleDateFormat formatternew = new SimpleDateFormat(patternnew);
	    String dateString = formatternew.format(dateDate);
	    return dateString != null ? dateString : "1900-01-01";
	  }

	  public static String SqlStrToStr(String oldstr)
	  {
	    String dateString = null;
	    dateString = oldstr.substring(0, 19);
	    return dateString != null ? dateString : "1900-01-01 00:00:00";
	  }

	  public static String SqlStrToDateStr(String oldstr)
	  {
	    String dateString = null;
	    dateString = oldstr.substring(0, 10);
	    return dateString != null ? dateString : "1900-01-01";
	  }

	  public static boolean isDateTimeBefore(String date1, String date2)
	  {
	    try
	    {
	      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	      return df.parse(date1).before(df.parse(date2));
	    }
	    catch (ParseException e)
	    {
	      System.out.print("[SYS] " + e.getMessage());
	    }
	    return false;
	  }

	  public static boolean isDateTimeBefore(String date2)
	  {
	    try
	    {
	      Date date1 = new Date();
	      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	      return date1.before(df.parse(date2));
	    }
	    catch (ParseException e)
	    {
	      System.out.print("[SYS] " + e.getMessage());
	    }
	    return false;
	  }

	  public static boolean isDateBefore(String date1, String date2)
	  {
	    try
	    {
	      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	      return df.parse(date1).before(df.parse(date2));
	    }
	    catch (ParseException e)
	    {
	      System.out.print("[SYS] " + e.getMessage());
	    }
	    return false;
	  }

	  public static boolean isDateBefore(String date2)
	  {
	    try
	    {
	      Date date1 = new Date();
	      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	      return date1.before(df.parse(date2));
	    }
	    catch (ParseException e)
	    {
	      System.out.print("[SYS] " + e.getMessage());
	    }
	    return false;
	  }
	  
	  
	  //----------------------------以下是commons-lang-2.3.jar中的内容 与weblogic中commons-lang冲突,所以提出来放这里
	  public static final TimeZone UTC_TIME_ZONE = TimeZone.getTimeZone("GMT");
	  public static final long MILLIS_PER_SECOND = 1000L;
	  public static final long MILLIS_PER_MINUTE = 60000L;
	  public static final long MILLIS_PER_HOUR = 3600000L;
	  public static final long MILLIS_PER_DAY = 86400000L;
	  public static final int SEMI_MONTH = 1001;
	  private static final int[][] fields = { { 14 }, { 13 }, { 12 }, { 11, 10 }, { 5, 5, 9 }, { 2, 1001 }, { 1 }, { 0 } };
	  public static final int RANGE_WEEK_SUNDAY = 1;
	  public static final int RANGE_WEEK_MONDAY = 2;
	  public static final int RANGE_WEEK_RELATIVE = 3;
	  public static final int RANGE_WEEK_CENTER = 4;
	  public static final int RANGE_MONTH_SUNDAY = 5;
	  public static final int RANGE_MONTH_MONDAY = 6;

	  /** @deprecated */
	  public static final int MILLIS_IN_SECOND = 1000;

	  /** @deprecated */
	  public static final int MILLIS_IN_MINUTE = 60000;

	  /** @deprecated */
	  public static final int MILLIS_IN_HOUR = 3600000;

	  /** @deprecated */
	  public static final int MILLIS_IN_DAY = 86400000;

	  public static boolean isSameDay(Date date1, Date date2)
	  {
	    if ((date1 == null) || (date2 == null)) {
	      throw new IllegalArgumentException("The date must not be null");
	    }
	    Calendar cal1 = Calendar.getInstance();
	    cal1.setTime(date1);
	    Calendar cal2 = Calendar.getInstance();
	    cal2.setTime(date2);
	    return isSameDay(cal1, cal2);
	  }

	  public static boolean isSameDay(Calendar cal1, Calendar cal2)
	  {
	    if ((cal1 == null) || (cal2 == null)) {
	      throw new IllegalArgumentException("The date must not be null");
	    }
	    return (cal1.get(0) == cal2.get(0)) && (cal1.get(1) == cal2.get(1)) && (cal1.get(6) == cal2.get(6));
	  }

	  public static boolean isSameInstant(Date date1, Date date2)
	  {
	    if ((date1 == null) || (date2 == null)) {
	      throw new IllegalArgumentException("The date must not be null");
	    }
	    return date1.getTime() == date2.getTime();
	  }

	  public static boolean isSameInstant(Calendar cal1, Calendar cal2)
	  {
	    if ((cal1 == null) || (cal2 == null)) {
	      throw new IllegalArgumentException("The date must not be null");
	    }
	    return cal1.getTime().getTime() == cal2.getTime().getTime();
	  }

	  public static boolean isSameLocalTime(Calendar cal1, Calendar cal2)
	  {
	    if ((cal1 == null) || (cal2 == null)) {
	      throw new IllegalArgumentException("The date must not be null");
	    }
	    return (cal1.get(14) == cal2.get(14)) && (cal1.get(13) == cal2.get(13)) && (cal1.get(12) == cal2.get(12)) && (cal1.get(10) == cal2.get(10)) && (cal1.get(6) == cal2.get(6)) && (cal1.get(1) == cal2.get(1)) && (cal1.get(0) == cal2.get(0)) && (cal1.getClass() == cal2.getClass());
	  }

	  public static Date parseDate(String str, String[] parsePatterns)
	    throws ParseException
	  {
	    if ((str == null) || (parsePatterns == null)) {
	      throw new IllegalArgumentException("Date and Patterns must not be null");
	    }

	    SimpleDateFormat parser = null;
	    ParsePosition pos = new ParsePosition(0);
	    for (int i = 0; i < parsePatterns.length; i++) {
	      if (i == 0)
	        parser = new SimpleDateFormat(parsePatterns[0]);
	      else {
	        parser.applyPattern(parsePatterns[i]);
	      }
	      pos.setIndex(0);
	      Date date = parser.parse(str, pos);
	      if ((date != null) && (pos.getIndex() == str.length())) {
	        return date;
	      }
	    }
	    throw new ParseException("Unable to parse the date: " + str, -1);
	  }

	  public static Date addYears(Date date, int amount)
	  {
	    return add(date, 1, amount);
	  }

	  public static Date addMonths(Date date, int amount)
	  {
	    return add(date, 2, amount);
	  }

	  public static Date addWeeks(Date date, int amount)
	  {
	    return add(date, 3, amount);
	  }

	  public static Date addDays(Date date, int amount)
	  {
	    return add(date, 5, amount);
	  }

	  public static Date addHours(Date date, int amount)
	  {
	    return add(date, 11, amount);
	  }

	  public static Date addMinutes(Date date, int amount)
	  {
	    return add(date, 12, amount);
	  }

	  public static Date addSeconds(Date date, int amount)
	  {
	    return add(date, 13, amount);
	  }

	  public static Date addMilliseconds(Date date, int amount)
	  {
	    return add(date, 14, amount);
	  }

	  public static Date add(Date date, int calendarField, int amount)
	  {
	    if (date == null) {
	      throw new IllegalArgumentException("The date must not be null");
	    }
	    Calendar c = Calendar.getInstance();
	    c.setTime(date);
	    c.add(calendarField, amount);
	    return c.getTime();
	  }

	  public static Date round(Date date, int field)
	  {
	    if (date == null) {
	      throw new IllegalArgumentException("The date must not be null");
	    }
	    Calendar gval = Calendar.getInstance();
	    gval.setTime(date);
	    modify(gval, field, true);
	    return gval.getTime();
	  }

	  public static Calendar round(Calendar date, int field)
	  {
	    if (date == null) {
	      throw new IllegalArgumentException("The date must not be null");
	    }
	    Calendar rounded = (Calendar)date.clone();
	    modify(rounded, field, true);
	    return rounded;
	  }

	  public static Date round(Object date, int field)
	  {
	    if (date == null) {
	      throw new IllegalArgumentException("The date must not be null");
	    }
	    if ((date instanceof Date))
	      return round((Date)date, field);
	    if ((date instanceof Calendar)) {
	      return round((Calendar)date, field).getTime();
	    }
	    throw new ClassCastException("Could not round " + date);
	  }

	  public static Date truncate(Date date, int field)
	  {
	    if (date == null) {
	      throw new IllegalArgumentException("The date must not be null");
	    }
	    Calendar gval = Calendar.getInstance();
	    gval.setTime(date);
	    modify(gval, field, false);
	    return gval.getTime();
	  }

	  public static Calendar truncate(Calendar date, int field)
	  {
	    if (date == null) {
	      throw new IllegalArgumentException("The date must not be null");
	    }
	    Calendar truncated = (Calendar)date.clone();
	    modify(truncated, field, false);
	    return truncated;
	  }

	  public static Date truncate(Object date, int field)
	  {
	    if (date == null) {
	      throw new IllegalArgumentException("The date must not be null");
	    }
	    if ((date instanceof Date))
	      return truncate((Date)date, field);
	    if ((date instanceof Calendar)) {
	      return truncate((Calendar)date, field).getTime();
	    }
	    throw new ClassCastException("Could not truncate " + date);
	  }

	  private static void modify(Calendar val, int field, boolean round)
	  {
	    if (val.get(1) > 280000000) {
	      throw new ArithmeticException("Calendar value too large for accurate calculations");
	    }

	    if (field == 14) {
	      return;
	    }

	    Date date = val.getTime();
	    long time = date.getTime();
	    boolean done = false;

	    int millisecs = val.get(14);
	    if ((!round) || (millisecs < 500)) {
	      time -= millisecs;
	      if (field == 13) {
	        done = true;
	      }

	    }

	    int seconds = val.get(13);
	    if ((!done) && ((!round) || (seconds < 30))) {
	      time -= seconds * 1000L;
	      if (field == 12) {
	        done = true;
	      }

	    }

	    int minutes = val.get(12);
	    if ((!done) && ((!round) || (minutes < 30))) {
	      time -= minutes * 60000L;
	    }

	    if (date.getTime() != time) {
	      date.setTime(time);
	      val.setTime(date);
	    }

	    boolean roundUp = false;
	    for (int i = 0; i < fields.length; i++) {
	      for (int j = 0; j < fields[i].length; j++) {
	        if (fields[i][j] != field)
	          continue;
	        if ((round) && (roundUp)) {
	          if (field == 1001)
	          {
	            if (val.get(5) == 1) {
	              val.add(5, 15);
	            } else {
	              val.add(5, -15);
	              val.add(2, 1);
	            }
	          }
	          else
	          {
	            val.add(fields[i][0], 1);
	          }
	        }
	        return;
	      }

	      int offset = 0;
	      boolean offsetSet = false;

	      switch (field) {
	      case 1001:
	        if (fields[i][0] != 5)
	        {
	          break;
	        }
	        offset = val.get(5) - 1;

	        if (offset >= 15) {
	          offset -= 15;
	        }

	        roundUp = offset > 7;
	        offsetSet = true; break;
	      case 9:
	        if (fields[i][0] != 11) {
	          break;
	        }
	        offset = val.get(11);
	        if (offset >= 12) {
	          offset -= 12;
	        }
	        roundUp = offset > 6;
	        offsetSet = true;
	      }

	      if (!offsetSet) {
	        int min = val.getActualMinimum(fields[i][0]);
	        int max = val.getActualMaximum(fields[i][0]);

	        offset = val.get(fields[i][0]) - min;

	        roundUp = offset > (max - min) / 2;
	      }

	      if (offset != 0) {
	        val.set(fields[i][0], val.get(fields[i][0]) - offset);
	      }
	    }
	    throw new IllegalArgumentException("The field " + field + " is not supported");
	  }

	  public static Iterator iterator(Date focus, int rangeStyle)
	  {
	    if (focus == null) {
	      throw new IllegalArgumentException("The date must not be null");
	    }
	    Calendar gval = Calendar.getInstance();
	    gval.setTime(focus);
	    return iterator(gval, rangeStyle);
	  }

	  public static Iterator iterator(Calendar focus, int rangeStyle)
	  {
	    if (focus == null) {
	      throw new IllegalArgumentException("The date must not be null");
	    }
	    Calendar start = null;
	    Calendar end = null;
	    int startCutoff = 1;
	    int endCutoff = 7;
	    switch (rangeStyle)
	    {
	    case 5:
	    case 6:
	      start = truncate(focus, 2);

	      end = (Calendar)start.clone();
	      end.add(2, 1);
	      end.add(5, -1);

	      if (rangeStyle != 6) break;
	      startCutoff = 2;
	      endCutoff = 1; break;
	    case 1:
	    case 2:
	    case 3:
	    case 4:
	      start = truncate(focus, 5);
	      end = truncate(focus, 5);
	      switch (rangeStyle)
	      {
	      case 1:
	        break;
	      case 2:
	        startCutoff = 2;
	        endCutoff = 1;
	        break;
	      case 3:
	        startCutoff = focus.get(7);
	        endCutoff = startCutoff - 1;
	        break;
	      case 4:
	        startCutoff = focus.get(7) - 3;
	        endCutoff = focus.get(7) + 3;
	      }

	      break;
	    default:
	      throw new IllegalArgumentException("The range style " + rangeStyle + " is not valid.");
	    }
	    if (startCutoff < 1) {
	      startCutoff += 7;
	    }
	    if (startCutoff > 7) {
	      startCutoff -= 7;
	    }
	    if (endCutoff < 1) {
	      endCutoff += 7;
	    }
	    if (endCutoff > 7) {
	      endCutoff -= 7;
	    }
	    while (start.get(7) != startCutoff) {
	      start.add(5, -1);
	    }
	    while (end.get(7) != endCutoff) {
	      end.add(5, 1);
	    }
	    return new DateIterator(start, end);
	  }

	  public static Iterator iterator(Object focus, int rangeStyle)
	  {
	    if (focus == null) {
	      throw new IllegalArgumentException("The date must not be null");
	    }
	    if ((focus instanceof Date))
	      return iterator((Date)focus, rangeStyle);
	    if ((focus instanceof Calendar)) {
	      return iterator((Calendar)focus, rangeStyle);
	    }
	    throw new ClassCastException("Could not iterate based on " + focus);
	  }

	  static class DateIterator
	    implements Iterator
	  {
	    private final Calendar endFinal;
	    private final Calendar spot;

	    DateIterator(Calendar startFinal, Calendar endFinal)
	    {
	      this.endFinal = endFinal;
	      this.spot = startFinal;
	      this.spot.add(5, -1);
	    }

	    public boolean hasNext()
	    {
	      return this.spot.before(this.endFinal);
	    }

	    public Object next()
	    {
	      if (this.spot.equals(this.endFinal)) {
	        throw new NoSuchElementException();
	      }
	      this.spot.add(5, 1);
	      return this.spot.clone();
	    }

	    public void remove()
	    {
	      throw new UnsupportedOperationException();
	    }
	  }
	  
	  
	  
	  
	  
}
