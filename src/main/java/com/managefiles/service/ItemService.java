package com.managefiles.service;

import com.managefiles.entity.FileEntity;
import com.managefiles.entity.Item;
import com.managefiles.enums.ItemType;
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

    public Item createSpace(String spaceName) {
        Item space = new Item();
        space.setName(spaceName);
        space.setType(ItemType.SPACE.name()); // Set the type to "Space" for spaces
        return itemRepository.save(space);
    }

    public Item createFolder(Long spaceId, String folderName) {
        // Assuming a check for EDIT access on the space
        checkUserHasEditAccessOnSpace(spaceId);

        Item folder = new Item();
        folder.setName(folderName);
        folder.setType(ItemType.FOLDER.name()); // Set the type to "Folder" for folders
        // Set other properties as needed
        return itemRepository.save(folder);
    }

    @Transactional
    public FileEntity createFile(Long folderId, String fileName, byte[] fileBinary) {

        Item folder = getItemById(folderId);

        FileEntity file = new FileEntity();
        file.setBinary(fileBinary);
        file.setItem(folder);

        return fileRepository.save(file);
    }

    private Item getItemById(Long itemId) {
        return itemRepository.getById(itemId);
    }

    public void checkUserHasEditAccessOnSpace(Long spaceId) {
        // Implement your logic to check if the user has EDIT access on the space
        // If the user doesn't have EDIT access, throw an exception or handle accordingly
    }

    public void checkUserHasEditAccessOnFolder(Long folderId, String userEmail){
//        // Assuming there is a method in ItemRepository to find folder by ID
//        Item folder = itemRepository.findByIdAndType(folderId, ItemType.FOLDER.name()).orElseThrow(Exception::new);
//
//        // Check if the user has EDIT access on the folder
//        if (!permissionService.hasEditAccess(userEmail)) {
//            throw new AccessDeniedException("User does not have EDIT access on the folder");
//        }
    }

}
