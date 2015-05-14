package com.sds.icto.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sds.icto.mysite.vo.MemberVo;

public class MemberDao {

	private Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "webdb", "webdb");
		return connection;
	}
	
	public void insert(MemberVo vo) throws ClassNotFoundException, SQLException{
		Connection conn = getConnection();
		
		String sql = "insert into member values(member_no_seq.nextval, ?, ?, ?, ?)";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, vo.getName());
		ps.setString(2, vo.getEmail());
		ps.setString(3, vo.getPassword());
		ps.setString(4, vo.getGender());
		
		int result = ps.executeUpdate();
		if(result>0) System.out.println("저장 성공");
		
		if(ps != null) ps.close();
		if(conn != null) conn.close();
	}
	
	public MemberVo getMember(String email, String password) throws ClassNotFoundException, SQLException{
		MemberVo vo = null;
		Connection conn = getConnection();
		String sql = "select * from member where email=? and password=?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, email);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			int no = rs.getInt(1);
			String name = rs.getString(3);
			String gender = rs.getString(5);
			
			vo = new MemberVo(no, name, email, password, gender);
		}
		
		if(rs != null) rs.close();
		if(ps != null) ps.close();
		if(conn != null) conn.close();
		
		return vo;
	}
	
	public boolean updateMember(MemberVo vo) throws ClassNotFoundException, SQLException{
		Connection conn = getConnection();
		
		String sql = "update member set email=?, name=?, password=?, gender=? where no=?";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, vo.getEmail());
		ps.setString(2, vo.getName());
		ps.setString(3, vo.getPassword());
		ps.setString(4, vo.getGender());
		ps.setInt(5, vo.getNo());
		
		boolean result = ps.executeUpdate() > 0;
		
		if(ps != null) ps.close();
		if(conn != null) conn.close();
		
		return result;
	}
}
