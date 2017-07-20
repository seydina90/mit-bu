/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.requetes;

/**
 *
 * @author Idriss
 */
import com.bean.*;

public class Requetes {

	// livre_auteur
	public static String livre_auteur_add(livre_auteur obj) {
		return "INSERT INTO livre_auteur(id_livre , id_auteur) values('"
				+ obj.getId_livre() + "', '" + obj.getId_auteur() + "');";
	}

	public static String livre_auteur_select(livre_auteur obj) {
		return "SELECT * FROM livre_auteur where id_livre='"
				+ obj.getId_livre() + "' and id_auteur='" + obj.getId_auteur()
				+ "';";
	}

	public static String livre_auteur_delete(String cle, int id) {
		return "DELETE FROM livre_auteur where " + cle + "='" + id + "';";
	}

	public static String livre_auteur_delete(livre_auteur obj) {
		return "DELETE FROM livre_auteur where id_livre='" + obj.getId_livre()
				+ "' and id_auteur='" + obj.getId_auteur() + "';";
	}

	// emprunt
	public static String emprunt_select() {
		return "SELECT e.id, e.id_personne, p.cin, p.nom, e.id_livre, l.titre, e.date_emprunt, e.date_retoure, e.etat "
				+ "FROM emprunt AS e INNER JOIN personne AS p, livre AS l WHERE e.id_livre = l.id AND p.id = e.id_personne";
	}

	public static String emprunt_select(String sql) {
		return "SELECT e.id, e.id_personne, p.cin, p.nom, e.id_livre, l.titre, e.date_emprunt, e.date_retoure, e.etat "
				+ "FROM emprunt AS e INNER JOIN personne AS p, livre AS l WHERE e.id_livre = l.id AND p.id = e.id_personne and "
				+ sql;
	}

	public static String emprunt_select(int id) {
		return "SELECT * FROM emprunt WHERE id=" + id;
	}

	// calcul le retard
	public static String emprunt_select_retard(int id) {
		return "SELECT TO_DAYS( date_retoure ) - (TO_DAYS(date_emprunt) + 30)  as id FROM emprunt WHERE id="
				+ id;
	}

	public static String emprunt_select(String cle, String val) {
		return "SELECT e.id, e.id_personne, p.cin, p.nom, e.id_livre, l.titre, e.date_emprunt, e.date_retoure, e.etat "
				+ " FROM emprunt AS e INNER JOIN personne AS p, livre AS l WHERE e.id_livre = l.id AND p.id = e.id_personne"
				+ " and " + cle + "='" + val + "'";
	}

	public static String emprunt_add(emprunt obj) {
		int etat = obj.getLivre_retourne() ? 1 : 0;
		return "INSERT INTO emprunt (id, id_livre, id_personne, date_emprunt, date_retoure, etat) "
				+ "VALUES (NULL, '"
				+ obj.getId_livre()
				+ "', '"
				+ obj.getId_user()
				+ "', DATE(NOW()), ADDDATE(DATE(NOW()),30), '" + etat + "');";
	}

	public static String emprunt_update(emprunt obj) {
		int etat = obj.getLivre_retourne() ? 1 : 0;
		return "UPDATE emprunt set id_livre='" + obj.getId_livre()
				+ "', id_personne='" + obj.getId_user() + "', date_emprunt='"
				+ obj.getD_emprunt() + "', retard='" + obj.getRetard()
				+ "', etat='" + etat + "' WHERE id='" + obj.getId() + "';";
	}

	// update date retour � now()
	public static String emprunt_update(int id) {
		return "UPDATE emprunt set date_retoure = DATE(NOW()) WHERE id='" + id
				+ "';";
	}

	// calcul le nombre d'empruntes effectu�s par cette personne pendant ces 30
	// jours
	public static String emprunt_count(int id_personne) {
		return "SELECT COUNT( * ) as id FROM emprunt WHERE id_personne ="
				+ id_personne
				+ " and TO_DAYS(NOW( )) - TO_DAYS( date_emprunt ) < 30";
	}

	public static String emprunt_delete(int id) {
		return "DELETE FROM emprunt where id='" + id + "';";
	}

	public static String emprunt_select_deja(int id, int id_livre) {
		return "SELECT COUNT( * ) as id FROM emprunt WHERE id_livre = '"
				+ id_livre + "' AND id_personne = '" + id
				+ "' and TO_DAYS(NOW( )) - TO_DAYS( date_emprunt ) <30";
	}

	// achat
	public static String achat_select() {
		return "SELECT a.id, a.id_personne, p.cin, p.nom, a.id_livre, l.titre, l.prix, a.date_achat"
				+ " FROM achat AS a INNER JOIN personne AS p, livre AS l WHERE a.id_livre = l.id AND p.id = a.id_personne";
	}

