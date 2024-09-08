package whistle.whistle_api.service;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import whistle.whistle_api.model.User;
import whistle.whistle_api.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class TestService {
  @Autowired
  UserRepository userRepository;

  @Autowired
  private final PasswordEncoder passwordEncoder;

  public void addUser(String name, String nickname, String email, String password, LocalDate dob, String role) {
    User user = User.builder().name(name).nickname(nickname).email(email)
        .password(passwordEncoder.encode(password)).dob(dob).role(role)
        .status(true)
        .createAt(new Date()).updateAt(new Date()).build();
    userRepository.save(user);
  }
}
