package actions;

import play.Play;
import play.libs.F.Promise;
import play.mvc.Action.Simple;
import play.mvc.Http.Context;
import play.mvc.Action;
import play.mvc.Result;

/**
 * Action cors for control origin request
 * @author Jean BOUDET
 */
public class CorsActionWrapper extends Simple
{
	public CorsActionWrapper(Action<?> action)
	{
		this.delegate = action;
	}
	
	public Promise<Result> call(Context ctx) throws Throwable 
	{
		Promise<Result> result = this.delegate.call(ctx);
		String origin = Play.application().configuration().getString("application.allowOrigin");
		ctx.response().setHeader("Access-Control-Allow-Origin", origin);
		return result;
	}
}