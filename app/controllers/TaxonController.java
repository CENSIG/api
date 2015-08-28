package controllers;

import java.util.List;

import annotations.Auth;
import annotations.Caching;
import annotations.Cors;
import annotations.RequiredParam;
import annotations.StringParam;
import managers.TaxonManager;
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
import play.libs.Akka;
import play.libs.F.Function;
import play.libs.F.Function0;
import play.libs.F.Promise;
import play.libs.HttpExecution;
import play.mvc.Controller;
import play.mvc.Result;
import scala.concurrent.ExecutionContext;
import static utils.BuildResult.build;

/**
 * 
 * A controller for taxon
 * @author Jean BOUDET
 *
 */
public class TaxonController extends Controller 
{
	private static ExecutionContext bigRequest = HttpExecution.fromThread(Akka.system().dispatchers().lookup("play.akka.actor.bigRequest"));
	
	/**
	 * Show GeoJson for a ressource with specific id
	 * @param id
	 * @return The GeoJson result
	 */
	@Cors
	@Auth
	@Caching(time=60*10)
	public static Promise<Result> showGeoJson(final Long id)
	{
		return Promise.promise(
			new Function0<GeoJsonModel>() {
				public GeoJsonModel apply() {
					return TaxonManager.showGeoJson(id);
				}
			}, bigRequest
		).map(
			new Function<GeoJsonModel, Result>() {
				public Result apply(GeoJsonModel geojson) {
					return build(geojson, "Aucune observations pour la ressource "+id);
				}
			}, bigRequest
		);
	}
	
	/**
	 * Show informations for a ressource with specific id
	 * @param id
	 * @return The informations result
	 */
	@Cors
	@Auth
	@Caching(time=60*10)
	public static Promise<Result> showInformations(final Long id)
	{	
		return Promise.promise(
			new Function0<InformationsModel>() {
				public InformationsModel apply() {
					return TaxonManager.showInformations(id);
				}
			}, bigRequest
		).map(
			new Function<InformationsModel, Result>() {
				public Result apply(InformationsModel info) {
					return build(info, "Aucune informations trouvées pour la ressource "+id);
				}
			}, bigRequest
		);
	}
	
	/**
	 * Show parents for a specific taxon
	 * @param  id
	 * @param  limit limit for display hierarchie (default KD)
	 * @return The parents result
	 */
	@Cors
	@Auth
	@Caching(time=60*20)
	public static Promise<Result> showParents(final Long id, final String limit)
	{	
		return Promise.promise(
			new Function0<List<ParentsModel>>() {
				public List<ParentsModel> apply() {
					return TaxonManager.showParents(id, limit);
				}
			}
		).map(
			new Function<List<ParentsModel>, Result>() {
				public Result apply(List<ParentsModel> parents) {
					return build(parents, "Aucun parents trouvés pour la ressource "+id);
				}
			}
		);
	}
	
	/**
	 * Show childs for a specific taxon
	 * @param id
	 * @param q filter for child name
	 * @return the childs
	 */
	@RequiredParam("q")
	@StringParam("q")
	@Cors
	@Auth
	@Caching(time=60*1440)
	public static Promise<Result> showChilds(final Long id, final String q)
	{
		return Promise.promise(
			new Function0<List<ChildsModel>>() {
				public List<ChildsModel> apply() {
					return TaxonManager.showChilds(id, q);
				}
			}
		).map(
			new Function<List<ChildsModel>, Result>() {
				public Result apply(List<ChildsModel> childs) {
					return build(childs, "Aucun enfants trouvés pour la ressource "+id+" avec le filtre "+q);
				}
			}
		);
	}
	
