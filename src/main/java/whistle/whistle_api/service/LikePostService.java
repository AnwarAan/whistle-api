package whistle.whistle_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import whistle.whistle_api.dto.LikePostDto;
import whistle.whistle_api.model.LikePost;
import whistle.whistle_api.model.Post;
import whistle.whistle_api.model.User;
import whistle.whistle_api.repository.LikePostRepository;

@Service
@RequiredArgsConstructor
public class LikePostService {
  @Autowired
  private final LikePostRepository likeRepository;

  @Autowired
  private final PostService postService;

  public List<LikePostDto> findAllLike() {
    List<LikePost> likes = likeRepository.findAll();
    return likes.stream().map(this::mapLikePost).toList();
  }

  public List<LikePost> findLikeByPostId(Long postId) {
    List<LikePost> likes = likeRepository.findByPostId(postId);
    return likes;
  }

  @Transactional
  public void likeOrDislike(User user, Long postId) {
    Optional<LikePost> getLike = likeRepository.findByPostIdAndUserId(postId, user.getId());
    Post post = postService.findPostById(postId);
    LikePost like;

    if (getLike.isEmpty()) {
      like = LikePost.builder().user(user).post(post).status(true).build();
      likeRepository.save(like);
      post.setTotalLike(post.getTotalLike() + 1);
    } else if (getLike.isPresent() && getLike.get().getStatus() == false) {
      like = getLike.get();
      like.setStatus(true);
      post.setTotalLike(post.getTotalLike() + 1);
      likeRepository.save(like);
    } else if (getLike.isPresent() && getLike.get().getStatus() == true) {
      like = getLike.get();
      like.setStatus(false);
      post.setTotalLike(post.getTotalLike() - 1);
      likeRepository.save(like);
    }

    postService.updatePost(post);
  }

  private LikePostDto mapLikePost(LikePost like) {
    return LikePostDto.builder().id(like.getId()).userId(like.getUser().getId()).postId(like.getPost().getId())
        .status(like.getStatus()).build();
  }
}
