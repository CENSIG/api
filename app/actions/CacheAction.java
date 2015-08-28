package actions;

import java.util.Iterator;
import java.util.Map;

import annotations.Caching;
import play.libs.F.Callback;
import play.libs.F.Promise;
import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.cache.Cache;

/**
 * Action for cache data. The key for cache is
 * "ressource.id.object" for example "taxon.185214.geojson"
 * @author Jean BOUDET
 */
public class CacheAction extends Action<Caching> {

	@Override
	public Promise<Result> call(Context ctx) throws Throwable 
	{
		String[] uri = ctx.request().uri().split("/");
		String ressource = uri[2];
		String id = uri[3];
		String objet = uri[4];
		
		StringBuffer cacheId = new StringBuffer();
		cacheId.append(ressource);
		cacheId.append(".");
		cacheId.append(id);
		cacheId.append(".");
		
		// Pour les paramètres dans l'url
		Iterator<Map.Entry<String, String[]>> iterator = ctx.request().queryString().entrySet().iterator();
		
		if (iterator.hasNext()) {
			String[] objetWithParam = objet.split("\\?");
			cacheId.append(objetWithParam[0]);
			
			/*
			 * On rajoute seulement les valeurs des paramètres. Par exemple
			 * pour la recherche d'un taxon qui est: /taxon/185214/childs?q=macu
			 * la clef enregistrée dans le cache est : taxon.185214.childs.macu
			 */
			while (iterator.hasNext()) {
				Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>) iterator.next();
				cacheId.append(".");
				cacheId.append(entry.getValue()[0]);
			}
		} else {
			cacheId.append(objet);
		}
		
		Promise<Result> res;
		final String cacheIdString = cacheId.toString();
		final int timeToCache = configuration.time();
		Result resultCache = (Result) Cache.get(cacheIdString);
		
		
		if (resultCache == null) {
			 res = delegate.call(ctx);
			 res.onRedeem(
				new Callback<Result>() {

					@Override
					public void invoke(Result r) throws Throwable {
						Cache.set(cacheIdString, r, timeToCache);
					}
					 
				}
			);
		} else {
			res = Promise.pure(resultCache);
		}
		
		return res;
	}

}
