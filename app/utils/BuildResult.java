package utils;

import errors.SimpleError;
import play.libs.Json;
import play.mvc.Result;
import static play.mvc.Results.*;

public final class BuildResult 
{
	public static Result build(Object obj, String error)
	{
		return obj == null
				? notFound(Json.toJson(new SimpleError(error)))
				: ok(Json.toJson(obj));
	}
}
