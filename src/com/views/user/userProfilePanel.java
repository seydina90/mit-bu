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
import com.bean.personne;
import com.connection.connexion;
import com.dao.DAO;
import com.dao.implement.*;
import com.requetes.Requetes;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.SystemColor;

public class userProfilePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private compte c;
	private JTable table;
	private personne p;
	private JTextField txt_nom;
	private JTextField txt_cin;
	private JTextField txt_email;
	private JTextField txt_prenom;
	private JCheckBox check_etat;
	private JTextField txt_login;
	private JTextField txt_password;
	private boolean emptyTable = false;

	/**
	 * Create the frame.
	 */
	public userProfilePanel(compte c) {
		setBackground(SystemColor.menu);
		this.c = c;
		DAO<personne> dao = new personneDAO(connexion.getInstance());
		this.p = dao.find(Requetes.user_select("id_compte", c.getId() + ""));
		setLayout(null);

		JPanel panel_4 = new JPanel();
		panel_4.setOpaque(false);
		panel_4.setBounds(11, 190, 230, 82);
		add(panel_4);
		panel_4.setBorder(new TitledBorder(null, "Compte",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panel_4.setLayout(null);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(10, 23, 93, 18);
		panel_4.add(lblLogin);

		txt_login = new JTextField();
		txt_login.setEditable(false);
		txt_login.setBounds(110, 21, 111, 20);
		panel_4.add(txt_login);
		txt_login.setColumns(10);

		txt_password = new JTextField();
		txt_password.setBounds(110, 47, 111, 20);
		panel_4.add(txt_password);
		txt_password.setColumns(10);

		JLabel lblPassword = new JLabel("Mot de passe");
		lblPassword.setBounds(10, 48, 93, 18);
		panel_4.add(lblPassword);

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(11, 11, 230, 168);
		add(panel);
		panel.setBorder(new TitledBorder(null, "Utilisateur",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nom");
		lblNewLabel.setBounds(10, 23, 93, 18);
		panel.add(lblNewLabel);

		txt_nom = new JTextField();
		txt_nom.setBounds(110, 22, 111, 20);
		panel.add(txt_nom);
		txt_nom.setColumns(10);

		JLabel lblPrenom = new JLabel("Pr\u00E9nom");
		lblPrenom.setBounds(10, 49, 93, 18);
		panel.add(lblPrenom);

		txt_cin = new JTextField();
		txt_cin.setEditable(false);
		txt_cin.setColumns(10);
		txt_cin.setBounds(110, 74, 111, 20);
		panel.add(txt_cin);

		JLabel lblCi = new JLabel("Cin");
		lblCi.setBounds(10, 76, 93, 18);
		panel.add(lblCi);

		txt_email = new JTextField();
		txt_email.setForeground(new Color(0, 0, 205));
		txt_email.setColumns(10);
		txt_email.setBounds(110, 102, 111, 20);
		panel.add(txt_email);

		JLabel lblPrix = new JLabel("Email");
		lblPrix.setBounds(10, 104, 93, 18);
		panel.add(lblPrix);

		txt_prenom = new JTextField();
		txt_prenom.setColumns(10);
		txt_prenom.setBounds(110, 48, 111, 20);
		panel.add(txt_prenom);

		JLabel lblDisponible = new JLabel("Etat");
		lblDisponible.setBounds(10, 129, 93, 18);
		panel.add(lblDisponible);

		check_etat = new JCheckBox("");
		check_etat.setOpaque(false);
		check_etat.setEnabled(false);
		check_etat.setBounds(110, 130, 97, 23);
		panel.add(check_etat);

		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panel_2.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Vos infos",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panel_2.setBounds(251, 11, 644, 92);
		add(panel_2);
		panel_2.setLayout(null);
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tableMouseClicked(e);
			}
		});
		table.setBounds(10, 16, 784, 54);
		table.setBounds(10, 16, 536, 394);
		panel_2.setLayout(new BorderLayout());
		panel_2.add(table.getTableHeader(), BorderLayout.NORTH);
		panel_2.add(table, BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(90, 283, 44, 44);
		add(panel_1);
		panel_1.setLayout(null);

		JButton btnModifier = new JButton("");
		btnModifier.setBounds(5, 5, 32, 32);
		panel_1.add(btnModifier);
		btnModifier.setIcon(new ImageIcon(userProfilePanel.class
				.getResource("/com/img/edit.png")));
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnModiefierActionPerformed(e);
			}
		});

		updateTable();

	}

	// update table
	public void updateTable() {

		DAO<personne> dao = new personneDAO(connexion.getInstance());
		ResultSet rs = dao.find_rs(Requetes.user_select(p.getId()));
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

				DAO<personne> dao1 = new personneDAO(connexion.getInstance());
				personne p1 = dao1.find(Requetes.user_select(id));
				DAO<compte> dao2 = new compteDAO(connexion.getInstance());
				compte c1 = dao2
						.find(Requetes.compte_select(p1.getId_compte()));
				txt_nom.setText(p1.getNom());
				txt_prenom.setText(p1.getPrenom());
				txt_cin.setText(p1.getCin());
				txt_email.setText(p1.getEmail());
				check_etat.setSelected(p1.getEtat());

				txt_login.setText(c1.getUsername());
				txt_password.setText(c1.getPassword());

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "plz select a valid row");
			}
	}

	// update
	private void btnModiefierActionPerformed(ActionEvent event) {
		try {
			compte c1 = new compte(c.getId(), txt_login.getText(),
					txt_password.getText(), "user");
			DAO<compte> dao1 = new compteDAO(connexion.getInstance());
			dao1.update(Requetes.compte_update(c1));
			personne p1 = new personne(p.getId(), c1.getId(),
					txt_nom.getText(), txt_prenom.getText(), txt_cin.getText(),
					txt_email.getText(), check_etat.isSelected());
			DAO<personne> dao2 = new personneDAO(connexion.getInstance());
			dao2.update(Requetes.user_update(p1));
			updateTable();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	public boolean valider() {
		DAO<emprunt> dao = new empruntDAO(connexion.getInstance());
		int nbre_emprunt = dao.findString(Requetes.emprunt_count(p.getId()));
		JOptionPane.showMessageDialog(null, nbre_emprunt);
		if (nbre_emprunt <= 3)
			return true;
		else
			return false;
	}
}

