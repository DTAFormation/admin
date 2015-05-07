package com.dta.beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import com.dta.entities.Utilisateur;
import com.dta.metier.DeleteUtilisateur;
import com.dta.metier.SearchUtilisateur;


@ManagedBean
@RequestScoped
public class UtilisateurBean {

	@EJB
	private DeleteUtilisateur deleteUtilisateur;

	@EJB
	private SearchUtilisateur searchUtilisateur;


	public Utilisateur GetUtilisateurById(int utilisateurId) {
		return searchUtilisateur.findById(utilisateurId);
	}

	public void delete(int utilisateurId) {

		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		AuthentificationBean auth = (AuthentificationBean) session.getAttribute("autehentificationBean");

		if("a".equals(searchUtilisateur.findById(utilisateurId).getTypeUtil()) && (!"a".equals(auth.getUtilisateur().getTypeUtil()))){
			RequestContext.getCurrentInstance().execute("PF('dlgErreurAuth').show()");
			return;
		}
		if(auth.getUtilisateur().getUtilisateurId() == utilisateurId){
			RequestContext.getCurrentInstance().execute("PF('dlgErreurAuth2').show()");
			return;
		}
		deleteUtilisateur.delete(utilisateurId);
		RequestContext.getCurrentInstance().execute("PF('dlgSucceed').show()");
    }
	
}
