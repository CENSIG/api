package controllers;

import actions.BodyIsJsonAction;

import com.fasterxml.jackson.databind.JsonNode;

import errors.SimpleError;
import play.cache.Cache;
import play.libs.F.Function;
import play.libs.F.Function0;
import play.libs.F.Promise;
import play.libs.Json;
import play.mvc.*;
import managers.AuthManager;
import models.AuthModel;

import java.util.UUID;

/**
 * Controller for authentification to api
 * @author jean
 *
 */
@BodyParser.Of(BodyParser.Json.class)
@With(BodyIsJsonAction.class)
public class AuthController extends Controller {
	
	/**
	 * Check the login, password and save client id / token if
	 * is good
	 * @return json response
	 */
	public static Promise<Result> login() {
		final JsonNode body = request().body().asJson();
		return Promise.promise(
				new Function0<AuthModel>() {
					public AuthModel apply() {
						AuthModel res = null;
						String login = body.has("login") ? body.get("login").asText() : "";
						String password = body.has("password") ? body.get("password").asText() : "";
						String token = body.has("token") ? body.get("token").asText() : null;
						
						if (AuthManager.checkApiLoginPassword(login, password) && token != null) {
							String clientId = UUID.randomUUID().toString();
							res = new AuthModel(clientId, token);
							Cache.set(clientId, token, 60);
						}
						return res;
					}
				}
		).map(
				new Function<AuthModel, Result>() {
					public Result apply(AuthModel auth) {
						if (auth == null) {
							return unauthorized(Json.toJson(
								new SimpleError("Pas d'authorisation pour utiliser les ressources")
							));
						} else {
							return ok(Json.toJson(auth));
						}
					}
				}
		);
	}
	
	/**
	 * Check login, password and if clientId exist
	 * @return json response
	 */
	public static Promise<Result> loginWithId() {
		final JsonNode body = request().body().asJson();
		return Promise.promise(
				new Function0<AuthModel>() {
					public AuthModel apply() {
						AuthModel res = null;
						String login = body.has("login") ? body.get("login").asText() : "";
						String password = body.has("password") ? body.get("password").asText() : "";
						String clientId = body.has("clientId") ? body.get("clientId").asText() : null;
						
						if (AuthManager.checkApiLoginPassword(login, password) && clientId != null) {
							String tokenClientIdCache = (String) Cache.get(clientId);
							res = new AuthModel(null, tokenClientIdCache);
						}
						return res;
					}
				}
		).map(
			new Function<AuthModel, Result>() {
				public Result apply(AuthModel auth) {
					Result res = null;
					SimpleError error = new SimpleError();
					if (auth == null) {
						error.setError("Pas d'authorisation pour utiliser les ressources");
						res = unauthorized(Json.toJson(error));
					} else if (auth.token == null) {
						error.setError("Le client id n'existe pas");
						res = notFound(Json.toJson(error));
					} else {
						res = ok(Json.toJson(auth));
					}
					return res;
				}
			}
		);
	}

}
