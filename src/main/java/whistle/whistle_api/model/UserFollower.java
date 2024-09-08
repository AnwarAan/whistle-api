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
@Table(name = "follower")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserFollower {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "follower_id", unique = true)
  private User follower;

  @ManyToOne
  @JoinColumn(name = "followed_id", unique = true)
  private User followed;

  private Boolean status;

}
