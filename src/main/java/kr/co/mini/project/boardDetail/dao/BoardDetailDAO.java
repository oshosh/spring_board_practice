package kr.co.mini.project.boardDetail.dao;

import org.apache.ibatis.annotations.Mapper;
import kr.co.mini.project.boardDetail.entity.BoardDetail;

@Mapper
public interface BoardDetailDAO {
    void save(BoardDetail boardDetail); // 게시판 등록 시 상세 테이블에도 데이터를 저장하기 위해서 만듬
    void update(BoardDetail boardDetail); // Boards 테이블의 updated_at 컬럼 업데이트 UPDATE 트리거를 같이 걸어둠
}