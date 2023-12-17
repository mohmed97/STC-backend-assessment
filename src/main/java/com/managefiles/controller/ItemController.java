package com.managefiles.controller;

import com.managefiles.dto.FileDTO;
import com.managefiles.entity.FileEntity;
import com.managefiles.entity.Item;
import com.managefiles.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;


    @PostMapping("/create-space")
    public ResponseEntity<?> createSpace() {
        Item space = itemService.createSpace("stc-assessments");
        return new ResponseEntity<>(space, HttpStatus.CREATED);
    }


    @PostMapping("/create-folder")
    public ResponseEntity<?> createFolder() {
        Item folder = itemService.createFolder();
        return new ResponseEntity<>(folder, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<?> createFile(@RequestBody FileDTO fileDTO) {
        try {
            byte[] fileBytes = fileDTO.getFile().getBytes();
            FileEntity createdFile = itemService.createFile(fileDTO.getFileName(), fileBytes);
            return new ResponseEntity<>(createdFile, HttpStatus.CREATED);
        } catch (IOException e) {
            // Handle file processing exception
            return new ResponseEntity<>("Error processing file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
