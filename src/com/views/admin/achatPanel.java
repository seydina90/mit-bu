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
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;

import net.proteanit.sql.DbUtils;

import com.connection.connexion;
import com.dao.DAO;
import com.dao.implement.achatDAO;
import com.dao.implement.livreDAO;
import com.dao.implement.personneDAO;
import com.requetes.Requetes;
import com.bean.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.ImageIcon;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;

public class achatPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private boolean emptyTable = false;
	private JTextField txt_id_achat;
	private JTextField txt_date_achat;
	private JTextField txt_id_personne;
	private JTextField txt_cin;
	private JTextField txt_nom;
	private JTextField txt_id_livre;
	private JTextField txt_titre;
	private JTextField txt_prix;

	/**
	 * Create the frame.
	 */
	public achatPanel() {
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Liste des achats",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panel.setBounds(254, 11, 635, 413);
		add(panel);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tableMouseClicked(e);
			}
		});
		table.setBounds(10, 16, 542, 421);
		panel.setLayout(new BorderLayout());
		panel.add(table.getTableHeader(), BorderLayout.NORTH);
		panel.add(table, BorderLayout.CENTER);

		JPanel panel_4 = new JPanel();
		panel_4.setOpaque(false);
		panel_4.setBounds(10, 230, 234, 110);
		add(panel_4);
		panel_4.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Livre",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panel_4.setLayout(null);

		JLabel lblIdLivre = new JLabel("Id livre");
		lblIdLivre.setBounds(10, 29, 93, 18);
		panel_4.add(lblIdLivre);

		JLabel lblTitre = new JLabel("Titre");
		lblTitre.setBounds(10, 56, 93, 18);
		panel_4.add(lblTitre);

		JLabel lblPrix = new JLabel("Prix");
		lblPrix.setBounds(10, 79, 93, 18);
		panel_4.add(lblPrix);

		txt_id_livre = new JTextField();
		txt_id_livre.setEditable(false);
		txt_id_livre.setColumns(10);
		txt_id_livre.setBounds(113, 23, 111, 18);
		panel_4.add(txt_id_livre);

		txt_titre = new JTextField();
		txt_titre.setEditable(false);
		txt_titre.setColumns(10);
		txt_titre.setBounds(113, 53, 111, 18);
		panel_4.add(txt_titre);

		txt_prix = new JTextField();
		txt_prix.setForeground(Color.BLUE);
		txt_prix.setEditable(false);
		txt_prix.setColumns(10);
		txt_prix.setBounds(113, 79, 111, 18);
		panel_4.add(txt_prix);

		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		panel_3.setBounds(10, 109, 234, 110);
		add(panel_3);
		panel_3.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Client",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panel_3.setLayout(null);

		JLabel lblNom = new JLabel("Id personne");
		lblNom.setBounds(10, 24, 93, 18);
		panel_3.add(lblNom);

		JLabel lblDateDeNaissance_1 = new JLabel("Cin");
		lblDateDeNaissance_1.setBounds(10, 53, 93, 18);
		panel_3.add(lblDateDeNaissance_1);

		JLabel lblNom_1 = new JLabel("Nom");
		lblNom_1.setBounds(10, 80, 93, 18);
		panel_3.add(lblNom_1);

		txt_id_personne = new JTextField();
		txt_id_personne.setEditable(false);
		txt_id_personne.setColumns(10);
		txt_id_personne.setBounds(113, 23, 111, 18);
		panel_3.add(txt_id_personne);

		txt_cin = new JTextField();
		txt_cin.setEditable(false);
		txt_cin.setColumns(10);
		txt_cin.setBounds(113, 53, 111, 18);
		panel_3.add(txt_cin);

		txt_nom = new JTextField();
		txt_nom.setEditable(false);
		txt_nom.setColumns(10);
		txt_nom.setBounds(113, 79, 111, 18);
		panel_3.add(txt_nom);

		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Achat",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panel_1.setBounds(10, 11, 234, 86);
		add(panel_1);

		JLabel label_4 = new JLabel("Id");
		label_4.setBounds(10, 26, 93, 18);
		panel_1.add(label_4);

		JLabel lblDateDemprunt = new JLabel("Date d'achat");
		lblDateDemprunt.setBounds(10, 56, 93, 18);
		panel_1.add(lblDateDemprunt);

		txt_id_achat = new JTextField();
		txt_id_achat.setEditable(false);
		txt_id_achat.setBounds(113, 25, 111, 18);
		panel_1.add(txt_id_achat);
		txt_id_achat.setColumns(10);

		txt_date_achat = new JTextField();
		txt_date_achat.setEditable(false);
		txt_date_achat.setColumns(10);
		txt_date_achat.setBounds(113, 55, 111, 18);
		panel_1.add(txt_date_achat);

		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(62, 351, 120, 44);
		add(panel_2);
		panel_2.setLayout(null);

		JButton btnRechercher = new JButton("");
		btnRechercher.setBounds(6, 6, 32, 32);
		panel_2.add(btnRechercher);
		btnRechercher.setIcon(new ImageIcon(achatPanel.class
				.getResource("/com/img/search.png")));

		JButton btnSupprimer = new JButton("");
		btnSupprimer.setBounds(44, 6, 32, 32);
		panel_2.add(btnSupprimer);
		btnSupprimer.setIcon(new ImageIcon(achatPanel.class
				.getResource("/com/img/trash.png")));

		JButton btnPrint = new JButton("");
		btnPrint.setIcon(new ImageIcon(achatPanel.class
				.getResource("/com/img/printer.png")));
		btnPrint.setBounds(82, 6, 32, 32);
		panel_2.add(btnPrint);
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPrintActionPerformed(e);
			}
		});
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSupprimerActionPerformed(e);
			}
		});
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRechercherActionPerformed(e);
			}
		});

		updateTable("1=1 ");

	}

	// update table
	public void updateTable(String sql) {

		DAO<achat> dao = new achatDAO(connexion.getInstance());
		ResultSet rs = dao.find_rs(Requetes.achat_select(sql));
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

				DAO<personne> dao1 = new personneDAO(connexion.getInstance());
				personne user1 = dao1.find(Requetes.user_select(achat1
						.getId_user()));
				txt_id_personne.setText(user1.getId() + "");
				txt_nom.setText(user1.getNom() + " " + user1.getPrenom());
				txt_cin.setText(user1.getCin());

				DAO<livre> dao2 = new livreDAO(connexion.getInstance());
				livre l1 = dao2
						.find(Requetes.livre_select(achat1.getId_livre()));
				txt_id_livre.setText("" + achat1.getId_livre());
				txt_titre.setText(l1.getTitre());
				txt_prix.setText(l1.getPrix() + " Fcfa");

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "plz select a valid row");
			}
	}

	// recherche
	private void btnRechercherActionPerformed(ActionEvent event) {
		try {
			String[] btns = { "cin", "titre", "date" };
			String cle = (String) JOptionPane.showInputDialog(null,
					"Veuillez indiquer les critéres de recherche !",
					"Recherche", JOptionPane.QUESTION_MESSAGE, null, btns,
					btns[2]);

			String val = "";
			switch (cle) {
			case "cin":
				val = JOptionPane.showInputDialog(null, cle, "recherche",
						JOptionPane.QUESTION_MESSAGE);
				updateTable("p.cin LIKE  '%" + val + "%' ");
				break;
			case "titre":
				val = JOptionPane.showInputDialog(null, cle, "recherche",
						JOptionPane.QUESTION_MESSAGE);
				updateTable("l.titre LIKE  '%" + val + "%' ");
				break;
			case "prix":
				val = JOptionPane.showInputDialog(null, cle, "recherche",
						JOptionPane.QUESTION_MESSAGE);
				updateTable("l.prix LIKE  '%" + val + "%' ");
				break;
			case "date":
				dateMsgbox date = new dateMsgbox(null, true);
				date.setVisible(true);
				updateTable("a.date_achat BETWEEN '" + date.getData()[0]
						+ "' and '" + date.getData()[1] + "'");
				break;
			default:
				break;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "don't do that again");
		}
	}

	// drop
	private void btnSupprimerActionPerformed(ActionEvent event) {
		try {
			int id = Integer.parseInt(txt_id_achat.getText());
			DAO<achat> dao = new achatDAO(connexion.getInstance());
			dao.update(Requetes.achat_delete(id));
			updateTable("1=1 ");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	// Print button
	public void btnPrintActionPerformed(ActionEvent event) {
		MessageFormat header = new MessageFormat(" La liste des achats");
		MessageFormat footer = new MessageFormat(" Page {0,number,integer}");
		try {
			table.print(JTable.PrintMode.FIT_WIDTH, header, footer);
		} catch (java.awt.print.PrinterException e) {
			JOptionPane.showMessageDialog(null,
					"cannot print " + e.getMessage());
		}
	}
}

