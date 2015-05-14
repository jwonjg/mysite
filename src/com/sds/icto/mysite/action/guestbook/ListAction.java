package com.sds.icto.mysite.action.guestbook;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.icto.mysite.dao.GuestbookDao;
import com.sds.icto.mysite.servlet.action.Action;
import com.sds.icto.mysite.vo.GuestbookVo;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, ServletException,
			IOException {
		GuestbookDao dao = new GuestbookDao();
		List<GuestbookVo> list = dao.fetchList();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/views/guestbook/list.jsp").forward(request, response);
	}

}
