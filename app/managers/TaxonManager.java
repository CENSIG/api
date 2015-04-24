package managers;

import static utils.JsonParse.parse;
import models.GeoJsonModel;
import models.InformationsModel;
import play.libs.F.Promise;
import play.mvc.Result;

import com.avaje.ebean.Ebean;

public class TaxonManager extends Manager 
{
	/**
	 * Get the response (GeoJson)
	 * @param  id   The identifiant
	 * @return Json response
	 */
	public static Promise<Result> showGeoJson(Long id)
	{
		GeoJsonModel res = Ebean.createNamedQuery(GeoJsonModel.class, "show")
				.setParameter("id", Long.toString(id))
				.findUnique();
		
		res = (isValid(res)) ? parse(res) : null;
		
		return createResponse(res, "L'identifiant "+id+" n'existe pas pour cette ressouce");
	}
	
	/**
	 * Get the response (Informations)
	 * @param  id   The identifiant
	 * @return Json response
	 */
	public static Promise<Result> showInformations(Long id)
	{
		InformationsModel res = Ebean.createNamedQuery(InformationsModel.class, "show")
				.setParameter("id", Long.toString(id))
				.findUnique();
		
		return createResponse(parse(res), "L'identifiant "+id+" n'existe pas pour cette ressouce");
	}
}
