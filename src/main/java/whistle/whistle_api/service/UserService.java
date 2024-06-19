package whistle.whistle_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import whistle.whistle_api.model.User;
import whistle.whistle_api.repository.PostRepository;
import whistle.whistle_api.repository.UserRepository;

@Repository
@RequiredArgsConstructor
public class UserService {

  @Autowired
  private final UserRepository userRepository;

  @Autowired
  private final PostRepository postRepository;

  public List<User> findAllUser() {
    List<User> users = userRepository.findAll();
    return users.stream().map(this::mapToUserResponse).toList();
  };

  public User findAUserById(Long id) {
    User user = userRepository.findById(id).orElseThrow();
    return user;
  };

  public void updateUser(Long id, String name) {
    User user = userRepository.findById(id).orElseThrow();
    user.setName(name);
    userRepository.save(user);
  }

  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }

  private User mapToUserResponse(User user) {
    return User.builder().id(user.getId()).name(user.getName()).email(user.getEmail()).role(user.getRole())
        .posts(postRepository.findByUser(user)).createAt(user.getCreateAt()).updateAt(user.getUpdateAt()).build();
  }

}
