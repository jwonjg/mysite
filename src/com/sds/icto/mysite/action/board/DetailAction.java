package com.sds.icto.mysite.action.board;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.icto.mysite.dao.BoardDao;
import com.sds.icto.mysite.dao.CommentDao;
import com.sds.icto.mysite.servlet.action.Action;
import com.sds.icto.mysite.vo.BoardVo;
import com.sds.icto.mysite.vo.CommentVo;

public class DetailAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, ServletException,
			IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		BoardDao dao = new BoardDao();
		BoardVo board = dao.selectBoard(no);
		
		dao.updateClicks(no);

		CommentDao cdao = new CommentDao();
		List<CommentVo> commentList = cdao.commentList(no);
		
		request.setAttribute("board", board);
		request.setAttribute("commentList", commentList);
		request.getRequestDispatcher("views/board/detail.jsp").forward(request, response);
	}

}
