package controllers;

import managers.InformationsManager;
import play.libs.F.Promise;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * 
 * A controller for informations on taxon
 * @author Jean BOUDET
 *
 */
public class InformationsController extends Controller 
{
	/**
	 * Show informations on ressource with specific id
	 * @param id
	 * @return The informations (Json)
	 */
	public static Promise<Result> show(Long id)
	{
		return InformationsManager.show(id);
	}
}
