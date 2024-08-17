package whistle.whistle_api.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import whistle.whistle_api.dto.ResponseData;
import whistle.whistle_api.service.FileStorageService;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileStorageController {
  @Autowired
  private FileStorageService fileDataService;

  @PostMapping("/upload-image")
  public ResponseEntity<ResponseData<?>> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
    fileDataService.uploadImage(file);
    return ResponseEntity.ok(ResponseData.responseSucceess());
  }

  @GetMapping("/download-image/{id}")
  public ResponseEntity<?> downloadImage(@PathVariable Long id) throws IOException {
    byte[] image = fileDataService.downloadImage(id);
    return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
  }

  @PostMapping("/upload-document")
  public ResponseEntity<ResponseData<?>> uploadDocument(@RequestParam("file") MultipartFile file) throws IOException {
    fileDataService.uploadImage(file);
    return ResponseEntity.ok(ResponseData.responseSucceess());
  }

  @GetMapping("/download-document/{id}")
  public ResponseEntity<?> downloadDocument(@PathVariable Long id) throws IOException {
    byte[] image = fileDataService.downloadImage(id);
    return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
  }

  // @GetMapping("/download-image/coba/${fileName}")
  // public ResponseEntity<?> downloadImage(@PathVariable String fileName) throws
  // IOException {
  // byte[] image = fileDataService.downloadImage2(fileName);
  // return
  // ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
  // }
}
