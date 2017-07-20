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

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;


import javax.swing.ImageIcon;

import net.proteanit.sql.DbUtils;

import com.bean.livre;
import com.connection.connexion;
import com.dao.DAO;
import com.dao.implement.livreDAO;
import com.requetes.Requetes;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.border.EtchedBorder;

public class tableMsgbox extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Create the dialog.
	 */
	public tableMsgbox() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				tableMsgbox.class.getResource("/com/img/lib.png")));
		setAlwaysOnTop(true);
		setTitle("Stock épuisé");
		this.setSize(712, 336);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton okButton = new JButton("OK");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					close();
				}
			});
			okButton.setIcon(new ImageIcon(tableMsgbox.class
					.getResource("/com/img/ok.png")));
			okButton.setBounds(299, 264, 82, 23);
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 62, 680, 191);
		contentPanel.add(panel);
		panel.setLayout(null);

		table = new JTable();
		table.setBounds(0, 0, 680, 191);
		panel.setLayout(new BorderLayout());
		panel.add(table.getTableHeader(), BorderLayout.NORTH);
		panel.add(table, BorderLayout.CENTER);

		JLabel lblNewLabel = new JLabel(
				"Vous devez ajouter des exemplaire pour ces livres");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setBounds(74, 25, 326, 34);
		contentPanel.add(lblNewLabel);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(tableMsgbox.class
				.getResource("/com/img/alert.png")));
		label.setBounds(10, 8, 46, 51);
		contentPanel.add(label);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(tableMsgbox.class
				.getResource("/com/img/bg-body.jpg")));
		lblNewLabel_1.setBounds(0, 0, 696, 298);
		contentPanel.add(lblNewLabel_1);
	}

	// update table
	public void updateTable(String sql) {

		DAO<livre> dao = new livreDAO(connexion.getInstance());
		ResultSet rs = dao.find_rs(Requetes.livre_select(sql));
		table.setModel((DbUtils.resultSetToTableModel(rs)));
	}

	public void close() {
		this.setVisible(false);
	}
}

