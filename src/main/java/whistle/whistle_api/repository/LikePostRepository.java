package whistle.whistle_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import whistle.whistle_api.model.LikePost;

@Repository
public interface LikePostRepository extends JpaRepository<LikePost, Long> {

  List<LikePost> findByPostId(Long postId);

  Optional<LikePost> findByUserId(Long userId);

  Optional<LikePost> findByPostIdAndUserId(Long postId, Long UserId);

  Long countByPostId(Long postId);

  void deleteLikeByPostIdAndUserId(Long postId, Long userId);

}
