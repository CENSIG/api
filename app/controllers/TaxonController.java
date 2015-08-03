package controllers;

import java.util.List;

import actions.CorsAction;
import actions.RequiredParamAnnotation;
import actions.StringParamAnnotation;
import managers.TaxonManager;
import models.ChildsModel;
import models.GeoJsonModel;
import models.InformationsModel;
import models.MonographieModel;
import models.ParentsModel;
import models.PhenologieModel;
import models.PhotoModel;
import models.TaxonObsModel;
import models.TaxonsModel;
import play.libs.Akka;
import play.libs.F;
import play.libs.F.Function;
import play.libs.F.Function0;
import play.libs.F.Promise;
import play.libs.HttpExecution;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import scala.concurrent.ExecutionContext;
import static utils.JsonParse.parse;

/**
 * 
 * A controller for taxon
 * @author Jean BOUDET
 *
 */
@With(CorsAction.class)
public class TaxonController extends Controller 
{
	//private static ExecutionContext context = Akka.system().dispatchers().lookup("play.akka.actor.my-context");
	
	/**
	 * Show GeoJson for a ressource with specific id
	 * @param id
	 * @return The GeoJson
	 */
	public static Promise<Result> showGeoJson(final Long id)
	{
		ExecutionContext context = HttpExecution.fromThread(Akka.system().dispatchers().lookup("play.akka.actor.my-context"));
		
		F.Promise<GeoJsonModel> promise = F.Promise.promise(
				new Function0<GeoJsonModel>() {
					public GeoJsonModel apply() {
						GeoJsonModel geojson = TaxonManager.showGeoJson(id);
						geojson.features = Json.parse(geojson.featuresString);
						geojson.featuresString = null;
						return geojson;
					}
				}, context
		);
		
		return promise.map(
			new Function<GeoJsonModel, Result>() {
				public Result apply(GeoJsonModel geojson) {
					return ok(Json.toJson(geojson));
				}
			}, context
		);
		
		//return geojson == null ? notFound("pas trouvé") : ok(Json.toJson(geojson));
	}
	
	/**
	 * Show informations for a ressource with specific id
	 * @param id
	 * @return The informations
	 */
	public static Promise<Result> showInformations(final Long id)
	{
		//ExecutionContext context = Akka.system().dispatchers().lookup("play.akka.actor.my-context");
		ExecutionContext context = HttpExecution.fromThread(Akka.system().dispatchers().lookup("play.akka.actor.my-context"));
		
		F.Promise<InformationsModel> promise = F.Promise.promise(
				new Function0<InformationsModel>() {
					public InformationsModel apply() {
						InformationsModel info = TaxonManager.showInformations(id);
						return info;
					}
				}, context
		);
		
		return promise.map(
			new Function<InformationsModel, Result>() {
				public Result apply(InformationsModel info) {
					return ok(Json.toJson(info));
				}
			}, context
		);
		//return TaxonManager.showInformations(id);
	}
	
	/**
	 * Show parents for a specific taxon
	 * @param  id
	 * @param  limit limit for display hierarchie (default KD)
	 * @return The parents
	 */
	@StringParamAnnotation("limit")
	public static Promise<Result> showParents(final Long id, final String limit)
	{
		//ExecutionContext context = Akka.system().dispatchers().lookup("play.akka.actor.my-context");
		
		F.Promise<List<ParentsModel>> promise = F.Promise.promise(
				new Function0<List<ParentsModel>>() {
					public List<ParentsModel> apply() {
						List<ParentsModel> info = TaxonManager.showParents(id, limit);
						return info;
					}
				}
		);
		
		return promise.map(
			new Function<List<ParentsModel>, Result>() {
				public Result apply(List<ParentsModel> info) {
					return ok(Json.toJson(info));
				}
			}
		);
		//return TaxonManager.showParents(id, limit);
	}
	
	/**
	 * Show childs for a specific taxon
	 * @param id
	 * @param q filter for child name
	 * @return the childs
	 */
	@RequiredParamAnnotation("q")
	@StringParamAnnotation("q")
	public static Promise<Result> showChilds(final Long id, final String q)
	{
		//ExecutionContext context = Akka.system().dispatchers().lookup("play.akka.actor.my-context");
		
		F.Promise<List<ChildsModel>> promise = F.Promise.promise(
				new Function0<List<ChildsModel>>() {
					public List<ChildsModel> apply() {
						List<ChildsModel> info = TaxonManager.showChilds(id, q);
						return info;
					}
				}
		);
		
		return promise.map(
			new Function<List<ChildsModel>, Result>() {
				public Result apply(List<ChildsModel> info) {
					return ok(Json.toJson(info));
				}
			}
		);
		//return TaxonManager.showChilds(id, q);
	}
	
