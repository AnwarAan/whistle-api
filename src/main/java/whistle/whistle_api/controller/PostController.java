package whistle.whistle_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import whistle.whistle_api.dto.ResponseData;
import whistle.whistle_api.model.Post;
import whistle.whistle_api.service.PostService;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    public ResponseEntity<ResponseData<List<Post>>> findAllPost() {
        List<Post> posts = postService.findAllPost();
        return ResponseEntity.ok(ResponseData.responseSucceess(posts));
    }

    public ResponseEntity<ResponseData<Post>> findPostById(@PathVariable Long id) {
        Post post = postService.findPostById(id);
        return ResponseEntity.ok(ResponseData.responseSucceess(post));
    }

    public ResponseEntity<ResponseData<Object>> createPost(@RequestParam String post, @RequestParam String imageUrl) {
        postService.createPost(post, imageUrl);
        return ResponseEntity.ok(ResponseData.responseSucceess());
    }

    public ResponseEntity<ResponseData<Object>> deletePost(@PathVariable Long id) {
        return ResponseEntity.ok(ResponseData.responseSucceess());
    }
}
