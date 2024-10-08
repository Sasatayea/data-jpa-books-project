package com.global.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.global.book.service.FileUploadService;

@RestController
@RequestMapping("/file")
public class FileUploadController {
 
	
	@Autowired
	FileUploadService fileUploadService ;
	
	@PostMapping("/upload")
	public ResponseEntity<Object> uploadFile(@RequestParam Long id , @RequestParam String pathType ,@RequestParam MultipartFile file){

        String fileName = fileUploadService.storeFile(fileUploadService.convertMultiPartFileToFile(file), id, pathType) ;
        
		return ResponseEntity.ok(fileName);
	}
}
