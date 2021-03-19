package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class BoardDao {
   private Connection con;
   private PreparedStatement pstmt;
   private ResultSet rs;
   
   public BoardDao() {
      try {
         String url = "jdbc:mysql://13.125.166.164:3306/P_Project";
         String root = "zoe";
         String pw = "woojin7929!M";
         Class.forName("com.mysql.jdbc.Driver");
         con = DriverManager.getConnection(url, root, pw);
         
      }catch(Exception e) {
         e.printStackTrace();
      }
      
   }
   
   public List<Board> getBoardList() {

      return getBoardList(1, "title", "");
   }

   public List<Board> getBoardList(int page) {

      return getBoardList(page, "title", "");
   }

   public List<Board> getBoardList(int page, String field, String query) {// 페이징 게시판
      int start = 1 + (page - 1) * 10; // 1, 11, 21, 31
      int end = page * 10; // 10, 20, 30, 40

      String sql = "Select *"
              + "from (Select @rownum:=@rownum+1 as num , n.*"
    		  + "        from(select *"
              + "               From board"
    		  + "           where " + field + " like ?" // %검색어%
              + "            order by id desc)n"
    		  + "        Where (@rownum:=0)=0) num "
              + "Where num.num between ? and ? ";

      List<Board> list = new ArrayList<Board>();

      try {
         System.out.println(sql);
         pstmt = con.prepareStatement(sql);
         // Statement st = con.createStatement();
         pstmt.setString(1, "%" + query + "%");
         pstmt.setInt(2, start);
         pstmt.setInt(3, end);
         ResultSet rs = pstmt.executeQuery();

         while (rs.next()) {
            int id = rs.getInt("id");
            String title = rs.getString("title");
            String writerid = rs.getString("writer_id");
            Date regDate = rs.getDate("regdate");
            String content = rs.getString("content");
            int hit = rs.getInt("hit");
            String files = rs.getString("files");
            String pubflag = rs.getString("pubflag");
            boolean pub = false;
            if (pubflag.equals("Y")) {
               pub = true;
            }

            Board bo = new Board(id, title, writerid, content, regDate, hit, files, pub);
            list.add(bo);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      return list;
   }

   public List<Board> getPubBoardlist(int page, String field, String query) {
      int start = (page - 1) * 10; 

//      String sql = "Select *" + "from (Select @rownum:=@rownum+1 as num , n.*" + "        from(select *"
//            + "               From board" + "           where pubflag = 'Y' " + "            and " + field
//            + " like ?" // %검색어%
//            + "            order by id desc)n" + "        Where (@rownum:=0)=0) num "
//            + "Where num.num between ? and ? ";
      
      String sql = "SELECT * "
              +"FROM board "
              +"where pubflag = 'Y' "
              +"and "+field+" like ? "
              +"ORDER BY ID DESC "
              +"LIMIT 10 OFFSET ?";

      List<Board> list = new ArrayList<Board>();

      try {
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, "%" + query + "%");
         pstmt.setInt(2, start);
         rs = pstmt.executeQuery();

         while (rs.next()) {
            int id = rs.getInt("id");
            String title = rs.getString("title");
            String writerid = rs.getString("writer_id");
            Date regDate = rs.getDate("regdate");
            String content = rs.getString("content");
            int hit = rs.getInt("hit");
            String files = rs.getString("files");
            String pubflag = rs.getString("pubflag");
            boolean pub = false;
            if (pubflag.equals("Y")) {
               pub = true;
            }

            Board bo = new Board(id, title, writerid, content, regDate, hit, files, pub);
            list.add(bo);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      return list;

   }

   public Board getBoardDetail(int id) {// detail

      String sql = "select * from board where id=" + id;

      Board bo = new Board();
      try {
         pstmt = con.prepareStatement(sql);
         rs = pstmt.executeQuery();

         while (rs.next()) {// db 값이 있을 때만 실행되고, 없으면 아무 것도 없음
            int id_ = rs.getInt("id");
            String title = rs.getString("title");
            String writerid = rs.getString("writer_id");
            Date regDate = rs.getTimestamp("regdate");
            String content = rs.getString("content");
            int hit = rs.getInt("hit");
            String files = rs.getString("files");
            String pubflag = rs.getString("pubflag");
            boolean pub = false;
            if (pubflag.equals("Y")) {
               pub = true;
            }
            bo = new Board(id, title, writerid, content, regDate, hit, files, pub);

         }

      } catch (Exception e) {
         e.printStackTrace();
      }

      return bo;
   }

   public void hit(int id) {// 조회수
      String sql = "UPDATE board SET hit = hit+1 WHERE id=" + id;

      Board bo = new Board();
      try {

         pstmt = con.prepareStatement(sql);
         int result = pstmt.executeUpdate();

         if (result == 1) {
            System.out.println("정상적으로 입력 되었습니다.");
         } else {
            System.out.println("정상적으로 입력되지 못했습니다.");
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public int getBoardCount() {
      return getBoardCount("title", "");
   }

   public int getBoardCount(String field, String query) {
      int count = 0;
      String sql = "Select count(num.id) as count " + "   from (Select @rownum:=@rownum+1 as num , n.*"
            + "        from(select *" + "               From board" + "           where " + field + " like ?" // %검색어%
            + "            order by id desc)n" + "        Where (@rownum:=0)=0) num";

      try {

         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, "%" + query + "%");
         rs = pstmt.executeQuery();

         if (rs.next()) {
            count = rs.getInt("count");

         }
      } catch (Exception e) {
         e.printStackTrace();
      }

      return count;
   }

   public int BoarddeliteAll(int[] ids) {// 글쓰기
      int result = 0;

      String sql = "delete from board where id=?";

      try {
         pstmt = con.prepareStatement(sql);
         for (int i = 0; i < ids.length; i++) {
            pstmt.setInt(1, ids[i]);
            pstmt.executeUpdate();
            result++;
         }
      } catch (Exception e) {
         e.printStackTrace();
      }

      return result;
   }

   public int updateBoard(Board nt) {// 글 수정
      return 0;
   }

   public int Boarddelite(int id) {// detail 글 삭제

      String sql = "delete from board where id=" + id;

      int result = -1;
      try {
         pstmt = con.prepareStatement(sql);
         result = pstmt.executeUpdate();

      } catch (Exception e) {
         e.printStackTrace();
      }

      return result;
   }


   public int insertBoard(Board nt) {// 글쓰기
	   
      int result = 0;
      String sql = " insert into board (title, writer_id, content, files, pubflag) " + " values(? , ? , ? , ? , ?)";

      try {
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, nt.getTitle());
         pstmt.setString(2, nt.getWriterID());
         pstmt.setString(3, nt.getContent());
         pstmt.setString(4, nt.getFiles());
         String pub = "N";
         if ((nt.getPub()) == true)
            pub = "Y";
         pstmt.setString(5, pub);
         result = pstmt.executeUpdate();
      } catch (Exception e) {
         e.printStackTrace();
      }
      return result;
   }

   public int insertBoardComment(BoardComment bc) {// 댓글입력
      int result = 0;
      String sql = " insert into boardcomment (mid,comment,writerID) " + " values(? , ? , ?)";

      try {
         pstmt = con.prepareStatement(sql);
         pstmt.setInt(1, bc.getMid());
         pstmt.setString(2, bc.getComment());
         pstmt.setString(3, bc.getWriterID());
         

         result = pstmt.executeUpdate();
      } catch (Exception e) {
         e.printStackTrace();
      }

      return result;
   }

   public List<BoardComment> getComment(int mid) {//댓글보기

      String sql = "select * from boardcomment where mid = ? order by regdate desc";
      List<BoardComment> list = new ArrayList();
      try {
         pstmt = con.prepareStatement(sql);
         pstmt.setInt(1, mid);
         rs = pstmt.executeQuery();
         
         while(rs.next()) {
            int id = rs.getInt("id");
            int mid_ = rs.getInt("mid");
            String comment = rs.getString("comment");
            String writerID = rs.getString("writerID");      
            Date regdate = rs.getDate("regdate");
            
            BoardComment bc = new BoardComment(id, mid_,  writerID, comment, regdate);
            
            list.add(bc);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      
      return list;

   }

   public int getComCount(int mid) {
      
      int count = 0;
      
      String sql = "select count(id) as count from boardcomment where mid = "+mid;

      try {
         pstmt = con.prepareStatement(sql);
         rs = pstmt.executeQuery();

         if (rs.next()) {
            count = rs.getInt("count");

         }
      } catch (Exception e) {
         e.printStackTrace();
      }

      return count;
      
   }
}
