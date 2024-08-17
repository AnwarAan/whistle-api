package whistle.whistle_api.exception;

public class NotFoundException extends RuntimeException {
  public NotFoundException() {
    super("Not Found");
  }

  public NotFoundException(String msg) {
    super(msg + " Not Found");
  }
}
