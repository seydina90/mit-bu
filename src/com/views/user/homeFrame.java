/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.user;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.bean.compte;
import com.bean.personne;
import com.views.admin.adminFrame;
import com.connection.connexion;
import com.dao.DAO;
import com.dao.implement.compteDAO;
import com.dao.implement.personneDAO;
import com.requetes.Requetes;
import javax.swing.border.EtchedBorder;

public class homeFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtCin;
	private JTextField txtEmail;
	private JButton btnValider;
	private JTextField txt_username;
	private JPasswordField txt_password;
	private JTextField txtLogin;
	private JPasswordField txtPassword1;
	private JPasswordField txtPassword2;
	private JPanel panel;
	private JLabel lblDjaInscrit;
	private JLabel lblPasEncore;
	private JLabel lblImg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					homeFrame frame = new homeFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public homeFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				homeFrame.class.getResource("/com/img/lib.png")));
		setTitle("Accueil");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(915, 607);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(77, 210, 97, 14);
		contentPane.add(lblNom);
		lblNom.setFont(new Font("Tahoma", Font.PLAIN, 12));

		JLabel lblPrnom = new JLabel("Pr\u00E9nom");
		lblPrnom.setBounds(77, 237, 97, 14);
		contentPane.add(lblPrnom);
		lblPrnom.setFont(new Font("Tahoma", Font.PLAIN, 12));

		JLabel lblCin = new JLabel("Cin");
		lblCin.setBounds(77, 264, 97, 14);
		contentPane.add(lblCin);
		lblCin.setFont(new Font("Tahoma", Font.PLAIN, 12));

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(77, 292, 97, 14);
		contentPane.add(lblEmail);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));

		txtNom = new JTextField();
		txtNom.setBounds(208, 208, 163, 16);
		contentPane.add(txtNom);
		txtNom.setColumns(10);

		txtPrenom = new JTextField();
		txtPrenom.setBounds(208, 235, 163, 16);
		contentPane.add(txtPrenom);
		txtPrenom.setColumns(10);

		txtCin = new JTextField();
		txtCin.setBounds(208, 262, 163, 16);
		contentPane.add(txtCin);
		txtCin.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setBounds(208, 290, 163, 16);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);

		btnValider = new JButton("Valider");
		btnValider.setBounds(231, 403, 109, 23);
		contentPane.add(btnValider);
		btnValider.setBackground(new Color(192, 192, 192));
		btnValider.setIcon(new ImageIcon(homeFrame.class
				.getResource("/com/img/ok.png")));

		txtLogin = new JTextField();
		txtLogin.setBounds(208, 317, 163, 16);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);

		JLabel lblLogin_1 = new JLabel("Login");
		lblLogin_1.setBounds(77, 319, 97, 14);
		contentPane.add(lblLogin_1);
		lblLogin_1.setFont(new Font("Tahoma", Font.PLAIN, 12));

		JLabel lblMotDePasse = new JLabel("Mot de passe");
		lblMotDePasse.setBounds(77, 346, 97, 14);
		contentPane.add(lblMotDePasse);
		lblMotDePasse.setFont(new Font("Tahoma", Font.PLAIN, 12));

		txtPassword1 = new JPasswordField();
		txtPassword1.setBounds(208, 344, 163, 16);
		contentPane.add(txtPassword1);

		JLabel lblVrification = new JLabel("V\u00E9rification");
		lblVrification.setBounds(77, 373, 97, 14);
		contentPane.add(lblVrification);
		lblVrification.setFont(new Font("Tahoma", Font.PLAIN, 12));

		txtPassword2 = new JPasswordField();
		txtPassword2.setBounds(208, 371, 163, 16);
		contentPane.add(txtPassword2);

		JLabel lblLogin = new JLabel("Login :");
		lblLogin.setBounds(264, 42, 63, 20);
		contentPane.add(lblLogin);
		lblLogin.setForeground(new Color(0, 0, 255));
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 12));

		txt_username = new JTextField();
		txt_username.setBounds(328, 42, 120, 20);
		contentPane.add(txt_username);
		txt_username.setColumns(10);

		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(458, 42, 63, 20);
		contentPane.add(lblPassword);
		lblPassword.setForeground(new Color(0, 0, 255));
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));

		txt_password = new JPasswordField();
		txt_password.setBounds(531, 42, 120, 20);
		contentPane.add(txt_password);

		JButton btnLogin = new JButton("Connecter");
		btnLogin.setBounds(670, 42, 116, 23);
		contentPane.add(btnLogin);
		btnLogin.setIcon(new ImageIcon(homeFrame.class
				.getResource("/com/img/ok.png")));

		panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(0, 105, 899, 2);
		contentPane.add(panel);

		lblDjaInscrit = new JLabel("D\u00E9ja inscrit ?");
		lblDjaInscrit.setForeground(SystemColor.activeCaptionText);
		lblDjaInscrit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDjaInscrit.setBounds(20, 56, 163, 51);
		contentPane.add(lblDjaInscrit);

		lblPasEncore = new JLabel("Pas encore");
		lblPasEncore.setForeground(SystemColor.activeCaptionText);
		lblPasEncore.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPasEncore.setBounds(20, 105, 163, 51);
		contentPane.add(lblPasEncore);

		lblImg = new JLabel("");
		lblImg.setIcon(new ImageIcon(homeFrame.class
				.getResource("/com/img/book.png")));
		lblImg.setForeground(new Color(0, 128, 0));
		lblImg.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblImg.setBounds(591, 183, 269, 363);
		contentPane.add(lblImg);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(homeFrame.class
				.getResource("/com/img/bg-body.jpg")));
		lblNewLabel.setBounds(0, 0, 899, 569);
		contentPane.add(lblNewLabel);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLoginActionPerformed(e);
			}
		});
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnValiderActionPerformed(e);
			}
		});
	}

	private void btnValiderActionPerformed(ActionEvent event) {
		try {

			if (check_password())
				if (check_cin())
					if (check_Login())
						inscription();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	private void btnLoginActionPerformed(ActionEvent event) {
		try {
			@SuppressWarnings("deprecation")
			compte c1 = new compte(0, txt_username.getText(),
					txt_password.getText(), "");
			DAO<compte> dao = new compteDAO(connexion.getInstance());
			c1 = dao.find(Requetes.compte_select(c1));
			if (c1 == null) {
				JOptionPane.showMessageDialog(
						null,
						"not correct",
						"Attention",
						JOptionPane.WARNING_MESSAGE,
						new ImageIcon(homeFrame.class
								.getResource("/com/img/alert.png")));
			} else if (c1.getRole().equals("admin")) {
				JOptionPane.showMessageDialog(
						null,
						"welcome admin : " + c1.getUsername(),
						"Information",
						JOptionPane.INFORMATION_MESSAGE,
						new ImageIcon(homeFrame.class
								.getResource("/com/img/info.png")));
				close();
				JFrame books = new adminFrame();
				books.setVisible(true);
			} else {
				DAO<personne> dao1 = new personneDAO(connexion.getInstance());
				personne p = dao1.find(Requetes.user_select("id_compte",
						c1.getId() + ""));
				if (p.getEtat()) {
					JOptionPane.showMessageDialog(
							null,
							"welcome user : " + c1.getUsername(),
							"Information",
							JOptionPane.INFORMATION_MESSAGE,
							new ImageIcon(homeFrame.class
									.getResource("/com/img/info.png")));
					close();
					JFrame books = new userFrame(c1);
					books.setVisible(true);
				} else {
					JOptionPane
							.showMessageDialog(
									null,
									"Désolé mais, vous devez attendre 24h pour que l'agent du bibliothéque valide votre compte et vos informations",
									"Information",
									JOptionPane.INFORMATION_MESSAGE,
									new ImageIcon(homeFrame.class
											.getResource("/com/img/info.png")));
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Plz run the MySQL server",
					"Attention", JOptionPane.WARNING_MESSAGE, new ImageIcon(
							homeFrame.class.getResource("/com/img/alert.png")));
		}
	}

	public void close() {
		WindowEvent theEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(theEvent);
	}

	public void inscription() {
		@SuppressWarnings("deprecation")
		compte c1 = new compte(0, txtLogin.getText(), txtPassword1.getText(),
				"user");
		DAO<compte> dao1 = new compteDAO(connexion.getInstance());
		dao1.update(Requetes.compte_add(c1));
		int id_compte = dao1.findString(Requetes.compte_max());
		personne p1 = new personne(0, id_compte, txtNom.getText(),
				txtPrenom.getText(), txtCin.getText(), txtEmail.getText(),
				false);
		DAO<personne> dao2 = new personneDAO(connexion.getInstance());
		dao2.update(Requetes.user_add(p1));
		JOptionPane
				.showMessageDialog(
						null,
						"Merci pour l'inscription mais, vous devez attendre 24h pour que l'agent du bibliothéque valide votre compte et vos informations",
						"Information",
						JOptionPane.INFORMATION_MESSAGE,
						new ImageIcon(homeFrame.class
								.getResource("/com/img/info.png")));
	}

	@SuppressWarnings("deprecation")
	public boolean check_password() {
		if (txtPassword1.getText().length() < 6) {
			JOptionPane.showMessageDialog(
					null,
					"votre mot de pass est trop court",
					"Attention",
					JOptionPane.WARNING_MESSAGE,
					new ImageIcon(homeFrame.class
							.getResource("/com/img/alert.png")));
			return false;
		} else if (!txtPassword1.getText().equals(txtPassword2.getText())) {
			JOptionPane.showMessageDialog(
					null,
					"le 1er mot de passe est different du 2eme",
					"Attention",
					JOptionPane.WARNING_MESSAGE,
					new ImageIcon(homeFrame.class
							.getResource("/com/img/alert.png")));
			return false;
		} else
			return true;
	}

	public boolean check_Login() {
		String msg = "";
		if (txtLogin.getText().length() < 6) {
			msg = "Votre pseudo doit contenir au moins 5 carracteres";
			JOptionPane.showMessageDialog(
					null,
					msg,
					"Attention",
					JOptionPane.WARNING_MESSAGE,
					new ImageIcon(homeFrame.class
							.getResource("/com/img/alert.png")));
			return false;
		} else {
			DAO<compte> dao = new compteDAO(connexion.getInstance());
			compte c1 = dao.find(Requetes.compte_select("username",
					txtLogin.getText()));
			if (c1 != null) {
				msg = "ce pseudo est déja existant, Veillez rentrer un noveau pseudo";
				JOptionPane.showMessageDialog(
						null,
						msg,
						"Attention",
						JOptionPane.WARNING_MESSAGE,
						new ImageIcon(homeFrame.class
								.getResource("/com/img/alert.png")));
				return false;
			} else
				return true;
		}
	}

	public boolean check_cin() {
		DAO<personne> dao = new personneDAO(connexion.getInstance());
		personne p1 = dao.find(Requetes.user_select("cin", txtCin.getText()));
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

