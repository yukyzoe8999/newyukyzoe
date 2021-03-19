package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bbs.web.connection.ConnectionProvider;
import com.bbs.web.connection.jdbcUtil;

public class TourDao {
	private static TourDao instance = new TourDao();

	public static TourDao getInstance() {
		return instance;
	}

	public List<Tour> getTourList() {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Tour> list = new ArrayList<Tour>();
		
		return null;
	}
	public List<Tour> getphotoList() {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Tour> list = new ArrayList<Tour>();
		
		String sql = "select * from tour_list";
		 try {
				conn = ConnectionProvider.getConnection();
				psmt = conn.prepareStatement(sql);
				rs = psmt.executeQuery();


	         while (rs.next()) {
	        	 int id = rs.getInt("id");
	        	 int location_num = rs.getInt("location_num");
	        	 String location = rs.getString("location");
	             String recap = rs.getString("recap");
	             String information = rs.getString("information");

	             Tour tour = new Tour(id, location_num, location, recap, information);
	             list.add(tour);
	         
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

}
