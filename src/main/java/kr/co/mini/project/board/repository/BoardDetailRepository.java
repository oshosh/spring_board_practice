package kr.co.mini.project.board.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import kr.co.mini.project.board.dao.BoardDetailDAO;
import kr.co.mini.project.board.entity.BoardDetail;

@Repository
@RequiredArgsConstructor
public class BoardDetailRepository {
    private final BoardDetailDAO boardDetailDAO;

    public void save(BoardDetail boardDetail) {
        boardDetailDAO.save(boardDetail);
    }

    public void update(BoardDetail boardDetail) {
        boardDetailDAO.update(boardDetail);
    }
} 