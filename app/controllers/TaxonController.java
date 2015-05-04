package controllers;

import actions.CorsAction;
import managers.TaxonManager;
import play.libs.F.Promise;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;

/**
 * 
 * A controller for taxon
 * @author Jean BOUDET
 *
 */
@With(CorsAction.class)
public class TaxonController extends Controller 
{
	/**
	 * Show GeoJson for a ressource with specific id
	 * @param id
	 * @return The GeoJson
	 */
	public static Promise<Result> showGeoJson(Long id)
	{
		return TaxonManager.showGeoJson(id);
	}
	
	/**
	 * Show informations for a ressource with specific id
	 * @param id
	 * @return The informations
	 */
	public static Promise<Result> showInformations(Long id)
	{
		return TaxonManager.showInformations(id);
	}
}