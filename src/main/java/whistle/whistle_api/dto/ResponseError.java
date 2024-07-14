package whistle.whistle_api.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseError {
  private final boolean status = false;
  private int statusCode;
  private String message;

  public static ResponseError notFound(String msg) {
    return ResponseError.builder().statusCode(HttpStatus.NOT_FOUND.value()).message(msg).build();
  }

  public static ResponseError internalServer() {
    return ResponseError.builder().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .message("Internal Server Error").build();
  }

  public static ResponseError forbidden(String msg) {
    return ResponseError.builder().statusCode(HttpStatus.FORBIDDEN.value()).message(msg).build();
  }

  public static ResponseError unauthorized(String msg) {
    return ResponseError.builder().statusCode(HttpStatus.UNAUTHORIZED.value()).message(msg).build();
  }

  public static ResponseError jwtExpired(String msg) {
    return ResponseError.builder().statusCode(HttpStatus.FORBIDDEN.value()).message(msg).build();
  }

}
