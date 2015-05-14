package com.sds.icto.mysite.action.main;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.icto.mysite.servlet.action.Action;

public class IndexAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, ServletException,
			IOException {
//		response.sendRedirect("views/main/index.jsp"); 주소노출
		request.getRequestDispatcher("views/main/index.jsp").forward(request, response);
	}

}
