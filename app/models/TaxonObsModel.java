package models;

import javax.persistence.Entity;

import com.avaje.ebean.annotation.Sql;

/**
 * 
 * Model for synonimes of taxon
 * (specific for sql show synonimes)
 * @author jean
 *
 */
@Entity
@Sql
public class TaxonObsModel extends TaxonsModel 
{
	public int observations;
}
