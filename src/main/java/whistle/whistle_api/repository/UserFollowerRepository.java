package whistle.whistle_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import whistle.whistle_api.model.UserFollower;

@Repository
public interface UserFollowerRepository extends JpaRepository<UserFollower, Long> {

  Optional<UserFollower> findUserFollowerByUserIdAndFollowerId(Long userId, Long followerId);

  List<UserFollower> findUserFollowerByUserId(Long userId);

  @Transactional
  void deleteUserFollowerByUserIdAndFollowerId(Long userId, Long followerId);
}
