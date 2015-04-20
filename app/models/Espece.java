package models;

import javax.persistence.Entity;

import play.db.ebean.Model;

import com.avaje.ebean.annotation.Sql;

/**
 * A simple model whish represent espece
 * @author Jean BOUDET
 */
@Entity
@Sql
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
	
	// Geojson property and geometry
	public String features;
	
}
