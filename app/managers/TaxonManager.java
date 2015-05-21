package managers;

import static utils.JsonParse.parse;

import java.util.List;

import models.ChildsModel;
import models.GeoJsonModel;
import models.InformationsModel;
import models.ParentsModel;
import models.TaxonsModel;
import play.Configuration;
import play.Play;
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
	 * @return	Json response (json array of parents)
	 */
	public static Promise<Result> showParents(Long id, String limit)
	{
		String filter = Play.application().configuration().getConfig("parents").getString(limit);
		
		List<ParentsModel> res = Ebean.createNamedQuery(ParentsModel.class, "show")
				.setParameter("id", Long.toString(id))
				.setParameter("limit", filter)
				.findList();
		
		res = (isValid(res)) ? res : null;
		
		return createResponse(res, "L'identifiant "+id+" n'existe pas pour cette ressource");
	}
	
	/**
	 * Get the response (childs)
	 * @param id the identifiant
	 * @param search filter for child name
	 * @return Json response (json array of childs)
	 */
	public static Promise<Result> showChilds(Long id, String search)
	{
		List<ChildsModel> res = Ebean.createNamedQuery(ChildsModel.class, "show")
				.setParameter("id", Long.toString(id))
				.setParameter("q", "%"+search+"%")
				.findList();
		
		res = (isValid(res)) ? res : null;
		
		return createResponse(res, "Aucun taxon trouvé pour la ressource "+id+" et le filtre "+search);
	}
}
