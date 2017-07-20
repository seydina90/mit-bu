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
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import net.proteanit.sql.DbUtils;

import com.connection.connexion;
import com.dao.DAO;
import com.dao.implement.auteurDAO;
import com.requetes.Requetes;
import com.toedter.calendar.JDateChooser;
import com.bean.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.border.EtchedBorder;

public class auteurPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField txt_nom;
	private JDateChooser txt_date;
	private boolean emptyTable = false;
	private JTextField txt_id;

	/**
	 * Create the frame.
	 */
	public auteurPanel() {
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Liste des auteur",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panel.setBounds(256, 11, 633, 467);
		add(panel);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tableMouseClicked(e);
			}
		});
		table.setBounds(10, 16, 536, 373);
		panel.setLayout(new BorderLayout());
		panel.add(table.getTableHeader(), BorderLayout.NORTH);
		panel.add(table, BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Auteur",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panel_1.setBounds(12, 11, 234, 115);
		add(panel_1);

		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(10, 52, 93, 18);
		panel_1.add(lblNom);

		txt_nom = new JTextField();
		txt_nom.setColumns(10);
		txt_nom.setBounds(113, 50, 111, 20);
		panel_1.add(txt_nom);

		JLabel lblDateDeNaissance_1 = new JLabel("Date de naissance");
		lblDateDeNaissance_1.setBounds(10, 77, 93, 18);
		panel_1.add(lblDateDeNaissance_1);

		txt_date = new JDateChooser();
		txt_date.setDateFormatString("yyyy-MM-dd");
		txt_date.setBounds(113, 76, 111, 20);
		panel_1.add(txt_date);

		JLabel label_4 = new JLabel("Id");
		label_4.setBounds(10, 26, 93, 18);
		panel_1.add(label_4);

		txt_id = new JTextField();
		txt_id.setEditable(false);
		txt_id.setColumns(10);
		txt_id.setBounds(113, 25, 111, 20);
		panel_1.add(txt_id);

		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(58, 138, 120, 78);
		add(panel_2);
		panel_2.setLayout(null);

		JButton btnModifier = new JButton("");
		btnModifier.setBounds(82, 6, 32, 32);
		panel_2.add(btnModifier);
		btnModifier.setIcon(new ImageIcon(auteurPanel.class
				.getResource("/com/img/edit.png")));

		JButton btnPrint = new JButton("");
		btnPrint.setIcon(new ImageIcon(auteurPanel.class
				.getResource("/com/img/printer.png")));
		btnPrint.setBounds(82, 40, 32, 32);
		panel_2.add(btnPrint);

		JButton btn_reset = new JButton("");
		btn_reset.setBounds(44, 40, 32, 32);
		panel_2.add(btn_reset);
		btn_reset.setIcon(new ImageIcon(auteurPanel.class
				.getResource("/com/img/clear.png")));

		JButton btnRechercher = new JButton("");
		btnRechercher.setBounds(44, 6, 32, 32);
		panel_2.add(btnRechercher);
		btnRechercher.setIcon(new ImageIcon(auteurPanel.class
				.getResource("/com/img/search.png")));

		JButton btnAjouter = new JButton("");
		btnAjouter.setIcon(new ImageIcon(auteurPanel.class
				.getResource("/com/img/plus.png")));
		btnAjouter.setBounds(6, 6, 32, 32);
		panel_2.add(btnAjouter);

		JButton btnSupprimer = new JButton("");
		btnSupprimer.setBounds(6, 40, 32, 32);
		panel_2.add(btnSupprimer);
		btnSupprimer.setIcon(new ImageIcon(auteurPanel.class
				.getResource("/com/img/trash.png")));
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSupprimerActionPerformed(e);
			}
		});
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAjouterActionPerformed(e);
			}
		});
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRechercherActionPerformed(e);
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

		updateTable("1=1 ");

	}

	// update table
	public void updateTable(String sql) {

		auteurDAO dao = new auteurDAO(connexion.getInstance());
		ResultSet rs = dao.find_rs(Requetes.auteur_select(sql));
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

				auteurDAO dao = new auteurDAO(connexion.getInstance());
				auteur auteur1 = dao.find(Requetes.auteur_select(id));
				txt_id.setText("" + auteur1.getId() + "");
				txt_nom.setText(auteur1.getNom());

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String s1 = auteur1.getDate_naissance();
				Date d1 = (Date) sdf.parse(s1);
				txt_date.setDate(d1);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "plz select a valid row");
			}
	}

	// Add
	private void btnAjouterActionPerformed(ActionEvent event) {
		try {
			String date1 = ((JTextField) txt_date.getDateEditor()
					.getUiComponent()).getText();
			DAO<auteur> dao = new auteurDAO(connexion.getInstance());
			auteur auteur1 = new auteur(0, txt_nom.getText(), date1);
			dao.update(Requetes.auteur_add(auteur1));
			updateTable("1=1 ");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	// update
	private void btnModiefierActionPerformed(ActionEvent event) {
		try {
			DAO<auteur> dao = new auteurDAO(connexion.getInstance());
			String date1 = ((JTextField) txt_date.getDateEditor()
					.getUiComponent()).getText();
			auteur auteur1 = new auteur(Integer.parseInt(txt_id.getText()),
					txt_nom.getText(), date1);
			dao.update(Requetes.auteur_update(auteur1));
			updateTable("1=1 ");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	// drop
	private void btnSupprimerActionPerformed(ActionEvent event) {
		try {
			int id = Integer.parseInt(txt_id.getText());
			DAO<auteur> dao = new auteurDAO(connexion.getInstance());
			dao.update(Requetes.auteur_delete(id));
			updateTable("1=1 ");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	// recherche
	private void btnRechercherActionPerformed(ActionEvent event) {
		try {
			String[] btns = { "nom", "date" };
			String cle = (String) JOptionPane.showInputDialog(null,
					"Veuillez indiquer le critére de recherche !", "Recherche",
					JOptionPane.QUESTION_MESSAGE, null, btns, btns[0]);
			String val = "";
			switch (cle) {
			case "nom":
				val = JOptionPane.showInputDialog(null, cle, "recherche",
						JOptionPane.QUESTION_MESSAGE);
				updateTable("nom LIKE  '%" + val + "%' ");
				break;
			case "date":
				dateMsgbox date = new dateMsgbox(null, true);
				date.setVisible(true);
				updateTable("date_naissance BETWEEN '" + date.getData()[0]
						+ "' and '" + date.getData()[1] + "'");
			default:
				break;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "don't do that again");
		}
	}

	// Print button
	public void btnPrintActionPerformed(ActionEvent event) {
		MessageFormat header = new MessageFormat(" La liste des auteurs");
		MessageFormat footer = new MessageFormat(" Page {0,number,integer}");
		try {
			table.print(JTable.PrintMode.FIT_WIDTH, header, footer);
		} catch (java.awt.print.PrinterException e) {
			JOptionPane.showMessageDialog(null,
					"cannot print " + e.getMessage());
		}
	}

	public void resetForme() {
		txt_id.setText("");
		txt_nom.setText("");
	}

}

