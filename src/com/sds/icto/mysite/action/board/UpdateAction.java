package com.sds.icto.mysite.action.board;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sds.icto.mysite.dao.BoardDao;
import com.sds.icto.mysite.servlet.action.Action;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, ServletException,
			IOException {
		
		String saveDir = "files";
		String saveFullDir = request.getServletContext().getRealPath(saveDir);
		int maxFileSize = 5 * 1024 * 1024;
		String encoding = "utf-8";
		
		MultipartRequest mRequest = new MultipartRequest(request, saveFullDir, maxFileSize, encoding, new DefaultFileRenamePolicy());
		
		int no = Integer.parseInt(mRequest.getParameter("boardNo"));
		String title = mRequest.getParameter("title");
		String content = mRequest.getParameter("content");
		String fileName = mRequest.getFilesystemName("fileName");
		String prevFileName = mRequest.getParameter("prevFileName");
		if(fileName == null){
			fileName = prevFileName;
		}else{
			if(prevFileName != null) new File(saveFullDir+"/"+prevFileName).delete();
		}
		
		BoardDao dao = new BoardDao();
		dao.update(no, title, content, fileName);
		
		response.sendRedirect("board?a=detail&no="+no);
	}

}
