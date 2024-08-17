package whistle.whistle_api.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PostDto {
  private final Long id;
  private final String content;
  private final String imageUrl;
  private final Long likeCount;
  private final Long imageId;
  private final MultipartFile file;
  private final Date createdAt;
  private final Date updatedAt;
  private final String userName;
  private final Long userImageId;

}
