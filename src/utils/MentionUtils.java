package utils;

import java.util.HashMap;
import java.util.Map;

public class MentionUtils {
	public static Integer checkMention(Integer note) {
		Integer ret = 0;
		if(note>=10) {
			ret = 10;
		}else if(note>=12 && note<14) {
			ret = 20;
		}else if(note>=14 && note<16) {
			ret = 30;
		}else if(note>=16 && note<18) {
			ret = 40;
		}else if(note>=18 && note<=20){
			ret = 50;
		} 
		System.out.println("Mention Utilise = "+ret);
		return ret;
	}
	public static String getMentionBy(int mention) {
		String ret = "";
		switch(mention) { 
			case 10:
				ret = "Passable";break;
			case 20:
				ret = "Assez bien";break;
			case 30:
				ret = "Bien";break;
			case 40:
				ret = "Très bien";break;
			case 50:
				ret = "Honorable";break;
			default:
				ret = " - ";break;
		}
		return ret;
	}
	public static Map<Integer, String> getOptions() {
		Map<Integer, String> ret = new HashMap<Integer, String>();
		ret.put(10, "Développement");
		ret.put(20, "Web Avancé");
		ret.put(30, "Réseaux informatique"); 

		return ret;
	}
	
	public static double[] convertStringTab(String[] tab) {
		double[] ret = new double[tab.length];
		for(int i=0; i<ret.length;i++) {
			Double subs = Double.valueOf(tab[i]);
			if(subs>20) {
				subs = subs/10;
			}
			ret[i] = subs;
		}
		return ret;
	}
	public static void afficherTableau(long[] tab) {
		System.out.print("[ ");
		for(int i=0; i<tab.length;i++) {
			System.out.print(tab[i]);
		}
		System.out.println(" ]");
	}
	public static void afficherTableau(double[] tab) {
		System.out.print("[ ");
		for(int i=0; i<tab.length;i++) {
			System.out.print(tab[i]);
		}
		System.out.println(" ]");
	}
}
