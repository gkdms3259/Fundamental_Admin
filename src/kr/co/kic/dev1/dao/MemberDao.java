package kr.co.kic.dev1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.co.kic.dev1.dto.MemberDto;
import kr.co.kic.dev1.util.ConnLocator;

public class MemberDao {
	private static MemberDao single;
	private MemberDao() {}
		public static MemberDao getInstance() {
			if(single == null) {
				single = new MemberDao();
			}
			return single;
	}
		public boolean insert (MemberDto m) {
			boolean isSuccess = false;
			Connection con = null;
			PreparedStatement pstmt = null;
			int index = 1;
			
			try {
				con = ConnLocator.getConnection();
				StringBuffer sql = new StringBuffer();
				sql.append("INSERT INTO member(m_seq,m_id,m_email,m_name,m_pwd,m_phone,m_regdate) ");
				sql.append("VALUES (NULL,?, ?,?,PASSWORD(?),?,NOW()) ");
				
				pstmt = con.prepareStatement(sql.toString());
				//바인딩 변수 세팅.
				pstmt.setString(index++,m.getId() );
				pstmt.setString(index++,m.getEmail() );
				pstmt.setString(index++, m.getName());
				pstmt.setString(index++,m.getPwd() );
				pstmt.setString(index++,m.getPhone() );

				pstmt.executeUpdate();
				isSuccess = true;
				
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
		public boolean update(MemberDto m) {
			boolean isSuccess = false;
			Connection con = null;
			PreparedStatement pstmt = null;
			int index = 1;
			
			try {
				con = ConnLocator.getConnection();
				StringBuffer sql = new StringBuffer();
				sql.append("UPDATE member ");
				//sql.append("SET m_id=?,m_email=?,m_name=?,m_pwd=PASSWORD(?),m_phone=?,m_regdate = NOW() ");
				sql.append("SET m_id=?,m_email=?,m_name=?,m_phone=?,m_regdate = NOW() ");
				sql.append("WHERE m_seq = ? ");
				
				pstmt = con.prepareStatement(sql.toString());
				//바인딩 변수 세팅.
				pstmt.setString(index++,m.getId() );
				pstmt.setString(index++,m.getEmail() );
				pstmt.setString(index++,m.getName() );
				//pstmt.setString(index++,m.getPwd());
				pstmt.setString(index++,m.getPhone() );
				pstmt.setInt(index++,m.getSeq());
				
				pstmt.executeUpdate();
				isSuccess = true;
				
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
		public boolean delete(int seq) {
			boolean isSuccess = false;
			Connection con = null;
			PreparedStatement pstmt = null;
			int index = 1;
			
			try {
				con = ConnLocator.getConnection();
				StringBuffer sql = new StringBuffer();
				sql.append("DELETE FROM member ");
				sql.append("WHERE m_seq = ? ");
				
				pstmt = con.prepareStatement(sql.toString());
				//바인딩 변수 세팅.
				pstmt.setInt(index++, seq ); //m.getSeq()가 아니라 seq임.
				
				pstmt.executeUpdate();
				isSuccess = true;
				
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
		public MemberDto select(int seq) {
			MemberDto obj = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int index = 1;
			try {
				con = ConnLocator.getConnection();
				StringBuffer sql = new StringBuffer();
				sql.append("SELECT m_seq,m_id,m_email,m_name,m_phone,date_format(m_regdate,'%Y/%m/%d') ");
				sql.append("FROM member ");
				sql.append("WHERE m_seq = ? ");
				
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setInt(index, seq );
				
				rs=pstmt.executeQuery();
				if(rs.next()) {
					index = 1; //초기화(재설정)을 해줘야한다. binding변수하면서 써서 그냥 쓰게 되면 2로(++한 상태로) 받아지게 된다.
					
					seq = rs.getInt(index++);
					String id = rs.getString(index++);
					String email = rs.getString(index++);
					String name = rs.getString(index++);
					String phone = rs.getString(index++);
					String regdate = rs.getString(index++);
					
					obj = new MemberDto(seq,id,email,name,phone,regdate); //생성자가 없기때문에 만들어주면된다.(빨간색 눌러서)
				}
				
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				try {
					if(rs!=null)rs.close();
					if(pstmt!=null)pstmt.close();
					if(con!=null)con.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
			return obj;
		}
		public ArrayList<MemberDto> select(int start, int length){
			ArrayList<MemberDto> list = new ArrayList<MemberDto>();
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int index = 1;
			try {
				
				con = ConnLocator.getConnection();
				StringBuffer sql = new StringBuffer();
				
				sql.append("SELECT m_seq,m_id,m_email,m_name,m_phone,date_format(m_regdate,'%Y/%m/%d') ");
				sql.append("FROM member ");
				sql.append("ORDER BY m_seq DESC ");
				sql.append("LIMIT ?,? ");
				pstmt = con.prepareStatement(sql.toString());
				//바인딩 변수 세팅
				pstmt.setInt(index++,start );
				pstmt.setInt(index++,length );
				
				rs = pstmt.executeQuery();
				while(rs.next()) {
					index = 1;
					int seq = rs.getInt(index++);
					String id = rs.getString(index++);
					String email = rs.getString(index++);
					String name = rs.getString(index++);
					String phone = rs.getString(index++);
					String regdate = rs.getString(index++);
					list.add(new MemberDto(seq,id,email,name,phone,regdate));
				}
				
			} catch (SQLException e) {
				// TODO: handle exception
			}finally {
				try {
					if(rs!=null)rs.close();
					if(con!=null)con.close();
					if(pstmt!=null)pstmt.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
			
		
			return list;
		}
		public int getRows() {
			int count = 0;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int index = 1;
			try {
				con = ConnLocator.getConnection();
				StringBuffer sql = new StringBuffer();
				sql.append("SELECT COUNT(*) FROM member ");
				
				pstmt = con.prepareStatement(sql.toString());
				
				rs=pstmt.executeQuery();
				if(rs.next()) {
					index = 1; //초기화(재설정)을 해줘야한다. binding변수하면서 써서 그냥 쓰게 되면 2로(++한 상태로) 받아지게 된다.
					count = rs.getInt(index);
				}
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				try {
					if(rs!=null)rs.close();
					if(pstmt!=null)pstmt.close();
					if(con!=null)con.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
			return count;
		}
		public String selectJson(int start, int length){
			JSONObject jsonObj = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int index = 1;
			try {
				
				con = ConnLocator.getConnection();
				StringBuffer sql = new StringBuffer();
				
				sql.append("SELECT m_seq,m_id,m_email,m_name,m_phone,date_format(m_regdate,'%Y/%m/%d') ");
				sql.append("FROM member ");
				sql.append("ORDER BY m_seq DESC ");
				sql.append("LIMIT ?,? ");
				pstmt = con.prepareStatement(sql.toString());
				//바인딩 변수 세팅
				pstmt.setInt(index++,start );
				pstmt.setInt(index++,length );
				
				rs = pstmt.executeQuery();
				JSONObject item = null;
				while(rs.next()) {
					index = 1;
					item = new JSONObject();
					int seq = rs.getInt(index++);
					String id = rs.getString(index++);
					String email = rs.getString(index++);
					String name = rs.getString(index++);
					String phone = rs.getString(index++);
					String regdate = rs.getString(index++);
					item.put("seq",seq);
					item.put("id",id);
					item.put("email",email);
					item.put("name",name);
					item.put("phone",phone);
					item.put("regdate",regdate);
					jsonArray.add(item);
				}
				jsonObj.put("item",jsonArray);
			} catch (SQLException e) {
				// TODO: handle exception
			}finally {
				try {
					if(rs!=null)rs.close();
					if(con!=null)con.close();
					if(pstmt!=null)pstmt.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
			
		
			return jsonObj.toString();
		}
		
		public boolean isCheck(String id) {
			boolean isExisted = false;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int index = 1;
			try {
				con = ConnLocator.getConnection();
				StringBuffer sql = new StringBuffer();
				sql.append("select m_id from member ");
				sql.append("where m_id = ?" );
				sql.append(" ");
				
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(index++,id );
				
				rs=pstmt.executeQuery();
				if(rs.next()) {
					isExisted = true;
				}
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				try {
					if(rs!=null)rs.close();
					if(pstmt!=null)pstmt.close();
					if(con!=null)con.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
			return isExisted;
		}
		
		public boolean isCheck2(String email) {
		boolean isExisted = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int index = 1;
		try {
			con = ConnLocator.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select m_email from member  ");
			sql.append("where m_email = ? ");
			sql.append(" ");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(index++,email );
			
			rs=pstmt.executeQuery();
			if(rs.next()) {
				isExisted = true;
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return isExisted;
	}
		
		public MemberDto isMember(MemberDto m) {
			MemberDto obj = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int index = 1;
			try {
				con = ConnLocator.getConnection();
				StringBuffer sql = new StringBuffer();
				sql.append("SELECT m_seq,m_id,m_email,m_name,m_phone,date_format(m_regdate,'%Y/%m/%d') ");
				sql.append("FROM member ");
				sql.append("WHERE m_email = ? and m_pwd=password(?) ");
				
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(index++, m.getEmail() );
				pstmt.setString(index++, m.getPwd() );
				
				rs=pstmt.executeQuery();
				if(rs.next()) {
					index = 1; //초기화(재설정)을 해줘야한다. binding변수하면서 써서 그냥 쓰게 되면 2로(++한 상태로) 받아지게 된다.
					
					int seq = rs.getInt(index++);
					String id = rs.getString(index++);
					String email = rs.getString(index++);
					String name = rs.getString(index++);
					String phone = rs.getString(index++);
					String regdate = rs.getString(index++);
					
					obj = new MemberDto(seq,id,email,name,phone,regdate); //생성자가 없기때문에 만들어주면된다.(빨간색 눌러서)
				}
				
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				try {
					if(rs!=null)rs.close();
					if(pstmt!=null)pstmt.close();
					if(con!=null)con.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
			return obj;
		}
}	