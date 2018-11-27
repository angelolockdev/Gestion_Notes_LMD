package utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FilenameUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import exceptions.EmptyException;
import exceptions.UploadException;
import models.BaseModel;

public class Functions {
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	public static String join(List<Column> columns) {
		String result = "";
		for (Column col : columns)
			result += col.getColumnName() + ", ";
		if (!result.equalsIgnoreCase(""))
			result = result.substring(0, result.length() - 2);
		return result;
	}

	public static String[] explode(String str, String regex) {
		if (str == null)
			return new String[0];
		if (!str.contains(regex))
			return new String[] { str };
		return str.split(regex);
	}

	public static String join(Object[] list, String separator) {
		String result = "";
		for (Object item : list)
			result += item + separator;
		if (!result.equalsIgnoreCase(""))
			result = result.substring(0, result.length() - separator.length());
		return result;
	}

	public static String join(Collection<?> list, String separator) {
		String result = "";
		for (Object item : list)
			result += item + separator;
		if (!result.equalsIgnoreCase(""))
			result = result.substring(0, result.length() - separator.length());
		return result;
	}

	public static String firstUpper(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	public static int inArray(Object value, Object... array) {
		int result = -1;
		for (int i = 0; i < array.length; i++) {
			if (array[i].equals(value)) {
				result = i;
				break;
			}
		}
		return result;
	}

	public static <T extends BaseModel> T objectFactory(CustomHttpRequest request, Class<T> model, String prefix,
			boolean throwException) throws Exception {
		T result = null;
		List<String> fields = Functions.getFields(model);
		String p = null;
		result = model.newInstance();
		List<String> paramName = request.getParameterNames();
		for (String item : paramName) {
			try {
				String param = item;
				if (param.startsWith(prefix + ".")) {
					p = param.substring(prefix.length() + 1);
					if (request.getParameter(param).replace(" ", "").equals(""))
						continue;
					if (fields.contains(p)) {
						Method get = model.getMethod("get" + firstUpper(p));
						Method set = model.getMethod("set" + firstUpper(p), get.getReturnType());
						if (get.getReturnType().equals(Date.class)) {
							Date d = dateFormat.parse(request.getParameter(param));
							set.invoke(result, d);
						} else if (get.getReturnType().equals(DateSimple.class)) {
							DateSimple d = DateSimple.parseDateSimple(request.getParameter(param));
							set.invoke(result, d);
						} else if (get.getReturnType().equals(DateTime.class)) {
							DateTime d = DateTime.parseDateTime(request.getParameter(param));
							set.invoke(result, d);
						} else if (get.getReturnType().equals(Integer.class))
							set.invoke(result, Integer.parseInt(request.getParameter(param)));
						else if (get.getReturnType().equals(Float.class))
							set.invoke(result, Float.parseFloat(request.getParameter(param)));
						else if (get.getReturnType().equals(String.class))
							set.invoke(result, request.getParameter(param));
						else {
							Method m = get.getReturnType().getMethod("parse" + get.getReturnType().getSimpleName(),
									String.class);
							set.invoke(result, m.invoke(get.getReturnType(), request.getParameter(param)));
						}
					}
				}
			} catch (ParseException e) {
				if (throwException)
					throw new Exception("Date invalide pour le champ " + p + "!");
			} catch (IllegalAccessException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
				if (throwException)
					throw new Exception("Le champ " + p + " est invalide!");
			} catch (NumberFormatException e) {
				if (throwException)
					throw new Exception("Le champ " + p + " est invalide!");
			}
		}
		return result;
	}

	public static List<String> getFields(Class<? extends BaseModel> model) {
		List<String> list = new ArrayList<>();
		Class<?> cl = model;
		while (!cl.equals(BaseModel.class)) {
			Field[] fields = cl.getDeclaredFields();
			for (Field item : fields) {
				if (item.getModifiers() != 2)
					continue;
				list.add(item.getName());
			}
			cl = cl.getSuperclass();
		}
		Field[] fields = cl.getDeclaredFields();
		for (Field item : fields) {
			if (item.getModifiers() != 2)
				continue;
			list.add(item.getName());
		}
		return list;
	}

	public static void testEmpty(BaseModel model, String... columns) throws EmptyException {
		try {
			List<String> empty = new ArrayList<>();
			List<String> fields = getFields(model.getClass());
			for (String str : columns) {
				if (fields.contains(str)) {
					Method m = model.getClass().getMethod("get" + firstUpper(str));
					if (m.invoke(model) == null)
						empty.add(str);
				}
			}
			if (empty.size() != 0)
				throw new EmptyException(empty);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public static String getFilter(BaseModel model, String prefix) {
		String result = "";
		List<String> fields = getFields(model.getClass());
		for (String str : fields) {
			Method m;
			try {
				m = model.getClass().getMethod("get" + firstUpper(str));
				Object o = m.invoke(model);
				if (o != null) {
					if (o.getClass().equals(Date.class))
						result += prefix + "." + str + "=" + dateFormat.format(o);
					else
						result += prefix + "." + str + "=" + o;
					result += "&";
				}
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static int countPage(int total, int limit) {
		int nombrePage = total / limit;
		nombrePage = total % limit != 0 ? nombrePage + 1 : nombrePage;
		return nombrePage;
	}

	public static String upload(FileItem file, String contextPath, String folder, String fileName, int size,
			String... extensions) throws UploadException {
		if (file == null)
			return null;
		String extension = FilenameUtils.getExtension(file.getName());
		if (Functions.inArray(extension, extensions) == -1)
			throw new UploadException("Veuillez choisir des fichiers " + join(extensions, ", "));
		if (size != 0 && size < file.getSize())
			throw new UploadException("La taille du fichier ne doit pas éxceder " + (size / 1000000) + "Mo");
		String targetDir = contextPath + "/resources" + folder + "/";
		if (!new File(targetDir).exists()) {
			Path path = Paths.get(targetDir);
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				throw new UploadException("Un problème est survenu lors du téléchargement du fichier!");
			}
		}
		try {
			file.write(new File(targetDir + fileName + "." + extension));
		} catch (Exception e) {
			throw new UploadException("Un problème est survenu lors du téléchargement du fichier!");
		}
		return fileName + "." + extension;
	}

	public static int getMaxSimple(JsonElement element, String vip_extra) {
		JsonObject object = element.getAsJsonObject();
		JsonArray bloc = object.get("bloc").getAsJsonArray();
		int maxPlace = bloc.get(bloc.size() - 1).getAsInt();
		/*
		 * JsonArray vip = object.get("vip").getAsJsonArray(); for(JsonElement item :
		 * vip){ JsonArray array = item.getAsJsonArray(); int diff =
		 * array.get(1).getAsInt() - array.get(0).getAsInt() + 1; maxPlace -= diff; }
		 */
		if (vip_extra != null && vip_extra.contains(","))
			maxPlace -= explode(vip_extra, ",").length;
		return maxPlace;
	}

	public static String formatNumber(Number number) {
		String nu = NumberFormat.getInstance(Locale.FRANCE).format(number);
		return nu + "";
	}

	public static String printLongDate(DateTime date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(date.getTime());
		String[] dayOfW = { "Dimanche", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi" };
		String[] months = { "Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre",
				"Octobre", "Novembre", "Décembre" };
		String result = dayOfW[calendar.get(Calendar.DAY_OF_WEEK) - 1] + " "
				+ padLeft(calendar.get(Calendar.DAY_OF_MONTH) + "", 2) + " " + months[calendar.get(Calendar.MONTH)]
				+ " " + calendar.get(Calendar.YEAR);
		result += " à " + padLeft(calendar.get(Calendar.HOUR_OF_DAY) + "", 2) + ":"
				+ padLeft(calendar.get(Calendar.MINUTE) + "", 2);
		return result;
	}

	public static String printLongDate(DateSimple date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(date.getTime());
		String[] dayOfW = { "Dimanche", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi" };
		String[] months = { "Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre",
				"Octobre", "Novembre", "Décembre" };
		String result = dayOfW[calendar.get(Calendar.DAY_OF_WEEK) - 1] + " "
				+ padLeft(calendar.get(Calendar.DAY_OF_MONTH) + "", 2) + " " + months[calendar.get(Calendar.MONTH)]
				+ " " + calendar.get(Calendar.YEAR);
		return result;
	}

	public static String padLeft(String s, int n) {
		return String.format("%1$" + n + "s", s).replace(" ", "0");
	}

	public static String padRight(String s, int n) {
		return String.format("%1$-" + n + "s", s);
	}

	public static void generateQRCode(String path, String text, int size) {
		try {
			BitMatrix bitMatrix = new QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, size, size);
			FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
			MatrixToImageWriter.writeToStream(bitMatrix, "png", fileOutputStream);
			fileOutputStream.close();
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getIP(HttpServletRequest request) {
		return request.getRemoteHost() + "|" + request.getLocalAddr() + "|" + request.getRemoteAddr();
	}

	public static void createTextImg(String text, String pathOutput, Font font, Color color) {
		File file = new File(pathOutput);
		BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = img.createGraphics();
		g2d.setFont(font);
		FontMetrics fm = g2d.getFontMetrics();
		int width = fm.stringWidth(text);
		int height = fm.getHeight();
		g2d.dispose();
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		g2d = img.createGraphics();
		/*
		 * g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
		 * RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		 * g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		 * RenderingHints.VALUE_ANTIALIAS_ON);
		 * g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
		 * RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		 * g2d.setRenderingHint(RenderingHints.KEY_DITHERING,
		 * RenderingHints.VALUE_DITHER_ENABLE);
		 * g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
		 * RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		 * g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
		 * RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		 * g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
		 * RenderingHints.VALUE_RENDER_QUALITY);
		 * g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
		 * RenderingHints.VALUE_STROKE_PURE);
		 */
		g2d.setFont(font);
		fm = g2d.getFontMetrics();
		g2d.setColor(color);
		g2d.drawString(text, 0, fm.getAscent());
		g2d.dispose();
		try {
			ImageIO.write(img, "png", file);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static String correctListPlace(String places) {
		String[] split = places.trim().split(",");
		List<String> str = new ArrayList<>();
		for (String item : split) {
			try {
				int p = Integer.parseInt(item);
				if (p > 0 && !str.contains(item))
					str.add(item);
			} catch (NumberFormatException e) {
			}
		}
		String[] result = new String[str.size()];
		String s = Functions.join(str.toArray(result), ",");
		return s.equals("") ? null : s;
	}

	public static Gson getGson() {
		GsonBuilder builder = new GsonBuilder();
		builder.setDateFormat("yyyy/MM/dd HH:mm:ss");
		builder.registerTypeAdapter(DateTime.class, new DateTimeSerializing());
		builder.registerTypeAdapter(DateSimple.class, new DateSimpleSerializing());
		builder.serializeNulls();
		return builder.create();
	}

	public static Map<String, Object> initWsResponse() {
		Map<String, Object> map = new HashMap<>();
		map.put("status", "error");
		map.put("values", null);
		map.put("message", null);
		//map.put("errorCode", null);
		return map;
	}
}