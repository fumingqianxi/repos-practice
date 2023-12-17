package com.itheima.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 时间工具类.
 *
 * @date: Oct 15, 2015
 * @author: 王靖靖
 */
public class DateUtil {

  private static final Pattern UNIX_TIMESTAMP_PATTERN = Pattern.compile("^\\d{10}$");
  private static final Pattern JAVA_TIMESTAMP_PATTERN = Pattern.compile("^\\d{13}$");
  private static final Pattern STANDARD_DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
  private static final Pattern SLASH_DATE_PATTERN = Pattern.compile("^\\d{4}/\\d{2}/\\d{2}$");

  private static final String ERROR_PARSE_STR = "error parse ";

  private static final String DEFAULT_FORMAT_VALUE = "EEE MMM dd HH:mm:ss zzz yyyy";
  private static final String STANDARD_DATE_FORMAT_VALUE = "yyyy-MM-dd";
  private static final String STANDARD_DATE_TIME_FORMAT_VALUE = "yyyy-MM-dd HH:mm:ss";
  private static final String DETAIL_STANDARD_DATE_TIME_FORMAT_VALUE = "yyyy-MM-dd HH:mm:ss,SSS";

  private static final String SLASH_DATE_FORMAT_VALUE = "yyyy/MM/dd";
  private static final String SLASH_DATE_TIME_FORMAT_VALUE = "yyyy/MM/dd HH:mm:ss";
  private static final String DETAIL_SLASH_DATE_TIME_FORMAT_VALUE = "yyyy/MM/dd HH:mm:ss,SSS";

  private static final String STR_STANDARD_DATE_FORMAT_VALUE = "yyyyMMdd";
  private static final String SD_FORMAT_YYYY_MM_VALUE = "yyyyMM";

  private static Date parse10Text(String newText) {
    try {
      Matcher matcher = UNIX_TIMESTAMP_PATTERN.matcher(newText);
      Matcher standardMatcher = STANDARD_DATE_PATTERN.matcher(newText);
      Matcher slashMatcher = SLASH_DATE_PATTERN.matcher(newText);
      if (matcher.matches()) {
        return new Date(Long.parseLong(newText + "000"));
      } else if (standardMatcher.matches()) {
        SimpleDateFormat standardDateFormat = new SimpleDateFormat(STANDARD_DATE_FORMAT_VALUE);
        return standardDateFormat.parse(newText);
      } else if (slashMatcher.matches()) {
        SimpleDateFormat slashDateFormat = new SimpleDateFormat(SLASH_DATE_FORMAT_VALUE);
        return slashDateFormat.parse(newText);
      } else {
        throw new IllegalArgumentException("wrong 10 length date format:" + newText);
      }
    } catch (Exception ex) {
      throw new IllegalArgumentException("wrong 10 length date format:" + newText, ex);
    }
  }

  private static Date parse13Text(String newText) {
    Matcher matcher = JAVA_TIMESTAMP_PATTERN.matcher(newText);
    if (matcher.matches()) {
      return new Date(Long.parseLong(newText));
    } else {
      throw new IllegalArgumentException("wrong java timestamp format:" + newText);
    }
  }

  private static Date parse19Text(String newText) {
    try {
      if (newText.contains("-")) {
        SimpleDateFormat standardDateTimeFormat =
            new SimpleDateFormat(STANDARD_DATE_TIME_FORMAT_VALUE);
        return standardDateTimeFormat.parse(newText);
      } else {
        SimpleDateFormat slashDateTimeFormat = new SimpleDateFormat(SLASH_DATE_TIME_FORMAT_VALUE);
        return slashDateTimeFormat.parse(newText);
      }
    } catch (ParseException ex) {
      throw new IllegalArgumentException(
          ERROR_PARSE_STR + newText + " to Date with standard format", ex);
    }
  }

