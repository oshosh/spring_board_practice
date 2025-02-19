package kr.co.mini.project.board.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import kr.co.mini.project.board.dto.BoardDTO;
import kr.co.mini.project.board.dto.BoardWithDetailDTO;
import kr.co.mini.project.board.entity.Board;

@Mapper
public interface BoardDAO {
    void save(Board board);
    void delete(Integer id);
    List<BoardWithDetailDTO> findAllBoardsWithDetails(); // 모든 게시판을 조회한다 (글번호, 내용, 작성자, 생성일자, 수정일자)
    Optional<BoardDTO> findBoardWithDetailsAndComments(Integer id); // 게시판 번호를 받아서 게시판 번호, 내용, 작성자, 생성일자, 수정일자, 댓글 목록을 조회한다
}