package com.dta.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
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

	private static Utilisateur utilisateur;

	@EJB
	private AddUtilisateurEJB addUtilisateurEJB;

	private HttpSession session;

	@PostConstruct
	public void init() {
		if (utilisateur == null) {
			utilisateur = new Utilisateur();
			utilisateur.setAdresses(new ArrayList<Adresse>());
		}
	}

	public static void saveAdresse(Adresse adresse) {
		List<Adresse> adresses = utilisateur.getAdresses();
		adresses.add(adresse);
		utilisateur.setAdresses(adresses);
		RequestContext.getCurrentInstance().execute("PF('dlgadress').hide()");
		updateListeAdresses();
		RequestContext.getCurrentInstance().update("adresseForm");
		notifySaveAdresse();
	}

	private static void notifySaveAdresse() {
		FacesMessage msg = new FacesMessage("Adresse enregistree");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		updateMessages();
	}

	private static void updateListeAdresses() {
		RequestContext.getCurrentInstance().update("clientForm:listeAdresses");
	}

	private static void updateMessages() {
		RequestContext.getCurrentInstance().update("clientForm:msgs");
	}

	public void cleanAdresses() {
		utilisateur.setAdresses(new ArrayList<Adresse>());
		updateListeAdresses();
	}

	public void save() {
		if ("a".equals(utilisateur.getTypeUtil())) {
			session = (HttpSession) FacesContext.getCurrentInstance()
					.getExternalContext().getSession(false);
			AuthentificationBean auth = (AuthentificationBean) session
					.getAttribute("authentificationBean");
			if (!"a".equals(auth.getUtilisateur().getTypeUtil())) {
				RequestContext.getCurrentInstance().execute(
						"PF('dlgErreurAuth').show()");
				notifyAuthError();
				return;
			}
		}
		List<Adresse> adresses = utilisateur.getAdresses();
		if (adresses != null) {
			for (Adresse a : adresses) {
				a.setUtilisateur(utilisateur);
			}
			utilisateur.setAdresses(adresses);
		}
		utilisateur.setActive(true);
		addUtilisateurEJB.save(utilisateur);
		RequestContext.getCurrentInstance().execute(
				"PF('dlgClientAjoute').show()");
		notifyAddUser();
		reset();
	}

	private void notifyAuthError() {
		FacesMessage msg = new FacesMessage(
				"Vous devez posseder les droits administrateur pour creer un administrateur");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		updateMessages();
	}

	private void notifyAddUser() {
		FacesMessage msg = new FacesMessage("Utilisateur enregistre");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		updateMessages();
	}

	private void reset() {
		utilisateur = new Utilisateur();
		updateClientForm();
	}

	private void updateClientForm() {
		RequestContext.getCurrentInstance().update("clientForm");
	}

	public Utilisateur getUtilisateur() {
		if (utilisateur == null) {
			utilisateur = new Utilisateur();
		}
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		AjoutUtilisateurBean.utilisateur = utilisateur;
	}

}
