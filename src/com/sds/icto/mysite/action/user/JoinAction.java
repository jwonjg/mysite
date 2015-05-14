package com.sds.icto.mysite.action.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.icto.mysite.dao.MemberDao;
import com.sds.icto.mysite.servlet.action.Action;
import com.sds.icto.mysite.vo.MemberVo;

public class JoinAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, ServletException,
			IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		
		MemberVo vo = new MemberVo(name, email, password, gender);
		
		MemberDao dao = new MemberDao();
		dao.insert(vo);
		
		response.sendRedirect("user?a=joinsuccess");
//		request.getRequestDispatcher("views/user/joinform.jsp").forward(request, response); 새로고침했을때 데이터베이스에 같은 정보가 입력됨
	}

}
