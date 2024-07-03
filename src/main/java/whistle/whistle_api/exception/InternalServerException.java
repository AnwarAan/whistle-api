package whistle.whistle_api.exception;

public class InternalServerException extends RuntimeException {
  public InternalServerException() {
    super("Internal Server Error");
  }
}
