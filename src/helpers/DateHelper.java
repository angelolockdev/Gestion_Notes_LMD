package helpers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class DateHelper {
	
	public static SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy"); 
	
	public static List<Integer> getAnneeList(int debut, int fin) throws Exception{
		List<Integer> ret = new ArrayList<Integer>();
		int i = fin;
		try {
			if(fin > debut) {
				while(i>debut) {
					ret.add(i);
					i--;
				}
				return ret;
			}
			else {
				throw new Exception("Erreur: annee fin invalide!");
			}
		}catch(Exception e) {
			throw e;
		}
		
	}
	public static List<Integer> getAllDaysInYear(){
		List<Integer> ret = new ArrayList<Integer>();
		int mois = 12;
		try {
			for(int i=1 ; i<=mois ; i++) {
				Integer daysOfMonth =  DateHelper.getNombreJoursParMois(mois);
				ret.add(daysOfMonth);
			}
		}catch(Exception e) {
			throw e;
		}
		return ret;
	}
	public static int getNombreJoursParMois(int mois){
		int ret = 0;
		try {
			
			/*Mettre ici les instructions */
			
			int year = (int)(new Date().getYear()+1900); 
			YearMonth yearMonthObject = YearMonth.of(year, mois);
			
		    ret = yearMonthObject.lengthOfMonth(); 
		}catch(Exception e) {
			throw e;
		}
		return ret;
	}
	public static String getCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		java.util.Date date = new java.util.Date();
		return  dateFormat.format(date);
	} 
	public static String convertToStringDate(java.sql.Date date) { 
		return  formater.format(date);
	} 
	public static String formatDate(String date) {  
		date = date.replaceAll("\\s+","");   //remove all space
		String[] daty = date.split("-"); 
		String ret = date;
		//System.out.println("--------------------------------------");
		//System.out.println("date = "+date);
		if(Integer.parseInt(daty[2])<Integer.parseInt(daty[0])) {
			ret = Integer.parseInt(daty[2])+"-"+Integer.parseInt(daty[1])+"-"+Integer.parseInt(daty[0]);
		}   
		return  ret;
	}
	public static String formatDate(Date date) {
		Date dat = new Date(date.getTime());
		return formater.format(dat);
	}
	public static Integer getByFormat(Date daty, String byFormat) {
		SimpleDateFormat simple = new SimpleDateFormat(byFormat);
		return Integer.parseInt(simple.format(daty));
	}
	 
	
	public static Date convertToDate(Date date) throws ParseException { 
		
		int day = date.getDay();
		int month = date.getMonth();
		int year = date.getYear()+(int)1900;
		Date ret = null;
		try {
			ret = formater.parse(year+"-"+month+"-"+day);
		} catch (java.text.ParseException e) { 
			e.printStackTrace();
		}
		return  ret;
	}
	public static Date convertToDateUtil(String date) throws ParseException { 
		Date ret = null;
		try {
			ret = formater.parse(date);
		} catch (java.text.ParseException e) { 
			e.printStackTrace();
		}
		return  ret;
	}
	public static java.sql.Date convertStringToDate(String date){ 
        java.sql.Date ret = null;
        java.util.Date temp = null;
        try{
            temp =  formater.parse(date);
            ret = new java.sql.Date(temp.getTime());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ret; 
    }
	
}