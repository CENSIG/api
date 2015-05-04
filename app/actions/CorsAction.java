package actions;

import play.Play;
import play.libs.F.Promise;
import play.mvc.Action.Simple;
import play.mvc.Http.Context;
import play.mvc.Result;


/**
 * 
 * An action for allow origin for CORS request
 * @author Jean BOUDET
 *
 */
public class CorsAction extends Simple {

	@Override
	public Promise<Result> call(Context ctx) throws Throwable 
	{
		String origin = Play.application().configuration().getString("application.allowOrigin");
		ctx.response().setHeader("Access-Control-Allow-Origin", origin);
		
		return delegate.call(ctx);
	}
}
