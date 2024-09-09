package whistle.whistle_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "t_like_post")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikePost {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long count;

  @ManyToOne
  @JoinColumn(name = "user_id")
  @JsonIgnore
  private User user;

  @ManyToOne
  @JoinColumn(name = "post_id")
  @JsonIgnore
  private Post post;

  private Boolean status;

}
