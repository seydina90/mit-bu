/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.implement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.bean.*;
import com.dao.DAO;

public class auteurDAO extends DAO<auteur> {

	public auteurDAO(Connection conn) {
		super(conn);
	}

	public auteur find(String sql) {

		auteur cl = null;

		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery(sql);

			if (result.first())
				cl = new auteur(result.getInt("id"), result.getString("nom"),
						result.getString("date_naissance"));
			else
				cl = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cl;
	}

}