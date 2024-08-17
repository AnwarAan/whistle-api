package whistle.whistle_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import whistle.whistle_api.dto.ResponseAuth;
import whistle.whistle_api.dto.ResponseData;
import whistle.whistle_api.dto.UserDto;
import whistle.whistle_api.model.User;
import whistle.whistle_api.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  @Autowired
  private final AuthService authService;

  @PostMapping("/register")
  public ResponseEntity<ResponseData<User>> register(@RequestBody UserDto user) {
    authService.register(user);
    return ResponseEntity.ok(ResponseData.responseSucceess());
  }

  @PostMapping("/login")
  public ResponseEntity<ResponseData<ResponseAuth>> login(@RequestBody UserDto user) {
    ResponseAuth responseAuth = authService.login(user);
    ResponseCookie cookie = ResponseCookie.from("access_token", responseAuth.getAccessToken()).secure(false)
        .httpOnly(false).path("/").maxAge(86400000).build();
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
        .body(ResponseData.responseSucceess(responseAuth));
  }

  @PostMapping("/activate")
  public ResponseEntity<ResponseData<Object>> activate(@RequestParam Long userId) {
    authService.activateUser(userId);
    return ResponseEntity.ok(ResponseData.responseSucceess());
  }
}
