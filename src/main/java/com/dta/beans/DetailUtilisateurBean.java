package com.dta.beans;

import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.dta.entities.Utilisateur;
import com.dta.metier.ModifyUtilisateurEJB;
import com.dta.metier.SearchUtilisateurEJB;

@ManagedBean
@ViewScoped
public class DetailUtilisateurBean {

	private Utilisateur utilisateur;
	private int requestedId;
	private String typeUtil;

	@EJB
	private SearchUtilisateurEJB searchUtilisateur;

	@EJB
	private ModifyUtilisateurEJB ejbUtilisateur;

	@ManagedProperty(value="#{authentificationBean}")
	private AuthentificationBean authentificationBean;

	public void openDetailUtilisateur() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		requestedId = Integer.valueOf(params.get("id"));
		utilisateur = searchUtilisateur.findById(requestedId);
		typeUtil = utilisateur.getTypeUtil();
		RequestContext.getCurrentInstance().update("modifUtilisateurForm");
		RequestContext.getCurrentInstance().execute(
				"PF('dlgdetailutilisateur').show()");
	}

	public void saveDetailsUtilisateur() {
		if (isAuthorizedModif()) {
			ejbUtilisateur.update(utilisateur);
			closeDetailUtilisateur();
			RequestContext.getCurrentInstance().execute("redoLastSearch()");
			notifyModif();
			return;
		}
		notifyError("modifUtilisateurForm:modifMsgs", "Erreur",
				"Vous devez être administrateur pour effectuer cette action");
	}

	private boolean isAuthorizedModif() {
		if (!typeUtil.equals("c")) {
			return authentificationBean.getUtilisateur().getTypeUtil()
					.equals("a");
		}
		return true;
	}

	private void closeDetailUtilisateur() {
		RequestContext.getCurrentInstance().execute(
				"PF('dlgdetailutilisateur').hide()");
	}

	private void notifyModif() {
		FacesMessage msg = new FacesMessage("Modification enregistrée");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		RequestContext.getCurrentInstance().update("searchUserForm:msgs");
	}

	private void notifyError(String growlId, String errorSummary,
			String errorText) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				errorSummary, errorText);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		RequestContext.getCurrentInstance().update(growlId);
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

	public void setAuthentificationBean(AuthentificationBean authentificationBean) {
		this.authentificationBean = authentificationBean;
	}
	
}
