package whistle.whistle_api.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import whistle.whistle_api.dto.UserDto;
import whistle.whistle_api.exception.NotFoundException;
import whistle.whistle_api.model.FileImage;
import whistle.whistle_api.model.User;
import whistle.whistle_api.repository.UserRepository;

@Repository
@RequiredArgsConstructor
public class UserService {

  @Autowired
  private final UserRepository userRepository;

  @Autowired
  private final FileStorageService fileStorageService;

  public List<UserDto> findAllUser() {
    List<User> users = userRepository.findAll();
    return users.stream().map(this::mapUser).toList();
  };

  public Optional<UserDto> findUserById(Long id) {
    Optional<User> user = userRepository.findById(id);
    return List.of(user.get()).stream().map(this::mapUser).findFirst();

  };

  public Optional<UserDto> findUserByEmail(String email) {
    Optional<User> user = userRepository.findByEmail(email);
    if (user.isEmpty())
      throw new NotFoundException("User");
    else
      return List.of(user.get()).stream().map(this::mapUser).findFirst();
  };

  public UserDto findUserByNickname(String nickname) {
    Optional<User> user = userRepository.findUserAndFollowersByNickname(nickname);
    if (user.isEmpty())
      throw new NotFoundException("User");
    else {
      UserDto userDto = UserDto.builder().id(user.get().getId()).name(user.get().getName())
          .nickname(user.get().getNickname())
          .email(user.get().getEmail())
          .role(user.get().getRole())
          .status(user.get().getStatus())
          .followed(user.get().getTotalFollower())
          .follower(user.get().getTotalFollowed())
          .createdAt(user.get().getCreateAt()).updatedAt(user.get().getUpdateAt()).build();
      return userDto;
    }
  };

  public void updateUser(User user, UserDto userDto) {
    User updatedUser = user;
    updatedUser.setName(userDto.getName());
    updatedUser.setImageUrl(userDto.getName());
    updatedUser.setRole(userDto.getName());
    userRepository.save(updatedUser);
  }

  public void uploadImageUser(User user, MultipartFile file) throws IOException {
    FileImage fileImage = fileStorageService.uploadImage(file);
    User upadtedUser = user;
    upadtedUser.setImageUrl(fileImage.getFilePath());
    userRepository.save(upadtedUser);
  }

  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }

  private UserDto mapUser(User user) {
    return UserDto.builder().id(user.getId()).name(user.getName()).nickname(user.getNickname()).email(user.getEmail())
        .followed(user.getTotalFollowed())
        .follower(user.getTotalFollower())
        .role(user.getRole())
        .status(user.getStatus())
        .createdAt(user.getCreateAt()).updatedAt(user.getUpdateAt()).build();
  }

}
