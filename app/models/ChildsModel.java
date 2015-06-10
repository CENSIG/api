package models;

import javax.persistence.Entity;

import com.avaje.ebean.annotation.Sql;

/**
 * 
 * Model for child of parent taxon 
 * (specific for SQL show childs)
 * @author jean
 *
 */
@Entity
@Sql
public class ChildsModel extends TaxonsModel 
{
	public boolean isref;
	
	public String cdref;
	
	public String nameRef;
}