	public static String achat_select(String sql) {
		return "SELECT a.id, a.id_personne, p.cin, p.nom, a.id_livre, l.titre, l.prix, a.date_achat"
				+ " FROM achat AS a INNER JOIN personne AS p, livre AS l WHERE a.id_livre = l.id AND p.id = a.id_personne and "
				+ sql;
	}

	public static String achat_select(int id) {
		return "SELECT * FROM achat WHERE id=" + id;
	}

	public static String achat_select(String cle, String val) {
		return "SELECT a.id, a.id_personne, p.cin, p.nom, a.id_livre, l.titre, a.date_achat"
				+ " FROM achat AS a INNER JOIN personne AS p, livre AS l WHERE a.id_livre = l.id AND p.id = a.id_personne"
				+ " and " + cle + "='" + val + "'";
	}

	public static String achat_add(achat obj) {
		return "INSERT INTO achat (`id`, `id_livre`, `id_personne`, `date_achat`) "
				+ "VALUES (NULL, '"
				+ obj.getId_livre()
				+ "', '"
				+ obj.getId_user() + "', DATE(NOW()));";
	}

	public static String achat_update(achat obj) {
		return "UPDATE achat set id_livre='" + obj.getId_livre()
				+ "', id_personne='" + obj.getId_user() + "', date_achat='"
				+ obj.getD_achat() + "' WHERE id='" + obj.getId() + "';";
	}

	public static String achat_delete(int id) {
		return "DELETE FROM achat where id='" + id + "';";
	}

	public static String achat_select_deja(int id, int id_livre) {
		return "SELECT COUNT( * ) as id FROM achat WHERE id_livre = '"
				+ id_livre + "' AND id_personne = " + id;
	}

	// personne
	public static String user_add(personne obj) {
		int etat = obj.getEtat() ? 1 : 0;
		return "INSERT INTO personne(id_compte,nom,prenom,cin,email,etat) values('"
				+ obj.getId_compte()
				+ "','"
				+ obj.getNom()
				+ "','"
				+ obj.getPrenom()
				+ "','"
				+ obj.getCin()
				+ "','"
				+ obj.getEmail() + "','" + etat + "');";
	}

	public static String user_select() {
		return "SELECT p.*, c.username, c.password, c.role FROM personne as p INNER JOIN compte AS c where p.id_compte=c.id";
	}

	public static String user_select(String sql) {
		return "SELECT p.*, c.username, c.password, c.role FROM personne as p INNER JOIN compte AS c where p.id_compte=c.id and "
				+ sql;
	}

	public static String user_select(int id) {
		return "SELECT * FROM personne where id='" + id + "'";
	}

	public static String user_select(String cle, String val) {
		return "SELECT * FROM personne where " + cle + "='" + val + "'";
	}

	public static String user_update(personne obj) {
		int etat = obj.getEtat() ? 1 : 0;
		return "UPDATE personne SET id_compte='" + obj.getId_compte()
				+ "', nom='" + obj.getNom() + "', prenom='" + obj.getPrenom()
				+ "', cin='" + obj.getCin() + "' ,email='" + obj.getEmail()
				+ "', etat='" + etat + "' WHERE id='" + obj.getId() + "';";
	}

	public static String user_delete(int id) {
		return "DELETE FROM personne WHERE id='" + id + "';";
	}

	// auteur
	public static String auteur_add(auteur obj) {
		return "INSERT INTO auteur(nom,date_naissance) values('" + obj.getNom()
				+ "','" + obj.getDate_naissance() + "');";
	}

	public static String auteur_select() {
		return "SELECT * FROM auteur";
	}

	public static String auteurs_select(int id) {
		return "SELECT auteur.nom FROM auteur INNER JOIN livre_auteur WHERE auteur.id = livre_auteur.id_auteur and livre_auteur.id_livre = "
				+ id;
	}

	public static String auteur_select(String sql) {
		return "SELECT * FROM auteur where " + sql;
	}

	public static String auteur_select(int id) {
		return "SELECT * FROM auteur where id='" + id + "'";
	}

	public static String auteur_select(String cle, String val) {
		return "SELECT * FROM auteur where " + cle + "='" + val + "'";
	}

	public static String auteur_find(String cle, String val) {
		return "SELECT * FROM auteur where " + cle + " LIKE '%" + val
				+ "%' ORDER BY " + cle + ";";
	}

	public static String auteur_update(auteur obj) {
		return "UPDATE auteur SET nom='" + obj.getNom() + "', date_naissance='"
				+ obj.getDate_naissance() + "' WHERE id='" + obj.getId() + "';";
	}

