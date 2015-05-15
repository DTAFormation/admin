package com.dta.beans;

import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.dta.entities.Article;
import com.dta.metier.ModifyArticleEJB;
import com.dta.metier.ModifyCatalogueEJB;
import com.dta.metier.ModifyProduitEJB;
import com.dta.metier.SearchArticleEJB;

@ManagedBean
@ViewScoped
public class DetailArticleBean {

	private Article article;

	private int requestedId;

	@EJB
	private ModifyArticleEJB ejbArticle;

	@EJB
	private ModifyProduitEJB ejbProduit;

	@EJB
	private ModifyCatalogueEJB ejbCatalogue;

	@EJB
	private SearchArticleEJB searchArticle;

	public void openDetailArticle() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		requestedId = Integer.valueOf(params.get("id"));
		article = searchArticle.findById(requestedId).get(0);
		RequestContext.getCurrentInstance().update("modifArticleForm");
		RequestContext.getCurrentInstance().execute(
				"PF('dlgdetailarticle').show()");
	}

	public void saveDetailsArticle() {
		ejbArticle.update(article);
		ejbProduit.update(article.getProduit());
		ejbCatalogue.update(article.getProduit().getCatalogue());
		closeDetailWidget();
		RequestContext.getCurrentInstance().execute("redoLastSearch()");
		notifyModif();
	}

	private void notifyModif() {
		FacesMessage msg = new FacesMessage("Modification enregistrée");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		RequestContext.getCurrentInstance()
				.update("criteresRechercheForm:msgs");
	}

	private void closeDetailWidget() {
		RequestContext.getCurrentInstance().execute(
				"PF('dlgdetailarticle').hide()");
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public int getRequestedId() {
		return requestedId;
	}

	public void setRequestedId(int requestedId) {
		this.requestedId = requestedId;
	}

}
