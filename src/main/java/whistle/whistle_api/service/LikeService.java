package whistle.whistle_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import whistle.whistle_api.exception.ForbiddenException;
import whistle.whistle_api.exception.NotFoundException;
import whistle.whistle_api.model.Like;
import whistle.whistle_api.model.Post;
import whistle.whistle_api.model.User;
import whistle.whistle_api.repository.LikeRepository;

@Service
public class LikeService {
  @Autowired
  private LikeRepository likeRepository;

  @Autowired
  private PostService postService;

  public List<Like> findAllLike() {
    List<Like> likes = likeRepository.findAll();
    return likes.stream().map(this::mapToResponse).toList();
  }

  public List<Like> findLikeByPostId(Long postId) {
    List<Like> likes = likeRepository.findByPostId(postId);
    return likes;
  }

  public void likePost(User user, Long postId) {
    if (likeRepository.existsByUserIdAndPostIdUsingQuery(user.getId(), postId)) {
      throw new ForbiddenException("User has Linked to Post");
    } else {
      Post post = postService.findPostById(postId);
      Like like = Like.builder().user(user).post(post).build();
      likeRepository.save(like);
      post.setLikeCount(post.getLikeCount() + 1);
      postService.updatePost(post);
    }
  }

  @Transactional
  public void dislikePost(User user, Long postId) {
    Optional<Like> chekLike = likeRepository.findByPostIdAndUserId(user.getId(), postId);
    if (!chekLike.isPresent()) {
      throw new NotFoundException("Like");
    } else {
      Post post = postService.findPostById(postId);
      post.setLikeCount(post.getLikeCount() - 1);
      likeRepository.deleteLikeByPostIdAndUserId(user.getId(), postId);
    }
  }

  public Long eventLike(Long postId) {
    Long count = likeRepository.countByPostId(postId);
    return count;
  }

  private Like mapToResponse(Like like) {
    return Like.builder().id(like.getId()).build();
  }

}
