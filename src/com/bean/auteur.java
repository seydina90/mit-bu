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
public class auteur {

	private int id;
	private String nom = null;
	private String date_naissance = null;

	public auteur(int id, String nom, String date_naissance) {
		this.id = id;
		this.nom = nom;
		this.date_naissance = date_naissance;
	}

	public auteur() {
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

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public void setDate_naissance(String date_naissance) {
		this.date_naissance = date_naissance;
	}

	public String getDate_naissance() {
		return date_naissance;
	}

}

