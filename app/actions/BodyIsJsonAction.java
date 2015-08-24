package actions;

import errors.SimpleError;
import play.libs.F.Promise;
import play.libs.Json;
import play.mvc.Action.Simple;
import play.mvc.Http.Context;
import play.mvc.Result;

/**
 * For post request. The body must be in json
 * @author Jean BOUDET 
 */
public class BodyIsJsonAction extends Simple 
{
	
	private final static String CONTENT_TYPE = "application/json";

	@Override
	public Promise<Result> call(Context ctx) throws Throwable 
	{
		Promise<Result> res = delegate.call(ctx);
		String[] type = ctx.request().getHeader("Content-Type").split(";");
		
		if (!type[0].equals(CONTENT_TYPE)) {
			res = Promise.pure((Result) badRequest(
				Json.toJson(new SimpleError("Impossible de traiter la requête"))
			));
		}
		
		return res;
	}

}
