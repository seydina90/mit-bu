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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class dateMsgbox extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JDateChooser date_debut, date_fin;
	@SuppressWarnings("unused")
	private boolean sendData;

	/**
	 * Create the dialog.
	 */
	public dateMsgbox(JFrame parent, boolean modal) {
		super(parent, "Recherche par date", modal);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				dateMsgbox.class.getResource("/com/img/lib.png")));
		this.setSize(345, 191);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.initComponent();
	}

	public void initComponent() {
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		date_debut = new JDateChooser();
		date_debut.setDateFormatString("yyyy-MM-dd");
		date_debut.setBounds(163, 38, 125, 20);
		contentPanel.add(date_debut);

		date_fin = new JDateChooser();
		date_fin.setDateFormatString("yyyy-MM-dd");
		date_fin.setBounds(163, 82, 125, 20);
		contentPanel.add(date_fin);

		JLabel lblNewLabel = new JLabel("Date début :");
		lblNewLabel.setBounds(51, 38, 102, 20);
		contentPanel.add(lblNewLabel);

		JLabel lblDateFin = new JLabel("Date fin :");
		lblDateFin.setBounds(51, 82, 102, 20);
		contentPanel.add(lblDateFin);
		{
			JButton okButton = new JButton("OK");
			okButton.setIcon(new ImageIcon(dateMsgbox.class
					.getResource("/com/img/ok.png")));
			okButton.setBounds(125, 129, 81, 23);
			contentPanel.add(okButton);
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					sendData = true;
					close();
				}
			});
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(dateMsgbox.class
				.getResource("/com/img/bg-body.jpg")));
		lblNewLabel_1.setBounds(0, 0, 339, 163);
		contentPanel.add(lblNewLabel_1);
	}

	public String[] getData() {
		String date1 = ((JTextField) date_debut.getDateEditor()
				.getUiComponent()).getText();
		String date2 = ((JTextField) date_fin.getDateEditor().getUiComponent())
				.getText();
		String[] date = { date1, date2 };
		return date;
	}

	public void close() {
		this.setVisible(false);
	}
}
