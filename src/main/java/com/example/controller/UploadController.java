package com.example.controller;

import com.example.demo.dto.UploadCloudinaryResponse;
import com.example.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
public class UploadController {

    private final CloudinaryService cloudinaryService;

    @Autowired
    public UploadController(CloudinaryService cloudinaryService) {
        this.cloudinaryService = cloudinaryService;
    }

    @PostMapping
    public ResponseEntity<UploadCloudinaryResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String fileUrl = cloudinaryService.uploadFile(file);
            UploadCloudinaryResponse response = new UploadCloudinaryResponse("success", fileUrl);
            return ResponseEntity.ok(response); // atau accepted() jika mau pakai 202
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(new UploadCloudinaryResponse("Upload file gagal: " + e.getMessage(), null));
        }
    }
}
