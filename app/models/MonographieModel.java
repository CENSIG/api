package models;

import javax.persistence.Entity;

import com.avaje.ebean.annotation.Sql;

import play.db.ebean.Model;

@Entity
@Sql
public class MonographieModel extends Model {
	
	public String bloc;
	
	public String contenu;
}
