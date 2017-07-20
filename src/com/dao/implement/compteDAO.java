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

public class compteDAO extends DAO<compte> {

	public compteDAO(Connection conn) {
		super(conn);
	}

	public compte find(String sql) {

		compte cl = new compte();

		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery(sql);

			if (result.first())
				cl = new compte(result.getInt("id"),
						result.getString("username"),
						result.getString("password"), result.getString("role"));
			else
				cl = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cl;
	}
}
