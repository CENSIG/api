package controllers;

import managers.GeoJsonManager;
import play.libs.F.Promise;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * 
 * A controller for geojson output
 * @author Jean BOUDET
 *
 */
public class GeoJsonController extends Controller 
{
	
	/**
	 * Show the geojson for a ressource with specific id
	 * @param id
	 * @return The GeoJson
	 */
	public static Promise<Result> show(Long id)
	{
		return GeoJsonManager.show(id);
	}
}
