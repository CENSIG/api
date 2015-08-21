package controllers;

import play.mvc.Controller;
import play.mvc.Result;

/**
 * Controller for OPTION preflight request
 * @author Jean BOUDET
 */
public class OptionsController extends Controller 
{
	public static Result preFlighted(String path)
	{
		response().setHeader("Access-Control-Allow-Headers", "X-Access-Token, X-Client-Id");
		response().setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
		return ok();
	}
}
