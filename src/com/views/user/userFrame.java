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
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.bean.compte;
import com.views.user.homeFrame;
import javax.swing.border.EtchedBorder;

public class userFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panel;
	private userLivrePanel card1;
	private userProfilePanel card2;
	private userEmpruntPanel card3;
	private userAchatPanel card4;
	private String[] listContent = { "CARD_1", "CARD_2", "CARD_3", "CARD_4" };
	private CardLayout myCard;
	@SuppressWarnings("unused")
	private compte c;

	/**
	 * Create the frame.
	 */
	public userFrame(compte c) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				userFrame.class.getResource("/com/img/lib.png")));
		setTitle("Les Livres");
		this.c = c;

		this.setTitle("user interface");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(915, 607);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_5.setBackground(Color.WHITE);
		panel_5.setBounds(0, -2, 899, 75);
		contentPane.add(panel_5);

		JButton btnLivresFrame = new JButton("");
		btnLivresFrame.setOpaque(false);
		btnLivresFrame.setIcon(new ImageIcon(userFrame.class
				.getResource("/com/img/books.png")));
		btnLivresFrame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myCard.show(panel, listContent[0]);
				card1.updateTable("1=1");
			}
		});
		btnLivresFrame.setForeground(new Color(0, 128, 0));
		btnLivresFrame.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLivresFrame.setBounds(1, 0, 179, 73);
		panel_5.add(btnLivresFrame);

		JButton btnProfile = new JButton("");
		btnProfile.setOpaque(false);
		btnProfile.setIcon(new ImageIcon(userFrame.class
				.getResource("/com/img/profile.png")));
		btnProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myCard.show(panel, listContent[1]);
				card2.updateTable();
			}
		});
		btnProfile.setForeground(new Color(0, 128, 0));
		btnProfile.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnProfile.setBounds(180, 0, 180, 73);
		panel_5.add(btnProfile);

		JButton btnEmprunt = new JButton("");
		btnEmprunt.setIcon(new ImageIcon(userFrame.class.getResource("/com/img/emprunt.png")));
		btnEmprunt.setOpaque(false);
		btnEmprunt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myCard.show(panel, listContent[2]);
				card3.updateTable();
			}
		});
		btnEmprunt.setForeground(new Color(0, 128, 0));
		btnEmprunt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnEmprunt.setBounds(360, 0, 180, 73);
		panel_5.add(btnEmprunt);

		JButton btnAchats = new JButton("");
		btnAchats.setOpaque(false);
		btnAchats.setIcon(new ImageIcon(userFrame.class
				.getResource("/com/img/basket.png")));
		btnAchats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myCard.show(panel, listContent[3]);
				card4.updateTable();
			}
		});
		btnAchats.setForeground(new Color(0, 128, 0));
		btnAchats.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAchats.setBounds(540, 0, 180, 73);
		panel_5.add(btnAchats);

		JButton btnHome = new JButton("");
		btnHome.setOpaque(false);
		btnHome.setIcon(new ImageIcon(userFrame.class
				.getResource("/com/img/logout.png")));
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnHomeFrameActionPerformed(e);
			}
		});
		btnHome.setForeground(new Color(0, 128, 0));
		btnHome.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnHome.setBounds(720, 0, 178, 73);
		panel_5.add(btnHome);

		panel = new JPanel();
		panel.setOpaque(false);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 71, 899, 498);
		contentPane.add(panel);
		myCard = new CardLayout(0, 0);
		panel.setLayout(myCard);

		card1 = new userLivrePanel(c);
		card1.setOpaque(false);
		card2 = new userProfilePanel(c);
		card2.setOpaque(false);
		card3 = new userEmpruntPanel(c);
		card3.setOpaque(false);
		card4 = new userAchatPanel(c);
		card4.setOpaque(false);
		panel.add(card1, listContent[0]);
		panel.add(card2, listContent[1]);
		panel.add(card3, listContent[2]);
		panel.add(card4, listContent[3]);
	}

	// log off
	private void btnHomeFrameActionPerformed(ActionEvent event) {
		try {
			close();
			JFrame writter = new homeFrame();
			writter.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	public void close() {
		WindowEvent theEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(theEvent);
	}
}

