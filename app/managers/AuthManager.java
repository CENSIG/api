package managers;

import java.util.List;

import models.AuthModel;

import com.avaje.ebean.Ebean;

/**
 * Manage for authentification
 * @author jean
 *
 */
public class AuthManager extends Manager 
{
	/**
	 * Check login, password couple
	 * @param login
	 * @param password
	 * @return true if login and password are right
	 */
	public static Boolean checkApiLoginPassword(String login, String password)
	{
		Boolean res = false;
		
		List<AuthModel> params = Ebean.createNamedQuery(AuthModel.class, "api-login")
				.setParameter("login", login)
				.setParameter("password", password)
				.findList();
		
		if (isValid(params) && params.size() > 1) {
			res = true;
		}
		
		return res;
	}
}
