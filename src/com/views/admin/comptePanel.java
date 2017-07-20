/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.admin;

/**
 *
 * @author Idriss
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;

import net.proteanit.sql.DbUtils;

import com.bean.compte;
import com.bean.personne;
import com.connection.connexion;
import com.dao.DAO;
import com.dao.implement.*;
import com.requetes.Requetes;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class comptePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField txt_nom;
	private JTextField txt_cin;
	private JTextField txt_email;
	private JTextField txt_prenom;
	private JCheckBox check_etat;
	private JTextField txt_login;
	private JTextField txt_password;
	private JComboBox<String> comboBox;
	private JTextField txt_id_personne;
	private JTextField txt_id_compte;
	private boolean emptyTable = false;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public comptePanel() {
		setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panel_2.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Liste des utilisateurs",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panel_2.setBounds(263, 11, 627, 458);
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

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Utilisateur",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panel.setBounds(19, 11, 234, 190);
		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nom");
		lblNewLabel.setBounds(10, 50, 93, 18);
		panel.add(lblNewLabel);

		txt_nom = new JTextField();
		txt_nom.setBounds(113, 48, 111, 20);
		panel.add(txt_nom);
		txt_nom.setColumns(10);

		JLabel lblPrenom = new JLabel("Pr\u00E9nom");
		lblPrenom.setBounds(10, 75, 93, 18);
		panel.add(lblPrenom);

		txt_cin = new JTextField();
		txt_cin.setColumns(10);
		txt_cin.setBounds(113, 100, 111, 20);
		panel.add(txt_cin);

		JLabel lblCi = new JLabel("Cin");
		lblCi.setBounds(10, 102, 93, 18);
		panel.add(lblCi);

		txt_email = new JTextField();
		txt_email.setForeground(new Color(0, 0, 205));
		txt_email.setColumns(10);
		txt_email.setBounds(113, 128, 111, 20);
		panel.add(txt_email);

		JLabel lblPrix = new JLabel("Email");
		lblPrix.setBounds(10, 130, 93, 18);
		panel.add(lblPrix);

		JLabel lblId = new JLabel("Id");
		lblId.setBounds(10, 24, 93, 18);
		panel.add(lblId);

		txt_prenom = new JTextField();
		txt_prenom.setColumns(10);
		txt_prenom.setBounds(113, 74, 111, 20);
		panel.add(txt_prenom);

		JLabel lblDisponible = new JLabel("Etat");
		lblDisponible.setBounds(10, 155, 93, 18);
		panel.add(lblDisponible);

		check_etat = new JCheckBox("");
		check_etat.setOpaque(false);
		check_etat.setBounds(113, 156, 97, 23);
		panel.add(check_etat);

		txt_id_personne = new JTextField();
		txt_id_personne.setEditable(false);
		txt_id_personne.setColumns(10);
		txt_id_personne.setBounds(113, 23, 111, 20);
		panel.add(txt_id_personne);

		JPanel panel_4 = new JPanel();
		panel_4.setOpaque(false);
		panel_4.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Compte d'utilisateur",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panel_4.setBounds(19, 220, 234, 150);
		add(panel_4);
		panel_4.setLayout(null);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(10, 58, 93, 18);
		panel_4.add(lblLogin);

		txt_login = new JTextField();
		txt_login.setBounds(113, 56, 111, 20);
		panel_4.add(txt_login);
		txt_login.setColumns(10);

		txt_password = new JTextField();
		txt_password.setBounds(113, 82, 111, 20);
		panel_4.add(txt_password);
		txt_password.setColumns(10);

		JLabel lblPassword = new JLabel("Mot de passe");
		lblPassword.setBounds(10, 83, 93, 18);
		panel_4.add(lblPassword);

		JLabel label_2 = new JLabel("Id");
		label_2.setBounds(10, 32, 93, 18);
		panel_4.add(label_2);

		JLabel lblRole = new JLabel("Role");
		lblRole.setBounds(10, 112, 93, 18);
		panel_4.add(lblRole);

		comboBox = new JComboBox();
		comboBox.addItem("admin");
		comboBox.addItem("user");
		comboBox.setBounds(113, 113, 111, 20);
		panel_4.add(comboBox);

		txt_id_compte = new JTextField();
		txt_id_compte.setEditable(false);
		txt_id_compte.setColumns(10);
		txt_id_compte.setBounds(113, 31, 111, 20);
		panel_4.add(txt_id_compte);

		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(58, 385, 120, 78);
		add(panel_1);
		panel_1.setLayout(null);

		JButton btnAjouter = new JButton("");
		btnAjouter.setIcon(new ImageIcon(comptePanel.class
				.getResource("/com/img/plus.png")));
		btnAjouter.setBounds(6, 6, 32, 32);
		panel_1.add(btnAjouter);

		JButton btnRechercher = new JButton("");
		btnRechercher.setBounds(44, 6, 32, 32);
		panel_1.add(btnRechercher);
		btnRechercher.setIcon(new ImageIcon(comptePanel.class
				.getResource("/com/img/search.png")));

		JButton btnModifier = new JButton("");
		btnModifier.setBounds(82, 6, 32, 32);
		panel_1.add(btnModifier);
		btnModifier.setIcon(new ImageIcon(comptePanel.class
				.getResource("/com/img/edit.png")));

		JButton btnPrint = new JButton("");
		btnPrint.setIcon(new ImageIcon(comptePanel.class
				.getResource("/com/img/printer.png")));
		btnPrint.setBounds(82, 40, 32, 32);
		panel_1.add(btnPrint);

		JButton btn_reset = new JButton("");
		btn_reset.setBounds(44, 40, 32, 32);
		panel_1.add(btn_reset);
		btn_reset.setIcon(new ImageIcon(comptePanel.class
				.getResource("/com/img/clear.png")));

		JButton btnSupprimer = new JButton("");
		btnSupprimer.setBounds(6, 40, 32, 32);
		panel_1.add(btnSupprimer);
		btnSupprimer.setIcon(new ImageIcon(comptePanel.class
				.getResource("/com/img/trash.png")));
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSupprimerActionPerformed(e);
			}
		});
		btn_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetForme();
			}
		});
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPrintActionPerformed(e);
			}
		});
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnModiefierActionPerformed(e);
			}
		});
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRechercherActionPerformed(e);
			}
		});
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAjouterActionPerformed(e);
			}
		});
		updateTable("1=1 ");

	}

	// update table
	public void updateTable(String sql) {

		DAO<personne> dao = new personneDAO(connexion.getInstance());
		ResultSet rs = dao.find_rs(Requetes.user_select(sql));
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
				txt_id_personne.setText(p1.getId() + "");
				txt_nom.setText(p1.getNom());
				txt_prenom.setText(p1.getPrenom());
				txt_cin.setText(p1.getCin());
				txt_email.setText(p1.getEmail());
				check_etat.setSelected(p1.getEtat());

				txt_id_compte.setText(c1.getId() + "");
				txt_login.setText(c1.getUsername());
				txt_password.setText(c1.getPassword());
				comboBox.setSelectedItem(c1.getRole());

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "plz select a valid row");
			}
	}

	// Add
	private void btnAjouterActionPerformed(ActionEvent event) {
		try {
			if (check_password())
				if (check_cin())
					if (check_login())
						inscription();
			updateTable("1=1 ");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	// update
	private void btnModiefierActionPerformed(ActionEvent event) {
		if (!emptyTable)
			try {
				int id_personne = Integer.parseInt(txt_id_personne.getText());
				int id_compte = Integer.parseInt(txt_id_compte.getText());
				compte c1 = new compte(id_compte, txt_login.getText(),
						txt_password.getText(), comboBox.getSelectedItem()
								.toString());
				DAO<compte> dao1 = new compteDAO(connexion.getInstance());
				dao1.update(Requetes.compte_update(c1));
				personne p1 = new personne(id_personne, c1.getId(),
						txt_nom.getText(), txt_prenom.getText(),
						txt_cin.getText(), txt_email.getText(),
						check_etat.isSelected());
				DAO<personne> dao2 = new personneDAO(connexion.getInstance());
				dao2.update(Requetes.user_update(p1));
				updateTable("1=1 ");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
	}

	// drop
	private void btnSupprimerActionPerformed(ActionEvent event) {
		try {
			int id_personne = Integer.parseInt(txt_id_personne.getText());
			int id_compte = Integer.parseInt(txt_id_compte.getText());
			DAO<personne> dao1 = new personneDAO(connexion.getInstance());
			dao1.update(Requetes.user_delete(id_personne));
			DAO<compte> dao2 = new compteDAO(connexion.getInstance());
			dao2.update(Requetes.compte_delete(id_compte));
			updateTable("1=1 ");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	// recherche
	private void btnRechercherActionPerformed(ActionEvent event) {
		try {
			String[] btns = { "cin", "nom", "username" };
			String cle = (String) JOptionPane.showInputDialog(null,
					"Veuillez indiquer le critére de recherche !", "Recherche",
					JOptionPane.QUESTION_MESSAGE, null, btns, btns[2]);

			String val = "";
			switch (cle) {
			case "cin":
				val = JOptionPane.showInputDialog(null, cle, "recherche",
						JOptionPane.QUESTION_MESSAGE);
				updateTable("p.cin LIKE  '%" + val + "%' ");
				break;
			case "nom":
				val = JOptionPane.showInputDialog(null, cle, "recherche",
						JOptionPane.QUESTION_MESSAGE);
				updateTable("p.nom LIKE  '%" + val + "%' ");
				break;
			case "username":
				val = JOptionPane.showInputDialog(null, cle, "recherche",
						JOptionPane.QUESTION_MESSAGE);
				updateTable("c.username LIKE  '%" + val + "%' ");
				break;
			default:
				break;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "don't do that again");
		}
	}

	// Print button
	public void btnPrintActionPerformed(ActionEvent event) {
		MessageFormat header = new MessageFormat(" La liste des utilisateurs");
		MessageFormat footer = new MessageFormat(" Page {0,number,integer}");
		try {
			table.print(JTable.PrintMode.FIT_WIDTH, header, footer);
		} catch (java.awt.print.PrinterException e) {
			JOptionPane.showMessageDialog(null,
					"cannot print " + e.getMessage());
		}
	}

	public void inscription() {
		compte c1 = new compte(0, txt_login.getText(), txt_password.getText(),
				"user");
		DAO<compte> dao1 = new compteDAO(connexion.getInstance());
		dao1.update(Requetes.compte_add(c1));
		int id_compte = dao1.findString(Requetes.compte_max());
		personne p1 = new personne(0, id_compte, txt_nom.getText(),
				txt_prenom.getText(), txt_cin.getText(), txt_email.getText(),
				check_etat.isSelected());
		DAO<personne> dao2 = new personneDAO(connexion.getInstance());
		dao2.update(Requetes.user_add(p1));
	}

	public boolean check_password() {
		if (txt_password.getText().length() < 6) {
			JOptionPane
					.showMessageDialog(
							null,
							"le mot de pass est trop court, il doit au moins contenir 6 carractere",
							"Attention", JOptionPane.WARNING_MESSAGE,
							new ImageIcon("img\\alert.png"));
			return false;
		} else
			return true;
	}

	public void resetForme() {
		txt_id_personne.setText("");
		txt_nom.setText("");
		txt_prenom.setText("");
		txt_cin.setText("");
		txt_email.setText("");
		check_etat.setSelected(false);

		txt_id_compte.setText("");
		txt_login.setText("");
		txt_password.setText("");
		comboBox.setSelectedItem("");
	}

	public boolean check_login() {
		DAO<compte> dao = new compteDAO(connexion.getInstance());
		compte c1 = dao.find(Requetes.compte_select("username",
				txt_login.getText()));
		if (c1 != null) {
			JOptionPane
					.showMessageDialog(
							null,
							"ce pseudo est déja existant, Veillez rentrer un noveau pseudo",
							"Attention", JOptionPane.WARNING_MESSAGE,
							new ImageIcon("img\\alert.png"));
			return false;
		} else
			return true;
	}

	public boolean check_cin() {
		DAO<personne> dao = new personneDAO(connexion.getInstance());
		personne p1 = dao.find(Requetes.user_select("cin", txt_cin.getText()));
		if (p1 != null) {
			JOptionPane.showMessageDialog(null,
					"ce cin est déja existant, Veillez rentrer un noveau cin",
					"Attention", JOptionPane.WARNING_MESSAGE, new ImageIcon(
							"img\\alert.png"));
			return false;
		} else
			return true;
	}
}

