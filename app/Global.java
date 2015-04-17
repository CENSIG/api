import errors.SimpleError;
import play.GlobalSettings;
import play.libs.F.Promise;
import play.libs.Json;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;
import static play.mvc.Results.*;

/**
 * Global settings for handle errors
 * @author Jean BOUDET
 */
public class Global extends GlobalSettings 
{
	
	/* (non-Javadoc)
	 * @see play.GlobalSettings#onHandlerNotFound(play.mvc.Http.RequestHeader)
	 */
	public Promise<Result> onHandlerNotFound(RequestHeader req)
	{
		return Promise.pure((Result) notFound(
			Json.toJson(new SimpleError("L'uri "+req.uri()+ " n'existe pas"))
		));
	}
	
	/* (non-Javadoc)
	 * @see play.GlobalSettings#onBadRequest(play.mvc.Http.RequestHeader, java.lang.String)
	 */
	public Promise<Result> onBadRequest(RequestHeader req, String error)
	{
		return Promise.pure((Result) badRequest(
			Json.toJson(new SimpleError("L'identifiant doit être un caractère numérique"))	
		));
	}
}
