package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import domain.Board;
import domain.dto.BoardDto;
import service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	private BoardService boardService;
	
	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	// 게시글 작성
	@GetMapping("/write")
	public String boardView(Model model) {
		model.addAttribute("boardDto", new BoardDto());
		return "board/boardWrite";
	}
	
	@PostMapping("/write")
	public String boardWrite(BoardDto boardDto,Model model) {
		
		boardService.write(boardDto);
		
		return "redirect:/board/list";
	}
	
	// 전체 게시글 보기
	@GetMapping("/list")
    public String boardList(Model model){
        List<Board> boardList = boardService.findAll();
        model.addAttribute("boardList",boardList);
        return "board/boardList";
    }
	
	//게시글 보기
    @GetMapping("/list/{id}")
    public String boardOneList(@PathVariable("id") Long boardId, Model model){
        Board board = boardService.findOne(boardId);
        if (board == null) {
        	throw new exception.MemberNotFoundException();
		}
        model.addAttribute("board",board);
        return "board/boardOne";
    }
    
    //게시글 수정
    @GetMapping("/modify/{id}")
	public String boardModifyView(@PathVariable("id") Long boardId , Model model) {
    	Board board = boardService.findOne(boardId);
    	model.addAttribute("board",board);
		return "board/boardModify";
	}
	
	@PostMapping("/modify/{id}")
	public String boardModify(BoardDto boardDto, @PathVariable("id") Long boardId ,Model model) {
		boardService.update(boardId, boardDto);
		return "redirect:/board/list/{id}";
	}
	
	//게시글 삭제
	@RequestMapping("/remove/{id}")
	public String boardRemove(@PathVariable("id") Long boardId ,Model model) {
		boardService.delete(boardId);
		return "redirect:/board/list";
	}
}
