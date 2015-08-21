package actions;

import play.Logger;
import play.libs.F.Promise;
import play.mvc.Action.Simple;
import play.mvc.Http.Context;
import play.mvc.Result;

public class PrivateAction extends Simple {

	@Override
	public Promise<Result> call(Context ctx) throws Throwable {
		Logger.debug(ctx.request().remoteAddress());
		Promise<Result> res = delegate.call(ctx);
		
		
		return res;
	}
}
