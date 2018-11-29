package utils;

import java.util.HashMap;
import java.util.Map;

public class MentionUtils {
	
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
				ret = "Tr�s bien";break;
			default:
				ret = "Honorable";break;
		}
		return ret;
	}
	public static Map<Integer, String> getOptions() {
		Map<Integer, String> ret = new HashMap<Integer, String>();
		ret.put(10, "D�veloppement");
		ret.put(20, "Web Avanc�");
		ret.put(30, "R�seaux informatique"); 

		return ret;
	}
}
