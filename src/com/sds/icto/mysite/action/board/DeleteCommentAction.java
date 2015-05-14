package com.sds.icto.mysite.action.board;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.icto.mysite.dao.CommentDao;
import com.sds.icto.mysite.servlet.action.Action;
import com.sds.icto.mysite.vo.MemberVo;

public class DeleteCommentAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, ServletException,
			IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		MemberVo authUser = (MemberVo)request.getSession().getAttribute("authUser");
		
		CommentDao dao = new CommentDao();
		dao.delete(no, authUser.getNo());

		response.sendRedirect("board?a=detail&no="+boardNo);
	}

}
