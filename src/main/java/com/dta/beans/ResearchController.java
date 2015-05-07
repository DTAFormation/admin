package com.dta.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.dta.entities.Article;
import com.dta.entities.Utilisateur;
import com.dta.metier.SearchArticle;
import com.dta.metier.SearchProduit;
import com.dta.metier.SearchUtilisateur;

@ManagedBean(name="research")
public class ResearchController {

	//user fields
	private String userName;
	private String userFirstName;
	private String userMail;
	private String userLogin;
	private String userType;

	//article fields
	private String articleName;
	private String articleProduct;
	private String articleId;
	private String articlePrice;
	private String articleStock;
	private String articleCatalogue;
	
	@EJB
	private SearchArticle searchArticle;
	
	@EJB
	private SearchProduit searchProduit;
	
	@EJB
	private SearchUtilisateur searchUtilisateur;

	// research results
	private List<Article> products;
	private List<Utilisateur> users;
	
	


	/*
	 * 
	 * Constructors
	 * 
	 */

	public ResearchController(){
		this("", "", "", "", "", "", "", "", "", "", "", null, null);		
		products = new ArrayList<Article>();
		users = new ArrayList<Utilisateur>();
	}

	public ResearchController(String userName, String userFirstName,
			String userMail, String userLogin, String userType,
			String articleName, String articleProduct, String articleId,
			String articlePrice, String articleStock, String articleCatalogue,
			List<Article> products, List<Utilisateur> users) {
		super();
		this.userName = userName;
		this.userFirstName = userFirstName;
		this.userMail = userMail;
		this.userLogin = userLogin;
		this.userType = userType;
		this.articleName = articleName;
		this.articleProduct = articleProduct;
		this.articleId = articleId;
		this.articlePrice = articlePrice;
		this.articleStock = articleStock;
		this.articleCatalogue = articleCatalogue;
		this.products = products;
		this.users = users;
	}

	
	/*
	 *  Methods research ARTICLE
	 */


	
	public void submitResearchArticle() {
		
		//priority to research by id
		if(!this.articleId.equals("")){
			int articleId = Integer.parseInt(this.articleId);
			products = searchArticle.findById(articleId);
			
		}else{
			// create a model article based on the search fields
			Article modelArticle = new Article();
			modelArticle.setNom( (this.articleName.equals("")) ? null : this.articleName);
			modelArticle.setPrix( (this.articlePrice.equals("")) ? -1 : Float.parseFloat(this.articlePrice));
			modelArticle.setStock( (this.articleStock.equals("")) ? -1 : Integer.parseInt(this.articleStock));

			products = searchArticle.findDetail(modelArticle, this.articleProduct, this.articleCatalogue);
		}
	}
	
	public void submitResearchAllArticle(){
		products = searchArticle.findAll();
	}
	
	public void deleteArticle(int id){
		System.out.println("deleteting article "+id);
	}
	
	
	/*
	 * Methods research USER
	 */	

	public void submitResearchUser(){
		Utilisateur modelUtilisateur = new Utilisateur();
		modelUtilisateur.setLogin(this.userLogin.equals("") ? null : this.userLogin);
		modelUtilisateur.setNom(this.userName.equals("") ? null : this.userName);
		modelUtilisateur.setPrenom(this.userFirstName.equals("") ? null : this.userFirstName);
		modelUtilisateur.setEmail(this.userMail.equals("") ? null : this.userMail);
		modelUtilisateur.setTypeUtil(this.userType.equals("") ? null : this.userType.substring(0, 1));

		users = searchUtilisateur.findDetail(modelUtilisateur);
	}
	
	public void submitResearchAllUser(){
		users = searchUtilisateur.findAll();
	}
	
	public void deleteUser(int id){
		System.out.println("deleteting user "+id);
	}
	

	@Override
	public String toString() {
		return "ResearchController [userName=" + userName + ", userFirstName="
				+ userFirstName + ", userMail=" + userMail + ", userLogin="
				+ userLogin + ", userType=" + userType + ", articleName="
				+ articleName + ", articleProduct=" + articleProduct
				+ ", articleId=" + articleId + ", articlePrice=" + articlePrice
				+ ", articleStock=" + articleStock + ", searchArticle="
				+ searchArticle + ", searchProduit=" + searchProduit
				+ ", products=" + products + "]";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getArticleName() {
		return articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}

	public String getArticleProduct() {
		return articleProduct;
	}

	public void setArticleProduct(String articleProduct) {
		this.articleProduct = articleProduct;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getArticlePrice() {
		return articlePrice;
	}

	public void setArticlePrice(String articlePrice) {
		this.articlePrice = articlePrice;
	}

	public String getArticleStock() {
		return articleStock;
	}

	public void setArticleStock(String articleStock) {
		this.articleStock = articleStock;
	}

	public String getArticleCatalogue() {
		return articleCatalogue;
	}

	public void setArticleCatalogue(String articleCatalogue) {
		this.articleCatalogue = articleCatalogue;
	}

	public SearchArticle getSearchArticle() {
		return searchArticle;
	}

	public void setSearchArticle(SearchArticle searchArticle) {
		this.searchArticle = searchArticle;
	}

	public SearchProduit getSearchProduit() {
		return searchProduit;
	}

	public void setSearchProduit(SearchProduit searchProduit) {
		this.searchProduit = searchProduit;
	}

	public SearchUtilisateur getSearchUtilisateur() {
		return searchUtilisateur;
	}

	public void setSearchUtilisateur(SearchUtilisateur searchUtilisateur) {
		this.searchUtilisateur = searchUtilisateur;
	}

	public List<Article> getProducts() {
		return products;
	}

	public void setProducts(List<Article> products) {
		this.products = products;
	}

	public List<Utilisateur> getUsers() {
		return users;
	}

	public void setUsers(List<Utilisateur> users) {
		this.users = users;
	}

}
