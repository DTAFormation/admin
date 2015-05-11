package com.dta.validateur;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.dta.metier.AddArticleEJB;

@ManagedBean
@RequestScoped
public class ArticleValidator implements Validator {
	
	private static final String ARTICLE_EXISTANT = "Ce nom d article est deja pris";
	
	@EJB
	private AddArticleEJB ejb;
	
	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		String artnom = (String) arg2;
		if(ejb.isArticleNameExists(artnom)){
		      throw new ValidatorException(
                      new FacesMessage(FacesMessage.SEVERITY_ERROR, ARTICLE_EXISTANT, null));
		}
	}
}