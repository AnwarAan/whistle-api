package whistle.whistle_api.repository;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import whistle.whistle_api.model.Post;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PostRepositoryTest {
  @Autowired
  private PostRepository postRepository;

  Post post = Post.builder().post("docker").imageUrl("here").build();
  Post post2 = Post.builder().post("docker").imageUrl("here").build();
  Post post3 = Post.builder().post("docker").imageUrl("here").build();

  @Test
  public void testCreatePost() {
    Post saved = postRepository.save(post);

    Assertions.assertThat(saved).isNotNull();
    Assertions.assertThat(saved.getId()).isGreaterThan(0);
  }

  @Test
  public void findAllPost() {
    postRepository.save(post);
    postRepository.save(post2);
    postRepository.save(post3);
    List<Post> posts = postRepository.findAll();

    Assertions.assertThat(posts).isNotNull();
    Assertions.assertThat(posts.size()).isGreaterThan(1);
  }

  @Test
  public void findPostById() {
    postRepository.save(post);
    postRepository.save(post);
    Post result = postRepository.findById(post.getId()).get();

    Assertions.assertThat(result).isNotNull();
  }

  @Test
  public void updatePost() {
    postRepository.save(post);
    Post getPost = postRepository.findById(post.getId()).get();
    getPost.setImageUrl("new");
    Post updatePost = postRepository.save(getPost);

    Assertions.assertThat(updatePost.getImageUrl()).isNotNull();
    Assertions.assertThat(updatePost.getImageUrl()).isEqualTo("new");
  }

  @Test
  public void deletePost() {
    postRepository.save(post);
    postRepository.deleteById(post.getId());
    Optional<Post> result = postRepository.findById(post.getId());

    Assertions.assertThat(result).isEmpty();
  }

}
