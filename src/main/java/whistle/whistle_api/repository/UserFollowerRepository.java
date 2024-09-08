package whistle.whistle_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import whistle.whistle_api.model.User;
import whistle.whistle_api.model.UserFollower;

@Repository
public interface UserFollowerRepository extends JpaRepository<UserFollower, Long> {

  List<UserFollower> findByFollowerId(Long followerId);

  @Query("SELECT u FROM User u LEFT JOIN UserFollower uf ON u.id = uf.follower.id WHERE uf.followed.id = ?1")
  List<User> findFollower(Long userId);

  @Query("SELECT u FROM User u LEFT JOIN UserFollower uf ON u.id = uf.followed.id WHERE uf.follower.id = ?1")
  List<User> findFollowed(Long userId);

  Optional<UserFollower> findByFollowedIdAndFollowerId(Long followedId, Long followerId);

}
