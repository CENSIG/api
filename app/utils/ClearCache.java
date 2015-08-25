package utils;

import play.cache.Cache;

/**
 * Class for remove data in the cache
 * @author Jean BOUDET
 *
 */
public class ClearCache 
{
	private final static String[] OBJECTS = { "geojson", "informations", "phenologie", "first_child_obs" };
	
	public static void removeFor(String ressource, String id)
	{
		StringBuffer beginkey = new StringBuffer();
		beginkey.append(ressource);
		beginkey.append(".");
		beginkey.append(id);
		beginkey.append(".");
		
		for (int i = 0; i < OBJECTS.length; i++) {
			StringBuffer key = new StringBuffer(beginkey);
			key.append(OBJECTS[i]);
			Cache.remove(key.toString());
		}
	}
}
