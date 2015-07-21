package models;

import javax.persistence.Entity;

import com.avaje.ebean.annotation.Sql;

import play.db.ebean.Model;

/**
 * Model for taxon photo
 * @author Jean BOUDET
 *
 */
@Entity
@Sql
public class PhotoModel extends Model 
{
	public String cdref;
	
	public String nom;
	
	public String nomVern;
	
	public String urlPhoto;
	
	public String urlPhotoResize;
}
