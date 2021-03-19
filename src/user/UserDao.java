package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bbs.web.connection.ConnectionProvider;
import com.bbs.web.connection.jdbcUtil;
import com.mysql.cj.protocol.Resultset;


public class UserDao {
	private Connection con;
	private PreparedStatement pstm;
	private ResultSet rs;

	private static UserDao instance = new UserDao();

	public static UserDao getInstance() {
		return instance;
	}
	
	public UserDao() {
		try {
			String dbURL = "jdbc:mysql://13.125.166.164:3306/P_Project";
			String dbID = "zoe";
			String dbPass = "woojin7929!M";
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(dbURL, dbID, dbPass);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// ID 중복 체크
	public int idCheck(String userID) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int value = 0;

		try {
			String sql = "select ID from user where ID = ?";
		
			conn = ConnectionProvider.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userID);
			System.out.println(psmt);
			rs = psmt.executeQuery();

			if (rs.next())
				value = 1;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(rs);
			jdbcUtil.close(psmt);
			jdbcUtil.close(conn);
		}
		return value;
	}

	public int login(String userID, String userPASS) { //login
		String SQL = "SELECT PASS FROM user WHERE ID = ?";
		try {
			pstm = con.prepareStatement(SQL);
			pstm.setString(1, userID);

			rs = pstm.executeQuery();
			if (rs.next()) {
				if (rs.getString(1).equals(userPASS)) {
					System.out.println("로그인 성공");
					return 1; //값 불러오기 성공  //로그인 성공
				} else {
					System.out.println("비밀번호 틀림");
					return 0;  //없음  //비밀번호 틀림
				}
			}
	System.out.println("접속오류");
			return -1;  //접속오류

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2; //데이터베이스 오류
		
	}

	public int join(User user) {
		String SQL = "INSERT INTO user (ID, PASS, NAME, EMAIL) VALUES(?,?,?,?)";
		try {
			pstm =con.prepareStatement(SQL);
			pstm.setString(1, user.getID());
			pstm.setString(2, user.getPASS());
			pstm.setString(3, user.getNAME());
			pstm.setString(4, user.getEMAIL());
			
			return pstm.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} return -1;
	}
}