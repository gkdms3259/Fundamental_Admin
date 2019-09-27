package kr.co.kic.dev1.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnLocator {
	public static Connection getConnection() {
		DataSource ds = null;
		Connection con = null;
		try {
			Context context = new InitialContext();
			//java:comp/env/ 고정
			//jdbc/kic => context.xml의 name속성 값과 일치해야 한다.
			ds = (DataSource)context.lookup("java:comp/env/jdbc/kic");
			con = ds.getConnection(); 
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

}
