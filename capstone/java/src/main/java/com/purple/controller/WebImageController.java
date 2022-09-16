package com.purple.controller;

import com.purple.handler.WebImageHandlerable;
import com.purple.model.WebImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class WebImageController extends BaseController {

    private final WebImageHandlerable webImageHandler;

    @Autowired
    public WebImageController(WebImageHandlerable webImageHandler){
        this.webImageHandler = webImageHandler;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<WebImage> uploadFile(@RequestParam MultipartFile file) {

        WebImage webImage = new WebImage();
        webImage.setFileName(file.getOriginalFilename());
        webImage.setMimeType(file.getContentType());
        webImage.setFileSize(file.getSize());
        try {
            webImage.setImage(file.getBytes());
            return ResponseEntity.ok(webImageHandler.saveWebImage(webImage));
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }


    }
    @GetMapping("/file/{id}")
    public ResponseEntity<?> getFile(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
        WebImage webImage = this.webImageHandler.getWebImage(id);
        if (webImage==null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + webImage.getFileName())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(webImage.getImage());
    }
    @GetMapping("/file")
    public ResponseEntity<?> getAllFiles(HttpServletRequest request, HttpServletResponse response) {
        return ResponseEntity.ok().body(this.webImageHandler.getAllWebImages());
    }

    @RequestMapping("/meme/{id}")
    public ResponseEntity<?> getMemeImage(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
        WebImage webImage = this.webImageHandler.getWebImageWithMeme(id);
        if (webImage==null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + webImage.getFileName())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(webImage.getImage());
    }
}
