package whistle.whistle_api.exception;

import io.jsonwebtoken.JwtException;

public class JwtExpiredException extends JwtException {
  public JwtExpiredException(String msg) {
    super(msg);
  }
}
