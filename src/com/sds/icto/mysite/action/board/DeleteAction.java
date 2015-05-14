package com.sds.icto.mysite.action.board;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.icto.mysite.dao.BoardDao;
import com.sds.icto.mysite.servlet.action.Action;
import com.sds.icto.mysite.vo.MemberVo;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, ServletException,
			IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		String fileName = request.getParameter("fileName");
		MemberVo authUser = (MemberVo)request.getSession().getAttribute("authUser");
		
		BoardDao dao = new BoardDao();
		dao.delete(no, authUser.getNo());
		

		String saveDir = "files";
		String saveFullDir = request.getServletContext().getRealPath(saveDir);
		if(fileName != null) new File(saveFullDir+"/"+fileName).delete();
		
		response.sendRedirect("board");
	}

}
