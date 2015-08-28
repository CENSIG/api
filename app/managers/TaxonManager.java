package managers;


import java.util.List;

import models.ChildsModel;
import models.GeoJsonModel;
import models.InformationsModel;
import models.MonographieModel;
import models.ParentsModel;
import models.PhenologieModel;
import models.PhotoModel;
import models.UserModel;
import models.TaxonObsModel;
import models.TaxonsModel;
import play.Play;
import play.libs.Json;

import com.avaje.ebean.Ebean;

public class TaxonManager extends Manager 
{
	/**
	 * Get the data (GeoJson)
	 * @param  id   The identifiant
	 * @return data or null
	 */
	public static GeoJsonModel showGeoJson(Long id)
	{
		GeoJsonModel res = Ebean.createNamedQuery(GeoJsonModel.class, "show")
				.setParameter("id", Long.toString(id))
				.findUnique();
		
		if (res.featuresString == null) {
			return null;
		}
		
		res.type = "FeatureCollection";
		res.features = Json.parse(res.featuresString);
		res.featuresString = null;
		
		return res;
	}
	
	/**
	 * Get the response (Informations)
	 * @param  id   The identifiant
	 * @return data or null
	 */
	public static InformationsModel showInformations(Long id)
	{
		return Ebean.createNamedQuery(InformationsModel.class, "show")
				.setParameter("id", Long.toString(id))
				.findUnique();
	}
	
	/**
	 * Get the response (parents)
	 * @param 	id The identifiant
	 * @return	data or null
	 */
	public static List<ParentsModel> showParents(Long id, String limit)
	{
		String filter = Play.application().configuration().getConfig("parents").getString(limit);
		
		List<ParentsModel> res = Ebean.createNamedQuery(ParentsModel.class, "show")
				.setParameter("id", Long.toString(id))
				.setParameter("limit", filter)
				.findList();
		
		return isValid(res) ? res : null;
	}
	
	/**
	 * Get the response (childs)
	 * @param id the identifiant
	 * @param search filter for child name
	 * @return data or null
	 */
	public static List<ChildsModel> showChilds(Long id, String search)
	{
		List<ChildsModel> res = Ebean.createNamedQuery(ChildsModel.class, "show")
				.setParameter("id", Long.toString(id))
				.setParameter("q", search+"%")
				.findList();
		
		return isValid(res) ? res : null;
	}
	
	/**
	 * Get the response (brothers)
	 * @param id the cdnom
	 * @return data or null
	 */
	public static List<TaxonsModel> showBrothers(Long id)
	{
		List<TaxonsModel> res = Ebean.createNamedQuery(TaxonsModel.class, "show")
				.setParameter("id", Long.toString(id))
				.findList();
		return isValid(res) ? res : null;
	}
	
	/**
	 * Get the response (first child)
	 * @param id	 The cdnom
	 * @param ordre	 The ordre
	 * @param format (optionnal) output for json
	 * @return data or null
	 */
	public static List<TaxonObsModel> showFirstChildObs(Long id)
	{
		List<TaxonObsModel> res = Ebean.createNamedQuery(TaxonObsModel.class, "firstChildObs")
				.setParameter("id", Long.toString(id))
				.findList();
		return isValid(res) ? res : null;
	}

	/**	
	 * Get the response (photo)
	 * @param id the cdnom
	 * @return data or null
	 */
	public static List<PhotoModel> showPhotos(Long id) {
		
		List<PhotoModel> res = Ebean.createNamedQuery(PhotoModel.class, "show")
				.setParameter("id", Long.toString(id))
				.findList();
		return isValid(res) ? res : null;
	}

	/**
	 * Get the response (monographie)
	 * @param id the cdnom
	 * @return data or null
	 */
	public static List<MonographieModel> showMonographies(Long id) {
		List<MonographieModel> res = Ebean.createNamedQuery(MonographieModel.class, "show")
				.setParameter("id", Long.toString(id))
				.findList();
		return isValid(res) ? res : null;
	}

	/**
	 * Get the response (phenologie)
	 * @param id the cdnom
	 * @return	data or null
	 */
	public static List<PhenologieModel> showPhenologie(Long id) {
		List<PhenologieModel> res = Ebean.createNamedQuery(PhenologieModel.class, "show")
				.setParameter("id", Long.toString(id))
				.findList();
		return isValid(res) ? res : null;
	}
	
	public static List<UserModel> showAlphabetObservateurs(Long id) {
		List<UserModel> res = Ebean.createNamedQuery(UserModel.class, "showAlphabet")
				.setParameter("id", Long.toString(id))
				.setParameter("delimiter", "&")
				.findList();
		return isValid(res) ? res : null;
	}
	
	public static List<UserModel> showObservateurs(Long id, String output) {
		List<UserModel> res = Ebean.createNamedQuery(UserModel.class, "showWithOutput")
				.setParameter("id", Long.toString(id))
				.setParameter("delimiter", "&")
				.setParameter("output", output+"%")
				.findList();
		return isValid(res) ? res : null;
	}
}

