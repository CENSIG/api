package models;

import javax.persistence.Entity;

import play.db.ebean.Model;
import actions.GeomJson;
import actions.GeomString;

import com.avaje.ebean.annotation.Sql;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * A simple model whish represent type information
 * @author Jean BOUDET
 */
@Entity
@Sql
@JsonInclude(Include.NON_NULL)
public class InfoType extends Model
{
	// Ordre (type)
	public String ordre;
	
	// Number observations
	public int observations;
	
	// Number especes
	public int especes;
	
	// Number observateurs
	public int observateurs;
	
	// Number communes
	public int communes;
	
	// Geojson property and geometry
	@GeomString
	public String geojsonString;
	
	@GeomJson
	public JsonNode geojson;
}
