package whistle.whistle_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import whistle.whistle_api.dto.ResponseData;
import whistle.whistle_api.dto.UserFollowerDto;
import whistle.whistle_api.helper.ExtractUser;
import whistle.whistle_api.model.User;
import whistle.whistle_api.service.UserFollowerService;

@RestController
@RequestMapping("/api/follow")
@RequiredArgsConstructor
public class UserFollowerController {

  @Autowired
  private final UserFollowerService followService;

  @Autowired
  private final ExtractUser extractUser;

  @GetMapping()
  public ResponseEntity<ResponseData<List<UserFollowerDto>>> getFollows(HttpServletRequest request) {
    User user = extractUser.extract(request);
    List<UserFollowerDto> follows = followService.getUserFollower(user.getId());
    return ResponseEntity.ok(ResponseData.responseSucceess(follows));
  }

  @PostMapping("/follow")
  public ResponseEntity<ResponseData<Object>> follow(HttpServletRequest request, @RequestParam Long followerId) {
    User user = extractUser.extract(request);
    followService.follow(user, followerId);
    return ResponseEntity.ok(ResponseData.responseSucceess());
  }

  @DeleteMapping("/unfollow")
  public ResponseEntity<ResponseData<Object>> unfollow(HttpServletRequest request, @RequestParam Long followerId) {
    User user = extractUser.extract(request);
    followService.unfollow(user.getId(), followerId);
    return ResponseEntity.ok(ResponseData.responseSucceess());
  }
}
