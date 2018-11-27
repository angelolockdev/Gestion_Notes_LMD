package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTime extends Date {

	private static final long serialVersionUID = 1L;
	private static final SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	private Calendar calendar;

	public static DateTime parseDateTime(String date) throws ParseException {
		if (date == null) {
			return null;
		}
		return new DateTime(format.parse(date).getTime());
	}

	public static DateTime getDateTime(String date) throws ParseException {
		SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
		return new DateTime(format1.parse(date).getTime());
	}

	@Override
	public String toString() {
		return format.format(this);
	}

	public String globalFormat() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		return format.format(this);
	}

	public DateTime(long time) {
		super(time);
		calendar = new GregorianCalendar();
		calendar.setTimeInMillis(this.getTime());
	}

	public DateTime() {
		super(new Date().getTime());
		calendar = new GregorianCalendar();
		calendar.setTimeInMillis(this.getTime());
	}

	public DateTime addSecond(int seconds) {
		return new DateTime(this.getTime() + 1000L * seconds);
	}

	public DateTime addMinute(int minutes) {
		return this.addSecond(60 * minutes);
	}

	public DateTime addHour(int hours) {
		return this.addMinute(hours * 60);
	}

	public DateTime addDay(int days) {
		return this.addHour(days * 24);
	}

	public DateTime addMonth(int months) {
		return this.addDay(30 * months);
	}

	public DateTime addYear(int years) {
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

	public int getHour() {
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	public int getMinute() {
		return calendar.get(Calendar.MINUTE);
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

	public void setHour(int hour) {
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		this.setTime(calendar.getTimeInMillis());
	}

	public void setMinute(int minute) {
		calendar.set(Calendar.MINUTE, minute);
		this.setTime(calendar.getTimeInMillis());
	}

	public static DateSimple getDateSimple(String date) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		return new DateSimple(df.parse(date).getTime());
	}

	public DateSimple getDateSimple() {
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		try {
			return new DateSimple(df.parse(this.toString()).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}