package models;

import javax.persistence.Entity;

import play.db.ebean.Model;

import com.avaje.ebean.annotation.Sql;

/**
 * Basic information on a taxon
 * @author jean
 *
 */
@Entity
@Sql
public class TaxonsModel extends Model
{
	public String cdnom;
	
	public String name;
}
