package whistle.whistle_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import whistle.whistle_api.dto.ResponseAuth;
import whistle.whistle_api.dto.ResponseData;
import whistle.whistle_api.model.User;
import whistle.whistle_api.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  @Autowired
  private final AuthService authService;

  @PostMapping("/register")
  public ResponseEntity<ResponseData<User>> register(@RequestParam String name, @RequestParam String email,
      @RequestParam String password, @RequestParam String role) {
    authService.register(name, email, password, role);
    return ResponseEntity.ok(ResponseData.responseSucceess());
  }

  @PostMapping("/login")
  public ResponseEntity<ResponseData<ResponseAuth>> login(@RequestParam String email, @RequestParam String password) {
    ResponseAuth responseAuth = authService.login(email, password);
    return ResponseEntity.ok(ResponseData.responseSucceess(responseAuth));
  }
}
