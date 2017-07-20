/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

/**
 *
 * @author Idriss
 */
public class livre {

	private int id;
	private String titre = null;
	private double prix = 0;
	private int nbre_exp = 0;
	private String date_edition = null;
	private boolean disponible = false;

	public livre(int id, String titre, double prix, int nbre_exp,
			String date_edition, boolean disponible) {
		this.id = id;
		this.titre = titre;
		this.prix = prix;
		this.nbre_exp = nbre_exp;
		this.date_edition = date_edition;
		this.disponible = disponible;
	}

	public livre() {
	};

	// ****************************************************
	// ACCESSEURS ET MUTATEURS
	// ****************************************************
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getTitre() {
		return titre;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public double getPrix() {
		return prix;
	}

	public void setNbre_exp(int nbre_exp) {
		this.nbre_exp = nbre_exp;
	}

	public int getNbre_exp() {
		return nbre_exp;
	}

	public void setDate_edition(String date_edition) {
		this.date_edition = date_edition;
	}

	public String getDate_edition() {
		return date_edition;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public boolean getDisponible() {
		return disponible;
	}

}
