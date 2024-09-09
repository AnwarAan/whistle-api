package whistle.whistle_api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LikePostDto {
    private Long id;
    private Long userId;
    private Long postId;
    private Boolean status;
}
