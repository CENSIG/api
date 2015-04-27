package models;

import java.util.List;

import javax.persistence.Entity;

import com.avaje.ebean.annotation.Sql;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonNode;

import play.db.ebean.Model;

/**
 * 
 * A model whish represent a grid
 * @author Jean BOUDET
 *
 */
@Entity
@Sql
public class GridModel extends Model 
{
	public String type;
	
	@JsonInclude(Include.NON_NULL)
	public String geometriesList;
	
	public List<JsonNode> geometries;
}
