package whistle.whistle_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import whistle.whistle_api.model.FileDocument;

public interface FileDocumentRepository extends JpaRepository<FileDocument, Long> {
}
