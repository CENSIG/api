package models;

import javax.persistence.Entity;

import com.avaje.ebean.annotation.Sql;

import play.db.ebean.Model;

/**
 * Model for phenologie
 * @author jean boudet
 *
 */
@Entity
@Sql
public class PhenologieModel extends Model 
{
	public int mois;
	
	public int total;
	
	public int adultes;
	
	public int larves;
}
