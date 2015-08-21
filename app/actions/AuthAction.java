package actions;

import errors.SimpleError;
import play.cache.Cache;
import play.libs.F.Promise;
import play.libs.Json;
import play.mvc.Action.Simple;
import play.mvc.Http.Context;
import play.mvc.Result;

/**
 * Action for check token and client id
 * @author Jean BOUDET
 */
public class AuthAction extends Simple {

	@Override
	public Promise<Result> call(Context ctx) throws Throwable {
		Promise<Result> res = Promise.pure((Result) unauthorized(
				Json.toJson(new SimpleError("Impossible d'accèder à cette ressource"))
		));
		
		String token = ctx.request().getHeader("X-Access-Token");
		String clientId = ctx.request().getHeader("X-Client-Id");
		
		Object tokenClientIdCache = Cache.get(clientId);
		
		if ((clientId != null && token != null) && (tokenClientIdCache != null)) {
			res = delegate.call(ctx);
		}
		
		return res;
	}
}
