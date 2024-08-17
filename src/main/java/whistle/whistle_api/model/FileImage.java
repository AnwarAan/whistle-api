package whistle.whistle_api.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "file_image")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileImage {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String type;
  @Column(name = "file_path")
  private String filePath;

  @OneToMany(mappedBy = "fileImage", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Post> posts;

}
