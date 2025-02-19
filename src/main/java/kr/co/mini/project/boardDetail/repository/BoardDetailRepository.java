package kr.co.mini.project.boardDetail.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import kr.co.mini.project.boardDetail.dao.BoardDetailDAO;
import kr.co.mini.project.boardDetail.entity.BoardDetail;

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