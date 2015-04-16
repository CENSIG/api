package models;

import javax.persistence.Entity;

import com.avaje.ebean.annotation.Sql;

@Entity
@Sql
public class Espece
{
	private String id;
	
	private String nomComplet;
	
	private String auteur;
	
	private String famille;
	
	private String ordre;
	
	private String phylum;
	
	private int observations;
	
	public Espece(String id)
	{
		super();
		this.setId(id);
	}
	
	public Espece() {}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return the nom_complet
	 */
	public String getNomComplet() {
		return nomComplet;
	}

	/**
	 * @param nom_complet the nom_complet to set
	 */
	public void setNomComplet(String nom_complet) {
		this.nomComplet = nom_complet;
	}

	/**
	 * @return the auteur
	 */
	public String getAuteur() {
		return auteur;
	}

	/**
	 * @param auteur the auteur to set
	 */
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	/**
	 * @return the famille
	 */
	public String getFamille() {
		return famille;
	}

	/**
	 * @param famille the famille to set
	 */
	public void setFamille(String famille) {
		this.famille = famille;
	}

	/**
	 * @return the ordre
	 */
	public String getOrdre() {
		return ordre;
	}

	/**
	 * @param ordre the ordre to set
	 */
	public void setOrdre(String ordre) {
		this.ordre = ordre;
	}

	/**
	 * @return the phylum
	 */
	public String getPhylum() {
		return phylum;
	}

	/**
	 * @param phylum the phylum to set
	 */
	public void setPhylum(String phylum) {
		this.phylum = phylum;
	}

	/**
	 * @return the observations
	 */
	public int getObservations() {
		return observations;
	}

	/**
	 * @param observations the observations to set
	 */
	public void setObservations(int observations) {
		this.observations = observations;
	}
	
}
