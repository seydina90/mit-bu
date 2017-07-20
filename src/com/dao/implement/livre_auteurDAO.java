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

public class livre_auteurDAO extends DAO<livre_auteur> {

	public livre_auteurDAO(Connection conn) {
		super(conn);
	}

	public livre_auteur find(String sql) {

		livre_auteur cl = new livre_auteur();

		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery(sql);

			if (result.first())
				cl = new livre_auteur(result.getInt("id_livre"),
						result.getInt("id_auteur"));
			else
				cl = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cl;
	}
}
