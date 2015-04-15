package managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import play.db.DB;

public abstract class AbstractManager 
{
	private static Connection connection = getInstance();
	
	private static Connection getInstance()
	{
		if (connection == null) {
			connection = DB.getConnection();
		}
		return connection;
	}
	
	protected static PreparedStatement prepared(String sql)
	{
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}
	
}
