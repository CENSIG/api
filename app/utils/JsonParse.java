package utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import play.libs.Json;

/**
 * 
 * This class allows convert String to JsonNode
 * for a specific field
 * @author Jean BOUDET
 *
 */
public final class JsonParse 
{
	
	/**
	 * Method for convert specific field String
	 * to JsonNode
	 * @param a An object
	 * @return The modified object
	 */
	public static <T> T parse(T a)
	{
		if (a == null) {
			return a;
		}
		
		Class<? extends Object> cls = a.getClass();
		
		Field geomStringField = null;
		Field geomJsonField = null;
		
		for (Field field : cls.getDeclaredFields()) {
			Annotation geomStringAnnotation = field.getAnnotation(actions.StringJson.class);
			Annotation geomJsonAnnotation   = field.getAnnotation(actions.ToJson.class);
			
			if (geomStringAnnotation != null) {
				geomStringField = field;
			}
			
			if (geomJsonAnnotation != null) {
				geomJsonField = field;
			}
		}
		
		if (geomStringField != null && geomJsonField != null) {
			try {
				Object typeString = geomStringField.getType().cast(geomStringField.get(a));
				geomJsonField.set(a, Json.parse((String) typeString));
				geomStringField.set(a, null);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		return a;
	}
}
