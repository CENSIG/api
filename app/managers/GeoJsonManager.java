package managers;

import static utils.JsonParse.parse;
import models.GeoJsonModel;

import com.avaje.ebean.Ebean;

import play.libs.F.Promise;
import play.mvc.Result;

/**
 * 
 * A manager for geojson
 * @author Jean BOUDET
 *
 */
public class GeoJsonManager extends Manager 
{
	/**
	 * Get the response
	 * @param  id   The identifiant
	 * @return Json response
	 */
	public static Promise<Result> show(Long id)
	{
		GeoJsonModel res = Ebean.createNamedQuery(GeoJsonModel.class, "show")
				.setParameter("id", Long.toString(id))
				.findUnique();
		
		res = (isValid(res)) ? parse(res) : null;
		
		return createResponse(res, "L'identifiant "+id+" n'existe pas pour cette ressouce");
	}
}
