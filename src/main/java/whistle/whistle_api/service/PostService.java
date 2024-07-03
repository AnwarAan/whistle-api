package whistle.whistle_api.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import whistle.whistle_api.model.Post;
import whistle.whistle_api.model.User;
import whistle.whistle_api.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class PostService {
    @Autowired
    private final PostRepository postRepository;

    public List<Post> findAllPost() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(this::mapToPost).toList();
    }

    public Post findPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        return post;
    }

    public Post createPost(User user, String post, String imageUrl) {
        Post posts = Post.builder().post(post).imageUrl(imageUrl).user(user).createdAt(new Date()).updatedAt(new Date())
                .build();

        user.getPosts().add(posts);
        Post result = postRepository.save(posts);
        return result;
    }

    public void updatePost(Post post) {
        Post posts = Post.builder().likeCount(post.getLikeCount()).build();
        postRepository.save(posts);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    private Post mapToPost(Post post) {
        return Post.builder().id(post.getId()).post(post.getPost()).imageUrl(post.getImageUrl())
                .likeCount(post.getLikeCount())
                .createdAt(post.getCreatedAt()).updatedAt(post.getUpdatedAt())
                .build();
    }

}
