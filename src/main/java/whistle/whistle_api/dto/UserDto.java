package whistle.whistle_api.dto;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
  private final Long id;
  private final String name;
  private final String email;
  private final String password;
  private final String imageUrl;
  private final String imageId;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private final LocalDate dob;
  private final String role;
  private final boolean status;
  private final Date createdAt;
  private final Date updatedAt;
}
