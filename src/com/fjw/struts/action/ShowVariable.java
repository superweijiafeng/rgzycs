package com.fjw.struts.action;

import java.io.File;
import java.io.IOException;

import com.fjw.dao.SQLHelper;
import com.fjw.util.ZipUtils;
import com.sina.sae.util.SaeUserInfo;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowVariable extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8497930527826304983L;

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		
		SQLHelper sqlHelper=new SQLHelper();
		String sql="select * from admin";
		ArrayList<ArrayList<Object>> sqlResult;
		sqlResult = sqlHelper.executeQueryTmp(sql, null,true,true);
		out.println("Admin: "+sqlResult.size());
		out.println(sqlResult.get(0).get(0));
		out.println(this.getServletContext().getRealPath("/file")+"<br/>");
		ZipUtils zip=new ZipUtils();
		zip.setInputFile(this.getServletContext().getRealPath("/file"));
		zip.generateFileList();
		List<String> al=zip.getFileList();
		for(int i=0;i<al.size();i++) {
			out.println(al.get(i)+"<br/>");
		}
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
