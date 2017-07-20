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

public class livreDAO extends DAO<livre> {

	public livreDAO(Connection conn) {
		super(conn);
	}

	public livre find(String sql) {

		livre obj = new livre();

		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery(sql);

			if (result.first())
				obj = new livre(result.getInt("id"), result.getString("titre"),
						result.getDouble("prix"),
						result.getInt("nbr_exemplaire"),
						result.getString("date_edition"),
						result.getBoolean("disponible"));
			else
				obj = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

}