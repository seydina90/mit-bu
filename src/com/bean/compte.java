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
public class compte {

	private int id;
	private String username = null;
	private String password = null;
	private String role = null;

	public compte(int id, String username, String password, String role) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public compte() {
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

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}
}

