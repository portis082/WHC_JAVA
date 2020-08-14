package BoardVO;

public class BoardVO {
	int boardNo;	// 게시글 번호
	String boardTitle;	// 게시글 제목
	String boardWriter;	// 게시글 작성자
	String boardDate;	// 게시글 작성일자
	String boardContent;	// 게시글 내용
	
	public BoardVO(String boardTitle, String boardWriter, String boardDate, String boardContent) {
		super();
		this.boardNo = 9999;
		this.boardTitle = boardTitle;
		this.boardWriter = boardWriter;
		this.boardDate = boardDate;
		this.boardContent = boardContent;
	}
	
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardWriter() {
		return boardWriter;
	}
	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}
	public String getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(String boardDate) {
		this.boardDate = boardDate;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
}
