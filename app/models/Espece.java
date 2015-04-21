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
 * A simple model whish represent espece
 * @author Jean BOUDET
 */
@Entity
@Sql
@JsonInclude(Include.NON_NULL)
public class Espece extends Model
{
	// Identifiant
	public String id;
	
	// Complet name
	public String nomComplet;
	
	// Author
	public String auteur;
	
	// Famille
	public String famille;
	
	// Ordre
	public String ordre;
	
	// Phylum
	public String phylum;
	
	// Numbers of observations
	public int observations;
	
	@GeomString
	// Geojson property and geometry
	public String geojsonString;
	
	@GeomJson
	public JsonNode geojson;
	
}
