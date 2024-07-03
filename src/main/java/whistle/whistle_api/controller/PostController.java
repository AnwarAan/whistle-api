package whistle.whistle_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import whistle.whistle_api.model.Post;
import whistle.whistle_api.model.User;
import whistle.whistle_api.service.PostService;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    @Autowired
    private final PostService postService;

    @Autowired
    private final ExtractUser extractUser;

    @GetMapping
    public ResponseEntity<ResponseData<List<Post>>> findAllPost() {
        List<Post> posts = postService.findAllPost();
        return ResponseEntity.ok(ResponseData.responseSucceess(posts));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Post>> findPostById(@PathVariable Long id) {
        Post post = postService.findPostById(id);
        return ResponseEntity.ok(ResponseData.responseSucceess(post));
    }

    @PostMapping
    public ResponseEntity<ResponseData<Object>> createPost(HttpServletRequest request,
            @RequestParam String post,
            @RequestParam String imageUrl) {
        User user = extractUser.extractUser(request);
        postService.createPost(user, post, imageUrl);
        return ResponseEntity.ok(ResponseData.responseSucceess());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Object>> deletePost(@PathVariable Long id) {
        return ResponseEntity.ok(ResponseData.responseSucceess());
    }
}
