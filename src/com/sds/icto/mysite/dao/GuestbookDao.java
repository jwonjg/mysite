package com.sds.icto.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sds.icto.mysite.vo.GuestbookVo;

public class GuestbookDao {

	private Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "webdb", "webdb");
		return connection;
	}
	
	public void insert(GuestbookVo vo) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		String sql = "insert into guestbook values (guestbook_SEQ.nextval, ?, ?, ?, SYSDATE)";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, vo.getName());
		ps.setString(2, vo.getPassword());
		ps.setString(3, vo.getMessage());
		
		int result = ps.executeUpdate();
		if(result>0) System.out.println("저장 성공");
		
		if(ps != null) ps.close();
		if(conn != null) conn.close();
	}
	
	public List<GuestbookVo> fetchList() throws SQLException, ClassNotFoundException{
		ArrayList<GuestbookVo> list = new ArrayList<GuestbookVo>();
		
		Connection conn = getConnection();
		Statement st = conn.createStatement();
		String sql = "select * from guestbook";
		
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			int no = rs.getInt(1);
			String name = rs.getString(2);
			String password = rs.getString(3);
			String message = rs.getString(4);
			String regDate = rs.getString(5);
			
			GuestbookVo vo = new GuestbookVo(no, name, password, message, regDate);
			list.add(vo);
		}
		
		if(rs != null) rs.close();
		if(st != null) st.close();
		if(conn != null) conn.close();
		
		return list;
	}
	
	public boolean delete(int no, String password) throws ClassNotFoundException, SQLException{
		Connection conn = getConnection();
		PreparedStatement ps = conn.prepareStatement("delete from guestbook where NO=? and password=?");
		ps.setInt(1, no);
		ps.setString(2, password);
		return ps.executeUpdate() > 0;
	}
}
