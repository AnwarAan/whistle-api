package whistle.whistle_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseData<T> {
  private boolean status;
  private int statusCode;
  private String message;
  private T data;

  public static <T> ResponseData<T> responseSucceess(int statusCode, String message, T data) {
    ResponseData<T> response = new ResponseData<>();
    response.setStatus(true);
    response.setStatusCode(statusCode);
    response.setMessage(message);
    response.setData(data);
    return response;
  }

  public static <T> ResponseData<T> responseSucceess(int statusCode, String message) {
    ResponseData<T> response = new ResponseData<>();
    response.setStatus(true);
    response.setStatusCode(statusCode);
    response.setMessage(message);
    response.setData(null);
    return response;
  }

  public static <T> ResponseData<T> responseSucceess(int statusCode) {
    ResponseData<T> response = new ResponseData<>();
    response.setStatus(true);
    response.setStatusCode(statusCode);
    response.setMessage("Success");
    response.setData(null);
    return response;
  }

  public static <T> ResponseData<T> responseSucceess(String message) {
    ResponseData<T> response = new ResponseData<>();
    response.setStatus(true);
    response.setStatusCode(200);
    response.setMessage(message);
    response.setData(null);
    return response;
  }

  public static <T> ResponseData<T> responseSucceess(T data) {
    ResponseData<T> response = new ResponseData<>();
    response.setStatus(true);
    response.setStatusCode(200);
    response.setMessage("Success");
    response.setData(data);
    return response;
  }

  public static <T> ResponseData<T> responseSucceess() {
    ResponseData<T> response = new ResponseData<>();
    response.setStatus(true);
    response.setStatusCode(200);
    response.setMessage("Success");
    response.setData(null);
    return response;
  }
}
