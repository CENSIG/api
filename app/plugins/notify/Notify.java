package plugins.notify;

import java.sql.DriverManager;
import java.sql.SQLException;

import play.Application;
import play.Configuration;
import play.Logger;
import play.Plugin;
import play.Play;

/**
 * Plugin for listen notify of database
 * @author Jean BOUDET 
 */
public class Notify extends Plugin 
{
	private static final Configuration DB_CONFIG = Play.application().configuration().getConfig("db.default");
	private static final String URL = DB_CONFIG.getString("url");
	private static final String USER = DB_CONFIG.getString("user");
	private static final String PASSWORD = DB_CONFIG.getString("password");
	public Notify(Application app) {
	}
	
	public void onStart()
	{
		try {
			NotifyListen listen = new NotifyListen(DriverManager.getConnection(URL, USER, PASSWORD));
			listen.start();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void onStop() 
	{
	   Logger.debug("stop");
	}
	
	public boolean enabled() 
	{
        return true;
    }
}
