package whistle.whistle_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import whistle.whistle_api.model.FileImage;

public interface FileImageRepository extends JpaRepository<FileImage, Long> {
  Optional<FileImage> findByName(String fileName);
}
