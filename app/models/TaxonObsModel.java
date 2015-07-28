package models;

import javax.persistence.Entity;

import com.avaje.ebean.annotation.Sql;

/**
 * 
 * Model for first childs childs of taxon
 * (specific for sql show first_childs_childs)
 * @author jean
 *
 */
@Entity
@Sql
public class TaxonObsModel extends TaxonsModel 
{
	public int observations;
}
