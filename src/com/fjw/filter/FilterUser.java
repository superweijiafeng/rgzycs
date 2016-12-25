package com.fjw.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fjw.domain.User;

public class FilterUser extends HttpServlet implements Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6148832682758590800L;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest=(HttpServletRequest)request;
		HttpServletResponse httpResponse=(HttpServletResponse)response;
		User user=(User) httpRequest.getSession().getAttribute("user");
		String uri=httpRequest.getRequestURI();

		if(uri.startsWith(request.getServletContext().getAttribute("appname")+"/InitServlet") &&
				user==null){
			httpResponse.sendRedirect(request.getServletContext().getAttribute("appname")+"/");
			return;
		} else if(uri.startsWith(request.getServletContext().getAttribute("appname")+"/nav.do") &&
				"changePassword".equals(httpRequest.getParameter("page"))==true &&
				user==null) {
			httpResponse.sendRedirect(request.getServletContext().getAttribute("appname")+"/");
			return;
		} else {
			chain.doFilter(request,response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		;
	}

}
