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
public class BoardDTO {
    private Integer id;
    private Integer userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}