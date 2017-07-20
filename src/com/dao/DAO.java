/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

/**
 *
 * @author Idriss
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class DAO<T> {

	protected Connection connect = null;

	public DAO(Connection conn) {
		this.connect = conn;
	}

	// fonction de recherche qui retourne element trouvé un objet de type T qui
	// depend de la class fille
	public abstract T find(String sql);


	public boolean update(String sql) {
		try {

			this.connect.createStatement().executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	// fonction de recherche qui retourne l'id d'element trouvé
	public int findString(String sql) {
		int id = 0;

		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
			if (result.first())
				id = result.getInt("id");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	/*
	 * cette class retourne une list ArrayList<String> des attributs recherché,
	 * elle peut etre utilisé par tous les class filles la seule class fille qui
	 * benefice de cette fonction pour le moment est la class auteurDAO .
	 * utilisé dans la fonction filcombo() pour remplire la list des auteurs
	 */
	public ArrayList<String> getList(String sql, String attribut) {
		ArrayList<String> list = new ArrayList<String>();

		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery(sql);

			if (result != null) {
				// stocker toutes les reponses dans une liste
				while (result.next()) {
					if (result.getString(attribut) != null)
						list.add(result.getString(attribut));

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erreur dans la class  ");
		}
		return list;
	}

	// fonction de recherche qui retourne un objet ResultSet utilisé par tous
	// les class filles pour remplire les tables avec la foction updateTable()
	public ResultSet find_rs(String sql) {

		ResultSet rs = null;
		try {
			rs = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
}

