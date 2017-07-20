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
import java.text.ParseException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;

import net.proteanit.sql.DbUtils;

import com.connection.connexion;
import com.dao.DAO;
import com.dao.implement.empruntDAO;
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

public class empruntPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JCheckBox checkBox;
	private JTextField txt_retard;
	private JTextField txt_id_personne;
	private JTextField txt_cin;
	private JTextField txt_nom;
	private JTextField txt_id_livre;
	private JTextField txt_titre;
	int jour_retard;
	private JTextField txt_id;
	private JTextField txt_taxe;
	private boolean emptyTable = false;
	private JTextField txt_emprunt;
	private JTextField txt_retoure;

	/**
	 * Create the frame.
	 */
	public empruntPanel() {
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Liste des empruntes",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panel.setBounds(254, 11, 635, 459);
		add(panel);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tableMouseClicked(e);
			}
		});
		table.setBounds(10, 16, 536, 394);
		panel.setLayout(new BorderLayout());
		panel.add(table.getTableHeader(), BorderLayout.NORTH);
		panel.add(table, BorderLayout.CENTER);

		JPanel panel_4 = new JPanel();
		panel_4.setOpaque(false);
		panel_4.setBounds(10, 327, 234, 86);
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

		txt_id_livre = new JTextField();
		txt_id_livre.setEditable(false);
		txt_id_livre.setColumns(10);
		txt_id_livre.setBounds(113, 28, 111, 20);
		panel_4.add(txt_id_livre);

		txt_titre = new JTextField();
		txt_titre.setEditable(false);
		txt_titre.setColumns(10);
		txt_titre.setBounds(113, 55, 111, 20);
		panel_4.add(txt_titre);

		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		panel_3.setBounds(10, 210, 234, 110);
		add(panel_3);
		panel_3.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Personne",
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
		txt_id_personne.setBounds(113, 23, 111, 20);
		panel_3.add(txt_id_personne);

		txt_cin = new JTextField();
		txt_cin.setEditable(false);
		txt_cin.setColumns(10);
		txt_cin.setBounds(113, 52, 111, 20);
		panel_3.add(txt_cin);

		txt_nom = new JTextField();
		txt_nom.setEditable(false);
		txt_nom.setColumns(10);
		txt_nom.setBounds(113, 79, 111, 20);
		panel_3.add(txt_nom);

		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Emprunt",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panel_1.setBounds(10, 12, 234, 187);
		add(panel_1);

		JLabel label_4 = new JLabel("Id");
		label_4.setBounds(10, 16, 93, 18);
		panel_1.add(label_4);

		JLabel lblDateDemprunt = new JLabel("Date d'emprunt");
		lblDateDemprunt.setBounds(10, 42, 93, 18);
		panel_1.add(lblDateDemprunt);

		JLabel lblDateDeRetoure = new JLabel("Date de retoure");
		lblDateDeRetoure.setBounds(10, 70, 93, 18);
		panel_1.add(lblDateDeRetoure);

		JLabel lblLivreRetourn = new JLabel("Livre retourn\u00E9");
		lblLivreRetourn.setBounds(10, 99, 93, 18);
		panel_1.add(lblLivreRetourn);

		checkBox = new JCheckBox("");
		checkBox.setOpaque(false);
		checkBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					calculerRetard();
					emprunt_modifier();
				} catch (Exception ev) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		checkBox.setBounds(113, 97, 111, 23);
		panel_1.add(checkBox);

		JLabel lblNJoursDe = new JLabel("Jours de retard ");
		lblNJoursDe.setBounds(10, 129, 93, 18);
		panel_1.add(lblNJoursDe);

		txt_retard = new JTextField();
		txt_retard.setEditable(false);
		txt_retard.setColumns(10);
		txt_retard.setBounds(113, 128, 111, 20);
		panel_1.add(txt_retard);

		txt_id = new JTextField();
		txt_id.setEditable(false);
		txt_id.setColumns(10);
		txt_id.setBounds(113, 15, 111, 20);
		panel_1.add(txt_id);

		txt_taxe = new JTextField();
		txt_taxe.setForeground(Color.BLUE);
		txt_taxe.setEditable(false);
		txt_taxe.setColumns(10);
		txt_taxe.setBounds(113, 158, 111, 20);
		panel_1.add(txt_taxe);

		JLabel lblTaxeDuRetard = new JLabel("Taxe du retard");
		lblTaxeDuRetard.setBounds(10, 159, 93, 18);
		panel_1.add(lblTaxeDuRetard);

		txt_emprunt = new JTextField();
		txt_emprunt.setForeground(Color.BLACK);
		txt_emprunt.setEditable(false);
		txt_emprunt.setColumns(10);
		txt_emprunt.setBounds(113, 42, 111, 20);
		panel_1.add(txt_emprunt);

		txt_retoure = new JTextField();
		txt_retoure.setForeground(Color.BLACK);
		txt_retoure.setEditable(false);
		txt_retoure.setColumns(10);
		txt_retoure.setBounds(113, 72, 111, 20);
		panel_1.add(txt_retoure);

		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(51, 418, 120, 44);
		add(panel_2);
		panel_2.setLayout(null);

		JButton btnSupprimer = new JButton("");
		btnSupprimer.setBounds(6, 6, 32, 32);
		panel_2.add(btnSupprimer);
		btnSupprimer.setIcon(new ImageIcon(empruntPanel.class
				.getResource("/com/img/trash.png")));

		JButton btnRechercher = new JButton("");
		btnRechercher.setBounds(44, 6, 32, 32);
		panel_2.add(btnRechercher);
		btnRechercher.setIcon(new ImageIcon(empruntPanel.class
				.getResource("/com/img/search.png")));

		JButton btnPrint = new JButton("");
		btnPrint.setIcon(new ImageIcon(empruntPanel.class
				.getResource("/com/img/printer.png")));
		btnPrint.setBounds(82, 6, 32, 32);
		panel_2.add(btnPrint);
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPrintActionPerformed(e);
			}
		});
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRechercherActionPerformed(e);
			}
		});
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSupprimerActionPerformed(e);
			}
		});
		updateTable("1=1 ");

	}

	// update table
	public void updateTable(String sql) {

		empruntDAO dao = new empruntDAO(connexion.getInstance());
		ResultSet rs = dao.find_rs(Requetes.emprunt_select(sql));
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
				txt_id.setText(emprunt1.getId() + "");

				txt_emprunt.setText(emprunt1.getD_emprunt());
				txt_retoure.setText(emprunt1.getD_retour());

				checkBox.setSelected(emprunt1.getLivre_retourne());
				jour_retard = emprunt1.getRetard();
				txt_retard.setText(jour_retard + "");
				int taxe = jour_retard * 5;
				txt_taxe.setText(taxe + " Fcfa");
				DAO<personne> dao1 = new personneDAO(connexion.getInstance());
				personne user1 = dao1.find(Requetes.user_select(emprunt1
						.getId_user()));
				txt_id_personne.setText("" + user1.getId() + "");
				txt_nom.setText(user1.getNom() + " " + user1.getPrenom());
				txt_cin.setText(user1.getCin());

				DAO<livre> dao2 = new livreDAO(connexion.getInstance());
				livre l1 = dao2.find(Requetes.livre_select(emprunt1
						.getId_livre()));
				txt_id_livre.setText("" + emprunt1.getId_livre() + "");
				txt_titre.setText(l1.getTitre());

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "plz select a valid row");
			}
	}

	// drop
	private void btnSupprimerActionPerformed(ActionEvent event) {
		try {
			int id = Integer.parseInt(txt_id.getText());
			DAO<emprunt> dao = new empruntDAO(connexion.getInstance());
			dao.update(Requetes.emprunt_delete(id));
			updateTable("1=1 ");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	// recherche
	private void btnRechercherActionPerformed(ActionEvent event) {
		try {
			String[] btns = { "cin", "titre", "date", "nom" };
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
			case "titre":
				val = JOptionPane.showInputDialog(null, cle, "recherche",
						JOptionPane.QUESTION_MESSAGE);
				updateTable("l.titre LIKE  '%" + val + "%' ");
				break;
			case "nom":
				val = JOptionPane.showInputDialog(null, cle, "recherche",
						JOptionPane.QUESTION_MESSAGE);
				updateTable("p.nom LIKE  '%" + val + "%' ");
				break;
			case "date":
				dateMsgbox date = new dateMsgbox(null, true);
				date.setVisible(true);
				updateTable("e.date_emprunt BETWEEN '" + date.getData()[0]
						+ "' and '" + date.getData()[1] + "'");
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
		MessageFormat header = new MessageFormat(" La liste des empruntes");
		MessageFormat footer = new MessageFormat(" Page {0,number,integer}");
		try {
			table.print(JTable.PrintMode.FIT_WIDTH, header, footer);
		} catch (java.awt.print.PrinterException e) {
			JOptionPane.showMessageDialog(null,
					"cannot print " + e.getMessage());
		}
	}

	public void emprunt_modifier() {
		DAO<emprunt> dao = new empruntDAO(connexion.getInstance());
		emprunt emprunt1 = new emprunt(Integer.parseInt(txt_id.getText()),
				Integer.parseInt(txt_id_personne.getText()),
				Integer.parseInt(txt_id_livre.getText()),
				txt_emprunt.getText(), txt_retoure.getText(), jour_retard,
				checkBox.isSelected());
		dao.update(Requetes.emprunt_update(emprunt1));
		updateTable("1=1 ");
	}

	// calculer le nbre de jours de retard
	public void calculerRetard() throws ParseException {
		if (checkBox.isSelected()) {
			int id_livre = Integer.parseInt(txt_id_livre.getText());
			nbreLivreUp(id_livre);
			int id = Integer.parseInt(txt_id.getText());
			DAO<emprunt> dao = new empruntDAO(connexion.getInstance());
			dao.update(Requetes.emprunt_update(id));
			emprunt e1 = dao.find(Requetes.emprunt_select(id));
			txt_retoure.setText(e1.getD_retour());
			// calcul du retard
			jour_retard = dao.findString(Requetes.emprunt_select_retard(id));
			if (jour_retard < 0)
				jour_retard = 0;
		} else
			jour_retard = 0;
		txt_retard.setText(jour_retard + "");
		int taxe = jour_retard * 5;
		txt_taxe.setText(taxe + " Fcfa");
	}

	// nbre d'exemplaire -1
	private void nbreLivreUp(int id_livre) {
		DAO<livre> dao = new livreDAO(connexion.getInstance());
		dao.update(Requetes.livre_nbreUp(id_livre));
	}
}
