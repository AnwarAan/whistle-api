package whistle.whistle_api.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.var;
import whistle.whistle_api.dto.ResponseAuth;
import whistle.whistle_api.dto.UserDto;
import whistle.whistle_api.exception.ForbiddenException;
import whistle.whistle_api.exception.NotFoundException;
import whistle.whistle_api.helper.EmailAlreadyExistException;
import whistle.whistle_api.model.User;
import whistle.whistle_api.repository.UserRepository;
import whistle.whistle_api.security.JwtService;

@Service
@RequiredArgsConstructor
public class AuthService {

  @Autowired
  private final UserRepository userRepository;
  @Autowired
  private final PasswordEncoder passwordEncoder;
  @Autowired
  private final AuthenticationManager authenticationManager;
  @Autowired
  private final JwtService jwtService;

  public User register(String name, String email, String password, String role) {
    Optional<User> checkUser = userRepository.findByEmail(email);
    if (checkUser.isPresent()) {
      throw new ForbiddenException("Email Already");
    }

    User user = User.builder().name(name).email(email).password(passwordEncoder.encode(password)).role(role)
        .createAt(new Date()).updateAt(new Date()).build();
    userRepository.save(user);
    return user;
  }

  public ResponseAuth login(UserDto userBody) throws RuntimeException {
    try {

      var auth = authenticationManager
          .authenticate(new UsernamePasswordAuthenticationToken(userBody.getEmail(), userBody.getPassword()));
      System.out.println(auth.isAuthenticated());
      if (!auth.isAuthenticated())
        throw new EmailAlreadyExistException();
      Optional<User> user = userRepository.findByEmail(userBody.getEmail());
      System.out.println("==========================");
      String jwtToken = jwtService.generateToken(user.get());
      String refreshToken = jwtService.generateRefreshToken(user.get());
      ResponseAuth responseAuth = ResponseAuth.builder().accessToken(jwtToken).refreshToken(refreshToken).build();
      return responseAuth;
    } catch (Exception e) {
      throw new ForbiddenException(e.getMessage());
    }
  }

  public void activateUser(Long userId) {
    Optional<User> user = userRepository.findById(userId);
    if (user.isEmpty())
      throw new NotFoundException();
    User activateUser = user.get();
    activateUser.setStatus(true);
    userRepository.save(activateUser);

    // User user = userRepository.findById(userId).orElseThrow();
    // user.setStatus(true);
    // userRepository.save(user);
  }
}
