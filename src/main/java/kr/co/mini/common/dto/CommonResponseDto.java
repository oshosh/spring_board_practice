package kr.co.mini.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommonResponseDto {
  private String code;
  private String message;
  private Object contents;
  
  public CommonResponseDto(String message) {
		this.code = "ok";
		this.message = message;
	}
	public CommonResponseDto(String code, String message) {
		this.code = code;
		this.message = message;
	}
	public CommonResponseDto(String code, String message, Object contents) {
		this(code, message);
		this.contents = contents;
	}
  
}
