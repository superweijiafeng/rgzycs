package com.fjw.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckCode extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7365684594418049541L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setDateHeader("Expires", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("image/jpeg");
		
		BufferedImage bufferedImage=new BufferedImage(60, 20, BufferedImage.TYPE_INT_RGB);
		Graphics g=bufferedImage.getGraphics();
		for(int i=0;i<bufferedImage.getWidth();i+=2){
			for(int j=0;j<bufferedImage.getHeight();j+=2){
				int r=getRandomCode(191)+64;
				int gg=getRandomCode(191)+64;
				int b=getRandomCode(191)+64;
				g.setColor(new Color(r,gg,b));
				g.fillRect(i, j, 5, 5);
			}
		}
		String code=String.valueOf(getRandomCode(10000));
		request.getSession().setAttribute("checkCode", code);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Time-New-Roman",Font.BOLD,16));
		g.drawString(code, 3, 15);
		ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

	private int getRandomCode(int max) {
		return (int)(Math.random()*max);
	}

}
