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

	// select findall
	public List<BoardDto> findAll() {
		List<BoardDto> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String url = "select a.no, a.title, a.contents, a.hit, a.reg_date as regDate, a.group_no as groupNo, a.order_no as orderNo, a.depth, b.name as userName"
					+ " from board a ,user b" + " where a.user_no = b.no"
					+ "  order by group_no desc , order_no desc";

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

	// select 1개 게시글 클릭했을때 1개만 보이게 하기

	public BoardDto findByNo(Long no) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDto dto = null;

		try {
			conn = getConnection();

			String sql = "select a.no, a.title, a.contents, a.hit, a.reg_date as regDate, a.group_no as groupNo, a.order_no as orderNo, a.depth, b.name as userName"
					+ " from board a ,user b" + " where a.user_no = b.no and a.no=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Long bno = rs.getLong(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);

				dto = new BoardDto();

				dto.setNo(bno);
				dto.setTitle(title);
				dto.setContents(contents);

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
		return dto;
	}
	
	// select userNo
	public BoardVo findUserNo(Long no) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVo vo = null;
		try {
			conn = getConnection();

			String sql = "select user_no " + " from board " + " where no= ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Long uno = rs.getLong(1);

				vo = new BoardVo();

				vo.setNo(uno);
			}

		} catch (SQLException e) {
			System.out.println("userno select error:" + e);
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
		return vo;
	}

	
	// insert

	public void insert(BoardVo vo) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "insert into board values (null, ?, ? , 0,  now(), (SELECT IFNULL(MAX(group_no) + 1, 1) FROM board b), 0, 0, ?, 0)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getUserNo());

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
	

	// delete

	public boolean delete(BoardVo vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "delete " + " from board" + " where no = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, vo.getNo());

			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error:" + e);
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
		return result;
	}

	// update

	public boolean update(BoardVo vo) {
		boolean result = false;
		PreparedStatement pstmt = null;

		Connection conn = null;

		try {
			conn = getConnection();

			String sql = " update board " + " set title = ?, contents = ? " + " where no= ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getNo());

			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("BoardDao update error" + e);
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
		return result;
	}

	public void insertreply(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt,pstmt1,pstmt2 = null;
		ResultSet rs = null;
		System.out.println("리플라이 메서드 실행");
		try {
			conn = getConnection();
			
			String sql="select group_no, order_no, depth from board where no=?";   // 부모에게서 ? no 를 가진 group_no와 depth를 select 한다.
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1,vo.getNo());
			
			rs = pstmt.executeQuery();
			System.out.println("select완료");
			
			if(rs.next()) {
				
				// select에서 뽑아준 group_no, order_no와 depth를 이용하여 update,insert를 해준다.
				Long groupno = rs.getLong("group_no");
				Long orderNo = rs.getLong("order_no");
				Long depth = rs.getLong("depth");
				
				//update
				String sql1 = "update board set order_no=order_no+1 where group_no =? and order_no >= ?";
				pstmt1 = conn.prepareStatement(sql1);
				
				pstmt1.setLong(1, groupno);
				pstmt1.setLong(2, orderNo);
				
				pstmt1.executeUpdate();
				System.out.println("update 완료");
				
				//insert
				String sql2 = "insert into board values(null, ?, ? ,0 ,now(), ?, ? ,?, ?,0)";
				
				pstmt2 = conn.prepareStatement(sql2);
				
				pstmt2.setString(1, vo.getTitle());
				pstmt2.setString(2, vo.getContents());
				pstmt2.setLong(3, groupno);
				pstmt2.setLong(4, orderNo);
				pstmt2.setLong(5, depth +1 );
				pstmt2.setLong(6, vo.getUserNo());
				
				pstmt2.executeUpdate();
				System.out.println("insert 완료");
				
			}else {
				System.out.println("그룹넘버 없음!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	// hitupdate
	
	public boolean hitupdate(BoardVo vo) {
		boolean result = false;
		PreparedStatement pstmt = null;
		Connection conn = null;
				
			try {
				conn = getConnection();
				
				String sql = "update board set hit = hit+1 where no = ?";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setLong(1,vo.getNo());
				
				int count = pstmt.executeUpdate();
				result = count == 1;
				
			} catch (SQLException e) {
				System.out.println("hit update error" + e);
			}finally {
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
			return result;
			
			
	}

}
