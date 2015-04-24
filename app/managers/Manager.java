package managers;

import java.util.Set;

import errors.SimpleError;
import play.data.validation.Constraints.Validator;
import play.data.validation.Validation;
import play.libs.F.Function;
import play.libs.F.Function0;
import play.libs.F.Promise;
import play.libs.Json;
import play.mvc.Result;
import static play.mvc.Results.*;


/**
 * A abstract manager
 * @author Jean BOUDET
 */
public abstract class Manager 
{
	
	/**
	 * Create a result promise with error message if error
	 * @param 	o 		 Feedback object
	 * @param 	message  Error message if erreur
	 * @return	result promise
	 */
	protected static Promise<Result> createResponse(final Object o, final String message)
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
						res = notFound(Json.toJson(new SimpleError(message)));
					} else {
						res = ok(Json.toJson(o));
					}
					return res;
				}
			}
		);
	}
	
	
	/**
	 * Create a result promise
	 * @param  o Feedback object
	 * @return result promise
	 */
	protected static Promise<Result> createResponse(final Object o)
	{
		return createResponse(o, null);
	}
	
	protected static boolean isValid(Object o)
	{
		Set errors = Validation.getValidator().validate(o);
		return errors.isEmpty();
	}
	
}
