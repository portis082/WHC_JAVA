package Service;

import java.util.List;

import BoardVO.BoardVO;

public interface Service {
	/**
	 * 게시글을 작성하는 메서드이다.
	 * @return
	 */
	public int writeBoard(BoardVO bo);
	
	/**
	 * 작성한 게시글을 수정하는 메서드이다.
	 * @param bo 수정한 게시글을 매개변수로 받는다. 
	 * @return update가 완료되었으면 1을 , 그렇지 않으면 0을 반환한다. 
	 */
	public int modBoard(BoardVO bo);
	
	/**
	 * 게시글을 삭제하는 메서드이다.
	 * 삭제할 게시글의 번호를 가져가면, 해당 게시글을 찾아 삭제한다. 
	 * @return 삭제가 완료되면 1을, 그렇지 않으면 0을 반환한다. 
	 */
	public int delBoard(int boardNo);
		
	/**
	 * 게시글을 검색하는 메서드.
	 * @param bo 게시글 정보를 담고 있는 객체이다.
	 * @return 검색조건에 맞는 게시글을 리스트 형태로 반환한다. 
	 */
	public List<BoardVO> searchBoard(BoardVO bo);
	
	/**
	 * 존재하는 모든 게시글을 조회하는 메서드.
	 * @return 모든 게시글을 저장하고 있는 리스트를 반환한다.
	 */
	public List<BoardVO> displayBoardAll();
	
	
	
}
