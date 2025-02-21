package kr.co.mini.project.web.config;

import java.net.BindException;
import java.security.InvalidKeyException;

import javax.security.auth.login.LoginException;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.mini.common.dto.CommonResponseDto;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {
  @ExceptionHandler(NoHandlerFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Object handleError404(NoHandlerFoundException ex, HttpServletRequest request) {
      // API 요청인 경우 JSON 응답
      if (isApiRequest(request)) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new CommonResponseDto("NOT_FOUND", "존재하지 않는 경로입니다."));
      }

      // 웹 페이지 요청인 경우 => 즉, 라우팅 경로가 없는 것들 404 에러 페이지로 이동
      return "error/404";
  }
  
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
  public ResponseEntity<CommonResponseDto> handleHttpRequestMethodNotSupportedException(
          HttpRequestMethodNotSupportedException ex,
          HttpServletRequest request) {

    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
      .body(new CommonResponseDto("METHOD_NOT_ALLOWED", "지원하지 않는 메소드입니다."));
  }

  /**
	 * 관리되지 않은 예상치 않은 에러
	 */
	@ExceptionHandler(Exception.class)
	public Object handleError(Exception e, HttpServletRequest request) {
		log.error("== ERROR: {}", e.getMessage(), e); // FIXME 에러 출력 임시

		String message = e.getMessage();

		HttpStatus httpStatus = getResponseCode(e);
		if (message.contains("JSON") || message.contains("Exception") || message.contains("Request")) {
			if (httpStatus.is4xxClientError()) {
				log.debug("ClientException : {}", e.getMessage());
				return ResponseEntity.status(httpStatus).body(new CommonResponseDto("BAD_REQUEST", "잘못된 요청입니다."));

			} else if (httpStatus.is5xxServerError()) {
				log.error("ServerException : {}", e.getMessage());

        if (isApiRequest(request)) {
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                  .body(new CommonResponseDto("INTERNAL_SERVER_ERROR", "서버 에러입니다."));
        }

				return "error/500";
			}
		}

		return ResponseEntity.status(httpStatus).body(new CommonResponseDto(httpStatus.name(), message));
	}

  @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
  public ResponseEntity<CommonResponseDto> handleAccessDeniedException(
          org.springframework.security.access.AccessDeniedException ex) {
      return ResponseEntity
              .status(HttpStatus.UNAUTHORIZED)
              .body(new CommonResponseDto("UNAUTHORIZED", "사용자가 인증되지 않았습니다."));
  }

  @ExceptionHandler(org.springframework.security.core.AuthenticationException.class)
  public ResponseEntity<CommonResponseDto> handleAuthenticationException(
          org.springframework.security.core.AuthenticationException ex) {
      return ResponseEntity
              .status(HttpStatus.UNAUTHORIZED)
              .body(new CommonResponseDto("UNAUTHORIZED", "사용자가 인증되지 않았습니다."));
  }


	/**
	 * 익셉션을 통해 httpStatusCode 를 취득한다.
	 */
	private HttpStatus getResponseCode(Exception e) {
		int responseCode;
		// 400
		if (e instanceof BindException || e instanceof HttpMessageNotReadableException
				|| e instanceof MissingServletRequestParameterException
				|| e instanceof MissingServletRequestPartException || e instanceof TypeMismatchException
				|| e instanceof IllegalArgumentException) {
			responseCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		// 403
		else if (e instanceof InvalidKeyException){
			responseCode = HttpServletResponse.SC_FORBIDDEN;
		}
		// 405
		else if (e instanceof HttpRequestMethodNotSupportedException || e instanceof LoginException) {
			responseCode = HttpServletResponse.SC_METHOD_NOT_ALLOWED;
		}
		// 406
		else if (e instanceof HttpMediaTypeNotAcceptableException) {
			responseCode = HttpServletResponse.SC_NOT_ACCEPTABLE;
		}
		// 415
		else if (e instanceof HttpMediaTypeNotSupportedException) {
			responseCode = HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE;
		}
		// 500
		else if (e instanceof HttpMessageNotWritableException) {
			responseCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
		}
		// 그외 모든 에러도 500
		else {
			responseCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
		}
		return HttpStatus.valueOf(responseCode);
	}

  /**
   * /api/ 로 시작하는 요청인지 확인
   */
   private boolean isApiRequest(HttpServletRequest request) {
    return request.getRequestURI().startsWith("/api/");
  }

}
