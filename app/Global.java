import errors.SimpleError;
import play.GlobalSettings;
import play.libs.F.Promise;
import play.libs.Json;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;
import static play.mvc.Results.*;


public class Global extends GlobalSettings 
{
	public Promise<Result> onHandlerNotFound(RequestHeader req)
	{
		return Promise.pure((Result) notFound(
			Json.toJson(new SimpleError("La ressource demandée n'est pas accessible"))
		));
	}
}
