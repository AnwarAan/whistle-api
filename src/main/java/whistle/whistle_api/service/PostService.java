package whistle.whistle_api.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import whistle.whistle_api.model.Post;
import whistle.whistle_api.model.User;
import whistle.whistle_api.repository.PostRepository;
import whistle.whistle_api.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class PostService {
    @Autowired
    private final PostRepository postRepository;

    @Autowired
    private final UserRepository userRepository;

    public List<Post> findAllPost() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(this::mapToPost).toList();
    }

    public Post findPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        return post;
    }

    public void createPost(Long id, String post, String imageUrl) {
        User user = userRepository.findById(id).orElseThrow();
        Post posts = Post.builder().post(post).imageUrl(imageUrl).user(user).createdAt(new Date()).updatedAt(new Date())
                .build();

        user.getPosts().add(posts);
        postRepository.save(posts);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    private Post mapToPost(Post post) {
        return Post.builder().id(post.getId()).post(post.getPost()).imageUrl(post.getImageUrl())
                .build();
    }

}
