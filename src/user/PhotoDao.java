package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bbs.web.connection.ConnectionProvider;
import com.bbs.web.connection.jdbcUtil;

public class PhotoDao {
//	private Connection con;
//	private PreparedStatement pstmt;
//	private ResultSet rs;
	private static PhotoDao instance = new PhotoDao();

	public static PhotoDao getInstance() {
		return instance;
	}
	public List<photo> getphotoList() {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<photo> list = new ArrayList<photo>();
		
		String sql = "select * from piccontent";
		 try {
				conn = ConnectionProvider.getConnection();
				psmt = conn.prepareStatement(sql);
				rs = psmt.executeQuery();


	         while (rs.next()) {
	        	 String pid = rs.getString("pid");
	             String picname = rs.getString("picname");
	             String content = rs.getString("content");
	             String category = rs.getString("category");
	             int price = rs.getInt("price");
	             Date regDate = rs.getDate("regdate");
	             String delflag = rs.getString("delflag");
	             String path = rs.getString("path");

	             photo pt = new photo(pid, picname, content, category, price, regDate, delflag, path);
	             list.add(pt);
	         
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
		 finally {
				jdbcUtil.close(rs);
				jdbcUtil.close(psmt);
				jdbcUtil.close(conn);
			}
		return list;
	}
	public photo getphotoDetail(String pidd) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		photo pt = null;
		
		String sql = "select * from piccontent where pid = ?";
		 try {
				conn = ConnectionProvider.getConnection();
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, pidd);
				rs = psmt.executeQuery();


	         while (rs.next()) {
	        	 String pid = rs.getString("pid");
	             String picname = rs.getString("picname");
	             String content = rs.getString("content");
	             String category = rs.getString("category");
	             int price = rs.getInt("price");
	             Date regDate = rs.getDate("regdate");
	             String delflag = rs.getString("delflag");
	             String path = rs.getString("path");

	             pt = new photo(pid, picname, content, category, price, regDate, delflag, path);
	             
	         
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
		 finally {
				jdbcUtil.close(rs);
				jdbcUtil.close(psmt);
				jdbcUtil.close(conn);
			}
		 return pt;
	}
	public List<photocomment> getphotocommentDetail(String ppid) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "select * from photocomment where pid = ?";
		List<photocomment> list = new ArrayList<photocomment>();
		
		try {
			conn = ConnectionProvider.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, ppid);
			rs = psmt.executeQuery();


         while (rs.next()) {
        	 int wid = rs.getInt("wid");
             String pid = rs.getString("pid");
             String comment = rs.getString("comment");
             int score = rs.getInt("score");
             Date regDate = rs.getDate("regdate");
             String userid = rs.getString("userid");
             String delflag = rs.getString("delflag");
            

             photocomment pt = new photocomment(wid, pid, comment, score, regDate, userid, delflag);
             list.add(pt);
         
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
	 finally {
			jdbcUtil.close(rs);
			jdbcUtil.close(psmt);
			jdbcUtil.close(conn);
		}
		return list;
	}
	public int getcommentcount(String ppid) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int count=0;
		String sql= "select count(wid) as count from photocomment where pid=?";
		
		try {
			conn = ConnectionProvider.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, ppid);
			rs = psmt.executeQuery();


         if (rs.next()) {
        	 count = rs.getInt("count");
         
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
	 finally {
			jdbcUtil.close(rs);
			jdbcUtil.close(psmt);
			jdbcUtil.close(conn);
		}
		
		return count;
	}
	public int insertConmment(photocomment cd) {
		Connection conn = null;
		PreparedStatement psmt = null;
		int result = 0;
		String sql = "insert into photocomment(pid, comment, score, userid) values (?,?,?,?)";
		
		 try {
				conn = ConnectionProvider.getConnection();
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, cd.getPid());
				psmt.setString(2, cd.getComment());
				psmt.setInt(3, cd.getScore());
				psmt.setString(4, cd.getUserid());

				result = psmt.executeUpdate();

	
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
		 finally {
				jdbcUtil.close(psmt);
				jdbcUtil.close(conn);
			}
		return 0;
	}

//	public PhotoDao() {
//		try {
//			String url = "jdbc:mysql://13.125.166.164:3306/P_Project";
//			String root = "zoe";
//			String pw = "woojin7929!M";
//			Class.forName("com.mysql.jdbc.Driver");
//			con = DriverManager.getConnection(url, root, pw);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
}
