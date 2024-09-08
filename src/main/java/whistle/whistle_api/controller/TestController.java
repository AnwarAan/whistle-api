package whistle.whistle_api.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import whistle.whistle_api.dto.ResponseData;
import whistle.whistle_api.service.TestService;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestController {
  @Autowired
  TestService testService;

  @PostMapping("/user")
  public ResponseEntity<ResponseData<?>> addUser(@RequestParam String name, @RequestParam String nickname,
      @RequestParam String email, @RequestParam String password, @RequestParam LocalDate dob,
      @RequestParam String role) {
    testService.addUser(name, nickname, email, password, dob, role);
    return ResponseEntity.ok(ResponseData.responseSucceess());
  }
}
