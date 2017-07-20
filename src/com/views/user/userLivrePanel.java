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
import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

import net.proteanit.sql.DbUtils;

import com.bean.achat;
import com.bean.compte;
import com.bean.emprunt;
import com.bean.livre;
import com.bean.auteur;
import com.bean.personne;
import com.connection.connexion;
import com.dao.DAO;
import com.dao.implement.*;
import com.requetes.Requetes;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class userLivrePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JCheckBox check_disponible;
	@SuppressWarnings("unused")
	private compte c;
	private personne p;
	private JTextField txt_id;
	private JTextField txt_titre;
	private JTextField txt_date;
	private JTextField txt_prix;
	private JTextField txt_nbre;
	private boolean emptyTable = false;
	private JList<Object> list_auteur;
	private auteurListModel auteurMoedel;

	/**
	 * Create the frame.
	 */
	public userLivrePanel(compte c) {
		this.c = c;
		DAO<personne> dao = new personneDAO(connexion.getInstance());
		this.p = dao.find(Requetes.user_select("id_compte", c.getId() + ""));
		setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panel_2.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Liste des livres",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panel_2.setBounds(254, 11, 627, 471);
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
		table.setBounds(10, 16, 536, 394);
		panel_2.setLayout(new BorderLayout());
		panel_2.add(table.getTableHeader(), BorderLayout.NORTH);
		panel_2.add(table, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Livre",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panel.setBounds(10, 16, 234, 327);
		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Titre");
		lblNewLabel.setBounds(10, 52, 93, 18);
		panel.add(lblNewLabel);

		JLabel lblPrenom = new JLabel("Auteur");
		lblPrenom.setBounds(10, 77, 93, 18);
		panel.add(lblPrenom);

		JLabel lblCi = new JLabel("Date d'edition");
		lblCi.setBounds(10, 199, 93, 18);
		panel.add(lblCi);

		JLabel lblPrix = new JLabel("Prix");
		lblPrix.setBounds(10, 227, 93, 18);
		panel.add(lblPrix);

		JLabel lblId = new JLabel("Id");
		lblId.setBounds(10, 26, 93, 18);
		panel.add(lblId);

		JLabel lblNobreDexemplaire = new JLabel("Nobre d'exemplaire");
		lblNobreDexemplaire.setBounds(10, 256, 93, 18);
		panel.add(lblNobreDexemplaire);

		JLabel lblDisponible = new JLabel("Disponible");
		lblDisponible.setBounds(10, 280, 93, 18);
		panel.add(lblDisponible);

		check_disponible = new JCheckBox("");
		check_disponible.setOpaque(false);
		check_disponible.setEnabled(false);
		check_disponible.setBounds(113, 281, 97, 23);
		panel.add(check_disponible);

		txt_id = new JTextField();
		txt_id.setEditable(false);
		txt_id.setColumns(10);
		txt_id.setBounds(113, 25, 111, 20);
		panel.add(txt_id);

		txt_titre = new JTextField();
		txt_titre.setEditable(false);
		txt_titre.setColumns(10);
		txt_titre.setBounds(113, 51, 111, 20);
		panel.add(txt_titre);

		txt_date = new JTextField();
		txt_date.setEditable(false);
		txt_date.setColumns(10);
		txt_date.setBounds(113, 198, 111, 20);
		panel.add(txt_date);

		txt_prix = new JTextField();
		txt_prix.setForeground(Color.BLUE);
		txt_prix.setEditable(false);
		txt_prix.setColumns(10);
		txt_prix.setBounds(113, 226, 111, 20);
		panel.add(txt_prix);

		txt_nbre = new JTextField();
		txt_nbre.setEditable(false);
		txt_nbre.setColumns(10);
		txt_nbre.setBounds(113, 255, 111, 20);
		panel.add(txt_nbre);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(113, 82, 111, 104);
		panel.add(scrollPane);
		auteurMoedel = new auteurListModel();
		list_auteur = new JList<Object>(auteurMoedel);
		scrollPane.setViewportView(list_auteur);

		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(51, 354, 120, 44);
		add(panel_1);
		panel_1.setLayout(null);

		JButton btnRechercher = new JButton("");
		btnRechercher.setBounds(82, 6, 32, 32);
		panel_1.add(btnRechercher);
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRechercherActionPerformed(e);
			}
		});
		btnRechercher.setIcon(new ImageIcon(userLivrePanel.class
				.getResource("/com/img/search.png")));

		JButton btnAcheter = new JButton("");
		btnAcheter.setBounds(44, 6, 32, 32);
		panel_1.add(btnAcheter);
		btnAcheter.setIcon(new ImageIcon(userLivrePanel.class
				.getResource("/com/img/basket-add.png")));

		JButton btnEmprunter = new JButton("");
		btnEmprunter.setBounds(6, 6, 32, 32);
		panel_1.add(btnEmprunter);
		btnEmprunter.setIcon(new ImageIcon(userLivrePanel.class
				.getResource("/com/img/emprunter.png")));
		btnEmprunter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEmprunterActionPerformed(e);
			}
		});
		btnAcheter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAcheterActionPerformed(e);
			}
		});

		updateTable("1=1 ");

	}

	// update table
	public void updateTable(String sql) {

		DAO<livre> dao = new livreDAO(connexion.getInstance());
		ResultSet rs = dao.find_rs(Requetes.livre_select_user(sql));
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

				DAO<livre> dao = new livreDAO(connexion.getInstance());
				livre l1 = dao.find(Requetes.livre_select(id));
				DAO<auteur> dao_auteur = new auteurDAO(connexion.getInstance());
				ArrayList<String> list = dao_auteur.getList(
						Requetes.auteur_select(), "nom");
				list = dao_auteur.getList(Requetes.auteurs_select(id), "nom");
				txt_id.setText(l1.getId() + "");
				txt_titre.setText(l1.getTitre());
				auteurMoedel.clear();
				auteurMoedel.addAll(list);
				txt_date.setText(l1.getDate_edition());
				txt_prix.setText("" + l1.getPrix() + " Fcfa");
				txt_nbre.setText("" + l1.getNbre_exp() + "");
				check_disponible.setSelected(l1.getDisponible());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "plz select a valid row");
			}
	}

	// bouton emprunter
	private void btnEmprunterActionPerformed(ActionEvent event) {
		try {

			if (emprunt_valider()) {
				int id_livre = Integer.parseInt(txt_id.getText());
				if (!isDejaEmprunter(id_livre)) {
					emprunt e1 = new emprunt(0, p.getId(), id_livre, "",
							"1111-11-11", 0, false);
					DAO<emprunt> dao = new empruntDAO(connexion.getInstance());
					dao.update(Requetes.emprunt_add(e1));
					nbreLivreDown(id_livre);
					updateTable("1=1 ");
				}
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	// bouton Acheter
	private void btnAcheterActionPerformed(ActionEvent event) {
		try {
			int id_livre = Integer.parseInt(txt_id.getText());

			if (!isDejaAcheter(id_livre)) {
				DAO<achat> dao = new achatDAO(connexion.getInstance());
				achat achat1 = new achat(0, p.getId(), id_livre, "1111-11-11");
				dao.update(Requetes.achat_add(achat1));
				JOptionPane.showMessageDialog(
						null,
						"Le livre a été bien ajouter dans votre panier.",
						"Information",
						JOptionPane.INFORMATION_MESSAGE,
						new ImageIcon(userLivrePanel.class
								.getResource("/com/img/info.png")));
				nbreLivreDown(id_livre);
				updateTable("1=1 ");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	// recherche
	private void btnRechercherActionPerformed(ActionEvent event) {
		try {
			String[] btns = { "auteur", "titre", "prix" };
			String cle = (String) JOptionPane.showInputDialog(null,
					"Veuillez indiquer le critére de recherche !", "Recherche",
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
				if (!val.equals(""))
					updateTable("livre.prix = " + val);
				break;
			default:
				break;
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "don't do that again");
		}
	}

	// nbre d'exemplaire -1
	private void nbreLivreDown(int id_livre) {
		DAO<livre> dao = new livreDAO(connexion.getInstance());
		dao.update(Requetes.livre_nbreDown(id_livre));
	}

	// valider l'emprunt
	public boolean emprunt_valider() {
		DAO<emprunt> dao = new empruntDAO(connexion.getInstance());
		int nbre_emprunt = dao.findString(Requetes.emprunt_count(p.getId()));
		if (nbre_emprunt < 5)
			return true;
		else {
			JOptionPane
					.showMessageDialog(
							null,
							"vous n'avez pas le droit d'emprunter plus que 5 livre par mois",
							"Attention",
							JOptionPane.WARNING_MESSAGE,
							new ImageIcon(userLivrePanel.class
									.getResource("/com/img/alert.png")));
			return false;
		}
	}

	// valider l'achat
	public boolean isDejaAcheter(int id_livre) {
		DAO<achat> dao = new achatDAO(connexion.getInstance());
		int id = dao
				.findString(Requetes.achat_select_deja(p.getId(), id_livre));
		if (id != 0) {
			int reponse = JOptionPane.showConfirmDialog(this,
					"Vous avez déja acheté ce livre. Voullez-vous contunier ?",
					"info", JOptionPane.YES_NO_OPTION);
			if (reponse == JOptionPane.YES_OPTION)
				return false;
			else
				return true;
		} else
			return false;
	}

	// tester si ce livre a ete deja emprunter
	public boolean isDejaEmprunter(int id_livre) {
		DAO<emprunt> dao = new empruntDAO(connexion.getInstance());
		int id = dao.findString(Requetes.emprunt_select_deja(p.getId(),
				id_livre));
		if (id != 0) {
			int reponse = JOptionPane
					.showConfirmDialog(
							this,
							"Vous avez déja emprunté ce livre. Voullez-vous contunier ?",
							"info", JOptionPane.YES_NO_OPTION);
			if (reponse == JOptionPane.YES_OPTION)
				return false;
			else
				return true;
		} else
			return false;
	}

	class auteurListModel extends AbstractListModel<Object> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		SortedSet<Object> model;

		public auteurListModel() {
			model = new TreeSet<Object>();
		}

		public int getSize() {
			return model.size();
		}

		public Object getElementAt(int index) {
			return model.toArray()[index];
		}

		public void add(Object element) {
			if (model.add(element)) {
				fireContentsChanged(this, 0, getSize());
			}
		}

		public void addAll(ArrayList<String> list) {
			for (int i = 0; i < list.size(); i++) {
				add(list.get(i));
			}
		}

		public void clear() {
			model.clear();
			fireContentsChanged(this, 0, getSize());
		}

		public String[] getAll() {
			int size = getSize();
			String[] tab = new String[size];
			for (int i = 0; i < size; i++)
				tab[i] = model.toArray()[i].toString();
			return tab;
		}

		public boolean removeElement(Object element) {
			boolean removed = model.remove(element);
			if (removed) {
				fireContentsChanged(this, 0, getSize());
			}
			return removed;
		}
	}
}

