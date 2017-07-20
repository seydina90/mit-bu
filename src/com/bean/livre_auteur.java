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
public class livre_auteur {

	private int id_auteur;
	private int id_livre;

	public livre_auteur(int id_livre, int id_auteur) {
		this.id_auteur = id_auteur;
		this.id_livre = id_livre;
	}

	public livre_auteur() {
	};

	// ****************************************************
	// ACCESSEURS ET MUTATEURS
	// ****************************************************
	public void setId_auteur(int id_auteur) {
		this.id_auteur = id_auteur;
	}

	public int getId_auteur() {
		return id_auteur;
	}

	public void setId_livre(int id_livre) {
		this.id_livre = id_livre;
	}

	public int getId_livre() {
		return id_livre;
	}

}