	/**
	 * Show brothers for a specific taxon
	 * @param id the cdnom
	 * @return the brothers
	 */
	public static Promise<Result> showBrothers(final Long id)
	{
		//ExecutionContext context = Akka.system().dispatchers().lookup("play.akka.actor.my-context");
		
		F.Promise<List<TaxonsModel>> promise = F.Promise.promise(
				new Function0<List<TaxonsModel>>() {
					public List<TaxonsModel> apply() {
						List<TaxonsModel> info = TaxonManager.showBrothers(id);
						return info;
					}
				}
		);
		
		return promise.map(
			new Function<List<TaxonsModel>, Result>() {
				public Result apply(List<TaxonsModel> info) {
					return ok(Json.toJson(info));
				}
			}
		);
		//return TaxonManager.showBrothers(id);
	}
	
	
	/**
	 * Show first child (with obs) of a specific taxon
	 * @param id	 The cdnom of taxon
	 * @param ordre	 The ordre of taxon
	 * @param format (optionnal) change the json output
	 * @return The first child
	 */
	public static Promise<Result> showFirstChildObs(final Long id)
	{
		//ExecutionContext context = Akka.system().dispatchers().lookup("play.akka.actor.my-context");
		
		F.Promise<List<TaxonObsModel>> promise = F.Promise.promise(
				new Function0<List<TaxonObsModel>>() {
					public List<TaxonObsModel> apply() {
						List<TaxonObsModel> info = TaxonManager.showFirstChildObs(id);
						return info;
					}
				}
		);
		
		return promise.map(
			new Function<List<TaxonObsModel>, Result>() {
				public Result apply(List<TaxonObsModel> info) {
					return ok(Json.toJson(info));
				}
			}
		);
		//return TaxonManager.showFirstChildObs(id);
	}
	
	/**
	 * Show photo of a specific taxon
	 * @param id
	 * @return the list of photos
	 */
	public static Promise<Result> showPhotos(final Long id)
	{
		//ExecutionContext context = Akka.system().dispatchers().lookup("play.akka.actor.my-context");
		
		F.Promise<List<PhotoModel>> promise = F.Promise.promise(
				new Function0<List<PhotoModel>>() {
					public List<PhotoModel> apply() {
						List<PhotoModel> info = TaxonManager.showPhotos(id);
						return info;
					}
				}
		);
		
		return promise.map(
			new Function<List<PhotoModel>, Result>() {
				public Result apply(List<PhotoModel> info) {
					return ok(Json.toJson(info));
				}
			}
		);
		//return TaxonManager.showPhotos(id);
	}
	
	/**
	 * Show monographies of a specific taxon
	 * @param id
	 * @return the list of monographies
	 */
	public static Promise<Result> showMonographies(final Long id)
	{
		//ExecutionContext context = Akka.system().dispatchers().lookup("play.akka.actor.my-context");
		
		F.Promise<List<MonographieModel>> promise = F.Promise.promise(
				new Function0<List<MonographieModel>>() {
					public List<MonographieModel> apply() {
						List<MonographieModel> info = TaxonManager.showMonographies(id);
						return info;
					}
				}
		);
		
		return promise.map(
			new Function<List<MonographieModel>, Result>() {
				public Result apply(List<MonographieModel> info) {
					return ok(Json.toJson(info));
				}
			}
		);
		//return TaxonManager.showMonographies(id);
	}
	
	/**
	 * Show phenologie of a specific taxon
	 * @param id
	 * @return the phenologie
	 */
	public static Promise<Result> showPhenologie(final Long id)
	{
		//ExecutionContext context = Akka.system().dispatchers().lookup("play.akka.actor.my-other-context");
		
		F.Promise<List<PhenologieModel>> promise = F.Promise.promise(
				new Function0<List<PhenologieModel>>() {
					public List<PhenologieModel> apply() {
						List<PhenologieModel> list = TaxonManager.showPhenologie(id);
						return list;
					}
				}
		);
		
		return promise.map(
			new Function<List<PhenologieModel>, Result>() {
				public Result apply(List<PhenologieModel> list) {
					return ok(Json.toJson(list));
				}
			}
		);
	}
}
