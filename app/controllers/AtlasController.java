package controllers;

import managers.AtlasManager;
import actions.UriLabel;
import play.Configuration;
import play.Play;
import play.libs.F.Promise;
import play.mvc.Controller;
import play.mvc.Result;



/**
 * A controller for atlas consultation
 * @author Jean BOUDET
 */
@UriLabel
public class AtlasController extends Controller 
{
	/**
	 * List all informations about resource (type)
	 * @param  type The ressource
	 * @return Http response (json)
	 */
	public static Promise<Result> info(String type) 
	{
		Configuration confLibelle = Play.application().configuration().getConfig("libelle");
		return AtlasManager.info(confLibelle.getString(type));
    }

	
	/**
	 * Show informations about ressource (type) with an id
	 * @param  type The ressouce
	 * @param  id   The identifiant
	 * @return Http response (json)
	 */
	public static Promise<Result> show(String type, Long id)
	{
		Configuration confLibelle = Play.application().configuration().getConfig("libelle");
		return AtlasManager.show(confLibelle.getString(type), id);
	}

}
