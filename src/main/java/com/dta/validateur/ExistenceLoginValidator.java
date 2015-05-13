package com.dta.validateur;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.dta.metier.AddUtilisateurEJB;


@ManagedBean
@RequestScoped
public class ExistenceLoginValidator implements Validator{

	private static final String LOGIN_EXISTE_DEJA = "Ce login est deja utilise";
	
	@EJB
	private AddUtilisateurEJB addclientEJB;


	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		String login = (String) arg2;
		if(addclientEJB.searchExistenceLogin(login)){
		      throw new ValidatorException(
                      new FacesMessage( FacesMessage.SEVERITY_ERROR, LOGIN_EXISTE_DEJA, null ) );
		}
	}
	
	
	
	public AddUtilisateurEJB getAddclientEJB() {
		return addclientEJB;
	}
	public void setAddclientEJB(AddUtilisateurEJB addclientEJB) {
		this.addclientEJB = addclientEJB;
	}
}