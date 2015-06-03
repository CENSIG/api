package managers;

import static utils.JsonParse.parse;

import java.util.List;

import models.ChartObsModel;
import models.ChildsModel;
import models.GeoJsonModel;
import models.InformationsModel;
import models.ParentsModel;
import models.TaxonObsModel;
import models.TaxonsModel;
import play.Configuration;
import play.Play;
import play.libs.F.Promise;
import play.mvc.Result;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Query;

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
		
		return createResponse(res, "L'identifiant "+id+" n'existe pas pour cette ressource");
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
	
	/**
	 * Get the response (synonimes)
	 * @param id the cdnom
	 * @return Json response (json array of synonimes)
	 */
	public static Promise<Result> showSynonimes(Long id)
	{
		List<TaxonObsModel> res = Ebean.createNamedQuery(TaxonObsModel.class, "synonymes")
				.setParameter("id", Long.toString(id))
				.findList();
		
		res = (isValid(res)) ? res : null;
		
		return createResponse(res, "Aucun synonimes trouvés pour la ressource "+id);
	}
	
	/**
	 * Get the response (brothers)
	 * @param id the cdnom
	 * @return Json response (json array of brothers)
	 */
	public static Promise<Result> showBrothers(Long id)
	{
		List<TaxonsModel> res = Ebean.createNamedQuery(TaxonsModel.class, "show")
				.setParameter("id", Long.toString(id))
				.findList();
		
		res = (isValid(res)) ? res : null;
		
		return createResponse(res, "Aucun frêres trouvés pour la ressource "+id);
	}
	
	/**
	 * Get the response (first child)
	 * @param id	 The cdnom
	 * @param ordre	 The ordre
	 * @param format (optionnal) output for json
	 * @return Json response (json array of first childs)
	 */
	public static Promise<Result> showFirstChildObs(Long id, String format)
	{
		Query query = null;
		
		if (format.equals("base")) {
			query = Ebean.createNamedQuery(TaxonObsModel.class, "firstChildObs");
		} else {
			query = Ebean.createNamedQuery(ChartObsModel.class, "firstChildObs");
				
		}
		
		List res = query.setParameter("id", Long.toString(id))
				.findList();
		
		res = (isValid(res)) ? res : null;
		
		return createResponse(res, "Aucun fils direct trouvés pour la ressource "+id);
	}
}

