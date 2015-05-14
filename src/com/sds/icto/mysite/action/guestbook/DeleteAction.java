package com.sds.icto.mysite.action.guestbook;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.icto.mysite.dao.GuestbookDao;
import com.sds.icto.mysite.servlet.action.Action;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, ServletException,
			IOException {
		GuestbookDao dao = new GuestbookDao();
		boolean isSuccess = dao.delete(Integer.parseInt(request.getParameter("no")), request.getParameter("password"));
		if(isSuccess){
			response.sendRedirect("guestbook");
		}else{
			response.sendRedirect("guestbook");
		}
	}

}
