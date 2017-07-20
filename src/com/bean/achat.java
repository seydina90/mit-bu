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
public class achat {

	private int id = 0;
	private int id_user = 0;
	private int id_livre = 0;
	private String d_achat = "";

	public achat(int id, int id_user, int id_livre, String d_achat) {
		this.id = id;
		this.id_user = id_user;
		this.id_livre = id_livre;
		this.d_achat = d_achat;
	}

	public achat() {
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

	public String getD_achat() {
		return d_achat;
	}

	public void setD_achat(String d_achat) {
		this.d_achat = d_achat;
	}

}
