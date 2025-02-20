package kr.co.mini.project.board.dto;

import java.time.LocalDateTime;
import java.util.List;

import kr.co.mini.project.comment.dto.CommentDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardWithDetailAndComments {
  private Integer boardsNum;
  private String content;
  private String userName;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private List<CommentDTO> comments;

}
