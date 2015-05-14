package com.sds.icto.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sds.icto.mysite.vo.BoardVo;
import com.sds.icto.mysite.vo.CommentVo;

public class CommentDao {

	private Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "webdb", "webdb");
		return connection;
	}
	
	public void insert(CommentVo vo) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();

		String sql = "select max(order_no) from board_comment where board_no = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, vo.getBoardNo());
		ResultSet rs = ps.executeQuery();
		rs.next();
		int orderNo = rs.getInt(1);
		
		sql = "insert into board_comment values (BOARD_COMMENT_SEQ.nextval, ?, ?, SYSDATE, ?, ?)";
		ps = conn.prepareStatement(sql);
		
		ps.setInt(1, vo.getUserNo());
		ps.setString(2, vo.getContent());
		ps.setInt(3, vo.getBoardNo());
		ps.setInt(4, ++orderNo);
		
		int result = ps.executeUpdate();
		if(result>0) System.out.println("저장 성공");
		
		if(rs != null) rs.close();
		if(ps != null) ps.close();
		if(conn != null) conn.close();
	}
	
	public List<CommentVo> commentList(int boardNo) throws SQLException, ClassNotFoundException{
		ArrayList<CommentVo> list = new ArrayList<CommentVo>();
		
		Connection conn = getConnection();
		String sql = "select c.no, c.user_no, c.content, c.reg_date, c.order_no, m.name from board_comment c, member m where c.user_no = m.no and c.board_no = ? order by order_no";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, boardNo);
		
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			int no = rs.getInt(1);
			int userNo = rs.getInt(2);
			String content = rs.getString(3);
			String regDate = rs.getString(4);
			int orderNo = rs.getInt(5);
			String userName = rs.getString(6);
			
			CommentVo vo = new CommentVo(no, userNo, content, regDate, boardNo, orderNo, userName);
			list.add(vo);
		}
		
		if(rs != null) rs.close();
		if(ps != null) ps.close();
		if(conn != null) conn.close();
		
		return list;
	}

	public boolean delete(int no, int userNo) throws ClassNotFoundException, SQLException{
		Connection conn = getConnection();
		PreparedStatement ps = conn.prepareStatement("delete from board_comment where no=? and user_no=?");
		ps.setInt(1, no);
		ps.setInt(2, userNo);

		boolean result = ps.executeUpdate() > 0;
		
		if(ps != null) ps.close();
		if(conn != null) conn.close();
		
		return result;
	}

}
