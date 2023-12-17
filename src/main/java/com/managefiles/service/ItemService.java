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
        Item folder = new Item();
        folder.setName(folderName);
        folder.setType(ItemType.FOLDER.name()); // Set the type to "Folder" for folders
        folder.setParentId(spaceId);
        return itemRepository.save(folder);
    }

    @Transactional
    public FileEntity createFile(Long folderId, String fileName, byte[] fileBinary) {

        Item newFile = new Item();
        newFile.setName(fileName);
        newFile.setType(ItemType.FILE.name());
        newFile.setParentId(getItemById(folderId).getId());
        FileEntity file = new FileEntity();
        file.setBinary(fileBinary);
        file.setItem(newFile);

        return fileRepository.save(file);
    }

    private Item getItemById(Long itemId) {
        return itemRepository.getById(itemId);
    }


}
