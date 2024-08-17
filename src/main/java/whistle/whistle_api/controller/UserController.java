package whistle.whistle_api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import whistle.whistle_api.dto.ResponseData;
import whistle.whistle_api.dto.UserDto;
import whistle.whistle_api.helper.ExtractUser;
import whistle.whistle_api.model.User;
import whistle.whistle_api.service.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

  @Autowired
  private final ExtractUser extractUser;

  @Autowired
  private final UserService userService;

  @GetMapping
  public ResponseEntity<ResponseData<List<UserDto>>> findAllUser() {
    List<UserDto> data = userService.findAllUser();
    return ResponseEntity.ok(ResponseData.responseSucceess(data));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseData<Optional<UserDto>>> findUserById(@PathVariable Long id) {
    Optional<UserDto> data = userService.findUserById(id);
    return ResponseEntity.ok(ResponseData.responseSucceess(data));
  }

  @GetMapping("email/{email}")
  public ResponseEntity<ResponseData<Optional<UserDto>>> findUserByEmail(@PathVariable String email) {
    Optional<UserDto> data = userService.findUserByEmail(email);
    return ResponseEntity.ok(ResponseData.responseSucceess(data));
  }

  @PostMapping("/upload")
  public ResponseEntity<ResponseData<Object>> uploadImage(HttpServletRequest request,
      @RequestParam("file") MultipartFile file) {
    return ResponseEntity.ok(ResponseData.responseSucceess());
  }

  @PutMapping()
  public @ResponseBody ResponseEntity<ResponseData<Object>> updateUser(HttpServletRequest request,
      @RequestBody UserDto userDto) {
    User user = extractUser.extract(request);
    userService.updateUser(user, userDto);
    return ResponseEntity.ok(ResponseData.responseSucceess());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseData<Object>> deletUser(@PathVariable Long id) {
    userService.deleteUser(id);
    return ResponseEntity.ok(ResponseData.responseSucceess());
  }
}
