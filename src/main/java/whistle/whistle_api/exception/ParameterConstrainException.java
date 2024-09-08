package whistle.whistle_api.exception;

public class ParameterConstrainException extends RuntimeException {
  public ParameterConstrainException(String message) {
    super(message);
  }

  public ParameterConstrainException() {
    super();
  }

}
