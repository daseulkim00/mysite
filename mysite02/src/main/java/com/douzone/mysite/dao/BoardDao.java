package com.douzone.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.mysite.vo.BoardDto;
import com.douzone.mysite.vo.BoardVo;

public class BoardDao {

		// 연결
	private Connection getConnection() throws SQLException {

		Connection conn = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3306/webdb?characterEncoding=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}

		return conn;
	}

		// select
	public List<BoardDto> findAll() {
		List<BoardDto> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String url = "select a.no, a.title, a.contents, a.hit, a.reg_date as regDate, a.group_no as groupNo, a.order_no as orderNo, a.depth, b.name as userName"
					+ " from board a ,user b" + " where a.user_no = b";

			pstmt = conn.prepareStatement(url);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);
				Long hit = rs.getLong(4);
				String regDate = rs.getString(5);
				Long groupNo = rs.getLong(6);
				Long orderNo = rs.getLong(7);
				Long depth = rs.getLong(8);
				String userName = rs.getString(9);

				BoardDto dto = new BoardDto();

				dto.setNo(no);
				dto.setTitle(title);
				dto.setContents(contents);
				dto.setHit(hit);
				dto.setRegDate(regDate);
				dto.setGroupNo(groupNo);
				dto.setOrderNo(orderNo);
				dto.setDepth(depth);
				dto.setUserName(userName);

				list.add(dto);

			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return list;
	}

		// insert

	public void insert(BoardVo vo) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "insert into board values (null, ?, ? , 0,  now(), (SELECT IFNULL(MAX(group_no) + 1, 1) FROM board b), 0, 0, ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getHit());
			pstmt.setString(4, vo.getRegDate());
			pstmt.setLong(5, vo.getGroupNo());
			pstmt.setLong(6, vo.getOrderNo());
			pstmt.setLong(7, vo.getDepth());
			pstmt.setLong(8, vo.getUserNo());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("erorr:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
		//delete
	
	public boolean delete(BoardVo vo) {
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "delete "
					   + " from board"
					   + " where no = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, vo.getNo());
			
			int count = pstmt.executeUpdate();
			result = count == 1;
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	//update
	
	
	

}