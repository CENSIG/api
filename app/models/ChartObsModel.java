package models;

import javax.persistence.Entity;

import com.avaje.ebean.annotation.Sql;

import play.db.ebean.Model;

@Entity
@Sql
public class ChartObsModel extends Model 
{
	public String label;
	
	public int value;
}
