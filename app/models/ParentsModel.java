package models;

import javax.persistence.Entity;

import com.avaje.ebean.annotation.Sql;

@Entity
@Sql
public class ParentsModel 
{

	public String cdnom;
	
	public String name;
}
