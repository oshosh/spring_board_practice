package kr.co.mini.project.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import kr.co.mini.common.dto.CommonResponseDto;
import kr.co.mini.project.board.dto.BoardDTO;
import kr.co.mini.project.board.dto.BoardWithDetailAndComments;
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
    @GetMapping("/list")
    public ResponseEntity<List<BoardWithDetailDTO>> getAllBoards() {
        List<BoardWithDetailDTO> allBoards = boardService.findAllBoards();
        return ResponseEntity.ok().body(allBoards);
    }

    /**
     * 게시물 상세 + 코멘트까지
     */
    @GetMapping("/detail")
    public ResponseEntity<BoardWithDetailAndComments> getBoard(@RequestParam("id") Integer id) {
        BoardWithDetailAndComments boardWithDetailAndComments = boardService.findBoardWithDetailsAndComments(id);
        return ResponseEntity.ok(boardWithDetailAndComments);
    }

    @PostMapping
    public ResponseEntity<CommonResponseDto> createBoard(@RequestBody Map<String, Object> request) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setUserId((Integer) request.get("userId"));
        String content = (String) request.get("content");
        
        boardService.createBoard(boardDTO, content);
        return ResponseEntity.ok(new CommonResponseDto("OK", "새로운 게시물이 생성되었습니다."));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommonResponseDto> updateBoardDetail(
            @PathVariable Integer id,
            @RequestBody Map<String, String> request) {
        boardService.updateBoardDetail(id, request.get("content"));
        return ResponseEntity.ok(new CommonResponseDto("OK", "게시물이 수정되었습니다."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponseDto> deleteBoard(@PathVariable Integer id) {
        boardService.deleteBoard(id);
        return ResponseEntity.ok(new CommonResponseDto("OK", "게시물이 삭제되었습니다."));
    }
} 