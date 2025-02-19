package kr.co.mini.project.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import kr.co.mini.project.board.dto.BoardDTO;
import kr.co.mini.project.board.dto.BoardWithDetailDTO;
import kr.co.mini.project.board.service.BoardService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardApiController {
    private final BoardService boardService;

    /**
     * 게시물 목록
     */
    @GetMapping
    public ResponseEntity<List<BoardWithDetailDTO>> getAllBoards() {
        return ResponseEntity.ok(boardService.findAllBoards());
    }

    /**
     * 게시물 상세 + 코멘트까지
     */
    @GetMapping("/{id}")
    public ResponseEntity<BoardDTO> getBoard(@PathVariable Integer id) {
        return ResponseEntity.ok(boardService.findBoardWithDetailsAndComments(id));
    }

    @PostMapping
    public ResponseEntity<Void> createBoard(@RequestBody Map<String, Object> request) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setUserId((Integer) request.get("userId"));
        String content = (String) request.get("content");
        
        boardService.createBoard(boardDTO, content);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBoardDetail(
            @PathVariable Integer id,
            @RequestBody Map<String, String> request) {
        boardService.updateBoardDetail(id, request.get("content"));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Integer id) {
        boardService.deleteBoard(id);
        return ResponseEntity.ok().build();
    }
} 