package kr.co.mini.project.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.mini.project.board.dto.BoardDTO;
import kr.co.mini.project.board.dto.BoardWithDetailAndComments;
import kr.co.mini.project.board.dto.BoardWithDetailDTO;
import kr.co.mini.project.board.entity.Board;
import kr.co.mini.project.board.entity.BoardDetail;
import kr.co.mini.project.board.repository.BoardDetailRepository;
import kr.co.mini.project.board.repository.BoardRepository;

@Service
public class BoardService {
  private final BoardDetailRepository boardDetailRepository;
  private final BoardRepository boardRepository;

  @Autowired
  public BoardService(BoardDetailRepository boardDetailRepository, BoardRepository boardRepository) {
    this.boardDetailRepository = boardDetailRepository;
    this.boardRepository = boardRepository;
  }

  @Transactional(readOnly = true)
  public List<BoardWithDetailDTO> findAllBoards() {
    return boardRepository.findAllBoardsWithDetails();
  }

  @Transactional(readOnly = true)
  public BoardWithDetailAndComments findBoardWithDetailsAndComments(Integer id) {
    return boardRepository.findBoardWithDetailsAndComments(id)
      .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
  }

  @Transactional
  public void createBoard(BoardDTO boardDTO, String content) {
    // 1. Board 엔티티 저장
    Board board = convertToEntity(boardDTO);
    boardRepository.save(board);

    // 2. BoardDetail 엔티티 저장
    BoardDetail boardDetail = new BoardDetail(board.getId(), content);
    boardDetailRepository.save(boardDetail);
  }

  @Transactional
  public void updateBoardDetail(Integer boardId, String content) {
    BoardDetail boardDetail = new BoardDetail(boardId, content);
    boardDetailRepository.update(boardDetail);
  }

  @Transactional
  public void deleteBoard(Integer id) {
    boardRepository.delete(id);
  }

  private Board convertToEntity(BoardDTO dto) {
    return new Board(
      dto.getId(),
      dto.getUserId(),
      dto.getCreatedAt(),
      dto.getUpdatedAt()
    );
  }
}
