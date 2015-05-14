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

public class BoardDao {

	private Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "webdb", "webdb");
		return connection;
	}
	
	public void insert(BoardVo vo) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		String sql = "insert into board values (BOARD_SEQ.nextval, ?, ?, ?, ?, 0, SYSDATE)";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, vo.getUserNo());
		ps.setString(2, vo.getTitle());
		ps.setString(3, vo.getContent());
		ps.setString(4, vo.getFileName());
		
		int result = ps.executeUpdate();
		if(result>0) System.out.println("저장 성공");
		
		if(ps != null) ps.close();
		if(conn != null) conn.close();
	}
	
	public List<BoardVo> fetchList() throws SQLException, ClassNotFoundException{
		ArrayList<BoardVo> list = new ArrayList<BoardVo>();
		
		Connection conn = getConnection();
		Statement st = conn.createStatement();
		String sql = "select b.no, b.user_no, b.title, b.content, b.file_name, b.clicks, b.reg_date, m.name from board b, member m where b.user_no = m.no";
		
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			int no = rs.getInt(1);
			int userNo = rs.getInt(2);
			String title = rs.getString(3);
			String content = rs.getString(4);
			String fileName = rs.getString(5);
			int clicks = rs.getInt(6);
			String regDate = rs.getString(7);
			String userName = rs.getString(8);
			
			BoardVo vo = new BoardVo(no, userNo, title, content, fileName, clicks, regDate, userName);
			list.add(vo);
		}
		
		if(rs != null) rs.close();
		if(st != null) st.close();
		if(conn != null) conn.close();
		
		return list;
	}

	public BoardVo selectBoard(int selectNo) throws SQLException, ClassNotFoundException{
		Connection conn = getConnection();
		String sql = "select b.no, b.user_no, b.title, b.content, b.file_name, b.clicks, b.reg_date, m.name from board b, member m where b.user_no = m.no and b.no = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, selectNo);
		
		BoardVo vo = null;		
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			int no = rs.getInt(1);
			int userNo = rs.getInt(2);
			String title = rs.getString(3);
			String content = rs.getString(4);
			String fileName = rs.getString(5);
			int clicks = rs.getInt(6);
			String regDate = rs.getString(7);
			String userName = rs.getString(8);
			
			vo = new BoardVo(no, userNo, title, content, fileName, clicks, regDate, userName);
		}
		
		if(rs != null) rs.close();
		if(ps != null) ps.close();
		if(conn != null) conn.close();
		
		return vo;
	}
	
	public boolean delete(int no, int userNo) throws ClassNotFoundException, SQLException{
		Connection conn = getConnection();
		PreparedStatement ps = conn.prepareStatement("delete from board where no=? and user_no=?");
		ps.setInt(1, no);
		ps.setInt(2, userNo);

		boolean result = ps.executeUpdate() > 0;
		
		if(ps != null) ps.close();
		if(conn != null) conn.close();
		
		return result;
	}

	public boolean update(int no, String title, String content, String fileName) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		PreparedStatement ps = conn.prepareStatement("update board set title = ?, content = ?, file_name = ?, reg_date = sysdate where no = ?");
		ps.setString(1, title);
		ps.setString(2, content);
		ps.setString(3, fileName);
		ps.setInt(4, no);

		boolean result = ps.executeUpdate() > 0;
		
		if(ps != null) ps.close();
		if(conn != null) conn.close();
		
		return result;
	}

	public void updateClicks(int no) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		PreparedStatement ps = conn.prepareStatement("update board set clicks = (select clicks+1 from board where no = ?) where no = ?");
		ps.setInt(1, no);
		ps.setInt(2, no);
	
		ps.executeUpdate();
		
		if(ps != null) ps.close();
		if(conn != null) conn.close();
	}

	public List<BoardVo> selectList(String option, String keyword) throws ClassNotFoundException, SQLException {
		ArrayList<BoardVo> list = new ArrayList<BoardVo>();
		
		Connection conn = getConnection();
		
		PreparedStatement ps = null;
		if("title".equals(option)) {
			String sql = "select b.no, b.user_no, b.title, b.content, b.file_name, b.clicks, b.reg_date, m.name from board b, member m where b.user_no = m.no and b.title like ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+keyword+"%");
		}else if("content".equals(option)) {
			String sql = "select b.no, b.user_no, b.title, b.content, b.file_name, b.clicks, b.reg_date, m.name from board b, member m where b.user_no = m.no and b.content like ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+keyword+"%");
		}else{
			String sql = "select b.no, b.user_no, b.title, b.content, b.file_name, b.clicks, b.reg_date, m.name from board b, member m where b.user_no = m.no and (b.title like ? or b.content like ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+keyword+"%");
			ps.setString(2, "%"+keyword+"%");
		}
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			int no = rs.getInt(1);
			int userNo = rs.getInt(2);
			String title = rs.getString(3);
			String content = rs.getString(4);
			String fileName = rs.getString(5);
			int clicks = rs.getInt(6);
			String regDate = rs.getString(7);
			String userName = rs.getString(8);
			
			BoardVo vo = new BoardVo(no, userNo, title, content, fileName, clicks, regDate, userName);
			list.add(vo);
		}
		
		if(rs != null) rs.close();
		if(ps != null) ps.close();
		if(conn != null) conn.close();
		
		return list;
	}
}
