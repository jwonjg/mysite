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

public class UserInfoAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, ServletException,
			IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		
		MemberVo vo = new MemberVo(no, name, email, password, gender);
		
		MemberDao dao = new MemberDao();
		boolean isUpdated = dao.updateMember(vo);
		
		if(isUpdated) {
			HttpSession session = request.getSession(true);
			session.setAttribute("authUser", vo);
		}
		
		response.sendRedirect("user?a=uinfosuccess");
	}

}
