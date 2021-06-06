package service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Board;
import domain.dto.BoardDto;
import model.BoardDao;

@Service
public class BoardService {
	
	private BoardDao boardDao;
	
	@Autowired
	public BoardService(BoardDao boardDao) {
		this.boardDao = boardDao;
	}

	// 단건조회
	public Board findOne(Long boardId) {
		return boardDao.selectById(boardId);
	}
	
	// 게시물 등록
    public Long write(BoardDto boardDto) {
    	
    	Board board = new Board(boardDto.getTitle(), boardDto.getContent(), boardDto.getWriter(), LocalDateTime.now());
    	
    	boardDao.insert(board);
    	
    	return board.getId();
    }
    
    // 게시물 리스트 가져오기
  	public List<Board> findAll(){
  		return boardDao.selectAll();
  	}
    
    // 게시글 수정
    public void update(Long boardId, BoardDto boardDto) {
    	Board updateBoard = findOne(boardId);
    	
    	updateBoard.setTitle(boardDto.getTitle());
    	updateBoard.setContent(boardDto.getContent());
    	updateBoard.setWriter(boardDto.getWriter());
    	
    	boardDao.update(updateBoard);
    	
    }
    
    // 게시글 삭제
    public void delete(Long boardId) {
    	boardDao.delete(boardId);
    }
    
    
    
}
