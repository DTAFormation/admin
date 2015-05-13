package com.dta.validateur;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.dta.metier.AddProduitEJB;

@ManagedBean
@RequestScoped
public class ProduitValidator implements Validator {

	private static final String PRODUIT_EXISTANT = "Ce nom de produit est deja pris";

	@EJB
	private AddProduitEJB addProduitEJB;

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		String prodnom = (String) arg2;
		if(addProduitEJB.isProduitNameExists(prodnom)){
			throw new ValidatorException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, PRODUIT_EXISTANT, null));
		}
	}
	
	public AddProduitEJB getAddProduitEJB() {
        return addProduitEJB;
    }
    public void setAddProduitEJB(AddProduitEJB addProduitEJB) {
        this.addProduitEJB = addProduitEJB;
    }
}