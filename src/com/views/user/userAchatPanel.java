/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.proteanit.sql.DbUtils;

import com.bean.achat;
import com.bean.compte;
import com.bean.livre;
import com.bean.personne;
import com.connection.connexion;
import com.dao.DAO;
import com.dao.implement.*;
import com.requetes.Requetes;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class userAchatPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	@SuppressWarnings("unused")
	private compte c;
	private personne p;
	private boolean emptyTable = false;
	private JTextField txt_id_achat;
	private JTextField txt_date_achat;
	private JTextField txt_id_livre;
	private JTextField txt_titre;
	private JTextField txt_prix;

	/**
	 * Create the frame.
	 */
	public userAchatPanel(compte c) {
		this.c = c;
		DAO<personne> dao = new personneDAO(connexion.getInstance());
		this.p = dao.find(Requetes.user_select("id_compte", c.getId() + ""));

		setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panel_2.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"),
				"Liste des achats que vous avez d\u00E9ja effectu\u00E9",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panel_2.setBounds(252, 11, 627, 424);
		add(panel_2);
		panel_2.setLayout(null);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tableMouseClicked(e);
			}
		});
		table.setBounds(6, 16, 540, 363);
		panel_2.setLayout(new BorderLayout());
		panel_2.add(table.getTableHeader(), BorderLayout.NORTH);
		panel_2.add(table, BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Achat",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panel_1.setBounds(10, 11, 232, 87);
		add(panel_1);

		JLabel label_6 = new JLabel("Id");
		label_6.setBounds(10, 24, 93, 18);
		panel_1.add(label_6);

		JLabel lblDateDachat = new JLabel("Date d'achat");
		lblDateDachat.setBounds(10, 54, 93, 18);
		panel_1.add(lblDateDachat);

		txt_id_achat = new JTextField();
		txt_id_achat.setEditable(false);
		txt_id_achat.setBounds(113, 23, 111, 20);
		panel_1.add(txt_id_achat);
		txt_id_achat.setColumns(10);

		txt_date_achat = new JTextField();
		txt_date_achat.setEditable(false);
		txt_date_achat.setColumns(10);
		txt_date_achat.setBounds(113, 53, 111, 20);
		panel_1.add(txt_date_achat);

		JPanel panel_4 = new JPanel();
		panel_4.setOpaque(false);
		panel_4.setLayout(null);
		panel_4.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Livre",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panel_4.setBounds(10, 109, 232, 117);
		add(panel_4);

		JLabel label_11 = new JLabel("Id livre");
		label_11.setBounds(10, 29, 93, 18);
		panel_4.add(label_11);

		JLabel label_12 = new JLabel("Titre");
		label_12.setBounds(10, 56, 93, 18);
		panel_4.add(label_12);

		txt_id_livre = new JTextField();
		txt_id_livre.setEditable(false);
		txt_id_livre.setColumns(10);
		txt_id_livre.setBounds(113, 27, 111, 20);
		panel_4.add(txt_id_livre);

		txt_titre = new JTextField();
		txt_titre.setEditable(false);
		txt_titre.setColumns(10);
		txt_titre.setBounds(113, 55, 111, 20);
		panel_4.add(txt_titre);

		txt_prix = new JTextField();
		txt_prix.setForeground(Color.BLUE);
		txt_prix.setEditable(false);
		txt_prix.setColumns(10);
		txt_prix.setBounds(111, 85, 111, 20);
		panel_4.add(txt_prix);

		JLabel label = new JLabel("Prix");
		label.setBounds(10, 86, 93, 18);
		panel_4.add(label);
		updateTable();

	}

	// update table
	public void updateTable() {

		DAO<achat> dao = new achatDAO(connexion.getInstance());
		ResultSet rs = dao.find_rs(Requetes.achat_select("id_personne",
				p.getId() + ""));
		table.setModel((DbUtils.resultSetToTableModel(rs)));
		try {
			if (!rs.first())
				emptyTable = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// table mouse clicked
	private void tableMouseClicked(MouseEvent ev) {
		if (!emptyTable)
			try {
				int row = table.getSelectedRow();
				int id = Integer.parseInt((table.getModel().getValueAt(row, 0))
						.toString());

				DAO<achat> dao = new achatDAO(connexion.getInstance());
				achat achat1 = dao.find(Requetes.achat_select(id));
				txt_id_achat.setText(achat1.getId() + "");
				txt_date_achat.setText(achat1.getD_achat());

				DAO<livre> dao2 = new livreDAO(connexion.getInstance());
				livre l1 = dao2
						.find(Requetes.livre_select(achat1.getId_livre()));
				txt_id_livre.setText(achat1.getId_livre() + "");
				txt_titre.setText(l1.getTitre());
				txt_prix.setText(l1.getPrix() + " Fcfa");


			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "plz select a valid row");
			}
	}
}
