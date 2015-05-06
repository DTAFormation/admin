package com.dta.validateur.addclient;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.dta.metier.AddCatalogueEJB;

@ManagedBean
@RequestScoped
public class CatalogueValidator implements Validator {
	
	private static final String CATALOGUE_EXISTANT = "Ce nom de catalogue est déjà pris";
	
	@EJB
	private AddCatalogueEJB ejb;
	
	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		String catnom = (String) arg2;
		if(ejb.isCatalogueNameExists(catnom)){
		      throw new ValidatorException(
                      new FacesMessage(FacesMessage.SEVERITY_ERROR, CATALOGUE_EXISTANT, null));
		}
	}
}