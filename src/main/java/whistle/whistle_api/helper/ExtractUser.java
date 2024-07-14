package whistle.whistle_api.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import whistle.whistle_api.model.User;
import whistle.whistle_api.repository.UserRepository;
import whistle.whistle_api.security.JwtService;

@Component
@RequiredArgsConstructor
public class ExtractUser {

  @Autowired
  private final JwtService jwtService;

  @Autowired
  private UserRepository userRepository;

  public User extract(@NonNull HttpServletRequest request) {
    String authHeader = request.getHeader("Authorization");
    String jwtToken;
    String userEmail;
    jwtToken = authHeader.substring(7);
    userEmail = jwtService.extractUsername(jwtToken);
    User user = userRepository.findByEmail(userEmail).orElseThrow();
    return user;
  }

}
