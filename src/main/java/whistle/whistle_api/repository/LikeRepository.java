package whistle.whistle_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import whistle.whistle_api.model.Like;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

  List<Like> findByPostId(Long postId);

  Optional<Like> findByPostIdAndUserId(Long postId, Long UserId);

  Long countByPostId(Long postId);

  void deleteLikeByPostIdAndUserId(Long postId, Long userId);

  @Query("SELECT EXISTS (SELECT 1 FROM Like l WHERE l.user.id = ?1 AND l.post.id = ?2)")
  boolean existsByUserIdAndPostIdUsingQuery(Long userId, Long postId);
}
