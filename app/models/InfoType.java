package models;

import javax.persistence.Entity;

import com.avaje.ebean.annotation.Sql;


/**
 * A simple model whish represent type information
 * @author Jean BOUDET
 */
@Entity
@Sql
public class InfoType 
{
	// Number observations
	private int observations;
	
	// Number especes
	private int especes;

	/**
	 * @return Number especes
	 */
	public int getEspeces() 
	{
		return especes;
	}

	/**
	 * Set number especes
	 * @param especes New number
	 */
	public void setEspeces(int especes) 
	{
		this.especes = especes;
	}

	/**
	 * @return Number observations
	 */
	public int getObservations() 
	{
		return observations;
	}

	/**
	 * Set number observations
	 * @param observations New number
	 */
	public void setObservations(int observations) 
	{
		this.observations = observations;
	}
}
