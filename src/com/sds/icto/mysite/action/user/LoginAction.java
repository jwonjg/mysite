package com.sds.icto.mysite.action.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sds.icto.mysite.dao.MemberDao;
import com.sds.icto.mysite.servlet.action.Action;
import com.sds.icto.mysite.vo.MemberVo;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, ServletException,
			IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		MemberDao dao = new MemberDao();
		MemberVo authUser = dao.getMember(email, password);
		if(authUser == null) {
			response.sendRedirect("user?a=loginform&result=fail");
		}else{
			HttpSession session = request.getSession(true);
			session.setAttribute("authUser", authUser);
			response.sendRedirect("main");
		}
	}

}
