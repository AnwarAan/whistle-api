package whistle.whistle_api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserFollowerDto {
  private final Long id;
  private final String name;
  private final Long followedId;
  private final Long followerId;
  private final Boolean status;
}
