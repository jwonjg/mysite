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

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, ServletException,
			IOException {
		BoardDao dao = new BoardDao();
		List<BoardVo> fetchList = dao.fetchList();
		request.setAttribute("list", fetchList);
		
		request.getRequestDispatcher("views/board/list.jsp").forward(request, response);
	}

}
