package com.managefiles.controller;

import com.managefiles.entity.FileEntity;
import com.managefiles.entity.Item;
import com.managefiles.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;


    @PostMapping("/create-space")
    @PreAuthorize("hasAuthority('CREATE_SPACE')")
    public ResponseEntity<?> createSpace() {
        Item space = itemService.createSpace("stc-assessments");
        return new ResponseEntity<>(space, HttpStatus.CREATED);
    }


    @PostMapping("/create-folder")
    @PreAuthorize("hasAuthority('CREATE_FOLDER')")
    public ResponseEntity<?> createFolder(@RequestParam Long spaceId, @RequestParam String folderName) {
        // Assuming the ItemService has a method to check EDIT access on the given space
        // and throws an exception if the user doesn't have EDIT access
        itemService.checkUserHasEditAccessOnSpace(spaceId);

        Item folder = itemService.createFolder(spaceId, folderName);
        return new ResponseEntity<>(folder, HttpStatus.CREATED);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_FILE')")
    public ResponseEntity<?> createFile(@RequestParam Long itemParentId,
                                        @RequestParam String fileName,
                                        @RequestParam MultipartFile file) {
        try {
            byte[] fileBytes = file.getBytes();
            FileEntity createdFile = itemService.createFile(itemParentId, fileName, fileBytes);
            return new ResponseEntity<>(createdFile, HttpStatus.CREATED);
        } catch (IOException e) {
            // Handle file processing exception
            return new ResponseEntity<>("Error processing file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
