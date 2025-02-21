package kr.co.mini.project.board.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.mini.project.board.dao.BoardDAO;
import kr.co.mini.project.board.dto.BoardWithDetailAndComments;
import kr.co.mini.project.board.dto.BoardWithDetailDTO;
import kr.co.mini.project.board.entity.Board;

@Repository
public class BoardRepository {
    private final BoardDAO boardDAO;
    
    @Autowired
    public BoardRepository(BoardDAO boardDAO) {
        this.boardDAO = boardDAO;
    }

    public void save(Board board) {
        boardDAO.save(board);
    }

    public void delete(Integer id) {
        boardDAO.delete(id);
    }

    public List<BoardWithDetailDTO> findAllBoardsWithDetails() {
        return boardDAO.findAllBoardsWithDetails();
    }

    public Optional<BoardWithDetailAndComments> findBoardWithDetailsAndComments(Integer id) {
        return boardDAO.findBoardWithDetailsAndComments(id);
    }
}