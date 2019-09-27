package kr.co.kic.dev1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.kic.dev1.dto.NoticeDto;
import kr.co.kic.dev1.util.ConnLocator;

public class NoticeDao { // singleton쓰고 ctrl+space+enter
	private static NoticeDao single;

	private NoticeDao() {

	}

	public static NoticeDao getInstance() {
		if (single == null) {
			single = new NoticeDao();

		}
		return single;
	}
	
	public boolean insert(NoticeDto n) {

		boolean isSuccess = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		int index = 1;

		try {
			con = ConnLocator.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("insert into notice(n_num, n_writer, n_title, n_content, n_regdate) ");
			sql.append("values (null, ?, ?, ?, now() ) ");

			pstmt = con.prepareStatement(sql.toString());
			// 바인딩 변수 세팅.
			pstmt.setString(index++, n.getWriter());
			pstmt.setString(index++, n.getTitle());
			pstmt.setString(index++, n.getContent());
			
			pstmt.executeUpdate();
			
			isSuccess=true;
			
		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e2) {
				// TODO: handle exception
			}
		}
		return isSuccess;
	}

	public boolean update(NoticeDto n) {

		boolean isSuccess = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		int index = 1;

		try {
			con = ConnLocator.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE notice ");
			sql.append("SET n_writer=?, n_title =?, n_content = ?,n_regdate=NOW() ");
			sql.append("WHERE n_num = ?  ");

			pstmt = con.prepareStatement(sql.toString());
			// 바인딩 변수 세팅.
			pstmt.setString(index++, n.getWriter());
			pstmt.setString(index++, n.getTitle());
			pstmt.setString(index++, n.getContent());
			pstmt.setInt(index++, n.getNum());
			
			pstmt.executeUpdate();
			
			isSuccess=true;
			
		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e2) {
				// TODO: handle exception
			}
		}
		return isSuccess;
	}

	public boolean delete(int num) {
		boolean isSuccess = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		int index = 1;

		try {
			con = ConnLocator.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("DELETE FROM notice ");
			sql.append("WHERE n_num = ? ");

			pstmt = con.prepareStatement(sql.toString());
			// 바인딩 변수 세팅.
			pstmt.setInt(index++, num);
			pstmt.executeUpdate();
			isSuccess=true;

		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e2) {
				// TODO: handle exception
			}
		}
		return isSuccess;

	}

	public NoticeDto select(int num) {
		NoticeDto obj = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int index = 1;
		try {
			con = ConnLocator.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT n_num,n_writer,n_title,n_content,DATE_FORMAT(n_regdate,'%Y/%m/%d') ");
			sql.append("FROM notice ");
			sql.append("WHERE n_num = ? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(index, num);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				index = 1; // 초기화(재설정)을 해줘야한다. binding변수하면서 써서 그냥 쓰게 되면 2로(++한 상태로) 받아지게 된다.
				obj = new NoticeDto();
				int _num = rs.getInt(index++);
				String writer = rs.getString(index++);
				String title = rs.getString(index++);
				String content = rs.getString(index++);
				String regdate = rs.getString(index++);
				obj = new NoticeDto(_num, writer, title, content, regdate);
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return obj;
	}
	public ArrayList<NoticeDto> select(int start, int length){
		
		ArrayList<NoticeDto> list = new ArrayList<NoticeDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int index = 1;
		
		try {
			con = ConnLocator.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT  n_num, n_writer,n_title,DATE_FORMAT(n_regdate,'%Y/%m/%d') ");
			sql.append("FROM notice ");
			sql.append("ORDER BY n_num DESC ");
			sql.append("LIMIT ?,? ");  
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(index++,start);
			pstmt.setInt(index++,length);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				index =1;
				int num = rs.getInt(index++);
				String writer = rs.getString(index++);
				String title = rs.getString(index++);
				String regdate = rs.getString(index++);
				list.add(new NoticeDto(num,writer,title,regdate));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			} catch (SQLException e2) {
				// TODO: handle exception
			}
		}
		return list;
	}
}
