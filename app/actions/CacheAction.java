package actions;

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
		cacheId.append(objet);
		
		
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
