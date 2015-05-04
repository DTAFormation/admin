package com.dta.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import com.dta.entities.Article;

@ManagedBean(name="research")
public class ResearchController {

	private String msg;

	private String userName;
	private String userType;

	private String articleName;
	private String product;
	private int articleId;

	private List<Article> products;

	public ResearchController(){

		this("", "", "CLIENT", "", "", 0, null);

		List<Article> liste = new ArrayList<Article>();

		products = liste;

	}


	public ResearchController(String msg, String userName, String userType,
			String articleName, String product, int articleId,
			List<Article> products) {
		super();
		this.msg = msg;
		this.userName = userName;
		this.userType = userType;
		this.articleName = articleName;
		this.product = product;
		this.articleId = articleId;
		this.products = products;
	}


	public String getMsg(){
		return this.msg;
	}

	public void setMsg(String msg){
		this.msg = msg;
	}

	public List<Article> getProducts(){
		return this.products;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public void setProducts(List<Article> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "ResearchController [msg=" + msg + "]";
	}

	public void submitResearchArticle() {

		//requetes recherche
		if(this.articleId!=0){
			products = mockRequest(this.articleId);
			
		}else if(!this.articleName.equals("") && !this.product.equals("")){
			
		}else if(!this.articleName.equals("")){
			products = mockRequest(this.articleName);
			
		}else if(!this.product.equals("")){
			products = mockRequest(this.product);
			
		}
		
		System.out.println("\n\n --> recherche: ");
		System.out.println(this.toString());

	}

	public void submitResearchUser(){

	}

	public int getResultSize(){
		return products.size();
	}
	
	public void submitResearchAllArticle(){
		//getAll
		System.out.println("azll");
		products = mockRequest("all");
	}

	private List<Article> mockRequest(int id){

		List<Article> result = new ArrayList<Article>();
		Article art = new Article();
		art.setNom("article n°"+id);
		art.setPrix(500);
		art.setProduit(null);
		art.setStock(100);
		result.add(art);

		return result;
	}

	private List<Article> mockRequest(String name){

		List<Article> result = new ArrayList<Article>();

		for(int i=0; i<50; i++){
			Article art = new Article();
			art.setNom(name+" n°"+i);
			art.setPrix(500);
			art.setProduit(null);
			art.setStock(100);
			result.add(art);
		}

		return result;
	}

}
