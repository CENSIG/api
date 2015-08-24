package actions;

import annotations.RequiredParam;
import errors.SimpleError;
import play.libs.F.Promise;
import play.libs.Json;
import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.Result;

/**
 * 
 * An action for require url param like ?param=value
 * @author Jean BOUDET
 *
 */
public class RequiredParamAction extends Action<RequiredParam> 
{
	@Override
	public Promise<Result> call(Context ctx) throws Throwable {
		String param  		 = configuration.value();
		String search 		 = ctx.request().getQueryString(param);
		Promise<Result> res  = null;
		
		if (search == null) {
			res = Promise.pure((Result) status(
					422, 
					Json.toJson(new SimpleError("La requête attend un paramètre "+param))
			));
		} else {
			res = delegate.call(ctx);
		}
		
		return res;
	}
}
