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
