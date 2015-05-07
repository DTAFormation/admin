package com.dta.validateur.addclient;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.dta.metier.AddClientEJB;

@ManagedBean
@RequestScoped
public class ExistenceEmailValidator implements Validator{

	private static final String EMAIL_EXISTE_DEJA = "Cette adresse email est deja utilisee";
	
	@EJB
	private AddClientEJB addclientEJB;
	
	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		String email = (String) arg2;
		if(addclientEJB.SearchExistenceEmail(email)){
		      throw new ValidatorException(
                      new FacesMessage( FacesMessage.SEVERITY_ERROR, EMAIL_EXISTE_DEJA, null ) );
		}
	}

	protected AddClientEJB getAddclientEJB() {
		return addclientEJB;
	}
	protected void setAddclientEJB(AddClientEJB addclientEJB) {
		this.addclientEJB = addclientEJB;
	}
}
