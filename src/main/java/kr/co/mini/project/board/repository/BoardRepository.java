package kr.co.mini.project.board.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import kr.co.mini.project.board.dao.BoardDAO;
import kr.co.mini.project.board.dto.BoardDTO;
import kr.co.mini.project.board.dto.BoardWithDetailDTO;
import kr.co.mini.project.board.entity.Board;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final BoardDAO boardDAO;

    public void save(Board board) {
        boardDAO.save(board);
    }

    public void delete(Integer id) {
        boardDAO.delete(id);
    }

    public List<BoardWithDetailDTO> findAllBoardsWithDetails() {
        return boardDAO.findAllBoardsWithDetails();
    }

    public Optional<BoardDTO> findBoardWithDetailsAndComments(Integer id) {
        return boardDAO.findBoardWithDetailsAndComments(id);
    }
}