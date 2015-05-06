package com.dta.validateur.addclient;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.dta.entities.Produit;
import com.dta.metier.AddProduitEJB;

@ManagedBean
@RequestScoped
public class ProduitValidator implements Validator {
	
	private static final String PRODUIT_EXISTANT = "Ce nom de produit est déjà pris";
	
	@EJB
	private AddProduitEJB ejb;
	
	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		String prodnom = (String) arg2;
		if(ejb.getEm().createNamedQuery("Produit.findByName", Produit.class).setParameter("name", prodnom).getResultList().size() != 0){
		      throw new ValidatorException(
                      new FacesMessage(FacesMessage.SEVERITY_ERROR, PRODUIT_EXISTANT, null));
		}
	}
}