  private static Date parse23Text(String newText) {
    try {
      if (newText.contains("-")) {
        SimpleDateFormat detailStandardDateTimeFormat =
            new SimpleDateFormat(DETAIL_STANDARD_DATE_TIME_FORMAT_VALUE);
        return detailStandardDateTimeFormat.parse(newText);
      } else {
        SimpleDateFormat detailSlashDateTimeFormat =
            new SimpleDateFormat(DETAIL_SLASH_DATE_TIME_FORMAT_VALUE);
        return detailSlashDateTimeFormat.parse(newText);
      }
    } catch (ParseException ex) {
      throw new IllegalArgumentException(
          ERROR_PARSE_STR + newText + " to Date with detail standard format", ex);
    }
  }

  private static Date parseDefaultText(String newText) {
    try {
      SimpleDateFormat defaultFormat = new SimpleDateFormat(DEFAULT_FORMAT_VALUE, Locale.US);
      return defaultFormat.parse(newText);
    } catch (ParseException ex) {
      throw new IllegalArgumentException(
          ERROR_PARSE_STR + newText + " to Date with default format", ex);
    }
  }

  /**
   * 将一个字符串转换成java.util.Date类型的对象.
   *
   * @date: Oct 15, 2015
   * @author : 王靖靖
   * @param text 支持的格式为：<br>
   *     1、10位的时间戳 <br>
   *     2、13位的时间戳 <br>
   *     3、Locale.US下的默认时间格式：EEE MMM dd HH:mm:ss zzz yyyy <br>
   *     4、标准时间格式：yyyy-MM-dd HH:mm:ss <br>
   *     5、标准时间格式：yyyy-MM-dd HH:mm:ss,SSS
   */
  public static Date convertToDate(String text) {
    if (text == null || text.trim().length() == 0) {
      return null;
    }
    String newText = text.trim();
    int length = newText.length();
    switch (length) {
      case 10:
        return parse10Text(newText);
      case 13:
        return parse13Text(newText);
      case 19:
        return parse19Text(newText);
      case 23:
        return parse23Text(newText);
      default:
        return parseDefaultText(newText);
    }
  }

  /**
   * 传入一个日期， 返回字符类型.<br>
   * 返回日期格式：yyyyMMdd.
   */
  public static String getStrDate(Date date) {
    if (date == null) {
      return "";
    }
    SimpleDateFormat strStandardDateFormat = new SimpleDateFormat(STR_STANDARD_DATE_FORMAT_VALUE);
    return strStandardDateFormat.format(date);
  }

  /**
   * 传入一个日期， 返回字符类型. <br>
   * 返回日期格式：yyyyMM.
   */
  public static String getyyyyMm(Date date) {
    if (date == null) {
      return "";
    }
    SimpleDateFormat sdFormatYyyyMm = new SimpleDateFormat(SD_FORMAT_YYYY_MM_VALUE);
    return sdFormatYyyyMm.format(date);
  }

  /**
   * 获取该时间的那天的 0时0分0秒0毫秒.
   *
   * @date: 2015年11月3日
   * @author: duanliangjun
   */
  public static Date getDayBegin(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    resetTime(calendar);
    return calendar.getTime();
  }

  /**
   * 获取该时间的那天的 23时59分59秒999毫秒.
   *
   * @date: 2015年11月3日
   * @author: duanliangjun
   */
  public static Date getDayEnd(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    resetTimeEnd(calendar);
    return calendar.getTime();
  }

