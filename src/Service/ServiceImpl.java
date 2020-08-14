package Service;

import java.util.List;

import BoardVO.BoardVO;
import Dao.Dao;
import Dao.DaoImpl;

public class ServiceImpl implements Service {
	Dao dao = new DaoImpl(); 
	
	@Override
	public int writeBoard(BoardVO bo) {
		return dao.writeBoard(bo);
	}

	@Override
	public int modBoard(BoardVO bo) {
		return dao.modBoard(bo);
	}

	@Override
	public int delBoard(int boardNo) {
		return dao.delBoard(boardNo);
	}

	@Override
	public List<BoardVO> searchBoard(BoardVO bo) {
		return dao.searchBoard(bo);
	}

	@Override
	public List<BoardVO> displayBoardAll() {
		return dao.displayBoardAll();
	}

}
