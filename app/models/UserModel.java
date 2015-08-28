package models;

import javax.persistence.Entity;

import com.avaje.ebean.annotation.Sql;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import play.db.ebean.Model;

/**
 * Model for user
 * @author Jean BOUDET
 *
 */
@Entity
@Sql
@JsonInclude(Include.NON_NULL)
public class UserModel extends Model 
{
	public String firstNameLetter;
	
	public String name;
	
	public String firstName;
}
