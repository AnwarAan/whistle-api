package whistle.whistle_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import whistle.whistle_api.dto.ResponseData;
import whistle.whistle_api.helper.ExtractUser;
import whistle.whistle_api.model.Like;
import whistle.whistle_api.model.User;
import whistle.whistle_api.service.LikeService;

@RestController
@RequestMapping("/api/like")
@RequiredArgsConstructor
public class LikeController {
  @Autowired
  private final LikeService likeService;

  @Autowired
  private final ExtractUser extractUser;

  @GetMapping
  public ResponseEntity<ResponseData<List<Like>>> findAllLike() {
    List<Like> likes = likeService.findAllLike();
    return ResponseEntity.ok(ResponseData.responseSucceess(likes));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseData<List<Like>>> findLikeByPostId(@PathVariable Long id) {
    List<Like> likes = likeService.findLikeByPostId(id);
    return ResponseEntity.ok(ResponseData.responseSucceess(likes));
  }

  @GetMapping("/count/{id}")
  public ResponseEntity<ResponseData<Long>> countLike(@PathVariable Long id) {
    Long count = likeService.eventLike(id);
    return ResponseEntity.ok(ResponseData.responseSucceess(count));
  }

  @PostMapping
  @CrossOrigin("http://localhost:3000")
  public ResponseEntity<ResponseData<Object>> createLike(HttpServletRequest request, @RequestParam Long postId) {
    User user = extractUser.extract(request);
    likeService.likePost(user, postId);
    return ResponseEntity.ok(ResponseData.responseSucceess());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseData<Object>> dislikePost(HttpServletRequest request, @PathVariable Long id) {
    User user = extractUser.extract(request);
    likeService.dislikePost(user, id);
    return ResponseEntity.ok(ResponseData.responseSucceess());
  }
}
