package whistle.whistle_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import whistle.whistle_api.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

  Optional<User> findByNickname(String nickname);

  Optional<User> findUserAndFollowersByNickname(String nickname);

  @Query("SELECT u FROM User u LEFT JOIN FETCH u.posts")
  List<User> findUserAndPost();
}
