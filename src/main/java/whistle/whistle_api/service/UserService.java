package whistle.whistle_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import whistle.whistle_api.model.User;
import whistle.whistle_api.repository.UserRepository;

@Repository
@RequiredArgsConstructor
public class UserService {

  @Autowired
  private final UserRepository userRepository;

  public List<User> findAllUser() {
    List<User> users = userRepository.findAll();
    return users.stream().map(this::mapToUserResponse).toList();
  };

  public User findAUserById(Long id) {
    User user = userRepository.findById(id).orElseThrow();
    return user;
  };

  public List<User> findUserAndPost() {
    List<User> user = userRepository.findUserAndPost();
    return user.stream().map(this::mapToUserPost).toList();
  }

  public void updateUser(Long id, String name) {
    User user = userRepository.findById(id).orElseThrow();
    user.setName(name);
    userRepository.save(user);
  }

  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }

  private User mapToUserResponse(User user) {
    return User.builder().id(user.getId()).name(user.getName()).email(user.getEmail()).role(user.getRole()).build();
  }

  private User mapToUserPost(User user) {
    return User.builder().name(user.getName()).email(user.getEmail()).role(user.getRole()).posts(user.getPosts())
        .build();
  }
}
