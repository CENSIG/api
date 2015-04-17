package managers;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Query;

import models.Espece;
import models.InfoType;
import play.libs.F.Promise;
import play.mvc.Result;

/**
 * A manager for atlas
 * @author Jean BOUDET
 */
public class AtlasManager extends Manager
{	
	/**
	 * Get informations about type
	 * @param 	type The ressouce
	 * @return	json
	 */
	public static Promise<Result> info(String type)
	{
		Query<InfoType> query = Ebean.createNamedQuery(InfoType.class, "info")
				.setParameter("type", type);
		return createResponse(query.findUnique());
	}
	
	/**
	 * Show informations on ressouce with a specific id
	 * @param  type The ressouce
	 * @param  id   The identifiant
	 * @return json
	 */
	public static Promise<Result> show(String type, Long id)
	{
		Query<Espece> query = Ebean.createNamedQuery(Espece.class, "show")
			.setParameter("id", Long.toString(id))
			.setParameter("type", type);
		return createResponse(query.findUnique(), "L'identifiant "+id+" n'existe pas pour cette ressouce");
	}

}
