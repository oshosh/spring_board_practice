package kr.co.mini.project.board.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardWithDetailDTO {
    private Integer boardsNum;      // boards_num과 매핑
    private String content;         // content와 매핑
    private String userName;        // user_name과 매핑
    private LocalDateTime createdAt; // created_at과 매핑
    private LocalDateTime updatedAt; // updated_at과 매핑
}