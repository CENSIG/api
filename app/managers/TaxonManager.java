package managers;

import static utils.JsonParse.parse;

import java.util.List;
import models.ChildsModel;
import models.GeoJsonModel;
import models.InformationsModel;
import models.MonographieModel;
import models.ParentsModel;
import models.PhenologieModel;
import models.PhotoModel;
import models.TaxonObsModel;
import models.TaxonsModel;
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
	public static GeoJsonModel showGeoJson(Long id)
	{
		return Ebean.createNamedQuery(GeoJsonModel.class, "show")
				.setParameter("id", Long.toString(id))
				.findUnique();
		
		//return createResponse(parse(res), "L'identifiant "+id+" n'existe pas pour cette ressouce");
	}
	
	/**
	 * Get the response (Informations)
	 * @param  id   The identifiant
	 * @return Json response
	 */
	public static InformationsModel showInformations(Long id)
	{
		InformationsModel res = Ebean.createNamedQuery(InformationsModel.class, "show")
				.setParameter("id", Long.toString(id))
				.findUnique();
		return res;
		//return createResponse(res, "L'identifiant "+id+" n'existe pas pour cette ressource");
	}
	
	/**
	 * Get the response (parents)
	 * @param 	id The identifiant
	 * @return	Json response (json array of parents)
	 */
	public static List<ParentsModel> showParents(Long id, String limit)
	{
		String filter = Play.application().configuration().getConfig("parents").getString(limit);
		
		List<ParentsModel> res = Ebean.createNamedQuery(ParentsModel.class, "show")
				.setParameter("id", Long.toString(id))
				.setParameter("limit", filter)
				.findList();
		return res;
		//res = (isValid(res)) ? res : null;
		
		//return createResponse(res, "L'identifiant "+id+" n'existe pas pour cette ressource");
	}
	
	/**
	 * Get the response (childs)
	 * @param id the identifiant
	 * @param search filter for child name
	 * @return Json response (json array of childs)
	 */
	public static List<ChildsModel> showChilds(Long id, String search)
	{
		List<ChildsModel> res = Ebean.createNamedQuery(ChildsModel.class, "show")
				.setParameter("id", Long.toString(id))
				.setParameter("q", search+"%")
				.findList();
		return res;
		//res = (isValid(res)) ? res : null;
		
		//return createResponse(res, "Aucun taxon trouvé pour la ressource "+id+" et le filtre "+search);
	}
	
	/**
	 * Get the response (brothers)
	 * @param id the cdnom
	 * @return Json response (json array of brothers)
	 */
	public static List<TaxonsModel> showBrothers(Long id)
	{
		List<TaxonsModel> res = Ebean.createNamedQuery(TaxonsModel.class, "show")
				.setParameter("id", Long.toString(id))
				.findList();
		return res;
		//res = (isValid(res)) ? res : null;
		
		//return createResponse(res, "Aucun frêres trouvés pour la ressource "+id);
	}
	
	/**
	 * Get the response (first child)
	 * @param id	 The cdnom
	 * @param ordre	 The ordre
	 * @param format (optionnal) output for json
	 * @return Json response (json array of first childs)
	 */
	public static List<TaxonObsModel> showFirstChildObs(Long id)
	{
		List<TaxonObsModel> res = Ebean.createNamedQuery(TaxonObsModel.class, "firstChildObs")
				.setParameter("id", Long.toString(id))
				.findList();
		return res;
		//res = (isValid(res)) ? res : null;
		
		//return createResponse(res, "Aucun fils direct trouvés pour la ressource "+id);
	}

	/**
	 * Get the response (photo)
	 * @param id the cdnom
	 * @return Json response (json array photo)
	 */
	public static List<PhotoModel> showPhotos(Long id) {
		
		List<PhotoModel> res = Ebean.createNamedQuery(PhotoModel.class, "show")
				.setParameter("id", Long.toString(id))
				.findList();
		return res;
		//res = (isValid(res)) ? res : null;
		
		//return createResponse(res, "Aucune photos trouvés pour la ressource "+id);
	}

	/**
	 * Get the response (monographie)
	 * @param id the cdnom
	 * @return Json response (json list monographies)
	 */
	public static List<MonographieModel> showMonographies(Long id) {
		List<MonographieModel> res = Ebean.createNamedQuery(MonographieModel.class, "show")
				.setParameter("id", Long.toString(id))
				.findList();
		return res;
		//res = (isValid(res)) ? res : null;
		
		//return createResponse(res, "Aucune monographies trouvés pour la ressource "+id);
	}

	/**
	 * Get the response (phenologie)
	 * @param id the cdnom
	 * @return	Json response (json phenologie: mois, total, adultes, larves)
	 */
	public static List<PhenologieModel> showPhenologie(Long id) {
		List<PhenologieModel> res = Ebean.createNamedQuery(PhenologieModel.class, "show")
				.setParameter("id", Long.toString(id))
				.findList();
		return res;
		
		//res = (isValid(res)) ? res : null;
		
		//return createResponse(res, "Aucune observations pour la ressource "+id);
	}
}

