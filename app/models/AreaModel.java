package models;

import javax.persistence.Entity;

import com.avaje.ebean.annotation.Sql;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import play.db.ebean.Model;

/**
 * Model for communes or other
 * geographic object
 * @author Jean BOUDET
 */
@Entity
@Sql
@JsonInclude(Include.NON_NULL)
public class AreaModel extends Model {

	public String firstNameLetter;
	
	public String name;
}
