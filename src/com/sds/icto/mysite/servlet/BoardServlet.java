package com.sds.icto.mysite.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.icto.mysite.action.board.ActionFactory;
import com.sds.icto.mysite.servlet.action.Action;

public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");

			String a = request.getParameter("a");
			ActionFactory af = ActionFactory.getInstance();
			Action action = af.getAction(a);
			action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
