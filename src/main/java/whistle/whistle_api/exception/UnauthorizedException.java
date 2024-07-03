package whistle.whistle_api.exception;

public class UnauthorizedException extends RuntimeException {
  public UnauthorizedException() {
    super("Unauthorized");
  }

  public UnauthorizedException(String msg) {
    super(msg);
  }
}
