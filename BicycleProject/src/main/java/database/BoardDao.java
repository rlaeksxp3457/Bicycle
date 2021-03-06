package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class BoardDao {
	DBDriver dbDriver = new DBDriver();
	private PreparedStatement pstmt;
	private ResultSet rs;

	public int getboardId(String writer, String boardContent , int member_id) {
		Connection connection = dbDriver.connDB();
		String sql = "select Board_id from Board where Board_writer = ? AND Board_content = ? AND B_Member_id = ?  ";
		int boardId = 0;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, writer);
			pstmt.setString(2, boardContent);
			pstmt.setInt(3, member_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				boardId = rs.getInt("Board_id");
			}
			dbDriver.closeAll(rs, pstmt, connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return boardId;
		
		
	}
	
	 

	public ArrayList<BoardDto> getList() {
		ArrayList<BoardDto> arrayList = new ArrayList<>();
		Connection connection = dbDriver.connDB();
		String sql = "select * from Board order by Board_regdate desc";
		try {
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String categoryBig = rs.getString("Category_big");
				String categorySmall = rs.getString("Category_small");
				String boardRegDate = rs.getString("Board_regdate");
				String boardContent = rs.getString("Board_content");
				String boardWriter = rs.getString("Board_writer");
				String boardTitle = rs.getString("Board_title");
				int board_hit = rs.getInt("Board_hit");
				int boardId = rs.getInt("Board_id");
				int member_uid = rs.getInt("B_Member_id");

				BoardDto boardDto = new BoardDto();
				boardDto.setCategory_big(categoryBig);
				boardDto.setCategory_small(categorySmall);
				boardDto.setBoard_regdate(boardRegDate);
				boardDto.setBoard_content(boardContent);
				boardDto.setBoard_writer(boardWriter);
				boardDto.setBoard_id(boardId);
				boardDto.setBoard_title(boardTitle);
				boardDto.setMember_uid(member_uid);
				boardDto.setInfo_hit(board_hit);

				arrayList.add(boardDto);

				dbDriver.closeAll(rs, pstmt, connection);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayList;

	}
	
	
	
	public List<BoardDto> getListPage(String cetegory_sm,int pagesize) {
		ArrayList<BoardDto> arrayList = new ArrayList<>();
		Connection connection = dbDriver.connDB();
		String sql = "select * from Board where Category_small = ? LIMIT ?, 10";
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, cetegory_sm);
			pstmt.setInt(2, pagesize);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String categoryBig = rs.getString("Category_big");
				String categorySmall = rs.getString("Category_small");
				String boardRegDate = rs.getString("Board_regdate");
				String boardContent = rs.getString("Board_content");
				String boardWriter = rs.getString("Board_writer");
				String boardTitle = rs.getString("Board_title");
				int boardId = rs.getInt("Board_id");
				int member_uid = rs.getInt("B_Member_id");

				BoardDto boardDto = new BoardDto();
				boardDto.setCategory_big(categoryBig);
				boardDto.setCategory_small(categorySmall);
				boardDto.setBoard_regdate(boardRegDate);
				boardDto.setBoard_content(boardContent);
				boardDto.setBoard_writer(boardWriter);
				boardDto.setBoard_id(boardId);
				boardDto.setBoard_title(boardTitle);
				boardDto.setMember_uid(member_uid);


				arrayList.add(boardDto);

				dbDriver.closeAll(rs, pstmt, connection);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayList;

	}

	public BoardDto getDto(int id) {
		Connection connection = dbDriver.connDB();
		String sql = "select * from Board where Board_id= ?";
		BoardDto boardDto = new BoardDto();
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();	
			while (rs.next()) {
				String categoryBig = rs.getString("Category_big");
				String categorySmall = rs.getString("Category_small");
				String boardRegDate = rs.getString("Board_regdate");
				String boardContent = rs.getString("Board_content");
				String boardWriter = rs.getString("Board_writer");
				String boardTitle = rs.getString("Board_title");
				int boardId = rs.getInt("Board_id");
				int member_uid = rs.getInt("B_Member_id");
				int board_hit = rs.getInt("Board_hit");

				boardDto.setCategory_big(categoryBig);
				boardDto.setCategory_small(categorySmall);
				boardDto.setBoard_regdate(boardRegDate);
				boardDto.setBoard_content(boardContent);
				boardDto.setBoard_writer(boardWriter);
				boardDto.setBoard_id(boardId);
				boardDto.setBoard_title(boardTitle);
				boardDto.setMember_uid(member_uid);
				boardDto.setInfo_hit(board_hit);

				dbDriver.closeAll(rs, pstmt, connection);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boardDto;
	}

	public boolean insert(String Category_big, String Category_small, String title, String content, String writer,
			int Member_id) {
		String sql = "insert into board (Category_big, Category_small, Board_title, Board_content, Board_writer, B_Member_id) values(?,?,?,?,?,?)";
		Connection connection = dbDriver.connDB();
		boolean check;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, Category_big);
			pstmt.setString(2, Category_small);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			pstmt.setString(5, writer);
			pstmt.setInt(6, Member_id);
			pstmt.executeUpdate();
			check = true;
			dbDriver.closeAll(pstmt, connection);
		} catch (SQLException e) {
			check = false;
			e.printStackTrace();
		}
		return check;
	}
	

	public boolean delete(int Board_id) {
		String sql = "DELETE FROM board WHERE Board_id=?";
		Connection connection = dbDriver.connDB();
		boolean check;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, Board_id);
			pstmt.executeUpdate();
			dbDriver.closeAll(pstmt, connection);
			check = true;
			dbDriver.closeAll(pstmt, connection);
		} catch (SQLException e) {
			check = false;
			e.printStackTrace();
		}
		return check;

	}
	// ????????? ????????? ??????
	public boolean update(int Board_id, String title, String content) {
		String sql = "update board set Board_title= ?, Board_content = ?  WHERE Board_id = ? ";
		Connection connection = dbDriver.connDB();
		boolean check;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, Board_id);
			pstmt.executeUpdate();
			check = true;
			dbDriver.closeAll(pstmt, connection);
		} catch (SQLException e) {
			check = false;
			e.printStackTrace();
		}

		return check;

	}

	// ????????? ????????? ?????? ????????????
	public boolean update(int Board_id, String title, String content, String category_sm) {
		String sql = "update board set Board_title= ?, Board_content = ? , Category_small = ? where Board_id = ? ";
		Connection connection = dbDriver.connDB();
		boolean check;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, category_sm);
			pstmt.setInt(4, Board_id);
			pstmt.executeUpdate();
			check = true;
			dbDriver.closeAll(pstmt, connection);
		} catch (SQLException e) {
			check = false;
			e.printStackTrace();
		}
		return check;
	}
	// ????????? ?????? ?????????
	public void readBoardHit(int boardid) {
		String sql = "SELECT Board_hit FROM Board WHERE Board_id = ?";
		Connection connection = dbDriver.connDB();
		int boardHit = 0;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1,boardid);
			rs = pstmt.executeQuery();	
			while(rs.next()) {
				boardHit = rs.getInt("Board_hit");
			}
			boardHit += 1;
			sql = "UPDATE board SET Board_Hit = ? WHERE Board_id=?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1,boardHit);
			pstmt.setInt(2, boardid);
			pstmt.executeUpdate();	
			dbDriver.closeAll(rs,pstmt,connection);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


}
