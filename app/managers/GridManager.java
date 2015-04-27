package managers;

import java.util.ArrayList;
import java.util.List;

import models.GridModel;

import com.avaje.ebean.Ebean;

import play.libs.F.Promise;
import play.libs.Json;
import play.mvc.Result;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * 
 * A manager for grid
 * @author Jean BOUDET
 *
 */
public class GridManager extends Manager 
{
	/**
	 * Get the response (Geojson)
	 * @return Json response
	 */
	public static Promise<Result> show()
	{
		GridModel grid 	= new GridModel();
		grid.type 		= "GeometryCollection";
		grid.geometries = new ArrayList<JsonNode>();
		
		List<GridModel> res = Ebean.createNamedQuery(GridModel.class, "show")
				.findList();
		
		for (GridModel g : res) {
			grid.geometries.add(Json.parse(g.geometriesList));
		}
		return createResponse(grid);
	}
}
