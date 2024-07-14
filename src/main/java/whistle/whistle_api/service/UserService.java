package whistle.whistle_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import whistle.whistle_api.dto.UserDto;
import whistle.whistle_api.model.User;
import whistle.whistle_api.repository.UserRepository;

@Repository
@RequiredArgsConstructor
public class UserService {

  @Autowired
  private final UserRepository userRepository;

  public List<UserDto> findAllUser() {
    List<User> users = userRepository.findAll();
    return users.stream().map(this::mapUser).toList();
  };

  public User findUserById(Long id) {
    User user = userRepository.findById(id).orElseThrow();
    return user;
  };

  public User findUserByEmail(String email) {
    User user = userRepository.findByEmail(email).orElseThrow();
    return user;
  };

  public void updateUser(User user, UserDto userDto) {
    User updatedUser = user;
    updatedUser.setName(userDto.getName());
    updatedUser.setImageUr(userDto.getName());
    updatedUser.setRole(userDto.getName());
    userRepository.save(updatedUser);
  }

  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }

  private UserDto mapUser(User user) {
    return UserDto.builder().id(user.getId()).name(user.getName()).email(user.getEmail()).role(user.getRole())
        .status(user.getStatus())
        .createdAt(user.getCreateAt()).updatedAt(user.getUpdateAt()).build();
  }

}
