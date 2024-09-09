package whistle.whistle_api.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import whistle.whistle_api.dto.PostDto;
import whistle.whistle_api.exception.NotFoundException;
import whistle.whistle_api.model.FileImage;
import whistle.whistle_api.model.Post;
import whistle.whistle_api.model.User;
import whistle.whistle_api.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class PostService {
    @Autowired
    private final PostRepository postRepository;

    @Autowired
    private final FileStorageService storageService;

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

    public Post createPost(User user, String content, MultipartFile file) throws IOException {
        FileImage fileImage = storageService.uploadImage(file);
        Post posts = Post.builder().content(content).imageUrl(fileImage.getFilePath()).user(user).createdAt(new Date())
                .fileImage(fileImage)
                .updatedAt(new Date())
                .build();
        user.getPosts().add(posts);
        Post result = postRepository.save(posts);
        return result;
    }

    public void updatePost(Post post) {
        Post posts = Post.builder().totalLike(post.getTotalLike()).build();
        postRepository.save(posts);
    }

    public void deletePost(Long id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isEmpty())
            throw new NotFoundException("Post ID: " + id);
        postRepository.deleteById(id);
    }

    private PostDto mapToPost(Post post) {
        return PostDto.builder().id(post.getId()).content(post.getContent()).imageUrl(post.getImageUrl())
                .likeCount(post.getTotalLike()).createdAt(post.getCreatedAt()).updatedAt(post.getUpdatedAt())
                .imageId(post.getFileImage() == null ? null : post.getFileImage().getId())
                .username(post.getUser().getName())
                .userImageId(post.getUser().getFileImage() == null ? null : post.getUser().getFileImage().getId())
                .build();
    }

}
