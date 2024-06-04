package whistle.whistle_api.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import whistle.whistle_api.dto.ResponseAuth;
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

  public void register(String name, String email, String password, String role) {
    Optional<User> checkUser = userRepository.findByEmail(email);
    if (checkUser.isPresent()) {
      throw new EmailAlreadyExistException();
    }

    User user = User.builder().name(name).email(email).password(passwordEncoder.encode(password)).role(role)
        .createAt(new Date()).updateAt(new Date()).build();
    System.out.println("====================" + user + "====================");
    userRepository.save(user);
  }

  public ResponseAuth login(String email, String password) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    User user = userRepository.findByEmail(email).orElseThrow();
    String jwtToken = jwtService.generateToken(user);
    String refreshToken = jwtService.generateRefreshToken(user);
    ResponseAuth responseAuth = ResponseAuth.builder().accessToken(jwtToken).refreshToken(refreshToken).build();
    return responseAuth;
  }
}
