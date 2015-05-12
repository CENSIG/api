package controllers;

import actions.CorsAction;
import managers.GridManager;
import play.libs.F.Promise;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;

/**
 * 
 * A controller for grid
 * @author Jean BOUDET
 *
 */
@With(CorsAction.class)
public class GridController extends Controller 
{
	/**
	 * Show for the moment the grid 10 km
	 * @return the geojson
	 */
	public static Promise<Result> show()
	{
		return GridManager.show();
	}
}
