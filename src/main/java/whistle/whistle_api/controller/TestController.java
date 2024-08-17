package whistle.whistle_api.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import whistle.whistle_api.dto.ResponseData;
import whistle.whistle_api.service.TestService;

@RestController
public class TestController {
  @Autowired
  TestService testService;

  @PostMapping("/user")
  public ResponseEntity<ResponseData<?>> addUser(@RequestParam String name,
      @RequestParam String email, @RequestParam String password, @RequestParam LocalDate dob,
      @RequestParam String role) {
    testService.addUser(name, email, password, dob, role);
    return ResponseEntity.ok(ResponseData.responseSucceess());
  }
}
