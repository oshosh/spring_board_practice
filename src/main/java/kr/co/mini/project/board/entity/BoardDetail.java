package kr.co.mini.project.board.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 데이터베이스 테이블과 매핑되는 엔티티 클래스를 생성합니다.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDetail {
    private Integer boardsId;
    private String content;
}