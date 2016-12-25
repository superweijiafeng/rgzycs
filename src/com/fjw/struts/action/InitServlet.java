package com.fjw.struts.action;

import java.io.IOException;
import com.sina.sae.util.SaeUserInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fjw.domain.Nav;
import com.fjw.service.NavService;

public class InitServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5117223856020082134L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		this.init();
		response.sendRedirect(this.getServletContext().getAttribute("appname")+"/");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

	@Override
	public void init() throws ServletException {
		Nav[] navs=new Nav[5];
		for(int i=0;i<5;i++){
			navs[i]=NavService.getNav(i+1);
		}
		this.getServletContext().setAttribute("navs", navs);
		
		if("127.0.0.1".equals(SaeUserInfo.getMcIp())) {
			this.getServletContext().setAttribute("appname", "/rgzycs");
		} else {
			this.getServletContext().setAttribute("appname", "");
		}
	}
}
