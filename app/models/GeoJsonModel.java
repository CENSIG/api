package models;

import javax.persistence.Entity;

import com.avaje.ebean.annotation.Sql;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonNode;

import play.db.ebean.Model;
import play.data.validation.Constraints;

/**
 * 
 * A model which represent a geojson
 * @author Jean BOUDET
 *
 */
@Entity
@Sql
@JsonInclude(Include.NON_NULL)
public class GeoJsonModel extends Model 
{	
	public String type;
	
	@Constraints.Required
	public String featuresString;
	
	public JsonNode features;
}
