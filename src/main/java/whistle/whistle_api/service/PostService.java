package whistle.whistle_api.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import whistle.whistle_api.dto.PostDto;
import whistle.whistle_api.exception.NotFoundException;
import whistle.whistle_api.model.Post;
import whistle.whistle_api.model.User;
import whistle.whistle_api.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class PostService {
    @Autowired
    private final PostRepository postRepository;

    public List<PostDto> findPostByUserId(Long userId) {
        List<Post> posts = postRepository.findPostByUserId(userId);
        return posts.stream().map(this::mapToPost).toList();
    }

    public Post findPostById(Long id) {
        Optional<Post> post = postRepository.findById(id);
        if (!post.isPresent())
            throw new NotFoundException();
        return post.get();
    }

    public Post createPost(User user, String post, String imageUrl) {
        Post posts = Post.builder().content(post).imageUrl(imageUrl).user(user).createdAt(new Date())
                .updatedAt(new Date())
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

    private PostDto mapToPost(Post post) {
        return PostDto.builder().id(post.getId()).content(post.getContent()).imgeUrl(post.getImageUrl())
                .likeCount(post.getLikeCount()).createdAt(post.getCreatedAt()).updatedAt(post.getUpdatedAt())
                .build();
    }

}
