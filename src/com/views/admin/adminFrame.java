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
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import com.views.user.homeFrame;
import javax.swing.border.EtchedBorder;

public class adminFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panel;
	private livrePanel card1;
	private auteurPanel card2;
	private empruntPanel card3;
	private achatPanel card4;
	private comptePanel card5;
	private String[] listContent = { "CARD_1", "CARD_2", "CARD_3", "CARD_4",
			"CARD_5" };
	private CardLayout myCard;
	private JLabel lblNewLabel;

	/**
	 * Create the frame.
	 */
	public adminFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				adminFrame.class.getResource("/com/img/lib.png")));
		this.setTitle("admin");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(915, 607);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_5 = new JPanel();
		panel_5.setOpaque(false);
		panel_5.setLayout(null);
		panel_5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_5.setBackground(Color.WHITE);
		panel_5.setBounds(0, 0, 899, 75);
		contentPane.add(panel_5);

		JButton btnLivresFrame = new JButton("");
		btnLivresFrame.setOpaque(false);
		btnLivresFrame.setIcon(new ImageIcon(adminFrame.class
				.getResource("/com/img/books.png")));
		btnLivresFrame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myCard.show(panel, listContent[0]);
				card1.updateTable("1=1");
			}
		});
		btnLivresFrame.setForeground(new Color(0, 128, 0));
		btnLivresFrame.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLivresFrame.setBounds(1, 0, 150, 73);
		panel_5.add(btnLivresFrame);

		JButton btnAuteursFrame = new JButton("");
		btnAuteursFrame.setOpaque(false);
		btnAuteursFrame.setIcon(new ImageIcon(adminFrame.class
				.getResource("/com/img/writer.png")));
		btnAuteursFrame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myCard.show(panel, listContent[1]);
				card2.updateTable("1=1");
			}
		});
		btnAuteursFrame.setForeground(new Color(0, 128, 0));
		btnAuteursFrame.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAuteursFrame.setBounds(150, 0, 150, 73);
		panel_5.add(btnAuteursFrame);

		JButton btnEmpruntFrame = new JButton("");
		btnEmpruntFrame.setIcon(new ImageIcon(adminFrame.class.getResource("/com/img/emprunt.png")));
		btnEmpruntFrame.setOpaque(false);
		btnEmpruntFrame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myCard.show(panel, listContent[2]);
				card3.updateTable("1=1");
			}
		});
		btnEmpruntFrame.setForeground(new Color(0, 128, 0));
		btnEmpruntFrame.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnEmpruntFrame.setBounds(300, 0, 150, 73);
		panel_5.add(btnEmpruntFrame);

		JButton btnAchatsFrame = new JButton("");
		btnAchatsFrame.setOpaque(false);
		btnAchatsFrame.setIcon(new ImageIcon(adminFrame.class
				.getResource("/com/img/basket.png")));
		btnAchatsFrame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myCard.show(panel, listContent[3]);
				card4.updateTable("1=1");
			}
		});
		btnAchatsFrame.setForeground(new Color(0, 128, 0));
		btnAchatsFrame.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAchatsFrame.setBounds(450, 0, 150, 73);
		panel_5.add(btnAchatsFrame);

		JButton btnCompteFrame = new JButton("");
		btnCompteFrame.setOpaque(false);
		btnCompteFrame.setIcon(new ImageIcon(adminFrame.class
				.getResource("/com/img/users.png")));
		btnCompteFrame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myCard.show(panel, listContent[4]);
				card5.updateTable("1=1");
			}
		});
		btnCompteFrame.setForeground(new Color(0, 128, 0));
		btnCompteFrame.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCompteFrame.setBounds(600, 0, 150, 73);
		panel_5.add(btnCompteFrame);

		JButton btnHomeFrame = new JButton("");
		btnHomeFrame.setOpaque(false);
		btnHomeFrame.setIcon(new ImageIcon(adminFrame.class
				.getResource("/com/img/logout.png")));
		btnHomeFrame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnHomeFrameActionPerformed(e);
			}
		});
		btnHomeFrame.setForeground(new Color(0, 128, 0));
		btnHomeFrame.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnHomeFrame.setBounds(750, 0, 149, 73);
		panel_5.add(btnHomeFrame);

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 73, 899, 496);
		contentPane.add(panel);
		myCard = new CardLayout(0, 0);
		panel.setLayout(myCard);

		card1 = new livrePanel();
		card1.setOpaque(false);
		card1.setBackground(Color.WHITE);
		card1.setBorder(null);
		card2 = new auteurPanel();
		card2.setOpaque(false);
		card3 = new empruntPanel();
		card3.setOpaque(false);
		card4 = new achatPanel();
		card4.setOpaque(false);
		card5 = new comptePanel();
		card5.setOpaque(false);
		panel.add(card1, listContent[0]);
		panel.add(card2, listContent[1]);
		panel.add(card3, listContent[2]);
		panel.add(card4, listContent[3]);
		panel.add(card5, listContent[4]);

		lblNewLabel = new JLabel("");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setIcon(null);
		lblNewLabel.setBounds(2, 0, 903, 569);
		contentPane.add(lblNewLabel);
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
