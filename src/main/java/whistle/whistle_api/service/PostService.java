package whistle.whistle_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import whistle.whistle_api.model.Post;
import whistle.whistle_api.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<Post> findAllPost() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(this::mapToPost).toList();
    }

    public Post findPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        return post;
    }

    public void createPost(String post, String imageUrl) {
        Post posts = Post.builder().post(post).imageUrl(imageUrl).build();
        postRepository.save(posts);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    private Post mapToPost(Post post) {
        return Post.builder().post(post.getPost()).imageUrl(post.getImageUrl()).build();
    }

}
