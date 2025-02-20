package kr.co.mini.project.comment.dto;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
  private Integer userId;
  private Integer boardDetailId;
  private String comment;
  private LocalDateTime createdAt;
  private String commenterName;
} 