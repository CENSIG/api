package managers;

import errors.SimpleError;
import play.libs.F.Function;
import play.libs.F.Function0;
import play.libs.F.Promise;
import play.libs.Json;
import play.mvc.Result;
import static play.mvc.Results.*;

public abstract class Manager 
{
	public static Promise<Result> createResponse(final Object o)
	{
		Promise<Object> promiseObj = Promise.promise(
			new Function0<Object>() {
				public Object apply() {
					return o;
				}
			}
		);
		return promiseObj.map(
			new Function<Object, Result>() {
				public Result apply(Object o) {
					Result res = null;
					if (o == null) {
						res = notFound(Json.toJson(new SimpleError("foo")));
					} else {
						res = ok(Json.toJson(o));
					}
					return res;
				}
			}
		);
	}
}
