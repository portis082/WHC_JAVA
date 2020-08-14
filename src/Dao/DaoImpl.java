package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import BoardVO.BoardVO;
import Util.JDBCUtil;

public class DaoImpl implements Dao{
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	/**
	 * 자원반납용 메서드
	 */
	private void disconnect() {
		// 6. 종료(사용했던 자원을 반납한다.)
		if(rs !=null) try { rs.close();} catch(SQLException e) {}
		if(stmt !=null) try { stmt.close();} catch(SQLException e) {}
		if(conn !=null) try { conn.close();} catch(SQLException e) {}
	}
	
	@Override
	public int writeBoard(BoardVO bo) {
		int cnt = 0;
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "INSERT INTO JDBC_BOARD"
						 + "(BOARD_NO, "
						 + "BOARD_TITLE, "
						 + "BOARD_WRITER, "
						 + "BOARD_DATE, "
						 + "BOARD_CONTENT) "
					     +"VALUES(board_Seq.nextVal,?,?,?,?) ";
			// ?가 나왔으면 PREPAREDSTATEMENT를 사용한다는 걸 뜻한다. 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bo.getBoardTitle());
			pstmt.setString(2, bo.getBoardWriter());
			pstmt.setString(3, bo.getBoardDate());
			pstmt.setString(4, bo.getBoardContent());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return cnt;
	}

	@Override
	public int modBoard(BoardVO bo) {
		int cnt = 0;
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "UPDATE JDBC_BOARD SET "
						  +"BOARD_TITLE = ?, "
					      +"BOARD_DATE = ?, "
					      +"BOARD_CONTENT = ? "
					      +"WHERE BOARD_WRITER = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bo.getBoardTitle());
			pstmt.setString(2, bo.getBoardDate());
			pstmt.setString(3, bo.getBoardContent());
			pstmt.setString(4, bo.getBoardWriter());
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return cnt;
	}

	@Override
	public int delBoard(int boardNo) {
		int cnt = 0;
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "DELETE FROM JDBC_BOARD WHERE BOARD_NO = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return cnt;
	}

	@Override
	public List<BoardVO> searchBoard(BoardVO bo) {
		List<BoardVO> boardList = new ArrayList<>();
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "SELECT  * FROM JDBC_BOARD "
					    +"WHERE 1=1 "
					    +"AND BOARD_NO = ? "
					    +"AND BOARD_TITLE LIKE % || ? || % "
					    +"AND BOARD_WRITER LIKE % || ? || % "
					    +"AND BOARD_DATE LIKE % || ? || % "
					    +"AND BOARD_CONTENT LIKE % || ? || % ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bo.getBoardNo());
			pstmt.setString(2, bo.getBoardTitle());	 
			pstmt.setString(3, bo.getBoardWriter()); 
			pstmt.setString(4, bo.getBoardDate()); 
			pstmt.setString(5, bo.getBoardContent()); 
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO bo1 = new BoardVO(rs.getString("BOARD_TITLE"), 
										rs.getString("BOARD_WRITER"),
										rs.getString("BOARD_DATE"),
										rs.getString("BOARD_CONTENT"));
				bo1.setBoardNo(rs.getInt("BOARD_NO"));
				boardList.add(bo1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return boardList;
	}

	@Override
	public List<BoardVO> displayBoardAll() {
		List<BoardVO> memList = new ArrayList<>();
		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT * FROM JDBC_BOARD";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			// 반복문 안에서 가져온 레코드 하나 하나를 MembeVO에 맵핑하고 이 VO를 List에 추가한다.
			while(rs.next()) {
				int boardNo = rs.getInt("BOARD_NO");
				String boardTitle = rs.getString("BOARD_TITLE");
				String boardWriter = rs.getString("BOARD_WRITER");
				String boardDate = rs.getString("BOARD_DATE");
				String boardContent = rs.getString("BOARD_CONTENT");
				
				
				BoardVO bo = new BoardVO(boardTitle, boardWriter, boardDate, boardContent);
				bo.setBoardNo(boardNo);
				memList.add(bo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return memList;
	}
	
}