  /**
   * 获取该时间的那月的第一天 0时0分0秒0毫秒.
   *
   * @date: 2015年11月3日
   * @author: duanliangjun
   */
  public static Date getMonthBegin(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.DAY_OF_MONTH, 1);
    resetTime(calendar);
    return calendar.getTime();
  }

  /**
   * 获取该时间的那月的第一天 0时0分0秒0毫秒.
   *
   * @date: 2015年11月3日
   * @author: duanliangjun
   */
  public static Date getYearBegin(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.MONTH, 0);
    calendar.set(Calendar.DAY_OF_MONTH, 1);
    resetTime(calendar);
    return calendar.getTime();
  }

  /**
   * 重置时间字段.
   *
   * @param calendar calendar
   */
  private static void resetTime(Calendar calendar) {
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
  }

  /**
   * 重置时间字段.
   *
   * @param calendar calendar
   */
  private static void resetTimeEnd(Calendar calendar) {
    calendar.set(Calendar.HOUR_OF_DAY, 23);
    calendar.set(Calendar.MINUTE, 59);
    calendar.set(Calendar.SECOND, 59);
    calendar.set(Calendar.MILLISECOND, 999);
  }

  /** 将日期对象转换为字符串，字符串格式为yyyy-MM-dd HH:mm:ss. */
  public static String formatStandardDateTime(Date date) {
    SimpleDateFormat standardDateTimeFormat = new SimpleDateFormat(STANDARD_DATE_TIME_FORMAT_VALUE);
    return standardDateTimeFormat.format(date);
  }

  /** 将Date对象转换为字符串，字符串格式为yyyy-MM-dd. */
  public static String formatStandardDate(Date date) {
    SimpleDateFormat standardDateFormat = new SimpleDateFormat(STANDARD_DATE_FORMAT_VALUE);
    return standardDateFormat.format(date);
  }

  /**
   * 将字符串解析为Date类型.
   *
   * @param dateStr 日期字符串，格式为yyyy-MM-dd
   * @return 日期对象
   */
  public static Date parseStandardDate(String dateStr) {
    return parse10Text(dateStr);
  }

  /**
   * 将date time字符串转换为date对象.
   *
   * @param dateStr 时间字符串，格式为yyyy-MM-dd HH:mm:ss
   * @return Date对象
   */
  public static Date parseStandardDateTime(String dateStr) {
    return parse19Text(dateStr);
  }

  /**
   * 得到本月的第一天，格式 "yyyy-MM-dd".
   *
   * @param calendar 日历
   * @return Date对象
   */
  public static String getMonthFirstDay(Calendar calendar) {
    calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
    return formatStandardDate(calendar.getTime());
  }

  /**
   * 得到本月的最后一天，格式 "yyyy-MM-dd".
   *
   * @param calendar 日历
   * @return Date对象
   */
  public static String getMonthLastDay(Calendar calendar) {
    calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
    return formatStandardDate(calendar.getTime());
  }

  /**
   * 得到本月的第一天，格式 "yyyy-MM-dd 00:00:00".
   *
   * @param calendar 日历
   * @return Date对象
   */
  public static String getMonthFirstDayHms(Calendar calendar) {
    calendar.set(Calendar.DAY_OF_MONTH, 1);
    resetTime(calendar);
    return formatStandardDateTime(calendar.getTime());
  }

  /**
   * 得到本月的最后一天，格式 "yyyy-MM-dd 23:59:59".
   *
   * @param calendar 日历
   * @return Date对象
   */
  public static String getMonthLastDayHms(Calendar calendar) {
    calendar.set(Calendar.DAY_OF_MONTH, 1);
    resetTime(calendar);
    // 获得当前月第一天
    // 将当前月加1；
    calendar.add(Calendar.MONTH, 1);
    // 在当前月的下一月基础上减去1毫秒
    calendar.add(Calendar.SECOND, -1);
    return formatStandardDateTime(calendar.getTime());
  }

  /**
   * 得到几天后的时间.
   *
   * @param date 已知的日期
   * @param day 日期时间段
   * @return 几天后的时间
   */
  public static Date getDateAfter(Date date, int day) {
    Calendar now = Calendar.getInstance();
    now.setTime(date);
    now.add(Calendar.DATE, day);
    resetTime(now);
    return now.getTime();
  }

  /**
   * 获取几天后的日期.
   *
   * @param localDate 已知的日期
   * @param day 日期差
   * @return 返回进行加算后的日期
   */
  public static LocalDate getDateAfter(LocalDate localDate, long day) {
    // 对当天日期的天数作加算
    localDate = localDate.plusDays(day);
    // 返回一个新的日期（LocalDate 类型）
    return localDate;
  }


  /**
   * 多租户时间使用.
   *
   * @param localDateTime time.
   * @return str.
   */
  public static String formatTenancy(LocalDateTime localDateTime) {
    return format(localDateTime, " yyyy-MM-dd HH:mm:ss ");
  }

  /**
   * 时间格式化.
   *
   * @param localDateTime time.
   * @param format format.
   * @return str.
   */
  public static String format(LocalDateTime localDateTime, String format) {
    DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
    return localDateTime.format(df);
  }
}
