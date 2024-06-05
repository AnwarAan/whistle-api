package whistle.whistle_api.model;

import java.util.Date;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
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
@Table(name = "t_post")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String post;

    @Column(name = "image_url", length = 1000)
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @CreatedDate
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @Column(name = "update_at")
    @UpdateTimestamp
    private Date updatedAt;
}
