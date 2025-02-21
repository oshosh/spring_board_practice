package kr.co.mini.project.user.entity;

import java.time.LocalDateTime;

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
public class User {
    private Integer id;
    private String name;
    private String password; // alert table 필요함 지금
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}