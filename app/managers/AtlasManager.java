package managers;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fasterxml.jackson.databind.JsonNode;

import models.Type;
import play.Configuration;
import play.Logger;
import play.Play;
import play.db.DB;
import play.libs.Json;


public class AtlasManager extends AbstractManager
{
	private static Configuration conf = Play.application().configuration().getConfig("requetes.atlas");
	
	public static JsonNode info(String type)
	{
		Type res = new Type();
		try {
			PreparedStatement ps = prepared(conf.getString("info"));
			ps.setString(1, type);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				res.setObservations(rs.getInt("nbobs"));
				res.setEspeces(rs.getInt("nbespeces"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Json.toJson(res);
	}
	
	

}
