package actions;

import annotations.StringParam;
import errors.SimpleError;
import play.Logger;
import play.libs.Json;
import play.libs.F.Promise;
import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.Result;

/**
 * 
 * An action for validate url param. The param must be a string
 * @author Jean BOUDET
 *
 */
public class StringParamAction extends Action<StringParam> 
{
	@Override
	public Promise<Result> call(Context ctx) throws Throwable {
		String param  		 = configuration.value();
		String search 		 = ctx.request().getQueryString(param);
		Promise<Result> res = Promise.pure((Result) status(
				422, 
				Json.toJson(new SimpleError("Le paramêtre "+param+" doit contenir une chaîne de caractère"))
		));
		Logger.debug("foo"+search.length());
		if ((search != null && search.length() > 0) && !isInteger(search))
			res = delegate.call(ctx);
			
		
		return res;
	}
	
	/**
	 * Return if the string is an integer
	 * @param s the string
	 * @return true if digit is valide
	 */
	public static boolean isInteger(String s)
	{
		return isInteger(s, 10);
	}
	
	/**
	 * Return if the string is an integer
	 * @param s the string
	 * @param radix the radix
	 * @return true if digit is valide
	 */
	public static boolean isInteger(String s, int radix) {
	    if (s.isEmpty()) return false;
	    for (int i = 0; i < s.length(); i++) {
	        if (i == 0 && s.charAt(i) == '-') {
	            if(s.length() == 1) return false;
	            else continue;
	        }
	        if (Character.digit(s.charAt(i),radix) < 0) return false;
	    }
	    return true;
	}

}