	/**
	 * Show brothers for a specific taxon
	 * @param id the cdnom
	 * @return the brothers
	 */
	@Cors
	@Auth
	@Caching(time=60*20)
	public static Promise<Result> showBrothers(final Long id)
	{	
		return Promise.promise(
				new Function0<List<TaxonsModel>>() {
					public List<TaxonsModel> apply() {
						return TaxonManager.showBrothers(id);
					}
				}
		).map(
			new Function<List<TaxonsModel>, Result>() {
				public Result apply(List<TaxonsModel> brothers) {
					return build(brothers, "Aucun frères trouvés pour la ressource "+id);
				}
			}
		);
	}
	
	/**
	 * Show first child (with obs) of a specific taxon
	 * @param id	 The cdnom of taxon
	 * @param ordre	 The ordre of taxon
	 * @param format (optionnal) change the json output
	 * @return The first child
	 */
	@Cors
	@Auth
	@Caching(time=60*20)
	public static Promise<Result> showFirstChildObs(final Long id)
	{	
		return Promise.promise(
			new Function0<List<TaxonObsModel>>() {
				public List<TaxonObsModel> apply() {
					List<TaxonObsModel> info = TaxonManager.showFirstChildObs(id);
					return info;
				}
			}, bigRequest
		).map(
			new Function<List<TaxonObsModel>, Result>() {
				public Result apply(List<TaxonObsModel> obs) {
					return build(obs, "Aucune observations trouvées pour les enfants direct de la ressource "+id);
				}
			}, bigRequest
		);
	}
	
	/**
	 * Show photo of a specific taxon
	 * @param id
	 * @return the list of photos
	 */
	@Cors
	@Auth
	@Caching(time=60*10)
	public static Promise<Result> showPhotos(final Long id)
	{
		return Promise.promise(
			new Function0<List<PhotoModel>>() {
				public List<PhotoModel> apply() {
					return TaxonManager.showPhotos(id);
				}
			}
		).map(
			new Function<List<PhotoModel>, Result>() {
				public Result apply(List<PhotoModel> photos) {
					return build(photos, "Aucune photos trouvées pour la ressource "+id);
				}
			}
		);
	}
	
	/**
	 * Show monographies of a specific taxon
	 * @param id
	 * @return the list of monographies
	 */
	@Cors
	@Auth
	@Caching(time=60*10)
	public static Promise<Result> showMonographies(final Long id)
	{
		return Promise.promise(
			new Function0<List<MonographieModel>>() {
				public List<MonographieModel> apply() {
					return TaxonManager.showMonographies(id);
				}
			}
		).map(
			new Function<List<MonographieModel>, Result>() {
				public Result apply(List<MonographieModel> monographies) {
					return build(monographies, "Aucune monographies trouvées pour la ressource "+id);
				}
			}
		);
	}
	
	/**
	 * Show phenologie of a specific taxon
	 * @param id
	 * @return the phenologie
	 */
	@Cors
	@Auth
	@Caching(time=60*10)
	public static Promise<Result> showPhenologie(final Long id)
	{	
		return Promise.promise(
			new Function0<List<PhenologieModel>>() {
				public List<PhenologieModel> apply() {
					return TaxonManager.showPhenologie(id);
				}
			}, bigRequest
		).map(
			new Function<List<PhenologieModel>, Result>() {
				public Result apply(List<PhenologieModel> phenologie) {
					return build(phenologie, "Aucune observations pour la ressource "+id);
				}
			}, bigRequest
		);
	}
	
	@Cors
	@RequiredParam("output")
	@StringParam("output")
	@Auth
	@Caching(time=60*1440)
	public static Promise<Result> showAlphabetObservateurs(final Long id, final String output)
	{
		return Promise.promise(
				new Function0<List<UserModel>>() {
					public List<UserModel> apply() {
						List<UserModel> users = null;
						if (output.equals("alphabet")) {
							users = TaxonManager.showAlphabetObservateurs(id);
						} else {
							users = TaxonManager.showObservateurs(id, output);
						}
						return users;
					}
				}
		).map(
			new Function<List<UserModel>, Result>() {
				public Result apply(List<UserModel> obj) {
					return build(obj, "Pas d'observateur pour la ressource "+id);
				}
			}
		);
	}
}
