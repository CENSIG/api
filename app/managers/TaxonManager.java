package managers;

import static utils.JsonParse.parse;

import java.util.List;

import models.GeoJsonModel;
import models.InformationsModel;
import models.ParentsModel;
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
		
		return createResponse(parse(res), "L'identifiant "+id+" n'existe pas pour cette ressouce");
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
		
		return createResponse(parse(res), "L'identifiant "+id+" n'existe pas pour cette ressource");
	}
	
	/**
	 * Get the response (parents)
	 * @param 	id The identifiant
	 * @return	Json response
	 */
	public static Promise<Result> showParents(Long id)
	{
		List<ParentsModel> res = Ebean.createNamedQuery(ParentsModel.class, "show")
				.setParameter("id", Long.toString(id))
				.findList();
		
		res = (isValid(res)) ? res : null;
		
		return createResponse(res, "L'identifiant "+id+" n'existe pas pour cette ressource");
	}
}
