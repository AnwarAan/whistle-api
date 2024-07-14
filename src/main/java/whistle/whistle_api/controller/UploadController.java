package whistle.whistle_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import whistle.whistle_api.dto.ResponseData;
import whistle.whistle_api.service.UploadService;

@RestController
@RequestMapping("/api/upload")
public class UploadController {

  @Autowired
  UploadService uploadService;

  @PostMapping
  public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
    String msg = "";
    try {
      uploadService.save(file);
      msg = "Success Upload File " + file.getOriginalFilename();
      return ResponseEntity.ok(msg);
    } catch (Exception e) {
      msg = "Failed Upload File " + file.getOriginalFilename() + ", Error " + e.getMessage();
      return ResponseEntity.ok(msg);
    }
  }

  @GetMapping("/files/{filename}")
  public ResponseEntity<ResponseData<Resource>> getFile(@PathVariable String filename) {
    Resource file = uploadService.load(filename);
    return ResponseEntity.ok(ResponseData.responseSucceess(file.getFilename()));
  }
}
