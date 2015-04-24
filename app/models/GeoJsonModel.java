package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import actions.ToJson;
import actions.StringJson;

import com.avaje.ebean.annotation.Sql;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonNode;

import play.db.ebean.Model;
import play.data.validation.Constraints;

@Entity
@Sql
@JsonInclude(Include.NON_NULL)
public class GeoJsonModel extends Model 
{	
	@Id
	public String type;
	
	@Constraints.Required
	@StringJson
	public String featuresString;
	
	@ToJson
	public JsonNode features;
}
