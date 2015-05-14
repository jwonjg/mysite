package com.sds.icto.mysite.action.board;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.icto.mysite.dao.BoardDao;
import com.sds.icto.mysite.servlet.action.Action;
import com.sds.icto.mysite.vo.BoardVo;

public class UpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, ServletException,
			IOException {
		BoardDao dao = new BoardDao();
		BoardVo board = dao.selectBoard(Integer.parseInt(request.getParameter("boardNo")));
		request.setAttribute("board", board);
		request.getRequestDispatcher("views/board/update_form.jsp").forward(request, response);
	}

}
