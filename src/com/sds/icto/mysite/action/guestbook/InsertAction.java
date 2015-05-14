package com.sds.icto.mysite.action.guestbook;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.icto.mysite.dao.GuestbookDao;
import com.sds.icto.mysite.servlet.action.Action;
import com.sds.icto.mysite.vo.GuestbookVo;

public class InsertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, ServletException,
			IOException {
		GuestbookDao dao = new GuestbookDao();
		dao.insert(new GuestbookVo(request.getParameter("name"), request.getParameter("pass"), request.getParameter("content")));
		response.sendRedirect("guestbook");
	}

}
