package models;

import javax.persistence.Entity;

import play.db.ebean.Model;

import com.avaje.ebean.annotation.Sql;

/**
 * A simple model whish represent type information
 * @author Jean BOUDET
 */
@Entity
@Sql
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
	public String features;
}
