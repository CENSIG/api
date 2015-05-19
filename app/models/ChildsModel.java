package models;

import javax.persistence.Entity;

import com.avaje.ebean.annotation.Sql;

/**
 * 
 * Model for child of parent taxon
 * @author jean
 *
 */
@Entity
@Sql
public class ChildsModel extends TaxonsModel 
{
	public boolean isref;
	
	public int observations;
	
	public String cdref;
	
	public String nameRef;
}
