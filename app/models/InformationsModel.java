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

/**
 * 
 * A model which aggregate informations on a taxon
 * @author Jean BOUDET
 *
 */
@Entity
@Sql
public class InformationsModel extends Model 
{
	@Id
	public String id;
	
	public String taxsup;
	
	public String nomVern;
	
	public String nom;
	
	public String rang;
	
	public String ordre;
	
	public String phylum;
	
	public String classe;
	
	@JsonInclude(Include.ALWAYS)
	public String famille;
	
	// Number observations
	public int observations;
	
	// Number especes
	public int especes;
	
	// Number observateur
	public int observateurs;
	
	public int communes;
	
	/*@JsonInclude(Include.NON_NULL)
	@StringJson
	//public String synonimesString;
	
	@ToJson
	//public JsonNode synonimes;*/
	
}
