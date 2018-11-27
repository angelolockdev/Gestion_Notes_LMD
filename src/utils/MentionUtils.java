package utils;

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
				ret = "Très bien";break;
			default:
				ret = "Honorable";break;
		}
		return ret;
	}
}
