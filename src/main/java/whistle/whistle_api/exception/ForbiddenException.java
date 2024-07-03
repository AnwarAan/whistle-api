package whistle.whistle_api.exception;

public class ForbiddenException extends RuntimeException {
  public ForbiddenException() {
    super();
  }

  public ForbiddenException(String msg) {
    super(msg);
  }
}
