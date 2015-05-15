package com.dta.beans;

import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.dta.entities.Utilisateur;
import com.dta.metier.ModifyUtilisateurEJB;
import com.dta.metier.SearchUtilisateurEJB;

@ManagedBean
@ViewScoped
public class DetailUtilisateurBean {

	public Utilisateur utilisateur;
	
	private int requestedId;
	
	@EJB
	private SearchUtilisateurEJB searchUtilisateur;
	
	@EJB
	private ModifyUtilisateurEJB ejbUtilisateur;
		
	public void openDetailUtilisateur() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		requestedId = Integer.valueOf(params.get("id"));
		utilisateur = searchUtilisateur.findById(requestedId);
		RequestContext.getCurrentInstance().update("modifUtilisateurForm");
		RequestContext.getCurrentInstance().execute(
				"PF('dlgdetailutilisateur').show()");
	}

	public void saveDetailsUtilisateur() {
		ejbUtilisateur.update(utilisateur);
		closeDetailUtilisateur();
		RequestContext.getCurrentInstance().execute("redoLastSearch()");
		notifyModif();
	}	
	
	private void closeDetailUtilisateur() {
		RequestContext.getCurrentInstance().execute(
				"PF('dlgdetailutilisateur').hide()");
	}	
	
	private void notifyModif() {
		FacesMessage msg = new FacesMessage("Modification enregistrée");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		RequestContext.getCurrentInstance()
				.update("searchUserForm:msgs");
	}	

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	public String getFullUserType() {
		return Util.getFullUserType(utilisateur.getTypeUtil());
	}
	
	public String getFullUserCivilite() {
		return Util.getFullUserCivilite(utilisateur.getTitre());
	}	
}
