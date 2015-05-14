package com.sds.icto.mysite.action.board;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.icto.mysite.dao.BoardDao;
import com.sds.icto.mysite.servlet.action.Action;
import com.sds.icto.mysite.vo.BoardVo;

public class SearchAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, ServletException,
			IOException {
		String option = request.getParameter("searchOption");
		String keyword = request.getParameter("keyword");

		BoardDao dao = new BoardDao();
		List<BoardVo> list = dao.selectList(option, keyword);
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("views/board/list.jsp").forward(request, response);
	}

}
