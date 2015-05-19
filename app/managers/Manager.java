package managers;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;

import errors.SimpleError;
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
		Promise<JsonNode> promiseObj = Promise.promise(
			new Function0<JsonNode>() {
				public JsonNode apply() {
					Object objectToJson = o;
					
					if (o == null) {
						objectToJson = new SimpleError(message);
					}
					
					return Json.toJson(objectToJson);
				}
			}
		);
		return promiseObj.map(
			new Function<JsonNode, Result>() {
				public Result apply(JsonNode json) {
					Result res = null;
					if (json.has("error")) {
						res = notFound(json);
					} else {
						res = ok(json);
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
	
	/**
	 * Return true if the object is ok
	 * @param 	o	object to validate
	 * @return	boolean
	 */
	protected static boolean isValid(Object o)
	{
		Set errors = Validation.getValidator().validate(o);
		return errors.isEmpty();
	}
	
	/**
	 * Return true if the list is not empty
	 * @param  l list to validate
	 * @return boolean
	 */
	protected static boolean isValid(List l)
	{
		return !l.isEmpty();
	}
	
}
