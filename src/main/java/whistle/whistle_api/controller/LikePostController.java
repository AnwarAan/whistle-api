package whistle.whistle_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import whistle.whistle_api.dto.LikePostDto;
import whistle.whistle_api.dto.ResponseData;
import whistle.whistle_api.helper.ExtractUser;
import whistle.whistle_api.model.LikePost;
import whistle.whistle_api.model.User;
import whistle.whistle_api.service.LikePostService;

@RestController
@RequestMapping("/api/like")
@RequiredArgsConstructor
public class LikePostController {
  @Autowired
  private final LikePostService likeService;

  @Autowired
  private final ExtractUser extractUser;

  @GetMapping
  public ResponseEntity<ResponseData<List<LikePostDto>>> findAllLike() {
    List<LikePostDto> likes = likeService.findAllLike();
    return ResponseEntity.ok(ResponseData.responseSucceess(likes));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseData<List<LikePost>>> findLikeByPostId(@PathVariable Long id) {
    List<LikePost> likes = likeService.findLikeByPostId(id);
    return ResponseEntity.ok(ResponseData.responseSucceess(likes));
  }

  @PostMapping
  @CrossOrigin("http://localhost:3000")
  public ResponseEntity<ResponseData<Object>> likeOrDislike(HttpServletRequest request, @RequestParam Long postId) {
    User user = extractUser.extract(request);
    likeService.likeOrDislike(user, postId);
    return ResponseEntity.ok(ResponseData.responseSucceess());
  }

}
