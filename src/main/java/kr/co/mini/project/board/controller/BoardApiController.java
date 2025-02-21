package kr.co.mini.project.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import kr.co.mini.common.dto.CommonResponseDto;
import kr.co.mini.project.board.dto.BoardDTO;
import kr.co.mini.project.board.dto.BoardWithDetailAndComments;
import kr.co.mini.project.board.dto.BoardWithDetailDTO;
import kr.co.mini.project.board.service.BoardService;
import kr.co.mini.project.user.service.MyUserDetailsService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardApiController {
    private final BoardService boardService;

    /**
     * 게시물 목록
     * http://localhost:8080/api/boards/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<BoardWithDetailDTO>> getAllBoards() {
        List<BoardWithDetailDTO> allBoards = boardService.findAllBoards();
        return ResponseEntity.ok().body(allBoards);
    }

    /**
     * 게시물 상세 + 코멘트까지
     * http://localhost:8080/api/boards/detail?id=1
     */
    @GetMapping("/detail")
    public ResponseEntity<BoardWithDetailAndComments> getBoard(@RequestParam("id") Integer id) {
        BoardWithDetailAndComments boardWithDetailAndComments = boardService.findBoardWithDetailsAndComments(id);
        return ResponseEntity.ok(boardWithDetailAndComments);
    }

    /*
     * 게시물 생성 하기
     * http://localhost:8080/api/boards
     * {
     * "content": "이것은 새로운 게시글입니다."
     * }
     */
    @PostMapping
    public ResponseEntity<CommonResponseDto> createBoard(@RequestBody Map<String, Object> request) {
        // 현재 인증된 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        MyUserDetailsService userDetails = (MyUserDetailsService) authentication.getPrincipal();
        Integer userId = userDetails.getId();

        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setUserId(userId);
        String content = (String) request.get("content");
        
        boardService.createBoard(boardDTO, content);
        return ResponseEntity.ok(new CommonResponseDto("OK", "새로운 게시물이 생성되었습니다."));
    }

    /*
     * 게시물 수정 하기
     * http://localhost:8080/api/boards/update?id=1
     * {
     * "content": "이것은 수정된 게시글입니다."
     * }
     */
    @PutMapping("/update")
    public ResponseEntity<CommonResponseDto> updateBoardDetail(
            @RequestParam("id") Integer id,
            @RequestBody Map<String, String> request) {
        boardService.updateBoardDetail(id, request.get("content"));
        return ResponseEntity.ok(new CommonResponseDto("OK", "게시물이 수정되었습니다."));
    }

    /*
     * 게시물 삭제 하기
     * http://localhost:8080/api/boards/delete?id=1
     * 
     */
    @DeleteMapping("/delete")
    public ResponseEntity<CommonResponseDto> deleteBoard(@RequestParam("id") Integer id) {
        boardService.deleteBoard(id);
        return ResponseEntity.ok(new CommonResponseDto("OK", "게시물이 삭제되었습니다."));
    }
} 