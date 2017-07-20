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
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import net.proteanit.sql.DbUtils;

import com.bean.livre;
import com.bean.auteur;
import com.bean.livre_auteur;
import com.connection.connexion;
import com.dao.DAO;
import com.dao.implement.*;
import com.requetes.Requetes;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import com.toedter.calendar.JDateChooser;

public class livrePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField txt_titre;
	private JTextField txt_prix;
	private JTextField txt_nbre;
	private JCheckBox check_disponible;
	private JTextField txt_id;
	private JDateChooser txt_date;
	private boolean emptyTable = false;
	private auteurListBox pan_auteur;

	/**
	 * Create the frame.
	 */
	public livrePanel() {
		setBorder(new EmptyBorder(2, 0, 0, 0));
		setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panel_2.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Liste des livres",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panel_2.setBounds(285, 11, 605, 460);
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
				.getBorder("TitledBorder.border"), "Livre",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panel.setBounds(10, 11, 272, 371);
		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Titre");
		lblNewLabel.setBounds(10, 52, 93, 18);
		panel.add(lblNewLabel);

		txt_titre = new JTextField();
		txt_titre.setBounds(131, 51, 111, 20);
		panel.add(txt_titre);
		txt_titre.setColumns(10);

		JLabel lblPrenom = new JLabel("Auteurs");
		lblPrenom.setBounds(10, 77, 93, 18);
		panel.add(lblPrenom);

		JLabel lblCi = new JLabel("Date d'edition");
		lblCi.setBounds(10, 258, 93, 18);
		panel.add(lblCi);

		txt_prix = new JTextField();
		txt_prix.setForeground(new Color(0, 0, 205));
		txt_prix.setColumns(10);
		txt_prix.setBounds(131, 285, 111, 20);
		panel.add(txt_prix);

		JLabel lblPrix = new JLabel("Prix");
		lblPrix.setBounds(10, 286, 93, 18);
		panel.add(lblPrix);

		JLabel lblId = new JLabel("Id");
		lblId.setBounds(10, 26, 93, 18);
		panel.add(lblId);

		JLabel lblNobreDexemplaire = new JLabel("Nobre d'exemplaire");
		lblNobreDexemplaire.setBounds(10, 315, 93, 18);
		panel.add(lblNobreDexemplaire);

		txt_nbre = new JTextField();
		txt_nbre.setColumns(10);
		txt_nbre.setBounds(131, 314, 111, 20);
		panel.add(txt_nbre);

		JLabel lblDisponible = new JLabel("Disponible");
		lblDisponible.setBounds(10, 339, 93, 18);
		panel.add(lblDisponible);

		check_disponible = new JCheckBox("");
		check_disponible.setOpaque(false);
		check_disponible.setBounds(131, 341, 97, 23);
		panel.add(check_disponible);

		txt_id = new JTextField();
		txt_id.setOpaque(false);
		txt_id.setEditable(false);
		txt_id.setColumns(10);
		txt_id.setBounds(131, 26, 111, 20);
		panel.add(txt_id);

		txt_date = new JDateChooser();
		txt_date.setDateFormatString("yyyy-MM-dd");
		txt_date.setBounds(131, 257, 111, 20);
		panel.add(txt_date);

		pan_auteur = new auteurListBox();
		pan_auteur.setBorder(null);
		pan_auteur.setBounds(10, 94, 246, 152);
		panel.add(pan_auteur);
		pan_auteur.addSourceElements(getAuteurs());

		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(65, 386, 121, 85);
		add(panel_1);
		panel_1.setLayout(null);

		JButton btnAjouter = new JButton("");
		btnAjouter.setIcon(new ImageIcon(livrePanel.class
				.getResource("/com/img/plus.png")));
		btnAjouter.setBounds(6, 6, 32, 32);
		panel_1.add(btnAjouter);

		JButton btnRechercher = new JButton("");
		btnRechercher.setBounds(44, 6, 32, 32);
		panel_1.add(btnRechercher);
		btnRechercher.setIcon(new ImageIcon(livrePanel.class
				.getResource("/com/img/search.png")));

		JButton btnModifier = new JButton("");
		btnModifier.setBounds(82, 6, 32, 32);
		panel_1.add(btnModifier);
		btnModifier.setIcon(new ImageIcon(livrePanel.class
				.getResource("/com/img/edit.png")));

		JButton btnPrint = new JButton("");
		btnPrint.setIcon(new ImageIcon(livrePanel.class
				.getResource("/com/img/printer.png")));
		btnPrint.setBounds(82, 44, 32, 32);
		panel_1.add(btnPrint);

		JButton btn_reset = new JButton("");
		btn_reset.setBounds(44, 44, 32, 32);
		panel_1.add(btn_reset);
		btn_reset.setIcon(new ImageIcon(livrePanel.class
				.getResource("/com/img/clear.png")));

		JButton btnSupprimer = new JButton("");
		btnSupprimer.setBounds(6, 44, 32, 32);
		panel_1.add(btnSupprimer);
		btnSupprimer.setIcon(new ImageIcon(livrePanel.class
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
		StockEmpty();
	}

	// update table
	public void updateTable(String sql) {

		DAO<livre> dao = new livreDAO(connexion.getInstance());
		ResultSet rs = dao.find_rs(Requetes.livre_select(sql));
		table.setModel((DbUtils.resultSetToTableModel(rs)));
		try {
			if (!rs.first())
				emptyTable = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getAuteurs() {
		DAO<auteur> dao_auteur = new auteurDAO(connexion.getInstance());
		ArrayList<String> list = dao_auteur.getList(Requetes.auteur_select(),
				"nom");
		return list;
	}

	// table mouse clicked
	private void tableMouseClicked(MouseEvent ev) {
		if (!emptyTable)
			try {
				int row = table.getSelectedRow();
				int id = Integer.parseInt((table.getModel().getValueAt(row, 0))
						.toString());
				DAO<livre> dao = new livreDAO(connexion.getInstance());
				livre l1 = dao.find(Requetes.livre_select(id));

				DAO<auteur> dao_auteur = new auteurDAO(connexion.getInstance());
				ArrayList<String> list = dao_auteur.getList(
						Requetes.auteur_select(), "nom");
				list = dao_auteur.getList(Requetes.auteurs_select(id), "nom");
				pan_auteur.clearDestinationListModel();
				pan_auteur.clearSourceListModel();
				pan_auteur.addDestinationElements(list);
				pan_auteur.addSourceElements(getAuteurs());

				txt_id.setText("" + l1.getId() + "");
				txt_titre.setText(l1.getTitre());

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
				String s1 = l1.getDate_edition();
				Date d1 = (Date) sdf.parse(s1);
				txt_date.setDate(d1);

				txt_prix.setText("" + l1.getPrix() + "");
				txt_nbre.setText("" + l1.getNbre_exp() + "");
				check_disponible.setSelected(l1.getDisponible());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "plzz select a valid row");
			}
	}

	// Add
	private void btnAjouterActionPerformed(ActionEvent event) {
		try {

			String date1 = ((JTextField) txt_date.getDateEditor()
					.getUiComponent()).getText();
			livre l1 = new livre(0, txt_titre.getText(),
					Double.parseDouble(txt_prix.getText()),
					Integer.parseInt(txt_nbre.getText()), date1,
					check_disponible.isSelected());
			DAO<livre> dao = new livreDAO(connexion.getInstance());
			dao.update(Requetes.livre_add(l1));
			int livre_id = dao.findString(Requetes.livre_max());
			updateAuteurs(livre_id);
			updateTable("1=1 ");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "don't do that again");
		}
	}

	// update
	private void btnModiefierActionPerformed(ActionEvent event) {
		try {
			int livre_id = Integer.parseInt(txt_id.getText());
			removeFromAuteurLivre(livre_id);
			updateAuteurs(livre_id);
			String date1 = ((JTextField) txt_date.getDateEditor()
					.getUiComponent()).getText();
			livre l1 = new livre(Integer.parseInt(txt_id.getText()),
					txt_titre.getText(),
					Double.parseDouble(txt_prix.getText()),
					Integer.parseInt(txt_nbre.getText()), date1,
					check_disponible.isSelected());
			DAO<livre> dao = new livreDAO(connexion.getInstance());
			dao.update(Requetes.livre_update(l1));
			updateTable("1=1 ");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	// recherche
	private void btnRechercherActionPerformed(ActionEvent event) {
		try {
			String[] btns = { "auteur", "titre", "prix", "nombre d'exemplaire" };
			String cle = (String) JOptionPane.showInputDialog(null,
					"Veuillez indiquer le critère de recherche !", "Recherche",
					JOptionPane.QUESTION_MESSAGE, null, btns, btns[2]);
			String val = "";
			switch (cle) {
			case "auteur":
				val = JOptionPane.showInputDialog(null, cle, "recherche",
						JOptionPane.QUESTION_MESSAGE);
				updateTable("auteur.nom LIKE  '%" + val + "%' ");
				break;
			case "titre":
				val = JOptionPane.showInputDialog(null, cle, "recherche",
						JOptionPane.QUESTION_MESSAGE);
				updateTable("livre.titre LIKE  '%" + val + "%' ");
				break;
			case "prix":
				val = JOptionPane.showInputDialog(null, cle, "recherche",
						JOptionPane.QUESTION_MESSAGE);
				updateTable("livre.prix = " + val);
				break;
			case "nombre d'exemplaire":
				val = JOptionPane.showInputDialog(null, cle, "recherche",
						JOptionPane.QUESTION_MESSAGE);
				updateTable("livre.nbr_exemplaire = " + val);
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
			int livre_id = Integer.parseInt(txt_id.getText());
			DAO<livre> dao = new livreDAO(connexion.getInstance());
			dao.update(Requetes.livre_delete(livre_id));
			removeFromAuteurLivre(livre_id);
			updateTable("1=1 ");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	// Print button
	public void btnPrintActionPerformed(ActionEvent event) {
		MessageFormat header = new MessageFormat(" La liste des livre");
		MessageFormat footer = new MessageFormat(" Page {0,number,integer}");
		try {
			table.print(JTable.PrintMode.NORMAL, header, footer);
		} catch (java.awt.print.PrinterException e) {
			JOptionPane.showMessageDialog(null,
					"cannot print " + e.getMessage());
		}
	}

	public void StockEmpty() {
		DAO<livre> dao = new livreDAO(connexion.getInstance());
		if (dao.find(Requetes.livre_select("livre.nbr_exemplaire <= 0")) != null) {
			tableMsgbox msg = new tableMsgbox();
			msg.updateTable("livre.nbr_exemplaire <= 0");
			msg.setVisible(true);
		}
	}

	public void resetForme() {
		txt_id.setText("");
		txt_titre.setText("");
		txt_prix.setText("");
		txt_nbre.setText("");
		check_disponible.setSelected(false);
	}

	public int getId_auteur(String auteur_name) {
		DAO<auteur> dao = new auteurDAO(connexion.getInstance());
		int id = dao.findString(Requetes.auteur_select("nom = '" + auteur_name
				+ "'"));
		return id;
	}

	public void addToAuteurLivre(int id_livre, int id_auteur) {
		DAO<livre_auteur> dao = new livre_auteurDAO(connexion.getInstance());
		livre_auteur obj = new livre_auteur(id_livre, id_auteur);
		dao.update(Requetes.livre_auteur_add(obj));
	}

	public void updateAuteurs(int livre_id) {
		int auteur_id;
		String[] tab = pan_auteur.getDestinationElements();
		for (int i = 0; i < tab.length; i++) {
			auteur_id = getId_auteur(tab[i]);
			addToAuteurLivre(livre_id, auteur_id);
		}
	}

	public void removeFromAuteurLivre(int id_livre) {
		DAO<livre_auteur> dao = new livre_auteurDAO(connexion.getInstance());
		dao.update(Requetes.livre_auteur_delete("id_livre", id_livre));
	}
}

