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
public class emprunt {

	private int id = 0;
	private int id_user = 0;
	private int id_livre = 0;
	private String d_emprunt = "";
	private String d_retour = "";
	private int retard = 0;
	private boolean livre_retourne;

	public emprunt(int id, int id_user, int id_livre, String d_emprunt,
			String d_retour, int retard, boolean livre_retourne) {
		this.id = id;
		this.id_user = id_user;
		this.id_livre = id_livre;
		this.d_emprunt = d_emprunt;
		this.d_retour = d_retour;
		this.retard = retard;
		this.livre_retourne = livre_retourne;
	}

	public emprunt() {
	};

	// ****************************************************
	// ACCESSEURS ET MUTATEURS
	// ****************************************************
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public int getId_livre() {
		return id_livre;
	}

	public void setId_livre(int id_livre) {
		this.id_livre = id_livre;
	}

	public String getD_emprunt() {
		return d_emprunt;
	}

	public void setD_emprunt(String d_emprunt) {
		this.d_emprunt = d_emprunt;
	}

	public String getD_retour() {
		return d_retour;
	}

	public void setD_retour(String d_retour) {
		this.d_retour = d_retour;
	}

	public int getRetard() {
		return retard;
	}

	public void setRetard(int retard) {
		this.retard = retard;
	}

	public boolean getLivre_retourne() {
		return livre_retourne;
	}

	public void setLivre_retourne(boolean livre_retourne) {
		this.livre_retourne = livre_retourne;
	}

}
