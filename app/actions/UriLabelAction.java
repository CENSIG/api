package actions;

import java.util.regex.*;

import errors.SimpleError;
import play.mvc.Action.Simple;
import play.mvc.Http;
import play.mvc.Result;
import play.libs.F.Promise;
import play.libs.Json;
import play.Configuration;
import play.Play;

/**
 * An simple action which check uri
 * @author Jean BOUDET
 */
public class UriLabelAction extends Simple
{
	private static Pattern pattern 	   = Pattern.compile("/apiv1/([a-z]*)");
	private static String CONF_LIBELLE = "libelle";
	
	/* (non-Javadoc)
	 * @see play.mvc.Action#call(play.mvc.Http.Context)
	 */
	public Promise<Result> call(Http.Context ctx) throws Throwable 
	{
		String uri      = ctx.request().uri();
		Matcher matcher = pattern.matcher(uri);
		matcher.find();

		Configuration confLibelle = Play.application().configuration().getConfig(CONF_LIBELLE);
		String ressource = matcher.group(1);

		if (confLibelle.getString(ressource) != null) {
			return delegate.call(ctx);
		}
		
		return Promise.pure((Result) notFound(Json.toJson(new SimpleError("La ressouce "+ressource+" n'existe pas"))));
	} 
}
