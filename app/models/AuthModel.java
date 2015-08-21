package models;

import javax.persistence.Entity;

import com.avaje.ebean.annotation.Sql;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import play.db.ebean.Model;

@Entity
@Sql
@JsonInclude(Include.NON_NULL)
public class AuthModel extends Model 
{
	public String id;
	
	public String token;
	
	public AuthModel(String id, String token) {
		this.id = id;
		this.token = token;
	}
}
