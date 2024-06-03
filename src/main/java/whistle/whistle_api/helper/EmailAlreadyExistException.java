package whistle.whistle_api.helper;

public class EmailAlreadyExistException extends RuntimeException {

  public EmailAlreadyExistException() {
    super("Email Already Exist");
  }

  public EmailAlreadyExistException(String message) {
    super(message);
  }
}