	public static String auteur_delete(int id) {
		return "DELETE FROM auteur WHERE id='" + id + "';";
	}

	// livre
	public static String livre_add(livre obj) {
		int disponible = obj.getDisponible() ? 1 : 0;
		return "INSERT INTO livre(titre,prix,nbr_exemplaire,date_edition,disponible) values('"
				+ obj.getTitre()
				+ "',"
				+ obj.getPrix()
				+ ","
				+ obj.getNbre_exp()
				+ ",'"
				+ obj.getDate_edition()
				+ "',"
				+ disponible + ");";
	}

	public static String livre_max() {
		return "SELECT MAX(id) as id FROM livre;";
	}

	public static String livre_select() {
		return "SELECT livre.* FROM livre "
				+ "INNER JOIN livre_auteur ON livre_auteur .id_livre= livre.id "
				+ "INNER JOIN auteur ON auteur.id = livre_auteur .id_auteur group by livre.id";
	}

	public static String livre_select_user(String sql) {
		return "SELECT livre.* FROM livre "
				+ "INNER JOIN livre_auteur ON livre_auteur .id_livre= livre.id "
				+ "INNER JOIN auteur ON auteur.id = livre_auteur .id_auteur and livre.disponible=1 and livre.nbr_exemplaire > 0 and  "
				+ sql + " group by livre.id";
	}

	public static String livre_select(String sql) {
		return "SELECT livre.* FROM livre "
				+ "INNER JOIN livre_auteur ON livre_auteur .id_livre= livre.id "
				+ "INNER JOIN auteur ON auteur.id = livre_auteur .id_auteur and  "
				+ sql + " group by livre.id";
	}

	public static String livre_select(int id) {
		return "SELECT livre.* FROM livre "
				+ "INNER JOIN livre_auteur ON livre_auteur .id_livre= livre.id "
				+ "INNER JOIN auteur ON auteur.id = livre_auteur .id_auteur and  livre.id = "
				+ id;
	}

	public static String livre_select(String cle, String val) {
		return "SELECT livre.* livre "
				+ "INNER JOIN livre_auteur ON livre_auteur .id_livre= livre.id "
				+ "INNER JOIN auteur ON auteur.id = livre_auteur .id_auteur and  "
				+ cle + "='" + val + "' group by livre.id";
	}

	public static String livre_update(livre obj) {
		int disponible = obj.getDisponible() ? 1 : 0;
		return "UPDATE livre SET titre='" + obj.getTitre() + "', prix='"
				+ obj.getPrix() + "', nbr_exemplaire='" + obj.getNbre_exp()
				+ "', date_edition='" + obj.getDate_edition()
				+ "', disponible='" + disponible + "' WHERE id='" + obj.getId()
				+ "';";
	}

	public static String livre_update(String cle, String val, int id) {
		return "UPDATE livre SET " + cle + "='" + val + "' WHERE id='" + id
				+ "';";
	}

	public static String livre_nbreDown(int id) {
		return "UPDATE livre SET nbr_exemplaire = nbr_exemplaire -1 WHERE id='"
				+ id + "';";
	}

	public static String livre_nbreUp(int id) {
		return "UPDATE livre SET nbr_exemplaire = nbr_exemplaire +1 WHERE id='"
				+ id + "';";
	}

	public static String livre_delete(int id) {
		return "DELETE FROM livre WHERE id='" + id + "';";
	}

	// compte
	public static String compte_add(compte obj) {
		return "INSERT INTO compte(username,password,role) values('"
				+ obj.getUsername() + "', '" + obj.getPassword() + "','"
				+ obj.getRole() + "');";
	}

	public static String compte_max() {
		return "SELECT MAX(id) as id FROM compte;";
	}

	public static String compte_select() {
		return "SELECT * FROM compte";
	}

	public static String compte_select(compte obj) {
		return "SELECT * FROM compte where username='" + obj.getUsername()
				+ "' and password='" + obj.getPassword() + "';";
	}

	public static String compte_select(int id) {
		return "SELECT * FROM compte where id='" + id + "'";
	}

	public static String compte_select(String cle, String val) {
		return "SELECT * FROM compte where " + cle + "='" + val + "'";
	}

	public static String compte_update(compte obj) {
		return "UPDATE compte SET username='" + obj.getUsername()
				+ "', password='" + obj.getPassword() + "', role='"
				+ obj.getRole() + "' where id='" + obj.getId() + "';";
	}

	public static String compte_delete(int id) {
		return "DELETE FROM compte WHERE id='" + id + "';";
	}

}
