package whistle.whistle_api.repository;

import org.springframework.stereotype.Repository;

import whistle.whistle_api.model.Post;
import whistle.whistle_api.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUser(User user);
}
