package whistle.whistle_api.controller;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import whistle.whistle_api.dto.PostDto;
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
    public ResponseEntity<ResponseData<List<PostDto>>> findAllPost(HttpServletRequest request) {
        User user = extractUser.extract(request);
        List<PostDto> posts = postService.findPostByUserId(user.getId());
        return ResponseEntity.ok(ResponseData.responseSucceess(posts));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Post>> findPostById(@PathVariable Long id) {
        Post post = postService.findPostById(id);
        return ResponseEntity.ok(ResponseData.responseSucceess(post));
    }

    @PostMapping
    public ResponseEntity<ResponseData<Object>> createPost(HttpServletRequest request,
            @RequestParam String content,
            @RequestParam("image") MultipartFile file) throws IOException {
        User user = extractUser.extract(request);
        postService.createPost(user, content, file);
        return ResponseEntity.ok(ResponseData.responseSucceess());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<?>> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.ok(ResponseData.responseSucceess());
    }
}
