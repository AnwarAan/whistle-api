package whistle.whistle_api.controller;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import whistle.whistle_api.dto.ResponseData;
import whistle.whistle_api.model.User;
import whistle.whistle_api.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

  @Autowired
  private final UserService userService;

  @GetMapping
  public ResponseEntity<ResponseData<List<User>>> findAllUser() {
    List<User> data = userService.findAllUser();
    System.out.println("=========================USER CONTROLLER=========================");
    return ResponseEntity.ok(ResponseData.responseSucceess(data));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseData<User>> findUserById(@PathVariable Long id) {
    User data = userService.findAUserById(id);
    return ResponseEntity.ok(ResponseData.responseSucceess(data));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ResponseData<Object>> updateUser(@PathVariable Long id, @RequestParam String name) {
    userService.updateUser(id, name);
    return ResponseEntity.ok(ResponseData.responseSucceess());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseData<Object>> deletUser(@PathVariable Long id) {
    userService.deleteUser(id);
    return ResponseEntity.ok(ResponseData.responseSucceess());
  }
}
