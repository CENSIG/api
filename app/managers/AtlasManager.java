package managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlRow;
import com.fasterxml.jackson.databind.JsonNode;

import models.Type;
import play.Configuration;
import play.Logger;
import play.Play;
import play.db.DB;
import play.libs.Json;

public class AtlasManager
{
	private static Configuration conf = Play.application().configuration().getConfig("requetes.atlas");
	
	public static JsonNode info(String type)
	{
		Type res = new Type();
		SqlRow row = Ebean.createSqlQuery(conf.getString("info"))
				.setParameter("type", type)
				.findUnique();
		
		res.setObservations(row.getInteger("nbobs"));
		res.setEspeces(row.getInteger("nbespeces"));
		return Json.toJson(res);
	}

}
