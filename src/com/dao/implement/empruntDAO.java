/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.implement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dao.DAO;
import com.bean.*;

public class empruntDAO extends DAO<emprunt> {

	public empruntDAO(Connection conn) {
		super(conn);
	}

	public emprunt find(String sql) {

		emprunt obj = new emprunt();

		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
			if (result.first())
				obj = new emprunt(result.getInt("id"),
						result.getInt("id_personne"),
						result.getInt("id_livre"),
						result.getString("date_emprunt"),
						result.getString("date_retoure"),
						result.getInt("retard"), result.getBoolean("etat"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

}
