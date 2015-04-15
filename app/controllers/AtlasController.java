package controllers;

import actions.UriLabel;

import play.Configuration;
import play.Play;
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
	public static Result list(String type) 
	{
		Configuration confLibelle = Play.application().configuration().getConfig("libelle");
		return ok("Affiche les infos sur les " + confLibelle.getString(type));
    }

	
	/**
	 * Show informations about ressource (type) with an id
	 * @param  type The ressouce
	 * @param  id   The identifiant
	 * @return Http response (json)
	 */
	public static Result show(String type, Long id)
	{
		Configuration confLibelle = Play.application().configuration().getConfig("libelle");
		return ok("Affiche les infos sur le " + confLibelle.getString(type) + " dont l'id est " + id);
	}

}
