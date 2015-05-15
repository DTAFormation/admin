package com.dta.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GestionSessionFilter implements Filter {
	private List<String> urlList;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String urls = filterConfig.getInitParameter("avoid-urls");
		StringTokenizer token = new StringTokenizer(urls, ",");
		urlList = new ArrayList<String>();

		while (token.hasMoreTokens()) {
			urlList.add(token.nextToken());
		}
	}

	@Override
	public void destroy() {
	    urlList.clear();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		AuthentificationBean authentificationBean = (AuthentificationBean) httpRequest.getSession().getAttribute("authentificationBean");
		String url = httpRequest.getServletPath();
		boolean allowedRequest = false;

		if(urlList.contains(url) || isResourceUrl(url)) {
			allowedRequest = true;
		}

		if (!allowedRequest && (authentificationBean == null || !authentificationBean.isLoggedIn())) {
				httpResponse.sendRedirect(httpRequest.getContextPath() + "/authentification.xhtml");
		}

		chain.doFilter(request, response);
	}

	private boolean isResourceUrl(String url) {
		return url.startsWith("/javax.faces.resource/");
	}

}