package com.example.springuploadimageapi.controller;

import com.example.springuploadimageapi.helper.ResponseHandler;
import com.example.springuploadimageapi.model.ImageEntity;
import com.example.springuploadimageapi.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class ImageController {
    @Autowired
    private ImageService postService;
    public static String uploadDirectory = "src/main/resources/static/upload";


    @GetMapping(value = "/image")
    public ResponseEntity<Object> Get() {
        try {
            Iterable<ImageEntity> result = postService.getAll();
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse("Data Not found", HttpStatus.MULTI_STATUS, null);
        }
    }

    @GetMapping({"/image/{id}"})
    public ResponseEntity<Object> getId(@PathVariable Long id) {
        try {
            ImageEntity post = postService.getById(id);
            return ResponseHandler.generateResponse("Data Inserted", HttpStatus.OK, post);
        } catch (Exception e) {
            return ResponseHandler.generateResponse("Data can not insert", HttpStatus.MULTI_STATUS, null);
        }
    }
    @PostMapping("/image")
    public ResponseEntity<Object> savePost(@RequestParam("img") MultipartFile file, ImageEntity post) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime sedateness = LocalDateTime.now();
        String date = dtf.format(sedateness);
        String filename = date+file.getOriginalFilename();
        Path filePath = Paths.get(uploadDirectory,filename);
        try {
            Files.write(filePath,file.getBytes());
            post.setImage(filename);
            ImageEntity data = postService.saveData(post);
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, data);
        } catch (Exception e) {
            return ResponseHandler.generateResponse("Data Not found", HttpStatus.MULTI_STATUS, null);
        }
    }

    @DeleteMapping({"/image/{id}"})
    public ResponseEntity<Object> deletePost(@PathVariable("id") Long id) {
        try {
            postService.deleteById(id);
            return ResponseHandler.generateResponse("Data Deleted !!", HttpStatus.OK,null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse("Data Not found", HttpStatus.MULTI_STATUS, null);
        }
    }

}

