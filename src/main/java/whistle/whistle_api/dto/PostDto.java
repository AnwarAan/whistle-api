package whistle.whistle_api.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PostDto {
  private final Long id;
  private final String content;
  private final String imgeUrl;
  private final Long likeCount;
  private final Date createdAt;
  private final Date updatedAt;

}
