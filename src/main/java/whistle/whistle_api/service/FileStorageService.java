package whistle.whistle_api.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import whistle.whistle_api.exception.NotFoundException;
import whistle.whistle_api.model.FileDocument;
import whistle.whistle_api.model.FileImage;
import whistle.whistle_api.repository.FileDocumentRepository;
import whistle.whistle_api.repository.FileImageRepository;

@Service
@RequiredArgsConstructor
public class FileStorageService {

  @Autowired
  private FileImageRepository fileImageRepository;
  @Autowired
  private FileDocumentRepository fileDocumentRepository;

  private final String PATH_IMAGES = System.getProperty("user.dir") + "/assets/images/";
  private final String PATH_DOCUMENT = System.getProperty("user.dir") + "/assets/files/";

  public FileImage uploadImage(MultipartFile file) throws IOException {
    String filePath = PATH_IMAGES + System.currentTimeMillis() + "-" + file.getOriginalFilename();
    FileImage fileImage = fileImageRepository
        .save(
            FileImage.builder().name(file.getOriginalFilename()).type(file.getContentType()).filePath(filePath)
                .build());
    file.transferTo(new File(filePath));
    if (fileImage != null)
      return fileImage;
    return null;
  }

  public byte[] downloadImage(Long id) throws IOException {
    // String path =
    // "/Users/mcnwr76/Documents/GitHub/sandbox/whistle-api/assets/images/home.png";
    Optional<FileImage> fileImage = fileImageRepository.findById(id);
    if (fileImage.isEmpty())
      throw new NotFoundException();
    String path = fileImage.get().getFilePath();
    byte[] images = Files.readAllBytes(new File(path).toPath());
    return images;
  }

  public String uploadDocument(MultipartFile file) throws IOException {
    String filePath = PATH_DOCUMENT + file.getOriginalFilename();
    FileImage fileData = fileImageRepository
        .save(
            FileImage.builder().name(file.getOriginalFilename()).type(file.getContentType()).filePath(filePath)
                .build());
    file.transferTo(new File(filePath));
    if (fileData != null)
      return filePath;
    return null;
  }

  public byte[] downloadDocument(Long id) throws IOException {
    Optional<FileDocument> fileDocument = fileDocumentRepository.findById(id);
    String path = fileDocument.get().getFilePath();
    byte[] images = Files.readAllBytes(new File(path).toPath());
    return images;
  }
}
