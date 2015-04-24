package managers;

import models.InformationsModel;
import play.libs.F.Promise;
import play.mvc.Result;

import com.avaje.ebean.Ebean;

import static utils.JsonParse.parse;

/**
 * 
 * A manager for informations
 * @author Jean BOUDET
 *
 */
public class InformationsManager extends Manager 
{
	/**
	 * Get the response
	 * @param  id   The identifiant
	 * @return Json response
	 */
	public static Promise<Result> show(Long id)
	{
		InformationsModel res = Ebean.createNamedQuery(InformationsModel.class, "show")
				.setParameter("id", Long.toString(id))
				.findUnique();
		
		return createResponse(parse(res), "L'identifiant "+id+" n'existe pas pour cette ressouce");
	}
}
