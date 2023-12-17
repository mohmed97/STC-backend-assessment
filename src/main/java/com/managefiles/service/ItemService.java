package com.managefiles.service;

import com.managefiles.dto.FolderDTO;
import com.managefiles.entity.FileEntity;
import com.managefiles.entity.Item;
import com.managefiles.entity.Permission;
import com.managefiles.entity.PermissionGroup;
import com.managefiles.enums.ItemType;
import com.managefiles.enums.PermissionLevel;
import com.managefiles.repository.FileRepository;
import com.managefiles.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    PermissionService permissionService;

    @Transactional
    public Item createSpace(String spaceName) {
        Item space = new Item();
        space.setName(spaceName);
        space.setType(ItemType.SPACE.name()); // Set the type to "Space" for spaces
        space.setParentId(0L);

        PermissionGroup adminGroup = new PermissionGroup("ADMIN");
        Permission viewUser = new Permission("viewUser", PermissionLevel.VIEW.name(),adminGroup);
        Permission editUser = new Permission("editUser", PermissionLevel.EDIT.name(),adminGroup);

        permissionService.createPermission(viewUser);
        permissionService.createPermission(editUser);
        space.setPermissionGroup(adminGroup);

        return itemRepository.save(space);
    }

    public Item createFolder() {
        Item folder = new Item();
        folder.setName("backend");
        folder.setType(ItemType.FOLDER.name()); // Set the type to "Folder" for folders
        folder.setParentId(getParentIdByName("stc-assessments"));
        return itemRepository.save(folder);
    }

    private Long getParentIdByName(String itemName) {
        return itemRepository.findByName(itemName).get().getId();
    }

    @Transactional
    public FileEntity createFile(String fileName, byte[] fileBinary) {

        Item newFile = new Item();
        newFile.setName("assessment.pdf");
        newFile.setType(ItemType.FILE.name());
        newFile.setParentId(getParentIdByName("backend"));
        FileEntity file = new FileEntity();
        file.setBinary(fileBinary);
        file.setItem(newFile);

        return fileRepository.save(file);
    }

}
