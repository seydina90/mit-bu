/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.user;

/**
 *
 * @author Idriss
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.proteanit.sql.DbUtils;

import com.bean.compte;
import com.bean.emprunt;
import com.bean.livre;
import com.bean.personne;
import com.connection.connexion;
import com.dao.DAO;
import com.dao.implement.*;
import com.requetes.Requetes;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class userEmpruntPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	@SuppressWarnings("unused")
	private compte c;
	private personne p;
	private boolean emptyTable = false;
	private JCheckBox checkBox;
	private JTextField txt_id_emprunt;
	private JTextField txt_date_emprunt;
	private JTextField txt_date_retoure;
	private JTextField txt_id_livre;
	private JTextField txt_titre;
	private JTextField txt_retard;
	private JTextField txt_taxe;
	private JTextField txt_prix;
	private JTextField txt_prix_ttc;

	/**
	 * Create the frame.
	 */
	public userEmpruntPanel(compte c) {
		this.c = c;
		DAO<personne> dao = new personneDAO(connexion.getInstance());
		this.p = dao.find(Requetes.user_select("id_compte", c.getId() + ""));
		setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panel_2.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Liste des livres",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panel_2.setBounds(252, 11, 627, 464);
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
				.getBorder("TitledBorder.border"), "Emprunt",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panel_1.setBounds(10, 11, 232, 263);
		add(panel_1);

		JLabel label_6 = new JLabel("Id");
		label_6.setBounds(10, 24, 93, 18);
		panel_1.add(label_6);

		JLabel label_8 = new JLabel("Date d'emprunt");
		label_8.setBounds(10, 54, 93, 18);
		panel_1.add(label_8);

		JLabel label_9 = new JLabel("Date de retoure");
		label_9.setBounds(10, 82, 93, 18);
		panel_1.add(label_9);

		JLabel label_10 = new JLabel("Livre retourn\u00E9");
		label_10.setBounds(10, 111, 93, 18);
		panel_1.add(label_10);

		checkBox = new JCheckBox("");
		checkBox.setOpaque(false);
		checkBox.setEnabled(false);
		checkBox.setBounds(113, 109, 111, 23);
		panel_1.add(checkBox);

		txt_id_emprunt = new JTextField();
		txt_id_emprunt.setEditable(false);
		txt_id_emprunt.setBounds(113, 23, 111, 20);
		panel_1.add(txt_id_emprunt);
		txt_id_emprunt.setColumns(10);

		txt_date_emprunt = new JTextField();
		txt_date_emprunt.setEditable(false);
		txt_date_emprunt.setColumns(10);
		txt_date_emprunt.setBounds(113, 53, 111, 20);
		panel_1.add(txt_date_emprunt);

		txt_date_retoure = new JTextField();
		txt_date_retoure.setEditable(false);
		txt_date_retoure.setColumns(10);
		txt_date_retoure.setBounds(113, 81, 111, 20);
		panel_1.add(txt_date_retoure);

		txt_retard = new JTextField();
		txt_retard.setEditable(false);
		txt_retard.setColumns(10);
		txt_retard.setBounds(113, 165, 111, 20);
		panel_1.add(txt_retard);

		JLabel label = new JLabel("Jours de retard ");
		label.setBounds(10, 166, 93, 18);
		panel_1.add(label);

		txt_taxe = new JTextField();
		txt_taxe.setEditable(false);
		txt_taxe.setColumns(10);
		txt_taxe.setBounds(113, 195, 111, 20);
		panel_1.add(txt_taxe);

		JLabel label_1 = new JLabel("Taxe du retard");
		label_1.setBounds(10, 196, 93, 18);
		panel_1.add(label_1);

		JLabel lblPrix = new JLabel("Prix");
		lblPrix.setBounds(10, 135, 93, 18);
		panel_1.add(lblPrix);

		txt_prix = new JTextField();
		txt_prix.setEditable(false);
		txt_prix.setColumns(10);
		txt_prix.setBounds(113, 134, 111, 20);
		panel_1.add(txt_prix);

		JLabel lblPrixTtc = new JLabel("Prix ttc");
		lblPrixTtc.setBounds(10, 227, 93, 18);
		panel_1.add(lblPrixTtc);

		txt_prix_ttc = new JTextField();
		txt_prix_ttc.setEditable(false);
		txt_prix_ttc.setForeground(Color.BLUE);
		txt_prix_ttc.setColumns(10);
		txt_prix_ttc.setBounds(113, 226, 111, 20);
		panel_1.add(txt_prix_ttc);

		JPanel panel_4 = new JPanel();
		panel_4.setOpaque(false);
		panel_4.setLayout(null);
		panel_4.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Livre",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panel_4.setBounds(10, 292, 232, 86);
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
		updateTable();

	}

	// update table
	public void updateTable() {

		empruntDAO dao = new empruntDAO(connexion.getInstance());
		ResultSet rs = dao.find_rs(Requetes.emprunt_select("id_personne",
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
				DAO<emprunt> dao = new empruntDAO(connexion.getInstance());
				emprunt emprunt1 = dao.find(Requetes.emprunt_select(id));
				txt_id_emprunt.setText(emprunt1.getId() + "");
				txt_date_emprunt.setText(emprunt1.getD_emprunt());
				txt_date_retoure.setText(emprunt1.getD_retour());
				checkBox.setSelected(emprunt1.getLivre_retourne());
				txt_prix.setText("20.0 Fcfa");
				txt_retard.setText(emprunt1.getRetard() + "");
				int taxe = emprunt1.getRetard() * 5;
				txt_taxe.setText(taxe + " Fcfa");
				double ttc = taxe + 20;
				txt_prix_ttc.setText(ttc + " Fcfa");
				DAO<livre> dao2 = new livreDAO(connexion.getInstance());
				livre l1 = dao2.find(Requetes.livre_select(emprunt1
						.getId_livre()));
				txt_id_livre.setText(emprunt1.getId_livre() + "");
				txt_titre.setText(l1.getTitre());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "plz select a valid row");
			}
	}
}
