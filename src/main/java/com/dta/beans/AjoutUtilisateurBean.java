package com.dta.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import com.dta.entities.Adresse;
import com.dta.entities.Utilisateur;
import com.dta.metier.AddUtilisateurEJB;

@ManagedBean
public class AjoutUtilisateurBean {

	Utilisateur utilisateur;

	private String email;
	private int fax;
	private String login;
	private String nom;
	private String password;
	private String prenom;
	private int telephone;
	private String titre;
	private String typeUtil;
	private static List<Adresse> adresses;

	@EJB
	private AddUtilisateurEJB addUtilisateurEJB;

	private HttpSession session;

	public static void saveAdresse(Adresse adresse) {
		if (adresses == null) {
			adresses = new ArrayList<Adresse>();
		}
		adresses.add(adresse);
		RequestContext.getCurrentInstance().execute("PF('dlgadress').hide()");
		updateListeAdresses();
		RequestContext.getCurrentInstance().update("adresseForm");
		notifySaveAdresse();
	}

	private static void notifySaveAdresse() {
		FacesMessage msg = new FacesMessage("Adresse enregistrée");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		RequestContext.getCurrentInstance().update("clientForm:msgs");
	}

	private static void updateListeAdresses() {
		RequestContext.getCurrentInstance().update("clientForm:listeAdresses");
	}

	public void cleanAdresses() {
		if (adresses != null) {
			adresses.clear();
		}
		updateListeAdresses();
	}

	public void save() {
		if (this.typeUtil.equals("a")) {
			session = (HttpSession) FacesContext.getCurrentInstance()
					.getExternalContext().getSession(false);
			AuthentificationBean auth = (AuthentificationBean) session
					.getAttribute("authentificationBean");
			if (!auth.getUtilisateur().getTypeUtil().equals("a")) {
//				RequestContext.getCurrentInstance().execute(
//						"PF('dlgErreurAuth').show()");
				notifyAuthError();
				return;
			}
		}
		utilisateur = new Utilisateur();
		utilisateur.setEmail(email);
		utilisateur.setFax(fax);
		utilisateur.setLogin(login);
		utilisateur.setNom(nom);
		utilisateur.setPassword(password);
		utilisateur.setPrenom(prenom);
		utilisateur.setTelephone(telephone);
		utilisateur.setTitre(titre);
		utilisateur.setTypeUtil(typeUtil);
		if (adresses != null) {
			utilisateur.setAdresses(adresses);
			for (Adresse a : utilisateur.getAdresses())
				a.setUtilisateur(utilisateur);
		}
		addUtilisateurEJB.save(utilisateur);
//		RequestContext.getCurrentInstance().execute(
//				"PF('dlgClientAjoute').show()");
		notifyAddUser();
		reset();
	}

	private void notifyAuthError() {
		FacesMessage msg = new FacesMessage(
				"Vous devez posséder les droits administrateur pour créer un administrateur");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		RequestContext.getCurrentInstance().update("clientForm:msgs");
	}

	private void notifyAddUser() {
		FacesMessage msg = new FacesMessage("Utilisateur enregistré");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		RequestContext.getCurrentInstance().update("clientForm:msgs");
	}

	private void reset() {
		email = "";
		fax = 0;
		login = "";
		nom = "";
		password = new String();
		prenom = "";
		telephone = 0;
		titre = "";
		typeUtil = "";
		adresses = new ArrayList<Adresse>();
		updateClientForm();
	}
	
	private void updateClientForm() {
		RequestContext.getCurrentInstance().update("clientForm");
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getFax() {
		return fax;
	}

	public void setFax(int fax) {
		this.fax = fax;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getTelephone() {
		return telephone;
	}

	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getTypeUtil() {
		return typeUtil;
	}

	public void setTypeUtil(String typeUtil) {
		this.typeUtil = typeUtil;
	}

	public List<Adresse> getAdresses() {
		return adresses;
	}

	public void setAdresses(List<Adresse> adresses) {
		AjoutUtilisateurBean.adresses = adresses;
	}

	@Override
	public String toString() {
		return "ClientAddBean [email=" + email + ", fax=" + fax + ", login="
				+ login + ", nom=" + nom + ", password=" + password
				+ ", prenom=" + prenom + ", telephone=" + telephone
				+ ", titre=" + titre + ", typeUtil=" + typeUtil + "]";
	}

}
