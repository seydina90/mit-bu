/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.implement;

/**
 *
 * @author Idriss
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.bean.*;
import com.dao.DAO;

public class personneDAO extends DAO<personne> {

	public personneDAO(Connection conn) {
		super(conn);
	}

	public personne find(String sql) {

		personne cl = new personne();

		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery(sql);

			if (result.first())
				cl = new personne(result.getInt("id"),
						result.getInt("id_compte"), result.getString("nom"),
						result.getString("prenom"), result.getString("cin"),
						result.getString("email"), result.getBoolean("etat"));
			else
				cl = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cl;
	}
}
