package models;

import javax.persistence.Entity;

import com.avaje.ebean.annotation.Sql;

/**
 * 
 * Simple model which represent parent for a taxon
 * @author jean
 *
 */
@Entity
@Sql
public class ParentsModel extends TaxonsModel 
{

}
