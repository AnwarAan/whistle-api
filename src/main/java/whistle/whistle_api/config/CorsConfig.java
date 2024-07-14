package whistle.whistle_api.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {
  @Override
  public void addCorsMappings(@NonNull CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedOrigins("*")
        .allowedMethods("GET", "POST", "PUT", "DELET")
        .allowedHeaders("*");
    // .allowedOrigins("http://localhost:8080/*")
    // .allowedMethods("GET", "POST", "PUT", "DELET")
    // .allowedHeaders("*");
  }

  // @Bean
  // public CorsFilter corsFilter() {
  // UrlBasedCorsConfigurationSource source = new
  // UrlBasedCorsConfigurationSource();
  // CorsConfiguration config = new CorsConfiguration();
  // config.setAllowedOrigins(true); // Be cautious with 'true' in production
  // config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE",
  // "OPTIONS"));
  // config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept"));
  // source.registerCorsConfiguration("/**", config);
  // return new CorsFilter(source);
  // }
}
