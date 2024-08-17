package whistle.whistle_api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {
  @Value("${application.security.cors.link_client}")
  private String linkClient;

  @Override
  public void addCorsMappings(@NonNull CorsRegistry registry) {
    registry.addMapping("/*")
        .allowedOrigins(linkClient)
        .allowedMethods("GET", "POST", "PUT", "DELET")
        .allowedHeaders("Authorization", "Content-Type")
        .allowCredentials(true);
  }

}
