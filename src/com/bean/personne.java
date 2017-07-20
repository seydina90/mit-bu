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
public class personne {

	private int id;
	private int id_compte;
	private String nom = null;
	private String prenom = null;
	private String cin = null;
	private String email = null;
	private boolean etat = false;

	public personne(int id, int id_compte, String nom, String prenom,
			String cin, String email, boolean etat) {
		this.id = id;
		this.id_compte = id_compte;
		this.nom = nom;
		this.prenom = prenom;
		this.cin = cin;
		this.email = email;
		this.etat = etat;
	}

	public personne() {
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

	public void setId_compte(int id_compte) {
		this.id_compte = id_compte;
	}

	public int getId_compte() {
		return id_compte;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getCin() {
		return cin;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEtat(Boolean etat) {
		this.etat = etat;
	}

	public Boolean getEtat() {
		return etat;
	}
}

