package whistle.whistle_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import whistle.whistle_api.dto.ResponseError;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ResponseError> handleNotFoundException(NotFoundException ex) {
    ResponseError responseError = ResponseError.notFound(ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseError);
  }

  @ExceptionHandler(InternalServerException.class)
  public ResponseEntity<ResponseError> internalServerException() {
    ResponseError responseError = ResponseError.internalServer();
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseError);
  }

  @ExceptionHandler(ForbiddenException.class)
  public ResponseEntity<ResponseError> forbidden(ForbiddenException ex) {
    ResponseError responseError = ResponseError.forbidden(ex.getMessage());
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseError);
  }

  @ExceptionHandler(UnauthorizedException.class)
  public ResponseEntity<ResponseError> unauthorized(UnauthorizedException ex) {
    ResponseError responseError = ResponseError.unauthorized(ex.getMessage());
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseError);
  }

  @ExceptionHandler(JwtExpiredException.class)
  public ResponseEntity<ResponseError> jwtExpired(JwtExpiredException ex) {
    ResponseError responseError = ResponseError.jwtExpired("JWT Expired");
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseError);
  }
}
