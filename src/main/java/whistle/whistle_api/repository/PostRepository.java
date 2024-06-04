package whistle.whistle_api.repository;

import org.springframework.stereotype.Repository;

import whistle.whistle_api.model.Post;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
