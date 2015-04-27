package controllers;

import managers.GridManager;
import play.libs.F.Promise;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * 
 * A controller for grid
 * @author Jean BOUDET
 *
 */
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
