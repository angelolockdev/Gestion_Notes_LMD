package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateSimple extends Date {

  private static final long serialVersionUID = 1L;
  private static final SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
  private Calendar calendar;

  public static DateSimple parseDateSimple(String date) throws ParseException {
    if (date == null) {
      return null;
    }
    return new DateSimple(format.parse(date).getTime());
  }

  @Override
  public String toString() {
    return format.format(this);
  }

  public String globalFormat() {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    return format.format(this);
  }

  public DateSimple(long time) {
    super(time);
    calendar = new GregorianCalendar();
    calendar.setTimeInMillis(this.getTime());
  }

  public DateSimple() {
    super();
    calendar = new GregorianCalendar();
    calendar.setTimeInMillis(this.getTime());
  }

  public DateSimple addDay(int days) {
    return new DateSimple(this.getTime() + 3600000L * 24 * days);
  }

  public DateSimple addMonth(int months) {
    return this.addDay(30 * months);
  }

  public DateSimple addYear(int years) {
    return this.addDay(365 * years);
  }

  @Override
  public int getDay() {
    return calendar.get(Calendar.DAY_OF_MONTH);
  }

  @Override
  public int getMonth() {
    return calendar.get(Calendar.MONTH);
  }

  @Override
  public int getYear() {
    return calendar.get(Calendar.YEAR);
  }

  public void setDay(int day) {
    calendar.set(Calendar.DAY_OF_MONTH, day);
    this.setTime(calendar.getTimeInMillis());
  }

  @Override
  public void setMonth(int month) {
    calendar.set(Calendar.MONTH, month);
    this.setTime(calendar.getTimeInMillis());
  }

  @Override
  public void setYear(int year) {
    calendar.set(Calendar.YEAR, year);
    this.setTime(calendar.getTimeInMillis());
  }
}